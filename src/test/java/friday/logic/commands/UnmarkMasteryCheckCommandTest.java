package friday.logic.commands;

import static friday.logic.commands.CommandTestUtil.assertCommandFailure;
import static friday.logic.commands.CommandTestUtil.assertCommandSuccess;

import static friday.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static friday.testutil.TypicalIndexes.INDEX_FOURTH_STUDENT;
import static friday.testutil.TypicalIndexes.INDEX_SEVENTH_STUDENT;
import static friday.testutil.TypicalStudents.getTypicalFriday;

import org.junit.jupiter.api.Test;

import friday.model.Model;
import friday.model.ModelManager;
import friday.model.UserPrefs;
import friday.model.student.Student;
import friday.testutil.StudentBuilder;

import java.time.LocalDate;

public class UnmarkMasteryCheckCommandTest {

    /**
     * Students 1 to 3 in this list do not have their Mastery Check already marked as passed, while the Students 4 to 6
     * do, and Student 7 has an empty Mastery Check.
     */
    private Model model = new ModelManager(getTypicalFriday(), new UserPrefs());

    @Test
    public void execute_studentNotMarked_throwsCommandException() {
        Student firstStudent = model.getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        UnmarkMasteryCheckCommand unmarkCommand = new UnmarkMasteryCheckCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(UnmarkMasteryCheckCommand.MESSAGE_NOT_MARKED, firstStudent.getName());

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_emptyMasteryCheck_throwsCommandException() {
        Student seventhStudent = model.getStudentList().get(INDEX_SEVENTH_STUDENT.getZeroBased());
        UnmarkMasteryCheckCommand unmarkCommand = new UnmarkMasteryCheckCommand(INDEX_SEVENTH_STUDENT);

        String expectedMessage = seventhStudent.getName() + UnmarkMasteryCheckCommand.MESSAGE_EMPTY_MASTERYCHECK;

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_studentIsMarked_success() {
        Student fourthStudent = model.getStudentList().get(INDEX_FOURTH_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(fourthStudent)
                .withMasteryCheck(LocalDate.of(2022, 4, 2), false).build();
        UnmarkMasteryCheckCommand unmarkCommand = new UnmarkMasteryCheckCommand(INDEX_FOURTH_STUDENT);

        String expectedMessage = String.format(UnmarkMasteryCheckCommand.MESSAGE_SUCCESS, editedStudent.getName());

        ModelManager expectedModel = new ModelManager(model.getFriday(), new UserPrefs());
        expectedModel.setStudent(model.getStudentList().get(3), editedStudent);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }
}
