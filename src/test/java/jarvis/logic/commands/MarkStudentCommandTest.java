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
import jarvis.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkStudentCommand}.
 */
public class MarkStudentCommandTest {

    private static final Index lessonIndex = TypicalIndexes.INDEX_FIRST;
    private static final Index studentIndex = TypicalIndexes.INDEX_FIRST;
    private static final int lessonInt = lessonIndex.getZeroBased();
    private static final int studentInt = studentIndex.getZeroBased();

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        Lesson lessonToMark = model.getFilteredLessonList().get(lessonInt);
        for (Student s: lessonToMark.getStudentList()) {
            lessonToMark.markAsAbsent(s);
        }
    }

    @Test
    public void execute_withValidIndex_success() throws Exception {
        MarkStudentCommand markStudentCommand = new MarkStudentCommand(lessonIndex, studentIndex);

        Lesson lessonToMark = model.getFilteredLessonList().get(lessonInt);
        Student studentToMark = lessonToMark.getStudentList().get(studentInt);
        CommandResult commandResult = markStudentCommand.execute(model);
        assertEquals(String.format(MarkStudentCommand.MESSAGE_MARK_STUDENT_SUCCESS, studentToMark, lessonToMark),
                commandResult.getFeedbackToUser());
        assertEquals(lessonToMark.isPresent(studentToMark), "Present"); // student should be marked Present

        commandResult = markStudentCommand.execute(model);
        assertEquals(String.format(MarkStudentCommand.MESSAGE_MARK_STUDENT_SUCCESS, studentToMark, lessonToMark),
                commandResult.getFeedbackToUser());
        assertEquals(lessonToMark.isPresent(studentToMark), "Present"); // student should remain Present
    }

    @Test
    public void execute_withInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        assertCommandFailure(new MarkStudentCommand(Index.fromZeroBased(lessonCount), studentIndex), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index

        int studentCount = model.getFilteredLessonList().get(0).getStudentList().size();
        assertCommandFailure(new MarkStudentCommand(lessonIndex, Index.fromZeroBased(studentCount)), model,
                Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX); // invalid student index
    }

    @Test
    public void equals() {
        MarkStudentCommand markStudentCommand = new MarkStudentCommand(lessonIndex, studentIndex);
        MarkStudentCommand markStudentCommandDifferentLessonIndex = new MarkStudentCommand(
                Index.fromZeroBased(lessonInt + 1), studentIndex);
        MarkStudentCommand markStudentCommandDifferentStudentIndex = new MarkStudentCommand(lessonIndex,
                Index.fromZeroBased(studentInt + 1));

        // same object -> returns true
        assertTrue(markStudentCommand.equals(markStudentCommand));

        // different types -> returns false
        assertFalse(markStudentCommand.equals(1));

        // null -> returns false
        assertFalse(markStudentCommand.equals(null));

        // same lesson -> returns true
        MarkStudentCommand markStudentCommandCopy = new MarkStudentCommand(lessonIndex, studentIndex);
        assertTrue(markStudentCommand.equals(markStudentCommandCopy));

        // different lesson -> returns false
        assertFalse(markStudentCommand.equals(markStudentCommandDifferentLessonIndex));

        // different student -> returns false
        assertFalse(markStudentCommand.equals(markStudentCommandDifferentStudentIndex));
    }
}
