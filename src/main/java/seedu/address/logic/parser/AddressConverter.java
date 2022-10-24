package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;

public class AddressConverter implements CommandLine.ITypeConverter<Address> {
    @Override
    public Address convert(String value) throws Exception {
        try {
            return ParserUtil.parseAddress(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
