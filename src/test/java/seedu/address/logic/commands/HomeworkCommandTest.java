package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOMEWORK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOMEWORK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Homework;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class HomeworkCommandTest {

    private static final String HOMEWORK_STUB = "Some homework";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addHomeworkUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson).withHomework(HOMEWORK_STUB).build();
        secondPerson.clearHomeworkList();

        HomeworkCommand homeworkCommand = new HomeworkCommand(INDEX_SECOND_PERSON,
                new Homework(HOMEWORK_STUB));

        String expectedMessage = String.format(HomeworkCommand.MESSAGE_ADD_HOMEWORK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(homeworkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addHomeworkFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withHomework(HOMEWORK_STUB).build();
        personInFilteredList.clearHomeworkList();

        HomeworkCommand homeworkCommand = new HomeworkCommand(INDEX_FIRST_PERSON,
                new Homework(HOMEWORK_STUB));

        String expectedMessage = String.format(HomeworkCommand.MESSAGE_ADD_HOMEWORK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(homeworkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withHomework(HOMEWORK_STUB).build();
        firstPerson.clearHomeworkList();

        HomeworkCommand homeworkCommand = new HomeworkCommand(
                INDEX_FIRST_PERSON, new Homework(HOMEWORK_STUB));

        String expectedMessage = String.format(HomeworkCommand.MESSAGE_ADD_HOMEWORK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(homeworkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        HomeworkCommand homeworkCommand = new HomeworkCommand(
                outOfBoundIndex, new Homework(HOMEWORK_STUB));

        assertCommandFailure(homeworkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        HomeworkCommand homeworkCommand = new HomeworkCommand(
                outOfBoundIndex, new Homework(HOMEWORK_STUB));
        assertCommandFailure(homeworkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_inDayView_failure() {
        model.setDayView();
        HomeworkCommand homeworkCommand = new HomeworkCommand(
                INDEX_FIRST_PERSON, new Homework(HOMEWORK_STUB));

        assertCommandFailure(homeworkCommand, model, HomeworkCommand.MESSAGE_IN_DAY_MODE);
    }

    @Test
    public void equals() {
        final HomeworkCommand standardCommand = new HomeworkCommand(INDEX_FIRST_PERSON,
                new Homework(VALID_HOMEWORK_AMY));
        // same values -> returns true
        HomeworkCommand commandWithSameValues = new HomeworkCommand(INDEX_FIRST_PERSON,
                new Homework(VALID_HOMEWORK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new HomeworkCommand(INDEX_SECOND_PERSON,
                new Homework(VALID_HOMEWORK_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new HomeworkCommand(INDEX_FIRST_PERSON,
                new Homework(VALID_HOMEWORK_BOB))));
    }
}
