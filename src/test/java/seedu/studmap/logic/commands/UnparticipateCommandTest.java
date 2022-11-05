package seedu.studmap.logic.commands;

import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.util.HashSet;
import java.util.Set;

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
 * {@code UnparticipateCommand}.
 */
class UnparticipateCommandTest {

    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToUnmark = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        Participation participation = new Participation("P01", true);
        UnparticipateCommand unparticipateCommand = new UnparticipateCommand(
                new SingleIndexGenerator(INDEX_SECOND_STUDENT),
                new UnparticipateCommand.UnparticipateCommandStudentEditor(participation));

        Set<Participation> participationSet = new HashSet<>(studentToUnmark.getParticipations());
        participationSet.remove(participation);
        Student unmarkedStudent = new StudentBuilder(studentToUnmark).setParticipated(participationSet).build();

        String expectedMessage = String.format(UnparticipateCommand.MESSAGE_UNMARK_SINGLE_PARTICIPATION_SUCCESS,
                participation.participationComponent, unmarkedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList()
                                      .get(INDEX_SECOND_STUDENT.getZeroBased()), unmarkedStudent);
        assertCommandSuccess(unparticipateCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_SECOND_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(0);
        Participation participation = new Participation("P01", true);
        UnparticipateCommand unparticipateCommand = new UnparticipateCommand(
                new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new UnparticipateCommand.UnparticipateCommandStudentEditor(participation));

        Set<Participation> participationSet = new HashSet<>(studentInFilteredList.getParticipations());
        participationSet.remove(participation);
        Student unmarkedStudent = new StudentBuilder(studentInFilteredList).setParticipated(participationSet).build();

        String expectedMessage = String.format(UnparticipateCommand.MESSAGE_UNMARK_SINGLE_PARTICIPATION_SUCCESS,
                participation.participationComponent, unmarkedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_SECOND_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), unmarkedStudent);
        assertCommandSuccess(unparticipateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Participation participation = new Participation("T04", true);
        UnparticipateCommand unparticipateCommand = new UnparticipateCommand(new SingleIndexGenerator(outOfBoundIndex),
                new UnparticipateCommand.UnparticipateCommandStudentEditor(participation));

        assertCommandFailure(unparticipateCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
