package seedu.foodrem.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult<String> commandResult = CommandResult.from("feedback");

        // same values -> returns true
        assertEquals(commandResult, CommandResult.from("feedback"));

        // same object -> returns true
        assertEquals(commandResult, commandResult);

        // null -> returns false
        assertNotEquals(null, commandResult);

        // different types -> returns false
        assertNotEquals(0.5f, commandResult);

        // different feedbackToUser value -> returns false
        assertNotEquals(commandResult, CommandResult.from("different"));
    }
}
