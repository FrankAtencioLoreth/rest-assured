package app.models;

public class RegisterUserBuilder {

    private final RequestRegisterUserPojo requestRegisterUserPojo;

    private RegisterUserBuilder() {
        requestRegisterUserPojo = new RequestRegisterUserPojo();
    }

    public static RegisterUserBuilder userBuilder() {
        return new RegisterUserBuilder();
    }

    public RegisterUserBuilder password(String password) {
        this.requestRegisterUserPojo.setPassword(password);
        return this;
    }

    public RegisterUserBuilder email(String email) {
        this.requestRegisterUserPojo.setEmail(email);
        return this;
    }

    public RequestRegisterUserPojo build() {
        return requestRegisterUserPojo;
    }

}
