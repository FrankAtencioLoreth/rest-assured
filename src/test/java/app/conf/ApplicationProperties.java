package app.conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;

public class ApplicationProperties {

    private static final String APPLICATION_PREFIX = "application";
    private static final String APPLICATION_SUFIX = "properties";
    private static final Logger log = LogManager.getLogger(ApplicationProperties.class);
    private static Properties instance = null;


    public void applicationProperties() {}

    private static Properties loadPropertieFile() {
        String environment = Optional.
                ofNullable(System
                .getenv("env"))
                .orElse("dev");

        String fileName = String.format("%s-%s.%s", APPLICATION_PREFIX, environment, APPLICATION_SUFIX);
        log.info("Property file to read {}", fileName);

        Properties propertie = new Properties();

        try {
            propertie.load(getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            log.error("Unable to load the file {}", fileName);
            throw new RuntimeException(e);
        }

        return propertie;
    }

    //Singleton
    public static synchronized Properties getInstance() {
        if(instance==null) {
            instance = loadPropertieFile();
        }
        return instance;
    }
}
