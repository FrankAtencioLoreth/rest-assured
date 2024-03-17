package app.tests.v1;

import app.models.EndPoints;
import app.tasks.GetSingleUserTask;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Tag("get")
@Tag("regression")
public class GetUserTest extends BaseTest {

    //Logs
    private static final Logger log = LogManager.getLogger(GetUserTest.class);

    @Test
    @Tag("getSingleUser")
    @DisplayName("GET single user")
    public void getSingleUserTest() {

        log.info("Running get single user test");
        GetSingleUserTask getSingleUserTask = new GetSingleUserTask();
        var response = getSingleUserTask.getSingleUserById(1);
        response.spec(defaultResponseSpecForSuccessFulOperation());
        response.body("data.id", equalTo(1));
    }

    @Test
    @Tag("getAllUsers")
    @DisplayName("GET ALL users")
    public void getAllUsersTest() {

        log.info("Running get all users test");

        Response response = given()
                .get(EndPoints.USER.path());
        Headers headers = response.getHeaders();

        int statusCode = response.statusCode();
        String body = response.getBody().asString();
        String contentType = response.getContentType();

        assertThat(statusCode, equalTo(HttpStatus.SC_OK));

        System.out.println("Headers: " + headers);
        System.out.println("ContentType: " + contentType);
        System.out.println("Body: " + body);
        System.out.println("-----------------------------");
        System.out.println(headers.get("Content-Type"));
        System.out.println(headers.get("Transfer-Encoding"));
    }

    @Test
    @Tag("getAllUsers2")
    @DisplayName("GET ALL users")
    public void getAllUsersTest2() {

        log.info("Running get all users test 2");

        String responseBody = given()
                .queryParams("page","2")
                .get(EndPoints.USER.path()).then().extract().body().asString();

        int page = from(responseBody).get("page");
        int totalPages = from(responseBody).get("total_pages");
        int idFirstUser = from(responseBody).get("data[0].id");

        System.out.println("page: " + page);
        System.out.println("totalPages: " + totalPages);
        System.out.println("idFirstUser: " + idFirstUser);

        List<Map> usersWithIDGreaterThan10 = from(responseBody).get("data.findAll { user -> user.id > 10 && user.last_name == 'Howell'}");
        String id = usersWithIDGreaterThan10.get(0).get("id").toString();
        System.out.println("DATA: " + id );
    }
}
