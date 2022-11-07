package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Converter from {@code String} to {@code Index}.
 */
public class IndexConverter implements CommandLine.ITypeConverter<Index> {
    @Override
    public Index convert(String value) throws Exception {
        try {
            return ParserUtil.parseIndex(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
