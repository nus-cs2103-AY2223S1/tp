package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.commands.ClearAppointmentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearRecordCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteRecordCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListRecordCommand;
import seedu.address.logic.commands.ShowAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteRecordCommand.COMMAND_WORD:
            return new DeleteRecordCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ShowAllCommand.COMMAND_WORD:
            return new ShowAllCommand();

        case ListRecordCommand.COMMAND_WORD:
            return new ListRecordCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FindRecordCommand.COMMAND_WORD:
            return new FindRecordCommandParser().parse(arguments);

        case ClearRecordCommand.COMMAND_WORD:
            return new ClearRecordCommand();

        case AddRecordCommand.COMMAND_WORD:
            return new AddRecordCommandParser().parse(arguments);

        case EditRecordCommand.COMMAND_WORD:
            return new EditRecordCommandParser().parse(arguments);

        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case ClearAppointmentCommand.COMMAND_WORD:
            return new ClearAppointmentCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
