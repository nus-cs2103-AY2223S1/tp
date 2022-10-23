package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.model.person.Email;

public class EmailConverter implements CommandLine.ITypeConverter<Email> {
    @Override
    public Email convert(String value) throws Exception {
        if (!Email.isValidEmail(value)) {
            throw new CommandLine.TypeConversionException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(value);
    }
}
