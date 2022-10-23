package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.model.person.Name;

public class NameConverter implements CommandLine.ITypeConverter<Name> {
    @Override
    public Name convert(String value) throws Exception {
        if (!Name.isValidName(value)) {
            throw new CommandLine.TypeConversionException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(value);
    }
}
