package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;

public class EmailConverter implements CommandLine.ITypeConverter<Email> {
    @Override
    public Email convert(String value) throws Exception {
        try {
            return ParserUtil.parseEmail(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
