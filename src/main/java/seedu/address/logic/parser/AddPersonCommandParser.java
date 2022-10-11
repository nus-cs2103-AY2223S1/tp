package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.address.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.desiredcharacteristics.DesiredCharacteristics;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.Price;
import seedu.address.model.tag.Tag;



/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddPersonCommandParser extends Parser<AddPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        
        // if user does not specify a budget for the buyer, then there will be no PriceRange object 
        PriceRange priceRange = null;
        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).get());
        }
        
        DesiredCharacteristics characteristics = null;
        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            characteristics = ParserUtil.parseCharacteristics(argMultimap.getValue(PREFIX_CHARACTERISTICS).get());
        }
        
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, address, priceRange, characteristics, tagList);

        return new AddPersonCommand(person);
    }

}
