package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<listType>.\\S*)(?<attributes>.*)");
    private final Integer firstAttributePos = 0;

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
        String[] attributesArr = attributes.split("\\s+");
        assertAlphabets(attributesArr[firstAttributePos]);
        Comparator<Buyer> comparator = SortCommandParserUtil.parseToSelectedBuyerComparator(
                attributesArr[firstAttributePos]);
        for (int i = 1; i < attributesArr.length; i++) {
            assertAlphabets(attributesArr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedBuyerComparator(attributesArr[i]));
        }
        return comparator;
    }

    private Comparator<Supplier> getSupplierComparator(String attributes) throws ParseException {
        String[] attributesArr = attributes.split("\\s+");
        assertAlphabets(attributesArr[firstAttributePos]);
        Comparator<Supplier> comparator = SortCommandParserUtil.parseToSelectedSupplierComparator(
                attributesArr[firstAttributePos]);
        for (int i = 1; i < attributesArr.length; i++) {
            assertAlphabets(attributesArr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedSupplierComparator(attributesArr[i]));
        }
        return comparator;
    }

    private Comparator<Deliverer> getDelivererComparator(String attributes) throws ParseException {
        String[] attributesArr = attributes.split("\\s+");
        assertAlphabets(attributesArr[firstAttributePos]);
        Comparator<Deliverer> comparator = SortCommandParserUtil.parseToSelectedDelivererComparator(
                attributesArr[firstAttributePos]);
        for (int i = 1; i < attributesArr.length; i++) {
            assertAlphabets(attributesArr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedDelivererComparator(attributesArr[i]));
        }
        return comparator;
    }

    private Comparator<Order> getOrderComparator(String attributes) throws ParseException {
        String[] attributesArr = attributes.split("\\s+");
        assertAlphabets(attributesArr[firstAttributePos]);
        Comparator<Order> comparator = SortCommandParserUtil.parseToSelectedOrderComparator(
                attributesArr[firstAttributePos]);
        for (int i = 1; i < attributesArr.length; i++) {
            assertAlphabets(attributesArr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedOrderComparator(attributesArr[i]));
        }
        return comparator;
    }

    private Comparator<Pet> getPetComparator(String attributes) throws ParseException {
        String[] attributesArr = attributes.split("\\s+");
        assertAlphabets(attributesArr[firstAttributePos]);
        Comparator<Pet> comparator = SortCommandParserUtil.parseToSelectedPetComparator(
                attributesArr[firstAttributePos]);
        for (int i = 1; i < attributesArr.length; i++) {
            assertAlphabets(attributesArr[i]);
            comparator = comparator.thenComparing(
                    SortCommandParserUtil.parseToSelectedPetComparator(attributesArr[i]));
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
