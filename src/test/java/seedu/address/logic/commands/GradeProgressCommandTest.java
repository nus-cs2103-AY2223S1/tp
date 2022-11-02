package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_LARGE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class GradeProgressCommandTest {

    private static final String GRADE_PROGRESS_STUB = "MATH: A";

    private static final String GRADE_PROGRESS_STUB_2 = "SCI: B";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addGradeProgressUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson).withGradeProgress(GRADE_PROGRESS_STUB).build();
        secondPerson.clearGradeProgressList();

        GradeProgressCommand gradeProgressCommand = new GradeProgressCommand(INDEX_SECOND_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB));

        String expectedMessage = String.format(GradeProgressCommand.MESSAGE_ADD_GRADE_PROGRESS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(gradeProgressCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addGradeProgressFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withGradeProgress(GRADE_PROGRESS_STUB).build();
        personInFilteredList.clearGradeProgressList();
        GradeProgressCommand gradeProgressCommand = new GradeProgressCommand(INDEX_FIRST_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB));

        String expectedMessage = String.format(GradeProgressCommand.MESSAGE_ADD_GRADE_PROGRESS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(gradeProgressCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_inDayView_failure() {
        model.setDayView();
        GradeProgressCommand gradeProgressCommand = new GradeProgressCommand(INDEX_FIRST_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB));

        assertCommandFailure(gradeProgressCommand, model, GradeProgressCommand.MESSAGE_IN_DAY_MODE);
    }

    @Test
    public void execute_index_failure() {
        GradeProgressCommand gradeProgressCommand = new GradeProgressCommand(INDEX_LARGE,
                new GradeProgress(GRADE_PROGRESS_STUB));
        assertCommandFailure(gradeProgressCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals_test() {
        GradeProgressCommand command = new GradeProgressCommand(INDEX_FIRST_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB));
        GradeProgressCommand sameCommand =  new GradeProgressCommand(INDEX_FIRST_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB));

        GradeProgressCommand diffIndexCommand = new GradeProgressCommand(INDEX_SECOND_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB));

        GradeProgressCommand diffGradeCommand = new GradeProgressCommand(INDEX_FIRST_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB_2));
        // For same commands
        assertEquals(command, command);

        // For commands with same inputs
        assertEquals(command,sameCommand);

        // For different commands
        assertNotEquals(command, new ListCommand());

        // For command of diff Index and grade
        assertNotEquals(command, diffGradeCommand);
        assertNotEquals(command, diffIndexCommand);
    }
}
