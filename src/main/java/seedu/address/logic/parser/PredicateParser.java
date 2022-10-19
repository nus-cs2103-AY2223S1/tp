package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.*;

import java.util.Arrays;
import java.util.function.Predicate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class PredicateParser {
    public static final String addressPrefix = "a";
    public static final String emailPrefix = "e";
    public static final String locPrefix = "l";
    public static final String namePrefix = "n";
    public static final String phonePrefix = "p";

    public static Predicate<Buyer> parseBuyer(String input) throws ParseException {
        String[] nameKeywords = input.trim().split("/", 2);
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
            case "a":
                return new AddressContainsKeywordsPredicate<>(Arrays.asList(query));
            case "e":
                return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
            case "l":
                return new LocationContainsKeywordsPredicate<>(Arrays.asList(query));
            case "n":
                return new NameContainsKeywordsPredicate<>(Arrays.asList(query));
            case "p":
                return new PhoneContainsKeywordsPredicate<>(Arrays.asList(query));
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    public static Predicate<Deliverer> parseDeliverer(String input) throws ParseException {
        String[] nameKeywords = input.trim().split("/", 2);
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
            case "a":
                return new AddressContainsKeywordsPredicate<>(Arrays.asList(query));
            case "e":
                return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
            case "l":
                return new LocationContainsKeywordsPredicate<>(Arrays.asList(query));
            case "n":
                return new NameContainsKeywordsPredicate<>(Arrays.asList(query));
            case "p":
                return new PhoneContainsKeywordsPredicate<>(Arrays.asList(query));
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    public static Predicate<Supplier> parseSupplier(String input) throws ParseException {
        String[] nameKeywords = input.trim().split("/", 2);
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
            case "a":
                return new AddressContainsKeywordsPredicate<>(Arrays.asList(query));
            case "e":
                return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
            case "l":
                return new LocationContainsKeywordsPredicate<>(Arrays.asList(query));
            case "n":
                return new NameContainsKeywordsPredicate<>(Arrays.asList(query));
            case "p":
                return new PhoneContainsKeywordsPredicate<>(Arrays.asList(query));
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
