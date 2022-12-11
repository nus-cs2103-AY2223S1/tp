package seedu.foodrem.logic.parser.itemcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.itemcommands.IncrementCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.ItemQuantity;

/**
 * Parses input arguments and creates a new IncrementCommand object.
 */
public class IncrementCommandParser implements Parser<IncrementCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DecrementCommand
     * and returns an DecrementCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public IncrementCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_ITEM_QUANTITY);
        Index index = StringUtil.validateAndGetIndexFromString(argMultimap.getPreamble().trim(),
                                                               IncrementCommand.getUsage());

        // Default increment by 1 if PREFIX_ITEM_QUANTITY not provided
        ItemQuantity incrementQuantity = ParserUtil.parseQuantity("1");
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_ITEM_QUANTITY)) {
            incrementQuantity = ParserUtil.parseQuantity(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_QUANTITY));
        }

        return new IncrementCommand(index, incrementQuantity);
    }
}
