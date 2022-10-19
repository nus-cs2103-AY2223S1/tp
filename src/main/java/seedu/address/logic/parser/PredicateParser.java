package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new Predicate.
 */
public class PredicateParser {
    public static final String ADDRESS_PREFIX = "a";
    public static final String EMAIL_PREFIX = "e";
    public static final String LOC_PREFIX = "l";
    public static final String NAME_PREFIX = "n";
    public static final String PHONE_PREFIX = "p";

    /**
     * Parses the given {@code String} of arguments in the context of a Predicate
     * and returns a Predicate.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public static Predicate<Buyer> parseBuyer(String input) throws ParseException {
        String[] nameKeywords = input.trim().split("/", 2);
        if (nameKeywords.length < 2 || nameKeywords[1].isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
        case ADDRESS_PREFIX:
            return new AddressContainsKeywordsPredicate<>(Arrays.asList(query));
        case EMAIL_PREFIX:
            return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
        case LOC_PREFIX:
            return new LocationContainsKeywordsPredicate<>(Arrays.asList(query));
        case NAME_PREFIX:
            return new NameContainsKeywordsPredicate<>(Arrays.asList(query));
        case PHONE_PREFIX:
            return new PhoneContainsKeywordsPredicate<>(Arrays.asList(query));
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of a Predicate
     * and returns a Predicate.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public static Predicate<Deliverer> parseDeliverer(String input) throws ParseException {
        String[] nameKeywords = input.trim().split("/", 2);
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
        case ADDRESS_PREFIX:
            return new AddressContainsKeywordsPredicate<>(Arrays.asList(query));
        case EMAIL_PREFIX:
            return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
        case LOC_PREFIX:
            return new LocationContainsKeywordsPredicate<>(Arrays.asList(query));
        case NAME_PREFIX:
            return new NameContainsKeywordsPredicate<>(Arrays.asList(query));
        case PHONE_PREFIX:
            return new PhoneContainsKeywordsPredicate<>(Arrays.asList(query));
        default:
            throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of a Predicate
     * and returns a Predicate.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public static Predicate<Supplier> parseSupplier(String input) throws ParseException {
        String[] nameKeywords = input.trim().split("/", 2);
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
        case ADDRESS_PREFIX:
            return new AddressContainsKeywordsPredicate<>(Arrays.asList(query));
        case EMAIL_PREFIX:
            return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
        case LOC_PREFIX:
            return new LocationContainsKeywordsPredicate<>(Arrays.asList(query));
        case NAME_PREFIX:
            return new NameContainsKeywordsPredicate<>(Arrays.asList(query));
        case PHONE_PREFIX:
            return new PhoneContainsKeywordsPredicate<>(Arrays.asList(query));
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
