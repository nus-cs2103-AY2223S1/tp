package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.commands.sortcommands.SortSupplierCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.SortCommandParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.TypicalSuppliers;

public class SortSupplierCommandTest {
    private Model sModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());
    private Model sExpectedModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());

    private final Integer firstAttributePos = 0;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortSupplierCommand(null));
    }

    @Test
    public void execute_noAttributes_noDifference() {
        CommandResult expectedResult = new CommandResult(SortSupplierCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Supplier> comparator = getSupplierComparator("");
            SortSupplierCommand command = new SortSupplierCommand(comparator);
            assertCommandSuccess(command, sModel, expectedResult, sExpectedModel);
            assertEquals(sModel.getFilteredSupplierList(), sExpectedModel.getFilteredSupplierList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_oneAttribute_accordingToThatAttribute() {
        CommandResult expectedResult = new CommandResult(SortSupplierCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Supplier> comparator = getSupplierComparator("Address");
            SortSupplierCommand command = new SortSupplierCommand(comparator);
            sExpectedModel.sortSupplier(comparator);
            assertCommandSuccess(command, sModel, expectedResult, sExpectedModel);
            assertEquals(sModel.getFilteredSupplierList(), sExpectedModel.getFilteredSupplierList());
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void execute_multipleAttributes_accordingToThoseAttributes() {
        CommandResult expectedResult = new CommandResult(SortSupplierCommand.MESSAGE_SUCCESS);

        try {
            Comparator<Supplier> comparator = getSupplierComparator("Address Email Phone");
            SortSupplierCommand command = new SortSupplierCommand(comparator);
            sExpectedModel.sortSupplier(comparator);
            assertCommandSuccess(command, sModel, expectedResult, sExpectedModel);
            assertEquals(sModel.getFilteredSupplierList(), sExpectedModel.getFilteredSupplierList());
        } catch (ParseException e) {
            assert false;
        }
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

    private boolean isAlphabets(String attribute) {
        return attribute != null && attribute.matches("^[a-zA-Z]*$");
    }

    private void assertAlphabets(String attribute) throws ParseException {
        if (!isAlphabets(attribute)) {
            throw new ParseException(String.format(SortCommand.MESSAGE_ONLY_ALPHABET_PARAMETERS, attribute));
        }
    }
}
