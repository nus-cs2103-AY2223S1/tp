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

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkLessonCommand}.
 */
public class MarkLessonCommandTest {

    private static final Index lessonIndex = Index.fromZeroBased(0);

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        List<Lesson> lessonList = model.getFilteredLessonList();
        for (Lesson l: lessonList) {
            l.markAsNotCompleted();
        }
    }

    @Test
    public void execute_withValidIndex_success() throws Exception {
        MarkLessonCommand markLessonCommand = new MarkLessonCommand(lessonIndex);

        Lesson lessonToMark = model.getFilteredLessonList().get(0);
        CommandResult commandResult = markLessonCommand.execute(model);
        assertEquals(String.format(MarkLessonCommand.MESSAGE_MARK_LESSON_SUCCESS, lessonToMark),
                commandResult.getFeedbackToUser());
        assertTrue(lessonToMark.isCompleted()); // lesson should be marked completed

        // mark everything as completed, to ensure lesson at index 0 is marked completed
        List<Lesson> lessonList = model.getFilteredLessonList();
        for (Lesson l: lessonList) {
            l.markAsCompleted();
        }
        lessonToMark = model.getFilteredLessonList().get(0);
        commandResult = markLessonCommand.execute(model);
        assertEquals(String.format(MarkLessonCommand.MESSAGE_MARK_LESSON_SUCCESS, lessonToMark),
                commandResult.getFeedbackToUser());
        assertTrue(lessonToMark.isCompleted()); // lesson should remain completed
    }

    @Test
    public void execute_withInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        assertCommandFailure(new MarkLessonCommand(Index.fromZeroBased(lessonCount)), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        assertCommandFailure(new MarkLessonCommand(Index.fromZeroBased(lessonCount + 100)), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkLessonCommand markLessonCommand = new MarkLessonCommand(lessonIndex);
        MarkLessonCommand markLessonCommandDifferentIndex = new MarkLessonCommand(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(markLessonCommand.equals(markLessonCommand));

        // different types -> returns false
        assertFalse(markLessonCommand.equals(1));

        // null -> returns false
        assertFalse(markLessonCommand.equals(null));

        // same lesson -> returns true
        MarkLessonCommand markLessonCommandCopy = new MarkLessonCommand(lessonIndex);
        assertTrue(markLessonCommand.equals(markLessonCommandCopy));

        // different lesson -> returns false
        assertFalse(markLessonCommand.equals(markLessonCommandDifferentIndex));
    }
}
