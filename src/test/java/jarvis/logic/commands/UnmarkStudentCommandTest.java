package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.testutil.TypicalLessons.getTypicalLessonBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.model.Lesson;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkStudentCommand}.
 */
public class UnmarkStudentCommandTest {

    private static final Index lessonIndex = Index.fromZeroBased(0);
    private static final Index studentIndex = Index.fromZeroBased(0);

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        Lesson lessonToMark = model.getFilteredLessonList().get(0);
        for (Student s: lessonToMark.getStudentList()) {
            lessonToMark.markAsPresent(s);
        }
    }

    @Test
    public void execute_withValidIndex_success() throws Exception {
        UnmarkStudentCommand unmarkStudentCommand = new UnmarkStudentCommand(lessonIndex, studentIndex);

        Lesson lessonToUnmark = model.getFilteredLessonList().get(0);
        Student studentToUnmark = lessonToUnmark.getStudentList().get(0);
        CommandResult commandResult = unmarkStudentCommand.execute(model);
        assertEquals(String.format(UnmarkStudentCommand.MESSAGE_MARK_STUDENT_SUCCESS, studentToUnmark, lessonToUnmark),
                commandResult.getFeedbackToUser());
        assertEquals(lessonToUnmark.isPresent(studentToUnmark), "Absent"); // student should be marked Absent

        commandResult = unmarkStudentCommand.execute(model);
        assertEquals(String.format(UnmarkStudentCommand.MESSAGE_MARK_STUDENT_SUCCESS, studentToUnmark, lessonToUnmark),
                commandResult.getFeedbackToUser());
        assertEquals(lessonToUnmark.isPresent(studentToUnmark), "Absent"); // student should remain Absent

    }

    @Test
    public void execute_withInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        assertCommandFailure(new UnmarkStudentCommand(Index.fromZeroBased(lessonCount), studentIndex), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index

        int studentCount = model.getFilteredLessonList().get(0).getStudentList().size();
        assertCommandFailure(new UnmarkStudentCommand(lessonIndex, Index.fromZeroBased(studentCount)), model,
                Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX); // invalid student index
    }

    @Test
    public void equals() {
        UnmarkStudentCommand unmarkStudentCommand = new UnmarkStudentCommand(lessonIndex, studentIndex);
        UnmarkStudentCommand unmarkStudentCommandDifferentLessonIndex = new UnmarkStudentCommand(Index.fromZeroBased(1),
                studentIndex);
        UnmarkStudentCommand unmarkStudentCommandDifferentStudentIndex = new UnmarkStudentCommand(lessonIndex,
                Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(unmarkStudentCommand.equals(unmarkStudentCommand));

        // different types -> returns false
        assertFalse(unmarkStudentCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkStudentCommand.equals(null));

        // same lesson -> returns true
        UnmarkStudentCommand unmarkStudentCommandCopy = new UnmarkStudentCommand(lessonIndex, studentIndex);
        assertTrue(unmarkStudentCommand.equals(unmarkStudentCommandCopy));

        // different lesson -> returns false
        assertFalse(unmarkStudentCommand.equals(unmarkStudentCommandDifferentLessonIndex));

        // different student -> returns false
        assertFalse(unmarkStudentCommand.equals(unmarkStudentCommandDifferentStudentIndex));
    }
}
