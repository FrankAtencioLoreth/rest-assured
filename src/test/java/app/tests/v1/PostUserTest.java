package app.tests.v1;

import app.data.builder.CreateUserDataBuilder;
import app.data.factory.CreateUserDataFactory;
import app.models.*;
import app.tasks.RegisterUserTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Tag("post")
@Tag("regression")
public class PostUserTest extends BaseTest {

    //Logs
    private static final Logger log = LogManager.getLogger(PostUserTest.class);


    @Test
    @Tag("createNewUser")
    @DisplayName("CREATE user")
    public void createNewUserTest() {
        RequestRegisterUserPojo user = new RequestRegisterUserPojo();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");

        RegisterUserTask registerUserTask = new RegisterUserTask();
        var response = registerUserTask.registerUserWithInfo(user).extract().as(ResponseRegisterUserPojo.class);
        assertThat(response.getId(), equalTo(4));
        assertThat(response.getToken(), notNullValue());
    }

    @Test
    @Tag("createNewUserUsingBuilderPatter")
    @DisplayName("CREATE user with builder pattern")
    public void createNewUserUsingBuilderPattern() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        RequestRegisterUserPojo userBuilder = RegisterUserBuilder
                .userBuilder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        System.out.println(objectMapper.writeValueAsString(userBuilder));


        RegisterUserTask registerUserTask = new RegisterUserTask();
        var response = registerUserTask.registerUserWithInfo(userBuilder).extract().as(ResponseRegisterUserPojo.class);
        assertThat(response.getId(), equalTo(4));
        assertThat(response.getToken(), notNullValue());
    }

    @Test
    @Tag("createNewUserUsingDataFactoryPatter")
    @DisplayName("CREATE user with data factory pattern")
    public void createNewUserUsingDataFactoryPattern() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        RequestRegisterUserPojo userValid = CreateUserDataFactory.invalidUserInformation();

        System.out.println(objectMapper.writeValueAsString(userValid));


        RegisterUserTask registerUserTask = new RegisterUserTask();
        var response = registerUserTask.registerUserWithInfo(userValid).extract().as(ResponseRegisterUserPojo.class);
        assertThat(response.getId(), equalTo(4));
        assertThat(response.getToken(), notNullValue());
    }

    @Test
    @Tag("createNewUserUsingDataBuilderPatter")
    @DisplayName("CREATE user with data builder pattern")
    public void createNewUserUsingDataBuilderPattern() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        RequestRegisterUserPojo user = CreateUserDataBuilder
                .anUser()
                .but()
                .withRoleAdmin().build();

        System.out.println(objectMapper.writeValueAsString(user));


        RegisterUserTask registerUserTask = new RegisterUserTask();
        var response = registerUserTask.registerUserWithInfo(user).extract().as(ResponseRegisterUserPojo.class);
        assertThat(response.getId(), equalTo(4));
        assertThat(response.getToken(), notNullValue());
    }

    @Test
    @Tag("postUser")
    @DisplayName("CREATE user")
    public void createUserTest() {

        log.info("Running create user test");

        String response = given()
                .body(
                        """
                           {
                               "name": "morpheus",
                               "job": "leader"
                           }
                        """
                )
                .post(EndPoints.USER.path())
                .then()
                .extract().body().asString();

        ResponseUser user = from(response).getObject("", ResponseUser.class);
        System.out.println("DATA: " + user.toString());
    }

    @Test
    @Tag("registerUser")
    @DisplayName("REGISTER user")
    public void registerUserTest() {

        log.info("Running register user test");

        //Serialized
        RequestRegisterUserPojo request = new RequestRegisterUserPojo();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");
        //Deserialized
        ResponseRegisterUserPojo response = given()
                .body(request)
                .post(EndPoints.REGISTER.path())
                .then()
                .extract()
                .body()
                .as(ResponseRegisterUserPojo.class);

        //Assertions
        assertThat(response.getId(), equalTo(4));
        assertThat(response.getToken(), equalTo("QpwL5tke4Pnpja7X4"));
    }
}
