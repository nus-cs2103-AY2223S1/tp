package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AttendanceAddCommand;
import seedu.address.logic.commands.AttendanceDeleteCommand;
import seedu.address.logic.commands.AttendanceMarkCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new {@code AttendanceCommand} object
 */
public class AttendanceCommandParser implements Parser<Command> {
    /**
     * Used for initial separation of next attendance command word and args.
     */
    private static final Pattern ATTENDANCE_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final String ATTENDANCE_COMMAND_WORD = "attendance";

    public static final String ATTENDANCE_ERROR = String.format(MESSAGE_INVALID_COMMAND_FORMAT, "insert here");

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AttendanceCommand}
     * and returns a {@code AttendanceCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        final Matcher matcher = ATTENDANCE_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AttendanceAddCommand.COMMAND_WORD:
            return new AttendanceAddCommandParser().parse(arguments);

        case AttendanceDeleteCommand.COMMAND_WORD:
            return new AttendanceDeleteCommandParser().parse(arguments);

        case AttendanceMarkCommand.COMMAND_WORD:
            return new AttendanceMarkCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
