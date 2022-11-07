package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AppendCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CloneCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.ToggleListModeCommand;
import seedu.address.logic.commands.UnappendCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not match any of the commands in Survin
     */
    @Override
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        switch (trimmedArgs) {
        case "":
            return new HelpCommand("");

        case AddCommand.COMMAND_WORD:
            return new HelpCommand(AddCommand.COMMAND_WORD);

        case EditCommand.COMMAND_WORD:
            return new HelpCommand(EditCommand.COMMAND_WORD);

        case DeleteCommand.COMMAND_WORD:
            return new HelpCommand(DeleteCommand.COMMAND_WORD);

        case ClearCommand.COMMAND_WORD:
            return new HelpCommand(ClearCommand.COMMAND_WORD);

        case ListCommand.COMMAND_WORD:
            return new HelpCommand(ListCommand.COMMAND_WORD);

        case ViewCommand.COMMAND_WORD:
            return new HelpCommand(ViewCommand.COMMAND_WORD);

        case CloneCommand.COMMAND_WORD:
            return new HelpCommand(CloneCommand.COMMAND_WORD);

        case UndoCommand.COMMAND_WORD:
            return new HelpCommand(UndoCommand.COMMAND_WORD);

        case MarkCommand.COMMAND_WORD:
            return new HelpCommand(MarkCommand.COMMAND_WORD);

        case UnmarkCommand.COMMAND_WORD:
            return new HelpCommand(UnmarkCommand.COMMAND_WORD);

        case ExitCommand.COMMAND_WORD:
            return new HelpCommand(ExitCommand.COMMAND_WORD);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand(HelpCommand.COMMAND_WORD);

        case ThemeCommand.COMMAND_WORD:
            return new HelpCommand(ThemeCommand.COMMAND_WORD);

        case AppendCommand.COMMAND_WORD:
            return new HelpCommand(AppendCommand.COMMAND_WORD);

        case UnappendCommand.COMMAND_WORD:
            return new HelpCommand(UnappendCommand.COMMAND_WORD);

        case ToggleListModeCommand.COMMAND_WORD:
            return new HelpCommand(ToggleListModeCommand.COMMAND_WORD);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
