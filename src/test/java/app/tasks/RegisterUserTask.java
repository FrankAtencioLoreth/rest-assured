package app.tasks;

import app.models.EndPoints;
import app.models.RequestRegisterUserPojo;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RegisterUserTask {

    public ValidatableResponse registerUserWithInfo(RequestRegisterUserPojo requestBody) {
        return given()
                .body(requestBody)
                .post(EndPoints.REGISTER.path())
                .then();
    }
}
