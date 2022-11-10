package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.filtercommands.FilterOrderCommand;
import seedu.address.logic.commands.filtercommands.FilterPetCommand;
import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.predicates.AdditionalRequestPredicate;
import seedu.address.model.order.predicates.OrderStatusPredicate;
import seedu.address.model.order.predicates.PriceRangePredicate;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.predicates.ColorContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PriceContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.SpeciesContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.VaccinationStatusPredicate;

/**
 * Parses input arguments and creates a new Predicate.
 */
public class PredicateParser {
    //For persons
    private static final String ADDRESS_PREFIX = "a";
    private static final String EMAIL_PREFIX = "e";
    private static final String LOC_PREFIX = "l";
    private static final String NAME_PREFIX = "n";
    private static final String PHONE_PREFIX = "ph";

    //For pets
    private static final String COLOR_PREFIX = "p_c/";
    private static final String PET_NAME_PREFIX = "p_n/";
    private static final String PRICE_PREFIX = "p_p/";
    private static final String SPECIES_PREFIX = "p_s/";
    private static final String VACCINATION_PREFIX = "p_v/";

    //For orders
    private static final String ADDITIONAL_REQUEST_PREFIX = "o_ar/";
    private static final String ORDER_STATUS_PREFIX = "o_st/";
    private static final String PRICE_RANGE_PREFIX = "o_pr/";

    /**
     * Parses the given {@code String} of arguments in the context of a Predicate
     * and returns a Predicate.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public static Predicate<Buyer> parseBuyer(String input) throws ParseException {
        String[] nameKeywords = input.trim().split("/");
        if (nameKeywords.length < 2 || nameKeywords[1].isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
        case ADDRESS_PREFIX:
            return new AddressContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case EMAIL_PREFIX:
            if (query.split("\\s+").length != 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
        case LOC_PREFIX:
            return new LocationContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case NAME_PREFIX:
            return new NameContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case PHONE_PREFIX:
            if (query.split("\\s+").length != 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
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
        String[] nameKeywords = input.trim().split("/");
        if (nameKeywords.length < 2 || nameKeywords[1].isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
        case ADDRESS_PREFIX:
            return new AddressContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case EMAIL_PREFIX:
            if (query.split("\\s+").length != 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
        case LOC_PREFIX:
            return new LocationContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case NAME_PREFIX:
            return new NameContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case PHONE_PREFIX:
            if (query.split("\\s+").length != 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
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
        String[] nameKeywords = input.trim().split("/");
        if (nameKeywords.length < 2 || nameKeywords[1].isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String query = nameKeywords[1].trim();
        switch (nameKeywords[0]) {
        case ADDRESS_PREFIX:
            return new AddressContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case EMAIL_PREFIX:
            if (query.split("\\s+").length != 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new EmailContainsKeywordsPredicate<>(Arrays.asList(query));
        case LOC_PREFIX:
            return new LocationContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case NAME_PREFIX:
            return new NameContainsKeywordsPredicate<>(Arrays.asList(query.split("\\s+")));
        case PHONE_PREFIX:
            if (query.split("\\s+").length != 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
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
    public static Predicate<Pet> parsePet(String input, String prefix) throws ParseException {

        input = input.trim();

        if (input.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPetCommand.MESSAGE_USAGE));
        }
        String[] inputs = input.split("\\s+");
        switch (prefix) {
        case COLOR_PREFIX:
            return new ColorContainsKeywordsPredicate<>(Arrays.asList(inputs));
        case PET_NAME_PREFIX:
            return new PetNameContainsKeywordsPredicate<>(Arrays.asList(inputs));
        case PRICE_PREFIX:
            if (!input.matches("[0-9][0-9.]*[0-9]")) {
                throw new ParseException("Input has to be numbers without spaces");
            }

            if (inputs.length > 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPetCommand.MESSAGE_USAGE));
            }
            return new PriceContainsKeywordsPredicate<>(Arrays.asList(Double.parseDouble(input)));
        case SPECIES_PREFIX:
            return new SpeciesContainsKeywordsPredicate<>(Arrays.asList(inputs));
        case VACCINATION_PREFIX:
            input = input.trim();
            if (!input.equals(Boolean.toString(true)) && !input.equals(Boolean.toString(false))) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPetCommand.MESSAGE_USAGE));
            }
            return new VaccinationStatusPredicate<>(Boolean.parseBoolean(input));
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPetCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of a Predicate
     * and returns a Predicate.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public static Predicate<Order> parseOrder(String input, String prefix) throws ParseException {
        input = input.trim();

        if (input.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterOrderCommand.MESSAGE_USAGE));
        }

        switch (prefix) {
        case ADDITIONAL_REQUEST_PREFIX:
            return new AdditionalRequestPredicate<>(Arrays.asList(input));
        case ORDER_STATUS_PREFIX:
            input = input.toUpperCase();
            if (input.equals(OrderStatus.DELIVERING.toString().toUpperCase())) {
                return new OrderStatusPredicate<>(OrderStatus.DELIVERING);
            } else if (input.equals(OrderStatus.NEGOTIATING.toString().toUpperCase())) {
                return new OrderStatusPredicate<>(OrderStatus.NEGOTIATING);
            } else if (input.equals(OrderStatus.PENDING.toString().toUpperCase())) {
                return new OrderStatusPredicate<>(OrderStatus.PENDING);
            }
            throw new ParseException(FilterOrderCommand.MESSAGE_INVALID_OS);
        case PRICE_RANGE_PREFIX:
            final Pattern format = Pattern.compile("(?<lower>.?\\S+),(?<upper>.?\\S+)");
            final Matcher matcher = format.matcher(input);
            if (!matcher.matches()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterOrderCommand.MESSAGE_USAGE));
            }
            final String lower = matcher.group("lower").strip();
            final String upper = matcher.group("upper").strip();
            Price lowerBound = new Price(Double.parseDouble(lower));
            Price upperBound = new Price(Double.parseDouble(upper));

            if ((lowerBound.getPrice() > upperBound.getPrice() && (!upperBound.isNotApplicablePrice()))
                    || ((!lowerBound.isNotApplicablePrice()) && lowerBound.getPrice() < 0)
                    || ((!upperBound.isNotApplicablePrice()) && upperBound.getPrice() < 0)
            ) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterOrderCommand.MESSAGE_USAGE));
            }
            return new PriceRangePredicate<>(lowerBound, upperBound);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterOrderCommand.MESSAGE_USAGE));
        }
    }
}
