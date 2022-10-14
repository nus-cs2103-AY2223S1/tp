package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.HomeworkTestTypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Duration;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class DurationCommandTest {

    private static final String DURATION_STUB = "08:30-09:00";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addDurationUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson).withDuration(DURATION_STUB).build();
        secondPerson.clearDurationList();

        DurationCommand durationCommand = new DurationCommand(INDEX_SECOND_PERSON,
                new Duration(DURATION_STUB));

        String expectedMessage = String.format(DurationCommand.MESSAGE_ADD_DURATION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(durationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addDurationFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withDuration(DURATION_STUB).build();
        personInFilteredList.clearDurationList();

        DurationCommand durationCommand = new DurationCommand(INDEX_FIRST_PERSON,
                new Duration(DURATION_STUB));

        String expectedMessage = String.format(DurationCommand.MESSAGE_ADD_DURATION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(durationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withDuration(DURATION_STUB).build();
        firstPerson.clearDurationList();

        DurationCommand durationCommand = new DurationCommand(
                INDEX_FIRST_PERSON, new Duration(DURATION_STUB));

        String expectedMessage = String.format(DurationCommand.MESSAGE_ADD_DURATION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(durationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DurationCommand durationCommand = new DurationCommand(
                outOfBoundIndex, new Duration(DURATION_STUB));

        assertCommandFailure(durationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        DurationCommand durationCommand = new DurationCommand(
                outOfBoundIndex, new Duration(DURATION_STUB));
        assertCommandFailure(durationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DurationCommand standardCommand = new DurationCommand(INDEX_FIRST_PERSON,
                new Duration(VALID_DURATION_AMY));
        // same values -> returns true
        DurationCommand commandWithSameValues = new DurationCommand(INDEX_FIRST_PERSON,
                new Duration(VALID_DURATION_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new DurationCommand(INDEX_SECOND_PERSON,
                new Duration(VALID_DURATION_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new DurationCommand(INDEX_FIRST_PERSON,
                new Duration(VALID_DURATION_BOB))));
    }
}
