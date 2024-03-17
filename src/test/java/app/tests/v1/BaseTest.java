package app.tests.v1;

import app.conf.ConfVariables;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BaseTest {

    /**
     * Commands:
     * gradle clean test -Denv = environment
     * gradle clean test -Dhost = host -DbasePath = basePath
     * gradle clean tagName
     * */


    //Logs
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    private static boolean started = false;
    private static ReentrantLock lock = new ReentrantLock();

    //Hooks
    @BeforeAll
    public static void setup() {

        lock.lock();

        try {
            if(!started) {
                log.info("Start configuration");
                RestAssured.requestSpecification = defaultRequestSpec();
                log.info("Finish configuration");
                started = true;
            }
        }catch (Exception ignored) {

        }finally {
            lock.unlock();
        }

    }

    //Configurations
    private static RequestSpecification defaultRequestSpec() {

        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());
        filters.add(new AllureRestAssured());

        return new RequestSpecBuilder()
                .setBaseUri(ConfVariables.getHost())
                .setBasePath(ConfVariables.getBasePath())
                .addFilters(filters)
                .setContentType(ContentType.JSON)
                .build();
    }

    public ResponseSpecification defaultResponseSpecForSuccessFulOperation() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
