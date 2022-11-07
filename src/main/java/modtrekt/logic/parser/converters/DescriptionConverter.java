package modtrekt.logic.parser.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

import modtrekt.model.task.Description;

/**
 * Converts an input String into Description, for use with JCommander annotations.
 */
public class DescriptionConverter implements IStringConverter<Description> {
    @Override
    public Description convert(String value) {
        if (!Description.isValidDescription(value)) {
            throw new ParameterException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(value);
    }
}
