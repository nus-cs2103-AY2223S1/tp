package modtrekt.logic.parser.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

import modtrekt.model.module.ModCredit;

/**
 * Converts an input String into ModCredit, for use with JCommander annotations.
 */
public class ModCreditConverter implements IStringConverter<ModCredit> {
    @Override
    public ModCredit convert(String value) {
        String trimmedString = value.trim();
        if (!ModCredit.isValidCredit(trimmedString)) {
            throw new ParameterException(ModCredit.MESSAGE_CONSTRAINTS);
        }
        return new ModCredit(trimmedString);
    }
}
