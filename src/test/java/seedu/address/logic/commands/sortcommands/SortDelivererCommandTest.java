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
import seedu.address.model.person.Deliverer;
import seedu.address.testutil.TypicalBuyers;

public class SortDelivererCommandTest {
    private Model dModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model dExpectedModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());

    private final Integer firstAttributePos = 0;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortDelivererCommand(null));
    }

    @Test
    public void execute_noAttributes_noDifference() {
        CommandResult expectedResult = new CommandResult(SortDelivererCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Deliverer> comparator = getDelivererComparator("");
            SortDelivererCommand command = new SortDelivererCommand(comparator);
            assertCommandSuccess(command, dModel, expectedResult, dExpectedModel);
            assertEquals(dModel.getFilteredDelivererList(), dExpectedModel.getFilteredDelivererList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_oneAttributes_sortedAccordingToThatAttribute() {
        CommandResult expectedResult = new CommandResult(SortDelivererCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Deliverer> comparator = getDelivererComparator("Address");
            SortDelivererCommand command = new SortDelivererCommand(comparator);
            dExpectedModel.sortDeliverer(comparator);
            assertCommandSuccess(command, dModel, expectedResult, dExpectedModel);
            assertEquals(dModel.getFilteredDelivererList(), dExpectedModel.getFilteredDelivererList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_multipleAttributes_sortedAccordingToThoseAttributes() {
        CommandResult expectedResult = new CommandResult(SortDelivererCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Deliverer> comparator = getDelivererComparator("Address Email Phone");
            SortDelivererCommand command = new SortDelivererCommand(comparator);
            dExpectedModel.sortDeliverer(comparator);
            assertCommandSuccess(command, dModel, expectedResult, dExpectedModel);
            assertEquals(dModel.getFilteredDelivererList(), dExpectedModel.getFilteredDelivererList());
        } catch (ParseException e) {
            assert false;
        }
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

    private boolean isAlphabets(String attribute) {
        return attribute != null && attribute.matches("^[a-zA-Z]*$");
    }

    private void assertAlphabets(String attribute) throws ParseException {
        if (!isAlphabets(attribute)) {
            throw new ParseException(String.format(SortCommand.MESSAGE_ONLY_ALPHABET_PARAMETERS, attribute));
        }
    }
}
