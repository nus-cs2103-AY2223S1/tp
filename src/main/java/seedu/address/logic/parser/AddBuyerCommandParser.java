package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.role.Buyer;
import seedu.address.model.role.DesiredCharacteristics;
import seedu.address.model.role.PriceRange;

/**
 * Parses input arguments and creates a new AddBuyerCommand object
 */
public class AddBuyerCommandParser extends Parser<AddBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddBuyerCommand
     * and returns an AddBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBuyerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE), pe);
        }

        PriceRange priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).orElse(""));
        DesiredCharacteristics characteristics = ParserUtil
                .parseCharacteristics(argMultimap
                        .getValue(PREFIX_CHARACTERISTICS)
                        .orElse(""));

        Buyer buyer = new Buyer(priceRange, characteristics);

        return new AddBuyerCommand(buyer, index);
    }
}
