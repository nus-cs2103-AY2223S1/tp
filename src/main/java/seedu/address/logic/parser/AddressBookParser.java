package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.INCOMPLETE_COMMAND;
import static seedu.address.commons.core.Messages.INCOMPLETE_LIST_COMMAND;
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
import seedu.address.logic.commands.GroupAppointmentCommand;
import seedu.address.logic.commands.GroupPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HideAppointmentsCommand;
import seedu.address.logic.commands.HidePatientsCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.UngroupCommand;
import seedu.address.logic.commands.UnhideAppointmentsCommand;
import seedu.address.logic.commands.UnhidePatientsCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    // Enhancement adapted from https://stackoverflow.com/a/73728685
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)"
            + "(?<descriptor>(?i:\\s+appts|\\s+patients|\\s+all)?)(?<arguments>.*)");

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
        final String descriptorAndArguments = descriptor + arguments;
        logger.info(descriptor);
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(descriptorAndArguments);

        case EditPatientCommand.COMMAND_WORD:
            return parseEditPatientCommand(descriptor, arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(descriptorAndArguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case GroupPatientCommand.COMMAND_WORD:
            return parseGroupCommand(descriptor, arguments);

        case UngroupCommand.COMMAND_WORD:
            return parseUngroupCommand(descriptor, arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(descriptorAndArguments);

        case HidePatientsCommand.COMMAND_WORD:
            return parseHidePatientsCommand(descriptor, arguments);

        case UnhidePatientsCommand.COMMAND_WORD:
            return parseUnhidePatientsCommand(descriptor, arguments);

        case BookCommand.COMMAND_WORD:
            return new BookCommandParser().parse(descriptorAndArguments);

        case CancelCommand.COMMAND_WORD:
            return new CancelCommandParser().parse(descriptorAndArguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(descriptorAndArguments);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(descriptorAndArguments);

        case ListCommand.COMMAND_WORD:
            return parseListCommand(descriptor, arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseEditPatientCommand(String descriptor, String arguments) throws ParseException {
        if (descriptor.equals(EditPatientCommand.DESCRIPTOR_WORD)) {
            return new EditPatientCommandParser().parse(arguments);
        } else if (descriptor.equals(EditAppointmentCommand.DESCRIPTOR_WORD)) {
            return new EditAppointmentCommandParser().parse(arguments);
        } else {
            throw new ParseException(INCOMPLETE_COMMAND);
        }
    }

    private Command parseGroupCommand(String descriptor, String arguments) throws ParseException {
        if (descriptor.equals(GroupPatientCommand.DESCRIPTOR_WORD)) {
            return new GroupPatientCommand();
        } else if (descriptor.equals(GroupAppointmentCommand.DESCRIPTOR_WORD)) {
            return new GroupAppointmentCommandParser().parse(arguments);
        } else {
            throw new ParseException(INCOMPLETE_COMMAND);
        }
    }

    private UngroupCommand parseUngroupCommand(String descriptor, String arguments) throws ParseException {
        if (!descriptor.isEmpty()) {
            return new UngroupCommand(descriptor);
        } else if (!arguments.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UngroupCommand.MESSAGE_USAGE));
        } else {
            throw new ParseException(INCOMPLETE_COMMAND);
        }
    }

    private Command parseHidePatientsCommand(String descriptor, String arguments) throws ParseException {
        if (descriptor.equals(HidePatientsCommand.DESCRIPTOR_WORD)) {
            return new HidePatientsCommandParser().parse(arguments);
        } else if (descriptor.equals(HideAppointmentsCommand.DESCRIPTOR_WORD)) {
            return new HideAppointmentsCommandParser().parse(arguments);
        } else {
            throw new ParseException(INCOMPLETE_COMMAND);
        }
    }

    private Command parseUnhidePatientsCommand(String descriptor, String arguments) throws ParseException {
        if (descriptor.equals(UnhidePatientsCommand.DESCRIPTOR_WORD)) {
            return new UnhidePatientsCommandParser().parse(arguments);
        } else if (descriptor.equals(UnhideAppointmentsCommand.DESCRIPTOR_WORD)) {
            return new UnhideAppointmentsCommandParser().parse(arguments);
        } else {
            throw new ParseException(INCOMPLETE_COMMAND);
        }
    }

    private ListCommand parseListCommand(String descriptor, String arguments) throws ParseException {
        if (!descriptor.isEmpty()) {
            return new ListCommand(descriptor);
        } else if (!arguments.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        } else {
            throw new ParseException(INCOMPLETE_LIST_COMMAND);
        }
    }
}
