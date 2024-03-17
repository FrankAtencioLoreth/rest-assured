package app.data.builder;

import app.data.factory.CreateUserDataFactory;
import app.models.RequestRegisterUserPojo;
import org.apache.commons.lang3.StringUtils;

public class CreateUserDataBuilder {

    private RequestRegisterUserPojo requestRegisterUserPojo;

    private CreateUserDataBuilder() {
        this.createDefaultUser();
    }

    public static CreateUserDataBuilder anUser() {
        return new CreateUserDataBuilder();
    }

    private void createDefaultUser() {
        this.requestRegisterUserPojo = new RequestRegisterUserPojo();
        this.requestRegisterUserPojo = CreateUserDataFactory.validUserInformation();
    }

    public CreateUserDataBuilder withEmail(String email) {
        this.requestRegisterUserPojo.setEmail(email);
        return this;
    }

    public CreateUserDataBuilder withPassword(String password) {
        this.requestRegisterUserPojo.setPassword(password);
        return this;
    }

    public CreateUserDataBuilder withRole(String role) {
        this.requestRegisterUserPojo.setRole(role);
        return this;
    }

    public CreateUserDataBuilder withRoleAdmin() {
        this.requestRegisterUserPojo.setRole("Administrator");
        return this;
    }

    public CreateUserDataBuilder withEmptyUsername() {
        this.requestRegisterUserPojo.setEmail(StringUtils.EMPTY);
        return this;
    }

    public CreateUserDataBuilder withEmptyPassword() {
        this.requestRegisterUserPojo.setPassword(StringUtils.EMPTY);
        return this;
    }

    public CreateUserDataBuilder and() {
        return this;
    }

    public CreateUserDataBuilder but() {
        return this;
    }

    public RequestRegisterUserPojo build() {
        return this.requestRegisterUserPojo;
    }

}
