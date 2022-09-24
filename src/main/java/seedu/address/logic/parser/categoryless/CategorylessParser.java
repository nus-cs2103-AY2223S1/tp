package seedu.address.logic.parser.categoryless;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.categoryless.ExitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input without a category.
 */
public class CategorylessParser {
    /**
     * Parses user input into command for execution.
     *
     * @param commandWord Command word provided by the user.
     * @param arguments   Arguments provided by the user.
     * @return The command based on the user command word and arguments.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public static Command parseCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
