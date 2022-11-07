package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.BackCommand;
import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DetailHelpCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class DetailHelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new HelpCommand();
        }

        String helpMessage;
        switch (trimmedArgs) {
        case BackCommand.COMMAND_WORD:
            helpMessage = BackCommand.MESSAGE_USAGE;
            break;
        case DeleteAttributeCommand.COMMAND_WORD:
            helpMessage = DeleteCommand.MESSAGE_USAGE;
            break;
        case ExitCommand.COMMAND_WORD:
            helpMessage = ExitCommand.MESSAGE_USAGE;
            break;
        case SetCommand.COMMAND_WORD:
            helpMessage = SetCommand.MESSAGE_USAGE;
            break;
        case DetailHelpCommand.COMMAND_WORD:
            helpMessage = DetailHelpCommand.MESSAGE_USAGE;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailHelpCommand.MESSAGE_USAGE));
        }

        return new HelpCommand(helpMessage);
    }
}
