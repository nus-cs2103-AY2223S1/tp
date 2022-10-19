package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.logic.commands.AddTaskCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.Task;

/**
 * Parses input arguments and creates a new {@code TaskCommand} object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    /**
     * Regex pattern for the AddTaskCommand, this ensures that the parsed pattern would be in the form of
     * {index} {description} /at {dateTime}
     */
    private static final Pattern TASK_COMMAND_FORMAT =
            Pattern.compile("(?<index>\\d+)\\s+(?<description>.*)\\s+/at(?<dateTime>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TaskCommand}
     * and returns a {@code TaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        final Matcher matcher = TASK_COMMAND_FORMAT.matcher(args.trim());

        Index index;

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTaskCommand.TASK_COMMAND_CONSTRAINTS));
        }

        String indexString = matcher.group("index").trim();
        String descriptionString = matcher.group("description").trim();
        String dateTimeString = matcher.group("dateTime").trim();

        int commandLength = args.split("\\s+").length;
        if (commandLength < 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(indexString);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE), ive);
        }

        return new AddTaskCommand(index, new Task(descriptionString, dateTimeString));
    }
}
