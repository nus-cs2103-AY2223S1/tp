package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.SortCommandParserUtil;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    private final Integer firstAttributePos = 0;

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
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
