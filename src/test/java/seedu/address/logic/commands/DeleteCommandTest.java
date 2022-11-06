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
import static seedu.address.testutil.TypicalNextOfKins.NEXTOFKIN1;
import static seedu.address.testutil.TypicalNextOfKins.NEXTOFKIN2;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS2;
import static seedu.address.testutil.TypicalTuitionClasses.getTypicalTuitionClassesAddressBook;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorsAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model studentModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
    private Model tutorModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
    private Model tuitionClassModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());

    @Test
    public void executeForStudent_validIndexUnfilteredList_success() {
        Student studentToDelete = studentModel.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(studentModel.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(studentToDelete);

        assertCommandSuccess(deleteCommand, studentModel, expectedMessage, expectedModel, studentToDelete);
    }

    @Test
    public void executeForStudent_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(studentModel.getFilteredStudentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, studentModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForStudent_validIndexFilteredList_success() {
        showStudentAtIndex(studentModel, INDEX_FIRST_PERSON);

        Student studentToDelete = studentModel.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, studentToDelete);

        Model expectedModel = new ModelManager(studentModel.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(studentToDelete);
        showNoEntity(expectedModel);

        assertCommandSuccess(deleteCommand, studentModel, expectedMessage, expectedModel, studentToDelete);
    }

    @Test
    public void executeForStudent_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(studentModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < studentModel.getAddressBook().getStudentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, studentModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTutor_validIndexUnfilteredList_success() {
        Tutor tutorToDelete = tutorModel.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, tutorToDelete);

        ModelManager expectedModel = new ModelManager(tutorModel.getAddressBook(), new UserPrefs());
        tutorModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.deletePerson(tutorToDelete);

        assertCommandSuccess(deleteCommand, tutorModel, expectedMessage, expectedModel, tutorToDelete);
    }

    @Test
    public void executeForTutor_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(tutorModel.getFilteredTutorList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, tutorModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTutor_validIndexFilteredList_success() {
        showTutorAtIndex(tutorModel, INDEX_FIRST_PERSON);

        Tutor tutorToDelete = tutorModel.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, tutorToDelete);

        Model expectedModel = new ModelManager(tutorModel.getAddressBook(), new UserPrefs());
        tutorModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.deletePerson(tutorToDelete);
        showNoEntity(expectedModel);

        assertCommandSuccess(deleteCommand, tutorModel, expectedMessage, expectedModel, tutorToDelete);
    }

    @Test
    public void executeForTutor_invalidIndexFilteredList_throwsCommandException() {
        showTutorAtIndex(tutorModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < tutorModel.getAddressBook().getTutorList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, tutorModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTuitionClass_validIndexUnfilteredList_success() {
        TuitionClass tuitionClassToDelete = tuitionClassModel.getFilteredTuitionClassList().get(
                INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, tuitionClassToDelete);

        ModelManager expectedModel = new ModelManager(tuitionClassModel.getAddressBook(), new UserPrefs());
        tuitionClassModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel.deleteTuitionClass(tuitionClassToDelete);

        assertCommandSuccess(deleteCommand, tuitionClassModel, expectedMessage, expectedModel, tuitionClassToDelete);
    }

    @Test
    public void executeForTuitionClass_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(tuitionClassModel.getFilteredTuitionClassList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, tuitionClassModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTuitionClass_validIndexFilteredList_success() {
        showTuitionClassAtIndex(tuitionClassModel, INDEX_FIRST_PERSON);

        TuitionClass tuitionClassToDelete = tuitionClassModel.getFilteredTuitionClassList().get(
                INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, tuitionClassToDelete);

        Model expectedModel = new ModelManager(tuitionClassModel.getAddressBook(), new UserPrefs());
        tuitionClassModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel.deleteTuitionClass(tuitionClassToDelete);
        showNoEntity(expectedModel);

        assertCommandSuccess(deleteCommand, tuitionClassModel, expectedMessage, expectedModel, tuitionClassToDelete);
    }

    @Test
    public void executeForTuitionClass_invalidIndexFilteredList_throwsCommandException() {
        showTuitionClassAtIndex(tuitionClassModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < tuitionClassModel.getAddressBook().getTuitionClassList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, tuitionClassModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_existingPersonClassList_success() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        List<TuitionClass> classList = new ArrayList<>();
        classList.add(TUITIONCLASS1);
        classList.add(TUITIONCLASS2);
        model.setTuitionClasses(classList);


        AddressBook expectedAddressBook = new AddressBook();
        expectedAddressBook.addPerson(new StudentBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253")
                .withTags("friends")
                .withSchool("Keming Primary School")
                .withLevel("PRIMARY3")
                .withNextOfKin(NEXTOFKIN1)
                .withTuitionClasses(TUITIONCLASS2)
                .build());
        expectedAddressBook.addPerson(new StudentBuilder().withName("Benson Meier")
                .withAddress("311, Clementi Ave 2, #02-25")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withTags("owesMoney", "friends")
                .withSchool("Zheng Hua Secondary School")
                .withLevel("SECONDARY2")
                .withNextOfKin(NEXTOFKIN2)
                .withTuitionClasses(TUITIONCLASS2)
                .build());

        Model expectedModel = new ModelManager(expectedAddressBook, new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        List<TuitionClass> expectedClassList = new ArrayList<>();
        expectedClassList.add(TUITIONCLASS2);
        expectedModel.setTuitionClasses(expectedClassList);

        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ENTITY_SUCCESS, TUITIONCLASS1);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, TUITIONCLASS1);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEntity(Model model) {
        model.updateFilteredStudentList(s -> false);
        model.updateFilteredTutorList(t -> false);
        model.updateFilteredTuitionClassList(c -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
        assertTrue(model.getFilteredTutorList().isEmpty());
        assertTrue(model.getFilteredTuitionClassList().isEmpty());

    }
}
