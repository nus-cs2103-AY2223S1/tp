package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.testutil.TypicalLessons.getTypicalLessonBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.model.Consult;
import jarvis.model.Lesson;
import jarvis.model.MasteryCheck;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddParticipationCommand}.
 */
public class AddParticipationCommandTest {

    private static final Index studentIndex = Index.fromZeroBased(0);
    private static final int participation = 100;

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());
    private Index studioIndex;
    private Index consultIndex;
    private Index mcIndex;

    @BeforeEach
    public void setUp() {
        List<Lesson> lessonList = model.getFilteredLessonList();
        for (int i = 0; i < lessonList.size(); i++) {
            Lesson lesson = lessonList.get(i);
            if (lesson instanceof Studio) {
                studioIndex = Index.fromZeroBased(i);
            } else if (lesson instanceof Consult) {
                consultIndex = Index.fromZeroBased(i);
            } else if (lesson instanceof MasteryCheck) {
                mcIndex = Index.fromZeroBased(i);
            }
        }
    }

    @Test
    public void execute_participationWithValidIndex_success() throws Exception {
        Studio studioToAdd = (Studio) model.getFilteredLessonList().get(studioIndex.getZeroBased());
        Student studentToAdd = studioToAdd.getStudentList().get(0);

        CommandResult commandResult = new AddParticipationCommand(participation, studioIndex, studentIndex)
                .execute(model);

        assertEquals(String.format(AddParticipationCommand.MESSAGE_ADD_PARTICIPATION_SUCCESS, studentToAdd, studioToAdd,
                participation), commandResult.getFeedbackToUser());
        assertEquals(participation, studioToAdd.getParticipationForStudent(studentToAdd));
    }

    @Test
    public void execute_overallNotesWithInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        int studentCount = model.getFilteredLessonList().get(studioIndex.getZeroBased()).getStudentList().size();
        assertCommandFailure(
                new AddParticipationCommand(participation, Index.fromZeroBased(lessonCount), studentIndex), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index
        assertCommandFailure(
                new AddParticipationCommand(participation, studioIndex, Index.fromZeroBased(studentCount)), model,
                Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX); // invalid student index
    }

    @Test
    public void execute_overallNotesWithInvalidLessonType_throwsCommandException() {
        assertCommandFailure(
                new AddParticipationCommand(participation, consultIndex, studentIndex), model,
                Messages.MESSAGE_INVALID_LESSON_TYPE); // adding participation to a Consult lesson
        assertCommandFailure(
                new AddParticipationCommand(participation, mcIndex, studentIndex), model,
                Messages.MESSAGE_INVALID_LESSON_TYPE); // adding participation to a MasteryCheck lesson
    }

    @Test
    public void equals() {
        AddParticipationCommand addParticipationCommand = new AddParticipationCommand(participation, studioIndex,
                studentIndex);
        AddParticipationCommand addParticipationCommandDifferentParticipation = new AddParticipationCommand(200,
                studioIndex, studentIndex);
        AddParticipationCommand addParticipationCommandDifferentLesson = new AddParticipationCommand(participation,
                consultIndex, studentIndex);
        AddParticipationCommand addParticipationCommandDifferentStudent = new AddParticipationCommand(participation,
                studioIndex, Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(addParticipationCommand.equals(addParticipationCommand));

        // different types -> returns false
        assertFalse(addParticipationCommand.equals(1));

        // null -> returns false
        assertFalse(addParticipationCommand.equals(null));

        // same values -> returns true
        AddParticipationCommand addParticipationCommandCopy = new AddParticipationCommand(participation, studioIndex,
                studentIndex);
        assertTrue(addParticipationCommand.equals(addParticipationCommandCopy));

        // different participation -> returns false
        assertFalse(addParticipationCommand.equals(addParticipationCommandDifferentParticipation));

        // different lesson -> returns false
        assertFalse(addParticipationCommand.equals(addParticipationCommandDifferentLesson));

        // different student -> returns false
        assertFalse(addParticipationCommand.equals(addParticipationCommandDifferentStudent));

    }
}
