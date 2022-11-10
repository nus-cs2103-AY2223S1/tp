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
import seedu.address.model.person.Buyer;
import seedu.address.testutil.TypicalBuyers;

public class SortBuyerCommandTest {
    private Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model bExpectedModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());

    private final Integer firstAttributePos = 0;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortBuyerCommand(null));
    }

    @Test
    public void execute_noAttributes_noDifference() {
        CommandResult expectedResult = new CommandResult(SortBuyerCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Buyer> comparator = getBuyerComparator("");
            SortBuyerCommand command = new SortBuyerCommand(comparator);
            assertCommandSuccess(command, bModel, expectedResult, bExpectedModel);
            assertEquals(bModel.getFilteredBuyerList(), bExpectedModel.getFilteredBuyerList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_oneAttributes_sortedAccordingToThatAttribute() {
        CommandResult expectedResult = new CommandResult(SortBuyerCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Buyer> comparator = getBuyerComparator("Address");
            SortBuyerCommand command = new SortBuyerCommand(comparator);
            bExpectedModel.sortBuyer(comparator);
            assertCommandSuccess(command, bModel, expectedResult, bExpectedModel);
            assertEquals(bModel.getFilteredBuyerList(), bExpectedModel.getFilteredBuyerList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_multipleAttributes_sortedAccordingToThoseAttributes() {
        CommandResult expectedResult = new CommandResult(SortBuyerCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Buyer> comparator = getBuyerComparator("Address Email Phone");
            SortBuyerCommand command = new SortBuyerCommand(comparator);
            bExpectedModel.sortBuyer(comparator);
            assertCommandSuccess(command, bModel, expectedResult, bExpectedModel);
            assertEquals(bModel.getFilteredBuyerList(), bExpectedModel.getFilteredBuyerList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        try {
            Comparator<Buyer> comparator = getBuyerComparator("Address Email Phone");
            SortBuyerCommand command = new SortBuyerCommand(comparator);
            assertEquals(command, command);
        } catch (ParseException e) {
            assert false;
        }
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

    private boolean isAlphabets(String attribute) {
        return attribute != null && attribute.matches("^[a-zA-Z]*$");
    }

    private void assertAlphabets(String attribute) throws ParseException {
        if (!isAlphabets(attribute)) {
            throw new ParseException(String.format(SortCommand.MESSAGE_ONLY_ALPHABET_PARAMETERS, attribute));
        }
    }
}
