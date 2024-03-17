package app.tests.v1;

import app.models.EndPoints;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Tag("delete")
@Tag("regression")
public class DeleteUserTest extends BaseTest {

    //Logs
    private static final Logger log = LogManager.getLogger(DeleteUserTest.class);

    @Test
    @Tag("deleteUser")
    @DisplayName("DELETE user")
    public void deleteUserTest() {

        log.info("Running delete user test");

        given()
                .pathParam("userId", 1)
                .delete(EndPoints.USERS.path())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
