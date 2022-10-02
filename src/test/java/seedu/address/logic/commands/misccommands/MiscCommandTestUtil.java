package seedu.address.logic.commands.misccommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Contains helper functions for testing {@code MiscCommand}.
 */
public class MiscCommandTestUtil {

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(MiscCommand command, CommandResult expectedCommandResult) {
        try {
            CommandResult result = command.execute();
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(MiscCommand, CommandResult)})}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(MiscCommand command, String expectedMessage) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, expectedCommandResult);
    }

}
