package foodwhere.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

public class ConfigTest {

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";
        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void generalTests_success() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);

        Config secondDefault = new Config();
        secondDefault.setLogLevel(Level.INFO);
        assertEquals(Level.INFO, secondDefault.getLogLevel());
        Path samePath = Paths.get("preferences.json");
        secondDefault.setUserPrefsFilePath(samePath);
        assertEquals(samePath, secondDefault.getUserPrefsFilePath());

        // EP: Compares the same for correctly set default values
        assertTrue(defaultConfig.equals(defaultConfig));
        assertTrue(defaultConfig.equals(secondDefault));
        assertEquals(defaultConfig.hashCode(), secondDefault.hashCode());

        // EP: null
        assertFalse(defaultConfig.equals(null));

        // EP: Non-default objects
        Level differentlogLevel = Level.ALL;
        Path differentPath = Paths.get("not_preferences.json");
        Config customConfig = new Config();
        customConfig.setLogLevel(differentlogLevel);
        assertEquals(differentlogLevel, customConfig.getLogLevel());
        customConfig.setUserPrefsFilePath(differentPath);
        assertEquals(differentPath, customConfig.getUserPrefsFilePath());

        Config customConfigCopy = new Config();
        customConfigCopy.setLogLevel(differentlogLevel);
        customConfigCopy.setUserPrefsFilePath(differentPath);
        assertTrue(customConfig.equals(customConfig));
        assertTrue(customConfig.equals(customConfigCopy));
        assertEquals(customConfig.hashCode(), customConfigCopy.hashCode());

        // EP: working with different objects
        assertFalse(defaultConfig.equals(customConfig));
    }
}
