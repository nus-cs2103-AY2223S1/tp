package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Description;

/**
 * Converter from {@code String} to {@code Description}
 */
public class DescriptionConverter implements CommandLine.ITypeConverter<Description> {
    @Override
    public Description convert(String value) throws Exception {
        try {
            return ParserUtil.parseDescription(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
