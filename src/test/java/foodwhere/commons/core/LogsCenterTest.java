package foodwhere.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import foodwhere.model.stall.Stall;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogsCenterTest {

    final Level testLevel = Level.SEVERE;

    @Test
    public void init_checkHandlerCorrect_success() {
        Config config = new Config();
        config.setLogLevel(testLevel);
        LogsCenter.init(config);

        Logger newLogger = LogsCenter.getLogger("newLogger");
        assertEquals(2, newLogger.getHandlers().length);
    }

    @Test
    public void getLogger_defaultClass_success() {
        Logger defaultLog = Logger.getLogger("");
        assertEquals(defaultLog, LogsCenter.getLogger((Class<Object>) null));

        Logger defaultLogWClass = Logger.getLogger("Stall");
        assertEquals(defaultLogWClass, LogsCenter.getLogger(Stall.class));

        Logger defaultLogWClassDiffName = Logger.getLogger("Review");
        assertNotEquals(defaultLogWClassDiffName, LogsCenter.getLogger(Stall.class));
    }

    @Test
    public void getLogger_sameString_success() {
        String testString = "this is a test string";
        Logger testLogger = Logger.getLogger(testString);
        assertEquals(testLogger, LogsCenter.getLogger(testString));
    }
}