package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddInternshipCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.BaseCommandUtil;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteInternshipCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditInternshipCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindInternshipCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.ListInternshipCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.SortInternshipCommand;
import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.commands.UnlinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>^[^-\\s]+)(\\s+)?(?<flag>-\\S+)?(?<arguments>\\s.*)?");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord;
        final String flag = matcher.group("flag");
        final String arguments = matcher.group("arguments") == null ? "" : matcher.group("arguments");
        if (flag != null) {
            commandWord = matcher.group("commandWord") + " " + flag;
        } else {
            commandWord = matcher.group("commandWord");
        }
        switch (commandWord) {

        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case AddInternshipCommand.COMMAND_WORD:
            return new AddInternshipCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case EditInternshipCommand.COMMAND_WORD:
            return new EditInternshipCommandParser().parse(arguments);

        case LinkCommand.COMMAND_WORD:
            return new LinkCommandParser().parse(arguments);

        case UnlinkCommand.COMMAND_WORD:
            return new UnlinkCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case DeleteInternshipCommand.COMMAND_WORD:
            return new DeleteInternshipCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindPersonCommand.COMMAND_WORD:
            return new FindPersonCommandParser().parse(arguments);

        case FindInternshipCommand.COMMAND_WORD:
            return new FindInternshipCommandParser().parse(arguments);

        case ListPersonCommand.COMMAND_WORD:
            return new ListPersonCommand();

        case ListInternshipCommand.COMMAND_WORD:
            return new ListInternshipCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SortPersonCommand.COMMAND_WORD:
            return new SortPersonCommandParser().parse(arguments);

        case SortInternshipCommand.COMMAND_WORD:
            return new SortInternshipCommandParser().parse(arguments);

        case BaseCommandUtil.ADD_COMMAND:
            throw new ParseException(BaseCommandUtil.getErrorMessage(BaseCommandUtil.ADD_COMMAND));

        case BaseCommandUtil.DELETE_COMMAND:
            throw new ParseException(BaseCommandUtil.getErrorMessage(BaseCommandUtil.DELETE_COMMAND));

        case BaseCommandUtil.EDIT_COMMAND:
            throw new ParseException(BaseCommandUtil.getErrorMessage(BaseCommandUtil.EDIT_COMMAND));

        case BaseCommandUtil.FIND_COMMAND:
            throw new ParseException(BaseCommandUtil.getErrorMessage(BaseCommandUtil.FIND_COMMAND));

        case BaseCommandUtil.LIST_COMMAND:
            throw new ParseException(BaseCommandUtil.getErrorMessage(BaseCommandUtil.LIST_COMMAND));

        case BaseCommandUtil.SORT_COMMAND:
            throw new ParseException(BaseCommandUtil.getErrorMessage(BaseCommandUtil.SORT_COMMAND));

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
