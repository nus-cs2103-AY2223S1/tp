package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.function.Predicate;

import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.PredicateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Supplier;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (moreThanOnePrefix(trimmedArgs)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, "More than 1 prefix present"));
        }
        Predicate<Buyer> buyerPredicate = PredicateParser.parseBuyer(trimmedArgs);
        Predicate<Deliverer> delivererPredicate = PredicateParser.parseDeliverer(trimmedArgs);
        Predicate<Supplier> supplierPredicate = PredicateParser.parseSupplier(trimmedArgs);

        return new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                PersonCategory.getFromString("Buyer"));
    }

    /**
     * Returns if a string input contains more than one prefix.
     *
     * @param input String input given by user.
     * @return           Whether the input has more than one prefix.
     */
    public boolean moreThanOnePrefix(String input) {
        int totalPrefixesPresent = countOccurrences(PREFIX_ADDRESS.getPrefix(), input)
                + countOccurrences(PREFIX_EMAIL.getPrefix(), input)
                + countOccurrences(PREFIX_INDEX.getPrefix(), input)
                + countOccurrences(PREFIX_LOCATION.getPrefix(), input)
                + countOccurrences(PREFIX_NAME.getPrefix(), input)
                + countOccurrences(PREFIX_PHONE.getPrefix(), input);
        return totalPrefixesPresent > 1;
    }

    /**
     * Counts the number of occurrences of prefix in string.
     *
     * @param prefix Prefix to count.
     * @param input String input given by user.
     * @return           Number of occurrences of that prefix.
     */
    public int countOccurrences(String prefix, String input) {
        int limit = input.length() - prefix.length();
        int count = 0;
        for (int i = 0; i < limit + 1; i++) {
            if (input.substring(i, i + prefix.length()).equals(prefix)) {
                count += 1;
            }
        }
        return count;
    }

}
