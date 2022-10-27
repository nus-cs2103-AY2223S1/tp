package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;

public class OrderConverter implements CommandLine.ITypeConverter<Order> {
    @Override
    public Order convert(String value) throws Exception {
        try {
            return ParserUtil.parseOrder(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
