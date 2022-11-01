package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.intrack.logic.commands.AddTaskCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.Task;

/**
 * Parses input arguments and creates a new {@code AddTaskCommand} object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    /**
     * Regex pattern for the {@code AddTaskCommand}, this ensures that the parsed pattern would be in the form of
     * {description} /at {dateTime}
     */
    private static final Pattern TASK_COMMAND_FORMAT =
            Pattern.compile("(?<description>.*)\\s+/at(?<dateTime>.*)");

    /**
     * Regex pattern for a valid datetime format, this ensures that the datetime format would be dd-MM-yyyy HH:mm
     */
    private static final String DATETIME_FORMAT = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}"
        + " ([01]?[0-9]|2[0-3]):[0-5][0-9]";

    /**
     * Format to parse date time string, this ensures that the date parsed is not unnecessarily rounded
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddTaskCommand}
     * and returns a {@code AddTaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        final Matcher matcher = TASK_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTaskCommand.MESSAGE_USAGE));
        }

        String descriptionString = matcher.group("description").trim();
        String dateTimeString = matcher.group("dateTime").trim();

        int commandLength = args.split("\\s+").length;
        if (commandLength < 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        if (!dateTimeString.matches(DATETIME_FORMAT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        String dateTimeParsed = LocalDateTime.parse(dateTimeString, FORMATTER).truncatedTo(ChronoUnit.MINUTES)
                .format(FORMATTER);
        if (!dateTimeParsed.equals(dateTimeString)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        return new AddTaskCommand(new Task(descriptionString, dateTimeString));
    }
}
