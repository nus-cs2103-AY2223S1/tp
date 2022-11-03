package seedu.taassist.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalSessions.ASSIGNMENT_1;
import static seedu.taassist.testutil.TypicalSessions.LAB_1;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.BENSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.stubs.ModelStub;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.uniquelist.exceptions.ElementNotFoundException;

class GradeCommandTest {

    @Test
    public void execute_notInFocusMode_throwsCommandException() {
        GradeCommand gradeCommand = new GradeCommand(List.of(INDEX_FIRST_STUDENT), LAB_1, 100);
        ModelStubNotInFocusMode modelStub = new ModelStubNotInFocusMode();
        String expectedMessage = String.format(Messages.MESSAGE_NOT_IN_FOCUS_MODE, GradeCommand.COMMAND_WORD);
        assertThrows(CommandException.class, expectedMessage, () -> gradeCommand.execute(modelStub));
    }

    @Test
    public void execute_focusedClassDoesntHaveSession_throwsCommandException() {
        GradeCommand gradeCommand = new GradeCommand(List.of(INDEX_FIRST_STUDENT), LAB_1, 100);
        ModelStubFocusedClassNoSession modelStub = new ModelStubFocusedClassNoSession();
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_SESSION,
                LAB_1.getSessionName(), modelStub.getFocusedClass());
        assertThrows(CommandException.class, expectedMessage, () -> gradeCommand.execute(modelStub));
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        GradeCommand gradeCommand = new GradeCommand(List.of(INDEX_SECOND_STUDENT), LAB_1, 100);
        ModelStubOneStudentNoGrades modelStub = new ModelStubOneStudentNoGrades();
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, () -> gradeCommand.execute(modelStub));
    }

    @Test
    public void execute_oneStudentNewGrade_addsGrade() throws CommandException {
        GradeCommand gradeCommand = new GradeCommand(List.of(INDEX_FIRST_STUDENT), LAB_1, 100);
        ModelStubOneStudentNoGrades modelStub = new ModelStubOneStudentNoGrades();

        Student expectedStudent = ALICE.updateGrade(modelStub.getFocusedClass(), LAB_1, 100);
        String expectedMessage = String.format(GradeCommand.MESSAGE_SUCCESS,
                "100.0", LAB_1.getSessionName(), expectedStudent.getName());

        CommandResult commandResult = gradeCommand.execute(modelStub);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedStudent, modelStub.getFilteredStudentList().get(0));
    }

    @Test
    public void execute_oneStudentExistingGrade_updatesGrade() throws CommandException {
        GradeCommand gradeCommand = new GradeCommand(List.of(INDEX_FIRST_STUDENT), ASSIGNMENT_1, 100);
        ModelStubOneStudentWithGrades modelStub = new ModelStubOneStudentWithGrades();

        Student expectedStudent = modelStub.students.get(0)
                .updateGrade(modelStub.getFocusedClass(), ASSIGNMENT_1, 100);
        String expectedMessage = String.format(GradeCommand.MESSAGE_SUCCESS,
                "100.0", ASSIGNMENT_1.getSessionName(), expectedStudent.getName());

        CommandResult commandResult = gradeCommand.execute(modelStub);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedStudent, modelStub.students.get(0));
    }

    @Test
    public void execute_multipleStudentsUpdateGrade_updatesGrade() throws CommandException {
        GradeCommand gradeCommand = new GradeCommand(
                List.of(INDEX_FIRST_STUDENT, INDEX_SECOND_STUDENT), LAB_1, 100);
        ModelStubTwoStudentsWithGrades modelStub = new ModelStubTwoStudentsWithGrades();

        Student expectedStudent1 = ALICE.updateGrade(modelStub.getFocusedClass(), LAB_1, 100);
        Student expectedStudent2 = BENSON.updateGrade(modelStub.getFocusedClass(), LAB_1, 100);

        String expectedMessage = String.format(GradeCommand.MESSAGE_SUCCESS,
                "100.0", LAB_1.getSessionName(), expectedStudent1.getName() + ", " + expectedStudent2.getName());

        CommandResult commandResult = gradeCommand.execute(modelStub);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedStudent1, modelStub.students.get(0));
        assertEquals(expectedStudent2, modelStub.students.get(1));
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
     * A model stub with a focused class but no sessions.
     */
    private class ModelStubFocusedClassNoSession extends ModelStub {

        private final ModuleClass focusedClass = CS1231S;

        @Override
        public boolean isInFocusMode() {
            return true;
        }

        @Override
        public ModuleClass getFocusedClass() {
            return focusedClass;
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
        public void setStudent(Student oldStudent, Student newStudent) {
            if (!students.get(0).isSame(oldStudent)) {
                throw new ElementNotFoundException();
            }
            students.set(0, newStudent);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                || (other instanceof ModelStubOneStudentNoGrades // instanceof handles nulls
                && students.equals(((ModelStubOneStudentNoGrades) other).students));
        }
    }

    /**
     * A model stub with one student in a focus mode, having two graded sessions.
     */
    private class ModelStubOneStudentWithGrades extends ModelStub {

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
        public void setStudent(Student oldStudent, Student newStudent) {
            if (!students.get(0).isSame(oldStudent)) {
                throw new ElementNotFoundException();
            }
            students.set(0, newStudent);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                || (other instanceof ModelStubOneStudentWithGrades // instanceof handles nulls
                && students.equals(((ModelStubOneStudentWithGrades) other).students));
        }
    }

    /**
     * A module stub with two students, each having a graded session. The students are in the same class.
     */
    private class ModelStubTwoStudentsWithGrades extends ModelStub {

        private final ModuleClass focusedClass = CS1231S.addSession(LAB_1);

        // Assumption of the view command is that the student is in the focused class.
        private final ObservableList<Student> students = FXCollections.observableArrayList(
            ALICE.addModuleClass(CS1231S).updateGrade(CS1231S, LAB_1, 50),
            BENSON.addModuleClass(CS1231S).updateGrade(CS1231S, LAB_1, 50)
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
        public void setStudent(Student oldStudent, Student newStudent) {
            if (!students.get(0).isSame(oldStudent) && !students.get(1).isSame(oldStudent)) {
                throw new ElementNotFoundException();
            }
            if (students.get(0).isSame(oldStudent)) {
                students.set(0, newStudent);
            } else {
                students.set(1, newStudent);
            }
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                || (other instanceof ModelStubTwoStudentsWithGrades // instanceof handles nulls
                && students.equals(((ModelStubTwoStudentsWithGrades) other).students));
        }
    }
}
