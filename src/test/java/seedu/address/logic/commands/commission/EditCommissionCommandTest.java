package seedu.address.logic.commands.commission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CAT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ANIMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DOG;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCommissionAtIndex;
import static seedu.address.testutil.TypicalCommissions.CAT_PRODUCER;
import static seedu.address.testutil.TypicalCommissions.ELEPHANT_PRODUCER;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.EditCommissionCommand;
import seedu.address.logic.commands.EditCommissionCommand.EditCommissionDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.testutil.CommissionBuilder;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.EditCommissionDescriptorBuilder;

class EditCommissionCommandTest {
    public static final Supplier<Customer> TOM_PRODUCER = () -> new CustomerBuilder().withName("Tom").build();
    private final Model model = getSetUpModelManager();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Commission editedCommission = new CommissionBuilder().build(model.getSelectedCustomer().getValue());
        EditCommissionDescriptor descriptor = new EditCommissionDescriptorBuilder(editedCommission).build();
        EditCommissionCommand editCommissionCommand = new EditCommissionCommand(INDEX_FIRST, descriptor);
        String expectedMessage = String.format(EditCommissionCommand.MESSAGE_EDIT_COMMISSION_SUCCESS, editedCommission);

        Model expectedModel = getSetUpModelManager();
        expectedModel.getSelectedCustomer().getValue().setCommission(model.getFilteredCommissionList().get(0),
                editedCommission);

        assertCommandSuccess(editCommissionCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCommission = Index.fromOneBased(model.getFilteredCommissionList().size());
        Commission lastCommission = model.getFilteredCommissionList().get(indexLastCommission.getZeroBased());

        CommissionBuilder commissionInList = new CommissionBuilder(lastCommission);
        Commission editedCommission = commissionInList.withTitle(VALID_TITLE_DOG).withDescription(VALID_DESCRIPTION_DOG)
                .withTags(VALID_TAG_ANIMAL).build(model.getSelectedCustomer().getValue());

        EditCommissionDescriptor descriptor = new EditCommissionDescriptorBuilder().withTitle(VALID_TITLE_DOG)
                .withDescription(VALID_DESCRIPTION_DOG).withTags(VALID_TAG_ANIMAL).build();
        EditCommissionCommand editCommissionCommand = new EditCommissionCommand(indexLastCommission, descriptor);

        String expectedMessage = String.format(EditCommissionCommand.MESSAGE_EDIT_COMMISSION_SUCCESS, editedCommission);

        Model expectedModel = getSetUpModelManager();;
        expectedModel.getSelectedCustomer().getValue().setCommission(lastCommission, editedCommission);

        assertCommandSuccess(editCommissionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Customer selectedCustomer = model.getSelectedCustomer().getValue();
        EditCommissionCommand editCommissionCommand = new EditCommissionCommand(INDEX_FIRST,
                new EditCommissionCommand.EditCommissionDescriptor());
        Commission commissionInFilteredList = model.getFilteredCommissionList().get(INDEX_FIRST.getZeroBased());
        Commission editedCommission = new CommissionBuilder(commissionInFilteredList)
                .build(selectedCustomer);
        selectedCustomer.setCommission(commissionInFilteredList, editedCommission);

        String expectedMessage = String.format(EditCommissionCommand.MESSAGE_EDIT_COMMISSION_SUCCESS, editedCommission);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommissionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCommissionAtIndex(model, INDEX_FIRST);

        Customer selectedCustomer = model.getSelectedCustomer().getValue();
        Commission commissionInFilteredList = model.getFilteredCommissionList().get(INDEX_FIRST.getZeroBased());
        Commission editedCommission = new CommissionBuilder(commissionInFilteredList).withTitle(VALID_TITLE_DOG)
                .build(selectedCustomer);
        EditCommissionCommand editCommissionCommand = new EditCommissionCommand(INDEX_FIRST,
                new EditCommissionDescriptorBuilder().withTitle(VALID_TITLE_DOG).build());

        String expectedMessage = String.format(EditCommissionCommand.MESSAGE_EDIT_COMMISSION_SUCCESS, editedCommission);

        Model expectedModel = getSetUpModelManager();
        expectedModel.getSelectedCustomer().getValue().setCommission(expectedModel.getFilteredCommissionList()
                .get(INDEX_FIRST.getZeroBased()), editedCommission);

        assertCommandSuccess(editCommissionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCommissionUnfilteredList_failure() {
        Commission firstCommission = model.getFilteredCommissionList().get(INDEX_FIRST.getZeroBased());
        EditCommissionDescriptor descriptor = new EditCommissionDescriptorBuilder(firstCommission).build();
        EditCommissionCommand editCommissionCommand = new EditCommissionCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommissionCommand, model, EditCommissionCommand.MESSAGE_DUPLICATE_COMMISSION);
    }

    @Test
    public void execute_duplicateCommissionFilteredList_failure() {
        showCommissionAtIndex(model, INDEX_FIRST);

        // edit commission in filtered list into a duplicate in address book
        Customer selectedCustomer = model.getSelectedCustomer().getValue();
        Commission commissionInList = selectedCustomer.getCommissionList().get(INDEX_SECOND.getZeroBased());
        EditCommissionCommand editCommissionCommand = new EditCommissionCommand(INDEX_FIRST,
                new EditCommissionDescriptorBuilder(commissionInList).build());

        assertCommandFailure(editCommissionCommand, model, EditCommissionCommand.MESSAGE_DUPLICATE_COMMISSION);
    }

    @Test
    public void execute_invalidCommissionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCommissionList().size() + 1);
        EditCommissionDescriptor descriptor = new EditCommissionDescriptorBuilder().withTitle(VALID_TITLE_DOG).build();
        EditCommissionCommand editCommissionCommand = new EditCommissionCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommissionCommand, model, Messages.MESSAGE_INVALID_COMMISSION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidCommissionIndexFilteredList_failure() {
        showCommissionAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCustomerList().size());

        EditCommissionCommand editCommissionCommand = new EditCommissionCommand(outOfBoundIndex,
                new EditCommissionDescriptorBuilder().withTitle(VALID_TITLE_DOG).build());

        assertCommandFailure(editCommissionCommand, model, Messages.MESSAGE_INVALID_COMMISSION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommissionCommand standardCommand = new EditCommissionCommand(INDEX_FIRST, DESC_CAT);

        // same values -> returns true
        EditCommissionDescriptor copyDescriptor = new EditCommissionDescriptor(DESC_CAT);
        EditCommissionCommand commandWithSameValues = new EditCommissionCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommissionCommand(INDEX_SECOND, DESC_CAT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommissionCommand(INDEX_FIRST, DESC_DOG)));
    }

    private static Model getSetUpModelManager() {
        Customer tom = TOM_PRODUCER.get();
        Commission tomCatCommission = CAT_PRODUCER.apply(tom);
        Commission tomElephantCommission = ELEPHANT_PRODUCER.apply(tom);
        tom.addCommission(tomCatCommission);
        tom.addCommission(tomElephantCommission);

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addCustomer(tom);
        model.selectCustomer(tom);
        model.selectCommission(tomCatCommission);
        return model;
    }
}
