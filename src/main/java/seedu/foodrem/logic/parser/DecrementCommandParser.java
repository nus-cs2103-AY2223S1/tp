package seedu.foodrem.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.itemcommands.DecrementCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_QUANTITY)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        ItemQuantity decrementQuantity = ParserUtil.parseQuantity(argMultimap.getPresentValue(PREFIX_ITEM_QUANTITY));

        return new DecrementCommand(index, decrementQuantity);
    }
}
