package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditLoanCommand;
import seedu.address.logic.commands.EditNoteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindNoteCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HideNotesPanelCommand;
import seedu.address.logic.commands.InspectCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListNoteCommand;
import seedu.address.logic.commands.ShowNotesPanelCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Model model) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser(model).parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser(model).parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser(model).parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindTagCommand.COMMAND_WORD:
            return new FindTagCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case InspectCommand.COMMAND_WORD:
            return new InspectCommandParser().parse(arguments);

        case ShowNotesPanelCommand.COMMAND_WORD:
            return new ShowNotesPanelCommand();

        case HideNotesPanelCommand.COMMAND_WORD:
            return new HideNotesPanelCommand();

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser(model).parse(arguments);

        case ListNoteCommand.COMMAND_WORD:
            return new ListNoteCommand();

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        case FindNoteCommand.COMMAND_WORD:
            return new FindNoteCommandParser().parse(arguments);

        case EditNoteCommand.COMMAND_WORD:
            return new EditNoteCommandParser(model).parse(arguments);

        case EditLoanCommand.COMMAND_WORD:
            return new EditLoanCommandParser(model).parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
