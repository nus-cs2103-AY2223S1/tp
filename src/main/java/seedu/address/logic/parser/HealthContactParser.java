package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddBillCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteBillCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditBillCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindBillCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SelectAppointmentCommand;
import seedu.address.logic.commands.SelectPatientCommand;
import seedu.address.logic.commands.SetPaidCommand;
import seedu.address.logic.commands.SetUnpaidCommand;
import seedu.address.logic.commands.SortAppointmentCommand;
import seedu.address.logic.commands.SortBillCommand;
import seedu.address.logic.commands.SortPatientCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class HealthContactParser {

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

        if (UndoCommand.COMMAND_WORD.matches(commandWord)) {
            return new UndoCommandParser().parse(arguments);
        } else if (RedoCommand.COMMAND_WORD.matches(commandWord)) {
            return new RedoCommandParser().parse(arguments);
        } else if (SortPatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new SortPatientCommandParser().parse(arguments);
        } else if (SortAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new SortAppointmentCommandParser().parse(arguments);
        } else if (SortBillCommand.COMMAND_WORD.matches(commandWord)) {
            return new SortBillCommandParser().parse(arguments);
        } else if (AddPatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new AddPatientCommandParser().parse(arguments);
        } else if (AddAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new AddAppointmentCommandParser().parse(arguments);
        } else if (AddBillCommand.COMMAND_WORD.matches(commandWord)) {
            return new AddBillCommandParser().parse(arguments);
        } else if (EditAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new EditAppointmentCommandParser().parse(arguments);
        } else if (EditPatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new EditPatientCommandParser().parse(arguments);
        } else if (EditBillCommand.COMMAND_WORD.matches(commandWord)) {
            return new EditBillCommandParser().parse(arguments);
        } else if (DeleteAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new DeleteAppointmentCommandParser().parse(arguments);
        } else if (DeletePatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new DeletePatientCommandParser().parse(arguments);
        } else if (DeleteBillCommand.COMMAND_WORD.matches(commandWord)) {
            return new DeleteBillCommandParser().parse(arguments);
        } else if (ClearCommand.COMMAND_WORD.matches(commandWord)) {
            return new ClearCommand();
        } else if (FindPatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new FindPatientCommandParser().parse(arguments);
        } else if (FindAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new FindAppointmentCommandParser().parse(arguments);
        } else if (FindBillCommand.COMMAND_WORD.matches(commandWord)) {
            return new FindBillCommandParser().parse(arguments);
        } else if (ListCommand.COMMAND_WORD.matches(commandWord)) {
            return new ListCommand();
        } else if (ExitCommand.COMMAND_WORD.matches(commandWord)) {
            return new ExitCommand();
        } else if (HelpCommand.COMMAND_WORD.matches(commandWord)) {
            return new HelpCommand();
        } else if (SelectPatientCommand.COMMAND_WORD.matches(commandWord)) {
            return new SelectPatientCommandParser().parse(arguments);
        } else if (SelectAppointmentCommand.COMMAND_WORD.matches(commandWord)) {
            return new SelectAppointmentCommandParser().parse(arguments);
        } else if (SetPaidCommand.COMMAND_WORD.matches(commandWord)) {
            return new SetPaidCommandParser().parse(arguments);
        } else if (SetUnpaidCommand.COMMAND_WORD.matches(commandWord)) {
            return new SetUnpaidCommandParser().parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
