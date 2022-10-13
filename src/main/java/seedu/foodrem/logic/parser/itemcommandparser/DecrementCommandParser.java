package seedu.foodrem.logic.parser.itemcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.itemcommands.DecrementCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.ItemQuantity;

/**
 * Parses input arguments and creates a new IncrementCommand object
 */
public class DecrementCommandParser implements Parser<DecrementCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DecrementCommand
     * and returns an DecrementCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DecrementCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ITEM_QUANTITY);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DecrementCommand.MESSAGE_USAGE), pe);
        }

        // Default decrement by 1 if PREFIX_ITEM_QUANTITY not provided
        ItemQuantity decrementQuantity = ParserUtil.parseQuantity("1");

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DecrementCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_ITEM_QUANTITY)) {
            decrementQuantity = ParserUtil.parseQuantity(argMultimap.getPresentValue(PREFIX_ITEM_QUANTITY));
        }

        return new DecrementCommand(index, decrementQuantity);
    }
}
