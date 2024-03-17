package app.data.factory;

import app.models.RegisterUserBuilder;
import app.models.RequestRegisterUserPojo;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import static app.models.RegisterUserBuilder.userBuilder;

public class CreateUserDataFactory {
    private static final Faker facker = new Faker();
    private static final String DEFAULT_EMAIL_USERNAME = "eve.holt@reqres.in";

    //user data empty
    public static RequestRegisterUserPojo missingAllInformation() {
        return userBuilder()
                .email(StringUtils.EMPTY)
                .password(StringUtils.EMPTY)
                .build();
    }

    //users data null
    public static RequestRegisterUserPojo nullInformation() {
        return userBuilder()
                .email(null)
                .password(null)
                .build();
    }

    //valid user
    public static RequestRegisterUserPojo validUserInformation() {
        return userBuilder()
                .email(DEFAULT_EMAIL_USERNAME)
                .password(facker.internet().password())
                .build();
    }

    //Invalid user
    public static RequestRegisterUserPojo invalidUserInformation() {
        return userBuilder()
                .email(facker.internet().emailAddress())
                .password(facker.internet().password())
                .build();
    }
}
