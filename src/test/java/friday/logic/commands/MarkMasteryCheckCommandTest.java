package friday.logic.commands;

import static friday.logic.commands.CommandTestUtil.assertCommandFailure;
import static friday.logic.commands.CommandTestUtil.assertCommandSuccess;

import static friday.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static friday.testutil.TypicalIndexes.INDEX_FOURTH_STUDENT;
import static friday.testutil.TypicalIndexes.INDEX_SEVENTH_STUDENT;
import static friday.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static friday.testutil.TypicalStudents.getTypicalFriday;

import org.junit.jupiter.api.Test;

import friday.model.Model;
import friday.model.ModelManager;
import friday.model.UserPrefs;
import friday.model.student.Student;
import friday.testutil.StudentBuilder;

import java.time.LocalDate;

public class MarkMasteryCheckCommandTest {

    /**
     * Students 1 to 3 in this list do not have their Mastery Check already marked as passed, while the Students 4 to 6
     * do, and Student 7 has an empty Mastery Check.
     */
    private Model model = new ModelManager(getTypicalFriday(), new UserPrefs());

    @Test
    public void execute_studentAlreadyMarked_throwsCommandException() {
        Student fourthStudent = model.getStudentList().get(INDEX_FOURTH_STUDENT.getZeroBased());
        MarkMasteryCheckCommand markCommand = new MarkMasteryCheckCommand(INDEX_FOURTH_STUDENT);

        String expectedMessage = fourthStudent.getName() + MarkMasteryCheckCommand.MESSAGE_ALREADY_MARKED;

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_emptyMasteryCheck_throwsCommandException() {
        Student seventhStudent = model.getStudentList().get(INDEX_SEVENTH_STUDENT.getZeroBased());
        MarkMasteryCheckCommand markCommand = new MarkMasteryCheckCommand(INDEX_SEVENTH_STUDENT);

        String expectedMessage = seventhStudent.getName() + MarkMasteryCheckCommand.MESSAGE_EMPTY_MASTERYCHECK;

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_dateNotReached_throwsCommandException() {
        Student thirdStudent = model.getStudentList().get(INDEX_THIRD_STUDENT.getZeroBased());
        MarkMasteryCheckCommand markCommand = new MarkMasteryCheckCommand(INDEX_THIRD_STUDENT);

        String expectedMessage = thirdStudent.getName() + String.format(MarkMasteryCheckCommand.MESSAGE_CANNOT_PASS,
                thirdStudent.getMasteryCheck().getValue(), LocalDate.now());

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_studentNotMarked_success() {
        Student firstStudent = model.getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent)
                .withMasteryCheck(LocalDate.of(2022, 2, 17), true).build();
        MarkMasteryCheckCommand markCommand = new MarkMasteryCheckCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(MarkMasteryCheckCommand.MESSAGE_SUCCESS, editedStudent.getName());

        ModelManager expectedModel = new ModelManager(model.getFriday(), new UserPrefs());
        expectedModel.setStudent(model.getStudentList().get(0), editedStudent);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }
}
