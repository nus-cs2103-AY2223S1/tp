package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STUDENT_AMY_WITH_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STUDENT_BOB_WITH_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.student.StudentEnrollCommand.EditStudentDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.EnrollStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class StudentEnrollCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student student = new StudentBuilder().build();
        Student editedStudent = new StudentBuilder().withTutorialGroup("T03").build();
        EditStudentDescriptor descriptor = new EnrollStudentDescriptorBuilder().withTutorialGroup("T03").build();
        StudentEnrollCommand studentEnrollCommand = new StudentEnrollCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(StudentEnrollCommand.MESSAGE_ENROLL_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), student);
        // TODO: need to build tutorial group first
        //assertCommandSuccess(studentEnrollCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        StudentEnrollCommand enrollCommand = new StudentEnrollCommand(INDEX_FIRST_PERSON, new EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(StudentEnrollCommand.MESSAGE_ENROLL_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    }



    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EnrollStudentDescriptorBuilder()
                .withTutorialGroup(VALID_TUTORIAL_GROUP_BOB)
                .build();
        StudentEnrollCommand editCommand = new StudentEnrollCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        StudentEnrollCommand enrollCommand = new StudentEnrollCommand(outOfBoundIndex,
                new EnrollStudentDescriptorBuilder().withTutorialGroup(VALID_TUTORIAL_GROUP_BOB).build());

        assertCommandFailure(enrollCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final StudentEnrollCommand standardCommand = new StudentEnrollCommand(INDEX_FIRST_PERSON,
                DESC_STUDENT_AMY_WITH_TUTORIAL);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_STUDENT_AMY_WITH_TUTORIAL);
        StudentEnrollCommand commandWithSameValues = new StudentEnrollCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new StudentEnrollCommand(INDEX_SECOND_PERSON,
                DESC_STUDENT_AMY_WITH_TUTORIAL)));

        // different descriptor but same tutorial group -> returns true
        assertTrue(standardCommand.equals(new StudentEnrollCommand(INDEX_FIRST_PERSON,
                DESC_STUDENT_BOB_WITH_TUTORIAL)));
    }

}
