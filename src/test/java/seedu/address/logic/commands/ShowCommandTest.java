package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showTuitionClassAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showTutorAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;
import static seedu.address.testutil.TypicalTuitionClasses.getTypicalTuitionClassesAddressBook;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorsAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowCommand}.
 */
public class ShowCommandTest {

    private Model studentModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
    private Model tutorModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
    private Model tuitionClassModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());

    @Test
    public void executeForStudent_validIndexUnfilteredList_success() {
        Student studentToShow = studentModel.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ShowCommand.MESSAGE_DELETE_ENTITY_SUCCESS, studentToShow);

        ModelManager expectedModel = new ModelManager(studentModel.getAddressBook(), new UserPrefs());

        assertCommandSuccess(showCommand, studentModel, expectedMessage, expectedModel);
    }

    @Test
    public void executeForStudent_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(studentModel.getFilteredStudentList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, studentModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForStudent_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(studentModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < studentModel.getAddressBook().getStudentList().size());

        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, studentModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTutor_validIndexUnfilteredList_success() {
        Tutor tutorToShow = tutorModel.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ShowCommand.MESSAGE_DELETE_ENTITY_SUCCESS, tutorToShow);

        ModelManager expectedModel = new ModelManager(tutorModel.getAddressBook(), new UserPrefs());
        tutorModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);

        assertCommandSuccess(showCommand, tutorModel, expectedMessage, expectedModel);
    }

    @Test
    public void executeForTutor_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(tutorModel.getFilteredTutorList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, tutorModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTutor_invalidIndexFilteredList_throwsCommandException() {
        showTutorAtIndex(tutorModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < tutorModel.getAddressBook().getTutorList().size());

        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, tutorModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTuitionClass_validIndexUnfilteredList_success() {
        TuitionClass tuitionClassToShow = tuitionClassModel.getFilteredTuitionClassList().get(
                INDEX_FIRST_PERSON.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ShowCommand.MESSAGE_DELETE_ENTITY_SUCCESS, tuitionClassToShow);

        ModelManager expectedModel = new ModelManager(tuitionClassModel.getAddressBook(), new UserPrefs());
        tuitionClassModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        assertCommandSuccess(showCommand, tuitionClassModel, expectedMessage, expectedModel);
    }

    @Test
    public void executeForTuitionClass_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(tuitionClassModel.getFilteredTuitionClassList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, tuitionClassModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTuitionClass_invalidIndexFilteredList_throwsCommandException() {
        showTuitionClassAtIndex(tuitionClassModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < tuitionClassModel.getAddressBook().getTuitionClassList().size());

        ShowCommand showCommand = new ShowCommand(outOfBoundIndex);

        assertCommandFailure(showCommand, tuitionClassModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShowCommand showFirstCommand = new ShowCommand(INDEX_FIRST_PERSON);
        ShowCommand showSecondCommand = new ShowCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(INDEX_FIRST_PERSON);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEntity(Model model) {
        model.updateFilteredStudentList(s -> false);
        model.updateFilteredTutorList(t -> false);
        model.updateFilteredTuitionClassList(c -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
