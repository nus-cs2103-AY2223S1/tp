package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Url;

public class UrlConverter implements CommandLine.ITypeConverter<Url> {
    @Override
    public Url convert(String value) throws Exception {
        try {
            return ParserUtil.parseUrl(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
