package seedu.studmap.logic.commands;

import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.student.Participation;
import seedu.studmap.model.student.Student;
import seedu.studmap.testutil.StudentBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ParticipateCommand}.
 */
class ParticipateCommandTest {

    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToMark = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Participation participation = new Participation("P04", true);
        ParticipateCommand participateCommand = new ParticipateCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new ParticipateCommand.ParticipateCommandStudentEditor(participation));

        Student markedStudent = new StudentBuilder(studentToMark).addParticipated("P04").build();

        String expectedMessage = String.format(ParticipateCommand.MESSAGE_MARK_SINGLE_SUCCESS_PARTICIPATION,
                participation.getParticipationString(), markedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);
        assertCommandSuccess(participateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Participation participation = new Participation("P04", true);
        ParticipateCommand participateCommand = new ParticipateCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new ParticipateCommand.ParticipateCommandStudentEditor(participation));

        Student markedStudent = new StudentBuilder(studentInFilteredList).addParticipated("P04").build();

        String expectedMessage = String.format(ParticipateCommand.MESSAGE_MARK_SINGLE_SUCCESS_PARTICIPATION,
                participation.getParticipationString(), markedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);
        assertCommandSuccess(participateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Participation participation = new Participation("P04", true);
        ParticipateCommand participateCommand = new ParticipateCommand(new SingleIndexGenerator(outOfBoundIndex),
                new ParticipateCommand.ParticipateCommandStudentEditor(participation));

        assertCommandFailure(participateCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
