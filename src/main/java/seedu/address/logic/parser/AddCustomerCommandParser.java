package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCustomerCommand object.
 */
public class AddCustomerCommandParser implements Parser<AddCustomerCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCustomerCommand
     * and returns an AddCustomerCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddCustomerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCustomerCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Optional<String> rawAddress = argMultimap.getValue(PREFIX_ADDRESS);
        Optional<Address> address = rawAddress.isEmpty()
            ? Optional.empty()
            : Optional.of(ParserUtil.parseAddress(rawAddress.get()));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));


        Customer.CustomerBuilder customerBuilder = new Customer.CustomerBuilder(name, phone, email, tagList);
        address.ifPresent(customerBuilder::setAddress);
        Customer customer = customerBuilder.build();

        return new AddCustomerCommand(customer);
    }

}
