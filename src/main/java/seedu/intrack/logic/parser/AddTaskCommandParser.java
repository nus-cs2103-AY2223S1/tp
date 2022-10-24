package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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

        return new AddTaskCommand(new Task(descriptionString, dateTimeString));
    }
}
