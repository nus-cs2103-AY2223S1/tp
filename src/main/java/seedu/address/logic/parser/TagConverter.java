package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Converter from {@code String} to {@code Tag}.
 */
public class TagConverter implements CommandLine.ITypeConverter<Tag> {
    @Override
    public Tag convert(String value) throws Exception {
        try {
            return ParserUtil.parseTag(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
