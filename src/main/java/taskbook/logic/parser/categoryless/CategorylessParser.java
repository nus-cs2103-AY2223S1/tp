package taskbook.logic.parser.categoryless;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.categoryless.ExitCommand;
import taskbook.logic.commands.categoryless.HelpCommand;
import taskbook.logic.commands.categoryless.RedoCommand;
import taskbook.logic.commands.categoryless.UndoCommand;
import taskbook.logic.parser.exceptions.ParseException;

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
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();
        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
