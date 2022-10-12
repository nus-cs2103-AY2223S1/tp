package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.ClassGroup;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

class ClassGroupCommandTest {

    private static final String CLASS_GROUP_STUB = "Some class";

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(), new UserPrefs());

    @Test
    public void execute_addClassGroupUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withClassGroup(CLASS_GROUP_STUB).build();

        ClassGroupCommand classGroupCommand =
                new ClassGroupCommand(INDEX_FIRST_STUDENT, new ClassGroup(editedStudent.getClassGroup().value));

        String expectedMessage = String.format(ClassGroupCommand.MESSAGE_ADD_CLASS_GROUP_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), model.getTaskBook(), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(classGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteClassGroupUnfilteredList_success() {
        Student firstPerson = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(firstPerson).withClassGroup("").build();

        ClassGroupCommand classGroupCommand = new ClassGroupCommand(INDEX_FIRST_STUDENT,
                new ClassGroup(editedPerson.getClassGroup().toString()));

        String expectedMessage = String.format(ClassGroupCommand.MESSAGE_DELETE_CLASS_GROUP_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), model.getTaskBook(), new UserPrefs());
        expectedModel.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(classGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student firstPerson = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()))
                .withClassGroup(CLASS_GROUP_STUB).build();

        ClassGroupCommand classGroupCommand =
                new ClassGroupCommand(INDEX_FIRST_STUDENT, new ClassGroup(editedPerson.getClassGroup().value));

        String expectedMessage = String.format(ClassGroupCommand.MESSAGE_ADD_CLASS_GROUP_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), model.getTaskBook(), new UserPrefs());
        expectedModel.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(classGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ClassGroupCommand classGroupCommand =
                new ClassGroupCommand(outOfBoundIndex, new ClassGroup(VALID_CLASS_GROUP_BOB));

        assertCommandFailure(classGroupCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        ClassGroupCommand classGroupCommand =
                new ClassGroupCommand(outOfBoundIndex, new ClassGroup(VALID_CLASS_GROUP_BOB));
        assertCommandFailure(classGroupCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }


    @Test
    void testEquals() {
        final ClassGroupCommand standardCommand = new ClassGroupCommand(INDEX_FIRST_STUDENT,
                new ClassGroup(VALID_CLASS_GROUP_AMY));

        // same values -> returns true
        ClassGroupCommand commandWithSameValues = new ClassGroupCommand(INDEX_FIRST_STUDENT,
                new ClassGroup(VALID_CLASS_GROUP_AMY));
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new ClassGroupCommand(INDEX_SECOND_STUDENT,
                new ClassGroup(VALID_CLASS_GROUP_AMY)));

        // different class group -> returns false
        ClassGroupCommand commandWithDifferentValues = new ClassGroupCommand(INDEX_FIRST_STUDENT,
                new ClassGroup(VALID_CLASS_GROUP_BOB));
        assertNotEquals(standardCommand, commandWithDifferentValues);
    }
}
