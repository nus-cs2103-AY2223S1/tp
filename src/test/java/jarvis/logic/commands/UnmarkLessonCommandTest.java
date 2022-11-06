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
import jarvis.model.Lesson;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.UserPrefs;
import jarvis.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkLessonCommand}.
 */
public class UnmarkLessonCommandTest {

    private static final Index lessonIndex = TypicalIndexes.INDEX_FIRST;
    private static final int lessonInt = lessonIndex.getZeroBased();

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        List<Lesson> lessonList = model.getFilteredLessonList();
        for (Lesson l: lessonList) {
            l.markAsCompleted();
        }
    }

    @Test
    public void execute_withValidIndex_success() throws Exception {
        UnmarkLessonCommand unmarkLessonCommand = new UnmarkLessonCommand(lessonIndex);

        Lesson lessonToUnmark = model.getFilteredLessonList().get(lessonInt);
        CommandResult commandResult = unmarkLessonCommand.execute(model);
        assertEquals(String.format(UnmarkLessonCommand.MESSAGE_MARK_LESSON_SUCCESS, lessonToUnmark),
                commandResult.getFeedbackToUser());
        assertFalse(lessonToUnmark.isCompleted()); // lesson should be marked not completed

        // mark everything as not completed, to ensure lesson at index 0 is marked not completed
        List<Lesson> lessonList = model.getFilteredLessonList();
        for (Lesson l: lessonList) {
            l.markAsNotCompleted();
        }
        lessonToUnmark = model.getFilteredLessonList().get(lessonInt);
        commandResult = unmarkLessonCommand.execute(model);
        assertEquals(String.format(UnmarkLessonCommand.MESSAGE_MARK_LESSON_SUCCESS, lessonToUnmark),
                commandResult.getFeedbackToUser());
        assertFalse(lessonToUnmark.isCompleted()); // lesson should remain not completed
    }

    @Test
    public void execute_withInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        assertCommandFailure(new UnmarkLessonCommand(Index.fromZeroBased(lessonCount)), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        assertCommandFailure(new UnmarkLessonCommand(Index.fromZeroBased(lessonCount + 100)), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnmarkLessonCommand unmarkLessonCommand = new UnmarkLessonCommand(lessonIndex);
        UnmarkLessonCommand unmarkLessonCommandDifferentIndex = new UnmarkLessonCommand(
                Index.fromZeroBased(lessonInt + 1));

        // same object -> returns true
        assertTrue(unmarkLessonCommand.equals(unmarkLessonCommand));

        // different types -> returns false
        assertFalse(unmarkLessonCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkLessonCommand.equals(null));

        // same lesson -> returns true
        UnmarkLessonCommand unmarkLessonCommandCopy = new UnmarkLessonCommand(lessonIndex);
        assertTrue(unmarkLessonCommand.equals(unmarkLessonCommandCopy));

        // different lesson -> returns false
        assertFalse(unmarkLessonCommand.equals(unmarkLessonCommandDifferentIndex));
    }
}
