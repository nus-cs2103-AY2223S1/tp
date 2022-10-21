package modtrekt.logic.parser.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

import modtrekt.model.module.ModCode;

/**
 * Converts an input String into ModCode, for use with JCommander annotations.
 */
public class ModCodeConverter implements IStringConverter<ModCode> {
    @Override
    public ModCode convert(String value) {
        String trimmedCode = value.trim();
        if (!ModCode.isValidCode(trimmedCode)) {
            throw new ParameterException(ModCode.MESSAGE_CONSTRAINTS);
        }
        return new ModCode(trimmedCode);
    }
}
