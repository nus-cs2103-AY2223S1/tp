package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BILL_7;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BILL_8;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BILL_DATE_7;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBillAtIndex;
import static seedu.address.testutil.TypicalBills.BILL_1;
import static seedu.address.testutil.TypicalBills.getTypicalBillsHealthContact;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BILL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BILL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBillCommand.EditBillDescriptor;
import seedu.address.model.HealthContact;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bill.Bill;
import seedu.address.testutil.BillBuilder;
import seedu.address.testutil.EditBillDescriptorBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditBillCommandTest {

    private Model model = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {

        Bill editedBill = new BillBuilder().withPaymentStatus(BILL_1.getPaymentStatus().toString()).build();

        EditBillDescriptor descriptor = new EditBillDescriptorBuilder(editedBill).build();
        EditBillCommand editBillCommand = new EditBillCommand(INDEX_FIRST_BILL, descriptor);

        String expectedMessage = String.format(EditBillCommand.MESSAGE_EDIT_BILL_SUCCESS, editedBill);

        Model expectedModel = new ModelManager(new HealthContact(model.getHealthContact()), new UserPrefs());
        expectedModel.setBill(model.getFilteredBillList().get(0), editedBill);

        assertCommandSuccess(editBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBill = Index.fromOneBased(model.getFilteredBillList().size());
        Bill lastBill = model.getFilteredBillList().get(indexLastBill.getZeroBased());

        BillBuilder billInList = new BillBuilder(lastBill);
        Bill editedBill = billInList.withBillDate(VALID_BILL_DATE_7).withAmount(VALID_AMOUNT_7).build();

        EditBillDescriptor descriptor = new EditBillDescriptorBuilder().withBillDate(VALID_BILL_DATE_7)
                .withAmount(VALID_AMOUNT_7).build();
        EditBillCommand editBillCommand = new EditBillCommand(indexLastBill, descriptor);

        String expectedMessage = String.format(EditBillCommand.MESSAGE_EDIT_BILL_SUCCESS, editedBill);

        Model expectedModel = new ModelManager(new HealthContact(model.getHealthContact()), new UserPrefs());
        expectedModel.setBill(lastBill, editedBill);

        assertCommandSuccess(editBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBillCommand editBillCommand = new EditBillCommand(INDEX_FIRST_BILL,
                new EditBillCommand.EditBillDescriptor());
        Bill editedBill = model.getFilteredBillList().get(INDEX_FIRST_BILL.getZeroBased());

        String expectedMessage = String.format(EditBillCommand.MESSAGE_EDIT_BILL_SUCCESS, editedBill);

        Model expectedModel = new ModelManager(new HealthContact(model.getHealthContact()), new UserPrefs());

        assertCommandSuccess(editBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBillAtIndex(model, INDEX_FIRST_BILL);

        Bill billInFilteredList = model.getFilteredBillList().get(INDEX_FIRST_BILL.getZeroBased());
        Bill editedBill = new BillBuilder(billInFilteredList).withBillDate(VALID_BILL_DATE_7).build();
        EditBillCommand editBillCommand = new EditBillCommand(INDEX_FIRST_BILL,
                new EditBillDescriptorBuilder().withBillDate(VALID_BILL_DATE_7).build());

        String expectedMessage = String.format(EditBillCommand.MESSAGE_EDIT_BILL_SUCCESS, editedBill);

        Model expectedModel = new ModelManager(new HealthContact(model.getHealthContact()), new UserPrefs());
        expectedModel.setBill(model.getFilteredBillList().get(0), editedBill);

        assertCommandSuccess(editBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBillIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBillList().size() + 1);
        EditBillCommand.EditBillDescriptor descriptor =
                new EditBillDescriptorBuilder().withBillDate(VALID_BILL_DATE_7).build();
        EditBillCommand editBillCommand = new EditBillCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBillCommand, model, Messages.MESSAGE_INVALID_BILL_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of HealthContact
     */
    @Test
    public void execute_invalidBillIndexFilteredList_failure() {
        showBillAtIndex(model, INDEX_FIRST_BILL);
        Index outOfBoundIndex = INDEX_SECOND_BILL;
        // ensures that outOfBoundIndex is still in bounds of HealthContact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHealthContact().getBillList().size());

        EditBillCommand editBillCommand = new EditBillCommand(outOfBoundIndex,
                new EditBillDescriptorBuilder().withBillDate(VALID_BILL_DATE_7).build());

        assertCommandFailure(editBillCommand, model, Messages.MESSAGE_INVALID_BILL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBillCommand standardCommand = new EditBillCommand(INDEX_FIRST_BILL, DESC_BILL_7);

        // same values -> returns true
        EditBillDescriptor copyDescriptor = new EditBillDescriptor(DESC_BILL_7);
        EditBillCommand commandWithSameValues = new EditBillCommand(INDEX_FIRST_BILL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBillCommand(INDEX_SECOND_BILL, DESC_BILL_8)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBillCommand(INDEX_FIRST_BILL, DESC_BILL_8)));
    }
}
