package foodwhere;

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
    public void equals_notEqualCases_isCorrect() {
        AppParameters appParameters = AppParameters.parse(parametersStub);

        // same object itself -> equals return true
        assertTrue(expected.equals(expected));

        // same config parameters -> equals return true
        assertTrue(expected.equals(appParameters));

        AppParameters appParametersNotEqual = AppParameters.parse(parametersStub);
        appParametersNotEqual.setConfigPath(Paths.get("adifferentconfig.json"));

        // different config path -> equals return false
        assertFalse(expected.equals(appParametersNotEqual));

        // different object type -> equals return false
        assertFalse(expected.equals(Paths.get("config.json")));
    }

    @Test
    public void hashCode_test() {
        Path configPath = Paths.get("config.json");

        AppParameters appParameters = AppParameters.parse(parametersStub);
        expected.setConfigPath(configPath);
        appParameters.setConfigPath(configPath);
        assertEquals(expected.hashCode(), appParameters.hashCode());

        AppParameters appParametersNotEqual = AppParameters.parse(parametersStub);
        appParametersNotEqual.setConfigPath(Paths.get("adifferentconfig.json"));
        assertNotEquals(expected.hashCode(), appParametersNotEqual.hashCode());
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
