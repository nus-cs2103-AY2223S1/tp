package seedu.address;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.application.Application;

public class AppParametersTest {

    private final ParametersStub parametersStub = new ParametersStub();
    private final AppParameters expected = new AppParameters();

    @Test
    public void parse_validConfigPath_success() {
        parametersStub.namedParameters.put("config", "config.json");
        expected.setConfigPath(Paths.get("config.json"));
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void parse_nullConfigPath_success() {
        parametersStub.namedParameters.put("config", null);
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void parse_invalidConfigPath_success() {
        parametersStub.namedParameters.put("config", "a\0");
        expected.setConfigPath(null);
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void equals() {
        // same values -> returns true
        AppParameters appParameters = new AppParameters();
        AppParameters appParametersCopy = new AppParameters();
        assertTrue(appParameters.equals(appParametersCopy));

        // same object -> returns true
        assertTrue(appParameters.equals(appParameters));

        // null -> returns false
        assertFalse(appParameters.equals(null));

        // different types -> returns false
        assertFalse(appParameters.equals(5));

        // different configPath -> returns false
        appParametersCopy.setConfigPath(Path.of(""));
        assertFalse(appParameters.equals(appParametersCopy));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        AppParameters appParameters = new AppParameters();
        appParameters.setConfigPath(Path.of("hi"));
        AppParameters appParametersCopy = new AppParameters();
        appParametersCopy.setConfigPath(Path.of("hi"));
        assertEquals(appParameters.hashCode(), appParametersCopy.hashCode());

        // different configPath -> returns different hashcode
        appParametersCopy.setConfigPath(Path.of("bye"));
        assertNotEquals(appParameters.hashCode(), appParametersCopy.hashCode());
    }

    private static class ParametersStub extends Application.Parameters {
        private Map<String, String> namedParameters = new HashMap<>();

        @Override
        public List<String> getRaw() {
            throw new AssertionError("should not be called");
        }

        @Override
        public List<String> getUnnamed() {
            throw new AssertionError("should not be called");
        }

        @Override
        public Map<String, String> getNamed() {
            return Collections.unmodifiableMap(namedParameters);
        }
    }
}
