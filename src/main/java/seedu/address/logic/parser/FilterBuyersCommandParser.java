package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.FilterBuyersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FilterBuyerByCharacteristicsPredicate;
import seedu.address.model.person.FilterBuyerByPricePredicate;



/**
 * Parses user input to create a {@code FilterBuyersCommand}.
 */
public class FilterBuyersCommandParser extends Parser<FilterBuyersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterBuyersCommand
     * and returns an FilterBuyersCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterBuyersCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CHARACTERISTICS, PREFIX_PRICE,
                PREFIX_PRIORITY);

        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            try {
                return new FilterBuyersCommand(new FilterBuyerByPricePredicate(argMultimap
                        .getValue(PREFIX_PRICE).get()));
            } catch (Exception e) {
                throw new ParseException(e.getMessage());
            }
        }
        // BUG: Owing to StringUtil.containsWordIgnoreCase, characteristics can only be a single word.
        // However, the error message does not show up in the dialog box in the GUI.
        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            try {
                return new FilterBuyersCommand(new FilterBuyerByCharacteristicsPredicate(argMultimap
                    .getValue(PREFIX_CHARACTERISTICS).get()));
            } catch (Exception e) {
                throw new ParseException(e.getMessage());
            }
        }
        // TODO andre: add case for tags

        throw new ParseException(FilterBuyersCommand.MESSAGE_USAGE);
    }
}
