package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import seedu.address.logic.commands.BuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.role.Buyer;
import seedu.address.model.role.Characteristics;
import seedu.address.model.role.PriceRange;

/**
 * Parses input arguments and creates a new BuyerCommand object
 */
public class BuyerCommandParser extends Parser<BuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BuyerCommand
     * and returns a BuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BuyerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BuyerCommand.MESSAGE_USAGE));
        }

        Name contactName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        PriceRange priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).orElse(""));
        Characteristics characteristics = ParserUtil
                .parseCharacteristics(argMultimap
                        .getValue(PREFIX_CHARACTERISTICS)
                        .orElse(""));

        Buyer buyer = new Buyer(priceRange, characteristics);

        return new BuyerCommand(buyer, contactName);
    }
}
