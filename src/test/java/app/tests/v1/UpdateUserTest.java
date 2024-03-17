package app.tests.v1;

import app.models.EndPoints;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Tag("update")
@Tag("regression")
public class UpdateUserTest extends BaseTest {

    //Logs
    private static final Logger log = LogManager.getLogger(UpdateUserTest.class);

    @Test
    @Tag("patchUser")
    @DisplayName("PATCH user")
    public void patchUserTest() {

        log.info("Running patch user test");

        Map<String, String> data = new HashMap<>();
        data.put("name","Silver");
        data.put("job","SQA Automation");

        String nameUpdated = given()
                .body(data)
                .pathParam("userId", 1)
                .patch(EndPoints.USERS.path())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getString("name");
        assertThat(data.get("name"), equalTo(nameUpdated));
    }

    @Test
    @Tag("putUser")
    @DisplayName("PUT user")
    public void putUserTest() {

        log.info("Running put user test");

        Map<String, String> data = new HashMap<>();
        data.put("name","Silver");
        data.put("job","SQA Automation");

        String nameUpdated = given()
                .body(data)
                .pathParam("userId", "1")
                .put(EndPoints.USERS.path())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getString("name");
        assertThat(data.get("name"), equalTo(nameUpdated));
    }
}
