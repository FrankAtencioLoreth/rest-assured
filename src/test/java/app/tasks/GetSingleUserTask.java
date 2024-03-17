package app.tasks;

import app.models.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetSingleUserTask {

    public ValidatableResponse getSingleUserById(Integer id) {
        return given()
                .pathParam("userId", id)
                .get(EndPoints.USERS.path())
                .then();
                //.spec(this.defaultResponseSpecForSuccessFulOperation())
                //.statusCode(HttpStatus.SC_OK)
                //.body("data.id", equalTo(1));
    }
}
