package seedu.phu.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
        assertFalse(defaultConfig.equals(null));

    }

    @Test
    public void updateFilePath() {
        Config testConfig = new Config();
        Path testPath = Paths.get("test.json");
        testConfig.setUserPrefsFilePath(testPath);
        assertEquals(testConfig.getUserPrefsFilePath(), testPath);
    }

    @Test
    public void updateLogLevel() {
        Config testConfig = new Config();
        Level warning = Level.WARNING;
        testConfig.setLogLevel(warning);
        assertEquals(testConfig.getLogLevel(), warning);
    }

    @Test
    public void hashcodeMethod() {
        Config defaultConfig = new Config();
        assertEquals(defaultConfig.hashCode(), new Config().hashCode());
    }
}
