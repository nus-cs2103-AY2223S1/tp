package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.logic.commands.UnhelpStuCommand.MESSAGE_UNHELP_STU_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class UnhelpStuCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToBeMarked = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToBeMarked)
                .withHelpTag(false)
                .build();
        UnhelpStuCommand unhelpStuCommand = new UnhelpStuCommand(INDEX_SECOND_STUDENT);

        String expectedMessage = String.format(MESSAGE_UNHELP_STU_SUCCESS, editedStudent);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(1), editedStudent);

        assertCommandSuccess(unhelpStuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        UnhelpStuCommand unhelpStuCommand = new UnhelpStuCommand(outOfBoundIndex);

        assertCommandFailure(unhelpStuCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_SECOND_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withHelpTag(false).build();
        UnhelpStuCommand unhelpStuCommand = new UnhelpStuCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(MESSAGE_UNHELP_STU_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(unhelpStuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        UnhelpStuCommand unhelpStuCommand = new UnhelpStuCommand(outOfBoundIndex);

        assertCommandFailure(unhelpStuCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
