package seedu.foodrem.logic.parser.itemcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.itemcommands.DecrementCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_ITEM_QUANTITY);
        Index index = StringUtil.validateAndGetIndexFromString(argMultimap.getPreamble().trim(),
                                                               DecrementCommand.getUsage());

        // Default decrement by 1 if PREFIX_ITEM_QUANTITY not provided
        ItemQuantity decrementQuantity = ParserUtil.parseQuantity("1");
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_ITEM_QUANTITY)) {
            decrementQuantity = ParserUtil.parseQuantity(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_QUANTITY));
        }

        return new DecrementCommand(index, decrementQuantity);
    }
}
