package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterNameCommand;
import seedu.address.logic.commands.FilterTagCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.SelectPatientCommand;
import seedu.address.logic.commands.SortCommand;
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

        if (SortCommand.COMMAND_WORD.matches(commandWord)) {
            return new SortCommandParser().parse(arguments);
        } else if (AddPatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new AddPatientCommandParser().parse(arguments);
        } else if (AddAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new AddAppointmentCommandParser().parse(arguments);
        } else if (EditAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new EditAppointmentCommandParser().parse(arguments);
        } else if (EditPatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new EditPatientCommandParser().parse(arguments);
        } else if (DeleteAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new DeleteAppointmentCommandParser().parse(arguments);
        } else if (DeletePatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new DeletePatientCommandParser().parse(arguments);
        } else if (ClearCommand.COMMAND_WORD.matches(commandWord)) {
            return new ClearCommand();
        } else if (FilterNameCommand.COMMAND_WORD.matches(commandWord)) {
            return new FilterNameCommandParser().parse(arguments);
        } else if (FilterTagCommand.COMMAND_WORD.matches(commandWord)) {
            return new FilterTagCommandParser().parse(arguments);
        } else if (RemarkCommand.COMMAND_WORD.matches(commandWord)) {
            return new RemarkCommandParser().parse(arguments);
        } else if (ListCommand.COMMAND_WORD.matches(commandWord)) {
            return new ListCommand();
        } else if (ExitCommand.COMMAND_WORD.matches(commandWord)) {
            return new ExitCommand();
        } else if (HelpCommand.COMMAND_WORD.matches(commandWord)) {
            return new HelpCommand();
        } else if (SelectPatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new SelectPatientCommandParser().parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
