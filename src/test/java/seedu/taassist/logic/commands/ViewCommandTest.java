package seedu.taassist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.student.Student;

class ViewCommandTest {

    @Test
    public void execute_notInFocusMode_throwsCommandException() {
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_STUDENT);
        ModelStubNotInFocusMode modelStub = new ModelStubNotInFocusMode();
        String expectedMessage = String.format(Messages.MESSAGE_NOT_IN_FOCUS_MODE, ViewCommand.COMMAND_WORD);
        assertThrows(CommandException.class, expectedMessage, () -> viewCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ViewCommand viewCommand = new ViewCommand(INDEX_THIRD_STUDENT);
        ModelStubOneStudentWithGrades modelStub = new ModelStubOneStudentWithGrades();
        assertThrows(CommandException.class,
            Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, () -> viewCommand.execute(modelStub));
    }

    @Test
    public void execute_noGrades_success() {
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_STUDENT);
        ModelStubOneStudentNoGrades modelStub = new ModelStubOneStudentNoGrades();
        String expectedMessage = String.format(ViewCommand.MESSAGE_EMPTY_GRADES_LIST, ALICE.getName());
        ModelStub expectedModelStub = new ModelStubOneStudentNoGrades();
        assertCommandSuccess(viewCommand, modelStub, expectedMessage, modelStub); // no change in model
    }

    @Test
    public void execute_hasGrades_success() throws CommandException {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
        ModelStubOneStudentWithGrades modelStub = new ModelStubOneStudentWithGrades();
        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS, ALICE.getName()) + "\n"
            + "1. " + ASSIGNMENT_1.getSessionName() + ": 50.0" + "\n"
            + "2. " + LAB_1.getSessionName() + ": 100.0";
        ModelStubOneStudentWithGrades expectedModel = new ModelStubOneStudentWithGrades();
        assertCommandSuccess(viewCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_STUDENT);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_THIRD_STUDENT);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same index -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_STUDENT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    /**
     * A model stub not in focus mode.
     */
    private class ModelStubNotInFocusMode extends ModelStub {
        @Override
        public boolean isInFocusMode() {
            return false;
        }
    }

    /**
     * A model stub with one student but no grades.
     */
    private class ModelStubOneStudentNoGrades extends ModelStub {

        private final ModuleClass focusedClass = CS1231S.addSession(LAB_1);

        // Assumption of the view command is that the student is in the focused class.
        // ALICE is in CS1231S class.
        private final ObservableList<Student> students = FXCollections.observableArrayList(
                ALICE.addModuleClass(CS1231S)
        );

        @Override
        public boolean isInFocusMode() {
            return true;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return focusedClass;
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return students;
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                || (other instanceof ModelStubOneStudentNoGrades // instanceof handles nulls
                && students.equals(((ModelStubOneStudentNoGrades) other).students));
        }
    }

    /**
     * A model stub with a student in a focus mode, having two graded sessions.
     */
    private static class ModelStubOneStudentWithGrades extends ModelStub {

        private final ModuleClass focusedClass = CS1231S.addSession(LAB_1).addSession(ASSIGNMENT_1);

        // Assumption of the view command is that the student is in the focused class.
        private final ObservableList<Student> students = FXCollections.observableArrayList(
            ALICE.addModuleClass(CS1231S)
                .updateGrade(CS1231S, ASSIGNMENT_1, 50)
                .updateGrade(CS1231S, LAB_1, 100)
        );

        @Override
        public boolean isInFocusMode() {
            return true;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return focusedClass;
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return students;
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                || (other instanceof ModelStubOneStudentWithGrades // instanceof handles nulls
                && students.equals(((ModelStubOneStudentWithGrades) other).students));
        }
    }
}
