package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.ModelType;

public class CommandResultTest {

    private CommandResult commandResult = new CommandResult("feedback");
    private CommandResult commandResultTutorial = new CommandResult("feedback", ModelType.TUTORIAL);

    @Test
    public void equals() {

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different model type value -> return false
        assertFalse(commandResult.equals(new CommandResult("feedback", ModelType.STUDENT)));

    }

    @Test
    public void hashcode() {

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different modelType value -> returns different hashcode
        assertNotEquals(commandResultTutorial.hashCode(),
                new CommandResult("feedback", ModelType.GRADE_CHART).hashCode());
    }
}
