package app.tests.v1;

import app.models.EndPoints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Tag("auth")
@Tag("regression")
public class AuthTest extends BaseTest{

    //Logs
    private static final Logger log = LogManager.getLogger(AuthTest.class);

    @Test
    @Tag("authentication")
    @DisplayName("AUTH Login successfull")
    public void loginTest() {

        Map<String, String> data = new HashMap<>();
        data.put("email","eve.holt@reqres.in");
        data.put("password","cityslicka");

        log.info("Running auth test: email {} and password {}", data.get("email"), data.get("password"));

        given()
                .body(data)
                .post(EndPoints.AUTH.path())
                .then()
                .spec(this.defaultResponseSpecForSuccessFulOperation())
                //.statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue());
    }
}
