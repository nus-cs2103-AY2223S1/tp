package seedu.address.logic.parser.buyer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.time.LocalDateTime;

import seedu.address.logic.commands.buyer.AddBuyerCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddBuyerCommandParser extends Parser<AddBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBuyerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS, PREFIX_PRIORITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        LocalDateTime entryTime = LocalDateTime.now();

        // if user does not specify a budget for the buyer, then there will be no PriceRange object
        PriceRange priceRange = null;
        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).get());
        }
        
        Characteristics characteristics = null;
        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            characteristics = ParserUtil.parseCharacteristics(argMultimap.getValue(PREFIX_CHARACTERISTICS).get());
            characteristics = characteristics.isReset() ? null : characteristics;
        }

        Priority priority = new Priority("normal");
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        }

        Buyer buyer = new Buyer(name, phone, email, address, priceRange, characteristics, priority, entryTime);

        return new AddBuyerCommand(buyer);
    }

}
