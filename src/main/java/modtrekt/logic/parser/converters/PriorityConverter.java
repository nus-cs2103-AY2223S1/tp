package modtrekt.logic.parser.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

import modtrekt.model.task.Task;

/**
 * Converts a String into a Priority, for use with JCommander annotations.
 */
public class PriorityConverter implements IStringConverter<Task.Priority> {
    @Override
    public Task.Priority convert(String value) {
        switch (value.toLowerCase().strip()) {
        case "high":
            return Task.Priority.HIGH;
        case "medium":
            return Task.Priority.MEDIUM;
        case "low":
            return Task.Priority.LOW;
        case "none":
            return Task.Priority.NONE;
        default:
            throw new ParameterException(String.format("The priority %s does not exist.", value));
        }
    }
}
