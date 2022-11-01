package seedu.address.logic.parser.util;

import java.util.Comparator;

import seedu.address.logic.commands.sortcommands.SortBuyerCommand;
import seedu.address.logic.commands.sortcommands.SortDelivererCommand;
import seedu.address.logic.commands.sortcommands.SortOrderCommand;
import seedu.address.logic.commands.sortcommands.SortPetCommand;
import seedu.address.logic.commands.sortcommands.SortSupplierCommand;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * Provides utils for Sort related classes.
 */
public class SortCommandParserUtil {

    //Comparators for person
    private static final Comparator<? extends Person> PERSON_ADDRESS_COMPARATOR = (o1, o2) ->
            o1.getAddress().compareTo(o2.getAddress());
    private static final Comparator<? extends Person> PERSON_EMAIL_COMPARATOR = (o1, o2) ->
            o1.getEmail().compareTo(o2.getEmail());
    private static final Comparator<? extends Person> PERSON_LOCATION_COMPARATOR = (o1, o2) ->
            o1.getLocation().compareTo(o2.getLocation());
    private static final Comparator<? extends Person> PERSON_NAME_COMPARATOR = (o1, o2) ->
            o1.getName().compareTo(o2.getName());
    private static final Comparator<? extends Person> PERSON_PHONE_COMPARATOR = (o1, o2) ->
            o1.getPhone().compareTo(o2.getPhone());

    //Comparators for buyer
    private static final Comparator<Buyer> BUYER_COMPARATOR = Buyer::compareTo;

    //Comparators for supplier
    private static final Comparator<Supplier> SUPPLIER_COMPARATOR = Supplier::compareTo;

    //Comparators for deliverer
    private static final Comparator<Deliverer> DELIVERER_COMPARATOR = Deliverer::compareTo;

    //Comparators for order
    private static final Comparator<Order> ORDER_PRICE_RANGE_COMPARATOR = Comparator.comparing(
            Order::getRequestedPriceRange);
    private static final Comparator<Order> ORDER_DUE_DATE_COMPARATOR = Comparator.comparing(Order::getByDate);
    private static final Comparator<Order> ORDER_PRICE_COMPARATOR = Comparator.comparing(Order::getSettledPrice);
    private static final Comparator<Order> ORDER_STATUS_COMPARATOR = Comparator.comparing(Order::getOrderStatus);

    //comparators for pet
    private static final Comparator<Pet> PET_NAME_COMPARATOR = (o1, o2) ->
            o1.getName().compareTo(o2.getName());
    private static final Comparator<Pet> PET_COLOR_COMPARATOR = Comparator.comparing(Pet::getColor);
    private static final Comparator<Pet> PET_COLOR_PATTERN_COMPARATOR = Comparator.comparing(Pet::getColorPattern);
    private static final Comparator<Pet> PET_BIRTH_DATE_COMPARATOR = Comparator.comparing(Pet::getDateOfBirth);
    private static final Comparator<Pet> PET_SPECIES_COMPARATOR = Comparator.comparing(Pet::getSpecies);
    private static final Comparator<Pet> PET_HEIGHT_COMPARATOR = Comparator.comparing(Pet::getHeight);
    private static final Comparator<Pet> PET_WEIGHT_COMPARATOR = Comparator.comparing(Pet::getWeight);
    private static final Comparator<Pet> PET_VACCINATION_STATUS_COMPARATOR = Comparator.comparing(
            Pet::getVaccinationStatus);
    private static final Comparator<Pet> PET_PRICE_COMPARATOR = Comparator.comparing(Pet::getPrice);
    private static final Comparator<Pet> PET_CERTIFICATE_COMPARATOR = Pet::compareCertificate;

    private static Comparator<? extends Person> parseToSelectedPersonComparator(String attribute) {

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_ADDRESS_PARAMETER, attribute)) {
            return PERSON_ADDRESS_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_EMAIL_PARAMETER, attribute)) {
            return PERSON_EMAIL_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_LOCATION_PARAMETER, attribute)) {
            return PERSON_LOCATION_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_NAME_PARAMETER, attribute)) {
            return PERSON_NAME_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_PHONE_PARAMETER, attribute)) {
            return PERSON_PHONE_COMPARATOR;
        }

        return null;
    }

    /**
     * Parses the input string to a buyer comparator according to the attribute specify by the input string.
     * @param attribute The input string.
     * @return The comparator.
     * @throws ParseException Throws an exception when the input string species an attribute that is not supported
     *     for sorting.
     */
    public static Comparator<Buyer> parseToSelectedBuyerComparator(String attribute) throws ParseException {
        if (attribute.isEmpty() || CommandUtil.isValidParameter(
                CommandUtil.ACCEPTABLE_SORT_ORDER_SIZE_PARAMETER, attribute)) {
            return BUYER_COMPARATOR;
        }
        if (parseToSelectedPersonComparator(attribute) == null) {
            throw new ParseException(String.format(SortBuyerCommand.MESSAGE_WRONG_ATTRIBUTE, attribute,
                    SortBuyerCommand.MESSAGE_USAGE));
        }

        /*
          parseToSelectedPersonComparator() will always return a valid person comparator if not null.
         */
        @SuppressWarnings("unchecked")
        Comparator<Buyer> comparator = (Comparator<Buyer>) parseToSelectedPersonComparator(attribute);

        return comparator;
    }

    /**
     * Parses the input string to a supplier comparator according to the attribute specify by the input string.
     * @param attribute The input string.
     * @return The comparator.
     * @throws ParseException Throws an exception when the input string species an attribute that is not supported
     *     for sorting.
     */
    public static Comparator<Supplier> parseToSelectedSupplierComparator(String attribute) throws ParseException {
        if (attribute.isEmpty() || CommandUtil.isValidParameter(
                CommandUtil.ACCEPTABLE_SORT_PET_LIST_SIZE_PARAMETER, attribute)) {
            return SUPPLIER_COMPARATOR;
        }
        if (parseToSelectedPersonComparator(attribute) == null) {
            throw new ParseException(String.format(SortSupplierCommand.MESSAGE_WRONG_ATTRIBUTE, attribute,
                    SortSupplierCommand.MESSAGE_USAGE));
        }

        /*
          parseToSelectedPersonComparator() will always return a valid person comparator if not null.
         */
        @SuppressWarnings("unchecked")
        Comparator<Supplier> comparator = (Comparator<Supplier>) parseToSelectedPersonComparator(attribute);

        return comparator;
    }

    /**
     * Parses the input string to a deliverer comparator according to the attribute specify by the input string.
     * @param attribute The input string.
     * @return The comparator.
     * @throws ParseException Throws an exception when the input string species an attribute that is not supported
     *     for sorting.
     */
    public static Comparator<Deliverer> parseToSelectedDelivererComparator(String attribute) throws ParseException {
        if (attribute.isEmpty() || CommandUtil.isValidParameter(
                CommandUtil.ACCEPTABLE_SORT_ORDER_SIZE_PARAMETER, attribute)) {
            return DELIVERER_COMPARATOR;
        }
        if (parseToSelectedPersonComparator(attribute) == null) {
            throw new ParseException(String.format(SortDelivererCommand.MESSAGE_WRONG_ATTRIBUTE, attribute,
                    SortSupplierCommand.MESSAGE_USAGE));
        }

        /*
          parseToSelectedPersonComparator() will always return a valid person comparator if not null.
         */
        @SuppressWarnings("unchecked")
        Comparator<Deliverer> comparator = (Comparator<Deliverer>) parseToSelectedPersonComparator(attribute);

        return comparator;
    }

    /**
     * Parses the input string to an order comparator according to the attribute specify by the input string.
     * @param attribute The input string.
     * @return The comparator.
     * @throws ParseException Throws an exception when the input string species an attribute that is not supported
     *     for sorting.
     */
    public static Comparator<Order> parseToSelectedOrderComparator(String attribute) throws ParseException {
        if (attribute.isEmpty() || CommandUtil.isValidParameter(
                CommandUtil.ACCEPTABLE_SORT_DUE_DATE_PARAMETER, attribute)) {
            return ORDER_DUE_DATE_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_PRICE_RANGE_PARAMETER, attribute)) {
            return ORDER_PRICE_RANGE_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_PRICE_PARAMETER, attribute)) {
            return ORDER_PRICE_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_STATUS_PARAMETER, attribute)) {
            return ORDER_STATUS_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_DUE_DATE_PARAMETER, attribute)) {
            return ORDER_DUE_DATE_COMPARATOR;
        }
        throw new ParseException(String.format(SortOrderCommand.MESSAGE_WRONG_ATTRIBUTE, attribute,
                SortOrderCommand.MESSAGE_USAGE));

    }


    /**
     * Parses the input string to a pet comparator according to the attribute specify by the input string.
     * @param attribute The input string.
     * @return The comparator.
     * @throws ParseException Throws an exception when the input string species an attribute that is not supported
     *     for sorting.
     */
    public static Comparator<Pet> parseToSelectedPetComparator(String attribute) throws ParseException {
        if (attribute.isEmpty() || CommandUtil.isValidParameter(
                CommandUtil.ACCEPTABLE_SORT_PRICE_PARAMETER, attribute)) {
            return PET_PRICE_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_NAME_PARAMETER, attribute)) {
            return PET_NAME_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_COLOR_PARAMETER, attribute)) {
            return PET_COLOR_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_COLOR_PATTERN_PARAMETER, attribute)) {
            return PET_COLOR_PATTERN_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_BIRTH_DATE_PARAMETER, attribute)) {
            return PET_BIRTH_DATE_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_SPECIES_PARAMETER, attribute)) {
            return PET_SPECIES_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_HEIGHT_PARAMETER, attribute)) {
            return PET_HEIGHT_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_WEIGHT_PARAMETER, attribute)) {
            return PET_WEIGHT_COMPARATOR;
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SORT_PRICE_PARAMETER, attribute)) {
            return PET_PRICE_COMPARATOR;
        }

        throw new ParseException(String.format(SortPetCommand.MESSAGE_WRONG_ATTRIBUTE, attribute,
                SortPetCommand.MESSAGE_USAGE));
    }
}
