package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.model.person.Phone;

public class PhoneConverter implements CommandLine.ITypeConverter<Phone> {
    @Override
    public Phone convert(String value) throws Exception {
        if (!Phone.isValidPhone(value)) {
            throw new CommandLine.TypeConversionException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(value);
    }
}
