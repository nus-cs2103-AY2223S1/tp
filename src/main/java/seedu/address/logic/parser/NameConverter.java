package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class NameConverter implements CommandLine.ITypeConverter<Name> {
    @Override
    public Name convert(String value) throws Exception {
        try {
            return ParserUtil.parseName(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
