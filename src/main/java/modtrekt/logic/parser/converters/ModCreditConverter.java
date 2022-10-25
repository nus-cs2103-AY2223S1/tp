package modtrekt.logic.parser.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

import modtrekt.model.module.ModCredit;

/**
 * Converts an input String into ModCode, for use with JCommander annotations.
 */
public class ModCreditConverter implements IStringConverter<ModCredit> {
    @Override
    public ModCredit convert(String value) {
        String trimmedName = value.trim();
        if (!ModCredit.isValidCredit(trimmedName)) {
            throw new ParameterException(ModCredit.MESSAGE_CONSTRAINTS);
        }
        return new ModCredit(trimmedName);
    }
}
