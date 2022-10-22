package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.SortBuyerCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.SortCommandParserUtil;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<listType>.\\S*)(?<attributes>.*)");
    private final Integer FIRST_ATTRIBUTE_POS = 0;

    @Override
    public SortCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String listType = matcher.group("listType");
        final String attributes = matcher.group("attributes");

        if (SortCommand.isValidParameter(SortCommand.ACCEPTABLE_BUYER_PARAMETER, listType)) {
            return parseToSortBuyerCommand(attributes);
        }

        if (SortCommand.isValidParameter(SortCommand.ACCEPTABLE_SUPPLIER_PARAMETER, listType)) {
            return parseToSortSupplierCommand(attributes);
        }

        if (SortCommand.isValidParameter(SortCommand.ACCEPTABLE_DELIVERER_PARAMETER, listType)) {
            return parseToSortDelivererCommand(attributes);
        }

        if (SortCommand.isValidParameter(SortCommand.ACCEPTABLE_ORDER_PARAMETER, listType)) {
            return parseToSortOrderCommand(attributes);
        }

        if (SortCommand.isValidParameter(SortCommand.ACCEPTABLE_PET_PARAMETER, listType)) {
            return parseToSortPetCommand(attributes);
        }

        throw new ParseException(String.format(SortCommand.MESSAGE_UNKNOWN_LIST, listType,
                SortCommand.MESSAGE_SUPPORTED_LIST));
    }

    private SortCommand parseToSortPetCommand(String attributes) {
        requireNonNull(attributes);
        return null;
    }

    private SortCommand parseToSortOrderCommand(String attributes) {
        requireNonNull(attributes);
        return null;
    }

    private SortCommand parseToSortDelivererCommand(String attributes) {
        requireNonNull(attributes);
        return null;
    }

    private SortCommand parseToSortSupplierCommand(String attributes) {
        requireNonNull(attributes);
        return null;
    }

    private SortCommand parseToSortBuyerCommand(String attributes) throws ParseException {
        requireNonNull(attributes);
        Comparator<Buyer> comparator = getBuyerComparator(attributes);
        return new SortBuyerCommand(comparator);
    }

    private Comparator<Buyer> getBuyerComparator(String attributes) throws ParseException {
        String[] attributes_arr = attributes.split("\\s+");
        assertAlphabets(attributes_arr[FIRST_ATTRIBUTE_POS]);
        Comparator<Buyer> comparator = SortCommandParserUtil.parseToSelectedBuyerComparator(
                attributes_arr[FIRST_ATTRIBUTE_POS]);
        for (int i = 1; i < attributes_arr.length; i++) {
            assertAlphabets(attributes_arr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedBuyerComparator(attributes_arr[i]));
        }
        return comparator;
    }

    private Comparator<Supplier> getSupplierComparator(String attributes) throws ParseException {
        String[] attributes_arr = attributes.split("\\s+");
        assertAlphabets(attributes_arr[FIRST_ATTRIBUTE_POS]);
        Comparator<Supplier> comparator = SortCommandParserUtil.parseToSelectedSupplierComparator(
                attributes_arr[FIRST_ATTRIBUTE_POS]);
        for (int i = 1; i < attributes_arr.length; i++) {
            assertAlphabets(attributes_arr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedSupplierComparator(attributes_arr[i]));
        }
        return comparator;
    }

    private Comparator<Deliverer> getDelivererComparator(String attributes) throws ParseException {
        String[] attributes_arr = attributes.split("\\s+");
        assertAlphabets(attributes_arr[FIRST_ATTRIBUTE_POS]);
        Comparator<Deliverer> comparator = SortCommandParserUtil.parseToSelectedDelivererComparator(
                attributes_arr[FIRST_ATTRIBUTE_POS]);
        for (int i = 1; i < attributes_arr.length; i++) {
            assertAlphabets(attributes_arr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedDelivererComparator(attributes_arr[i]));
        }
        return comparator;
    }

    private Comparator<Order> getOrderComparator(String attributes) throws ParseException {
        String[] attributes_arr = attributes.split("\\s+");
        assertAlphabets(attributes_arr[FIRST_ATTRIBUTE_POS]);
        Comparator<Order> comparator = SortCommandParserUtil.parseToSelectedOrderComparator(
                attributes_arr[FIRST_ATTRIBUTE_POS]);
        for (int i = 1; i < attributes_arr.length; i++) {
            assertAlphabets(attributes_arr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedOrderComparator(attributes_arr[i]));
        }
        return comparator;
    }

    private Comparator<Pet> getPetComparator(String attributes) throws ParseException {
        String[] attributes_arr = attributes.split("\\s+");
        assertAlphabets(attributes_arr[FIRST_ATTRIBUTE_POS]);
        Comparator<Pet> comparator = SortCommandParserUtil.parseToSelectedPetComparator(
                attributes_arr[FIRST_ATTRIBUTE_POS]);
        for (int i = 1; i < attributes_arr.length; i++) {
            assertAlphabets(attributes_arr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedPetComparator(attributes_arr[i]));
        }
        return comparator;
    }

    private boolean isAlphabets(String attribute) {
        return attribute != null && attribute.matches("^[a-zA-Z]*$");
    }

    private void assertAlphabets(String attribute) throws ParseException {
        if (!isAlphabets(attribute)) {
            throw new ParseException(String.format(SortCommand.MESSAGE_ONLY_ALPHABET_PARAMETERS, attribute));
        }
    }

}
