package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InsuranceCommand.EditInsuranceDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditInsuranceDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class InsuranceCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        InsuranceCommand insuranceCommand = new InsuranceCommand(INDEX_FIRST_PERSON,
                new InsuranceCommand.EditInsuranceDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(InsuranceCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(insuranceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withHealthInsurance(true).build();
        InsuranceCommand insuranceCommand = new InsuranceCommand(INDEX_FIRST_PERSON,
                new EditInsuranceDescriptorBuilder().withHealthInsurance(true).build());

        String expectedMessage = String.format(InsuranceCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(insuranceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        InsuranceCommand.EditInsuranceDescriptor descriptor = new EditInsuranceDescriptorBuilder()
                .withHealthInsurance(true).build();
        InsuranceCommand insuranceCommand = new InsuranceCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(insuranceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        InsuranceCommand insuranceCommand = new InsuranceCommand(outOfBoundIndex,
                new EditInsuranceDescriptorBuilder().withLifeInsurance(false).build());

        assertCommandFailure(insuranceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final InsuranceCommand standardCommand = new InsuranceCommand(INDEX_FIRST_PERSON, DESC_AMY_INSURANCE);

        // same values -> returns true
        EditInsuranceDescriptor copyDescriptor = new EditInsuranceDescriptor(DESC_AMY_INSURANCE);
        InsuranceCommand commandWithSameValues = new InsuranceCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new InsuranceCommand(INDEX_SECOND_PERSON, DESC_AMY_INSURANCE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new InsuranceCommand(INDEX_FIRST_PERSON, DESC_BOB_INSURANCE)));
    }

}
