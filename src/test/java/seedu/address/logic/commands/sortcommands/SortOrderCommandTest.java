package seedu.address.logic.commands.sortcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.SortCommandParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.TypicalOrders;

public class SortOrderCommandTest {
    private Model oModel = new ModelManager(TypicalOrders.getTypicalOrdersAddressBook(), new UserPrefs());
    private Model oExpectedModel = new ModelManager(TypicalOrders.getTypicalOrdersAddressBook(), new UserPrefs());

    private final Integer firstAttributePos = 0;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortOrderCommand(null));
    }

    @Test
    public void execute_noAttributes_noDifference() {
        CommandResult expectedResult = new CommandResult(SortOrderCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Order> comparator = getOrderComparator("");
            SortOrderCommand command = new SortOrderCommand(comparator);
            oExpectedModel.sortOrder(comparator);
            assertCommandSuccess(command, oModel, expectedResult, oExpectedModel);
            assertEquals(oModel.getFilteredOrderList(), oExpectedModel.getFilteredOrderList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_oneAttributes_sortedAccordingToThatAttribute() {
        CommandResult expectedResult = new CommandResult(SortOrderCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Order> comparator = getOrderComparator("Status");
            SortOrderCommand command = new SortOrderCommand(comparator);
            oExpectedModel.sortOrder(comparator);
            assertCommandSuccess(command, oModel, expectedResult, oExpectedModel);
            assertEquals(oModel.getFilteredBuyerList(), oExpectedModel.getFilteredBuyerList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_multipleAttributes_sortedAccordingToThoseAttributes() {
        CommandResult expectedResult = new CommandResult(SortOrderCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Order> comparator = getOrderComparator("Status PRICERANGE DUE");
            SortOrderCommand command = new SortOrderCommand(comparator);
            oExpectedModel.sortOrder(comparator);
            assertCommandSuccess(command, oModel, expectedResult, oExpectedModel);
            assertEquals(oModel.getFilteredOrderList(), oExpectedModel.getFilteredOrderList());
        } catch (ParseException e) {
            assert false;
        }
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

    private boolean isAlphabets(String attribute) {
        return attribute != null && attribute.matches("^[a-zA-Z]*$");
    }

    private void assertAlphabets(String attribute) throws ParseException {
        if (!isAlphabets(attribute)) {
            throw new ParseException(String.format(SortCommand.MESSAGE_ONLY_ALPHABET_PARAMETERS, attribute));
        }
    }
}
