package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Task;

public class TaskConverter implements CommandLine.ITypeConverter<Task> {
    @Override
    public Task convert(String value) throws Exception {
        String name = value.trim();
        if (!Task.isValidName(name)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Task.MESSAGE_CONSTRAINTS));
        }
        return new Task(name, List.of(), false, null);
    }
}
