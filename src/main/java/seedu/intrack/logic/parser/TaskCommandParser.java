package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.logic.commands.TaskCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.Task;

/**
 * Parses input arguments and creates a new {@code TaskCommand} object
 */
public class TaskCommandParser implements Parser<TaskCommand> {
    private static final Pattern TASK_COMMAND_FORMAT =
            Pattern.compile("(?<index>\\d+)\\s+(?<description>.*)\\s+/at(?<dateTime>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TaskCommand}
     * and returns a {@code TaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        final Matcher matcher = TASK_COMMAND_FORMAT.matcher(args.trim());

        Index index;

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TaskCommand.TASK_COMMAND_CONSTRAINTS));
        }

        String indexString = matcher.group("index").trim();
        String descriptionString = matcher.group("description").trim();
        String dateTimeString = matcher.group("dateTime").trim();

        int commandLength = args.split("\\s+").length;
        if (commandLength < 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(indexString);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE), ive);
        }

        return new TaskCommand(index, new Task(descriptionString, dateTimeString));
    }
}
