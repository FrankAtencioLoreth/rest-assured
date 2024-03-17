package app.tests.v1;

import app.models.ResponseUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("serialization")
public class SerializationDesetializationTest extends BaseTest {


    //Logs
    private static final Logger log = LogManager.getLogger(SerializationDesetializationTest.class);

    @Test
    @Tag("erialization")
    //Convert java object to json object using  jackson library
    public void serializationTest() {

        log.info("Running serialization test");

        final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        //Java object
        var newUser = new ResponseUser();
        newUser.setId("1");
        newUser.setCreatedAt("07/03/2023");
        newUser.setJob("SQA");
        newUser.setName("Silver");

        //serialized
        try {
            var serialized = mapper.writeValueAsString(newUser);
            System.out.println(serialized);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Tag("deserialization")
    //Convert json object to java object using  jackson library
    public void deserializationTest() throws JsonProcessingException {

        log.info("Running deserialization test");

        final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        //JSON object
        var json = """
                    {
                        "name": "morpheus",
                        "job": "leader",
                        "id": "37",
                        "createdAt": "2024-03-08T01:43:26.567Z"
                    }
                   """;

        //deserialized
        var deserialized = mapper.readValue(json, ResponseUser.class);
        System.out.println(deserialized.toString());
    }
}
