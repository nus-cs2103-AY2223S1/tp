package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;

/**
 * Converter from {@code String} to {@code Task}.
 */
public class TaskConverter implements CommandLine.ITypeConverter<Task> {
    @Override
    public Task convert(String value) throws Exception {
        String name = value.trim();
        if (!TaskName.isValidTaskName(name)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TaskName.MESSAGE_CONSTRAINTS));
        }
        return new Task(new TaskName(name), List.of(), false, null);
    }
}
