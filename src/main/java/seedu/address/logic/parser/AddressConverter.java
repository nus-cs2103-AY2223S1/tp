package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.model.person.Address;

public class AddressConverter implements CommandLine.ITypeConverter<Address> {
    @Override
    public Address convert(String value) throws Exception {
        if (!Address.isValidAddress(value)) {
            throw new CommandLine.TypeConversionException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(value);
    }
}
