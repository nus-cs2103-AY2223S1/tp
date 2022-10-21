package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.INCOMPLETE_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BookCommand;
import seedu.address.logic.commands.CancelCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HideAppointmentsCommand;
import seedu.address.logic.commands.HidePatientsCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)"
            + "(?<descriptor>(?i:\\s+appts|\\s+patients)?)(?<arguments>.*)");

    private final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

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

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String descriptor = matcher.group("descriptor").trim().toLowerCase();
        final String arguments = matcher.group("arguments");
        logger.info(descriptor);
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditPatientCommand.COMMAND_WORD:
            if (descriptor.equals(EditPatientCommand.DESCRIPTOR_WORD)) {
                return new EditPatientCommandParser().parse(arguments);
            } else if (descriptor.equals(EditAppointmentCommand.DESCRIPTOR_WORD)) {
                return new EditAppointmentCommandParser().parse(arguments);
            } else {
                throw new ParseException(INCOMPLETE_COMMAND);
            }

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case HidePatientsCommand.COMMAND_WORD:
            if (descriptor.equals(HidePatientsCommand.DESCRIPTOR_WORD)) {
                return new HidePatientsCommandParser().parse(arguments);
            } else if (descriptor.equals(HideAppointmentsCommand.DESCRIPTOR_WORD)) {
                return new HideAppointmentsCommandParser().parse(arguments);
            } else {
                throw new ParseException(INCOMPLETE_COMMAND);
            }

        case BookCommand.COMMAND_WORD:
            return new BookCommandParser().parse(arguments);

        case CancelCommand.COMMAND_WORD:
            return new CancelCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            if (!descriptor.isEmpty()) {
                return new ListCommand(descriptor);
            } else if (!arguments.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            } else {
                throw new ParseException(INCOMPLETE_COMMAND);
            }

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
