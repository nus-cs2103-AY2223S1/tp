package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BackCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

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
        case AddCommand.COMMAND_WORD:
            helpMessage = AddCommand.MESSAGE_USAGE;
            break;
        case BackCommand.COMMAND_WORD:
            helpMessage = BackCommand.MESSAGE_USAGE;
            break;
        case ClearCommand.COMMAND_WORD:
            helpMessage = ClearCommand.MESSAGE_USAGE;
            break;
        case DeleteCommand.COMMAND_WORD:
            helpMessage = DeleteCommand.MESSAGE_USAGE;
            break;
        case ExitCommand.COMMAND_WORD:
            helpMessage = ExitCommand.MESSAGE_USAGE;
            break;
        case FindCommand.COMMAND_WORD:
            helpMessage = FindCommand.MESSAGE_USAGE;
            break;
        case SortCommand.COMMAND_WORD:
            helpMessage = SortCommand.MESSAGE_USAGE;
            break;
        case ResetCommand.COMMAND_WORD:
            helpMessage = ResetCommand.MESSAGE_USAGE;
            break;
        case HelpCommand.COMMAND_WORD:
            helpMessage = HelpCommand.MESSAGE_USAGE;
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        return new HelpCommand(helpMessage);
    }
}
