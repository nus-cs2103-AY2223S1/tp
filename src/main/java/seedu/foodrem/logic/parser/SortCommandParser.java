package seedu.foodrem.logic.parser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_NAME;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_QTY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_SORT_BY_TYPE;

import seedu.foodrem.logic.commands.FindCommand;
import seedu.foodrem.logic.commands.SortCommand;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.itemcomparators.ItemNameComparator;

/**
 * Parses input arguments and creates a new
 * SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_SORT_BY_NAME,
                PREFIX_SORT_BY_QTY,
                PREFIX_SORT_BY_BOUGHT_DATE,
                PREFIX_SORT_BY_EXPIRY_DATE,
                PREFIX_SORT_BY_TYPE);


        return new SortCommand(new ItemNameComparator());
    }
}
