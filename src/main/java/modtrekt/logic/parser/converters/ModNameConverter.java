package modtrekt.logic.parser.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

import modtrekt.model.module.ModName;

/**
 * Converts an input String into ModName, for use with JCommander annotations.
 */
public class ModNameConverter implements IStringConverter<ModName> {
    @Override
    public ModName convert(String value) {
        String trimmedName = value.trim();
        if (!ModName.isValidName(trimmedName)) {
            throw new ParameterException(ModName.MESSAGE_CONSTRAINTS);
        }
        return new ModName(trimmedName);
    }
}
