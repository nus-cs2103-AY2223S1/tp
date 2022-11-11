package seedu.address.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.level.Level;
import seedu.address.model.level.exceptions.InvalidLevelException;

public class LevelTest {

    @Test
    public void createLevel_invalidLevel_throwsInvalidLevelException() {
        String invalidLevel = "p1";
        assertThrows(InvalidLevelException.class, () -> Level.createLevel(invalidLevel));
    }

    @Test
    public void createLevel_validLevel_success() {
        assertTrue(Level.createLevel("PRIMARY1") == Level.PRIMARY1);
        assertTrue(Level.createLevel("PRIMARY2") == Level.PRIMARY2);
        assertTrue(Level.createLevel("PRIMARY3") == Level.PRIMARY3);
        assertTrue(Level.createLevel("PRIMARY4") == Level.PRIMARY4);
        assertTrue(Level.createLevel("PRIMARY5") == Level.PRIMARY5);
        assertTrue(Level.createLevel("PRIMARY6") == Level.PRIMARY6);
        assertTrue(Level.createLevel("SECONDARY1") == Level.SECONDARY1);
        assertTrue(Level.createLevel("SECONDARY2") == Level.SECONDARY2);
        assertTrue(Level.createLevel("SECONDARY3") == Level.SECONDARY3);
        assertTrue(Level.createLevel("SECONDARY4") == Level.SECONDARY4);
    }
}
