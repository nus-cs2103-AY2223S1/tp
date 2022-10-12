package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTIES;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.role.Properties;
import seedu.address.model.role.Seller;

/**
 * Parses input arguments and creates a new AddSellerCommand object
 */
public class AddSellerCommandParser extends Parser<AddSellerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSellerCommand
     * and returns an AddSellerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSellerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE), pe);
        }

        Properties propertyIndices = ParserUtil.parseProperties(argMultimap.getValue(PREFIX_PROPERTIES).orElse(""));

        Seller seller = new Seller(propertyIndices);

        return new AddSellerCommand(seller, index);
    }
}
