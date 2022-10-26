package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Phone;

/**
 * Converter from {@code String} to {@code Phone}.
 */
public class PhoneConverter implements CommandLine.ITypeConverter<Phone> {
    @Override
    public Phone convert(String value) throws Exception {
        try {
            return ParserUtil.parsePhone(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
