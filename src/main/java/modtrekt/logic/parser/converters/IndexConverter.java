package modtrekt.logic.parser.converters;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

import modtrekt.commons.core.index.Index;
import modtrekt.commons.util.StringUtil;

/**
 * Converts an input String into an Index, for use with JCommander annotations.
 */
public class IndexConverter implements IStringConverter<Index> {
    @Override
    public Index convert(String value) {
        if (!isValidInteger(value)) {
            throw new ParameterException(
                    "Syntax error. If your command arguments contain spaces, surround them with quotes."
            );
        }
        if (!StringUtil.isNonZeroUnsignedInteger(value)) {
            throw new ParameterException("The index must be a non-zero unsigned integer.");
        }
        return Index.fromOneBased(Integer.parseInt(value));
    }

    private boolean isValidInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
