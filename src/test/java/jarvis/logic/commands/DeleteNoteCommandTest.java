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
import jarvis.model.LessonNotes;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteNoteCommand}.
 */
public class DeleteNoteCommandTest {

    private static final Index lessonIndex = Index.fromZeroBased(0);
    private static final Index studentIndex = Index.fromZeroBased(0);
    private static final Index noteIndex = Index.fromZeroBased(0);
    private static final String validNote = "This is a valid note";

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());
    private LessonNotes expectedNotes;

    @BeforeEach
    public void setUp() throws Exception {
        Student studentToAdd = model.getFilteredLessonList().get(0).getStudent(studentIndex);
        model.getFilteredLessonList().get(0).addOverallNote(validNote);
        model.getFilteredLessonList().get(0).addStudentNote(validNote, studentToAdd);

        expectedNotes = new LessonNotes(model.getFilteredLessonList().get(0).getStudentList());
        expectedNotes.addNote(validNote);
        expectedNotes.addNote(studentToAdd, validNote);
    }

    @Test
    public void execute_overallNotesWithValidIndex_success() throws Exception {
        expectedNotes.deleteNote(0);

        CommandResult commandResult = new DeleteNoteCommand(noteIndex, lessonIndex, null).execute(model);

        Lesson lessonToDelete = model.getFilteredLessonList().get(0);
        assertEquals(String.format(DeleteNoteCommand.MESSAGE_DELETE_OVERALL_NOTE_SUCCESS, lessonToDelete, validNote),
                commandResult.getFeedbackToUser());
        assertEquals(expectedNotes, model.getFilteredLessonList().get(0).getLessonNotes());
    }

    @Test
    public void execute_overallNotesWithInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        int noteCount = model.getFilteredLessonList().get(0).getGeneralNotes().size();
        assertCommandFailure(
                new DeleteNoteCommand(noteIndex, Index.fromZeroBased(lessonCount), null), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index

        Lesson lessonToDelete = model.getFilteredLessonList().get(0);
        assertCommandFailure(
                new DeleteNoteCommand(Index.fromZeroBased(noteCount), lessonIndex, null), model,
                String.format(Messages.MESSAGE_OVERALL_NOTE_NOT_FOUND, noteCount + 1, lessonToDelete));
        // invalid note index
    }

    @Test
    public void execute_studentNotesWithValidIndex_success() throws Exception {
        Student studentToDelete = model.getFilteredLessonList().get(0).getStudent(studentIndex);
        expectedNotes.deleteNote(studentToDelete, 0);

        CommandResult commandResult = new DeleteNoteCommand(noteIndex, lessonIndex, studentIndex).execute(model);

        Lesson lessonToDelete = model.getFilteredLessonList().get(0);
        assertEquals(String.format(DeleteNoteCommand.MESSAGE_DELETE_STUDENT_NOTE_SUCCESS, studentToDelete,
                lessonToDelete, validNote), commandResult.getFeedbackToUser());
        assertEquals(expectedNotes, model.getFilteredLessonList().get(0).getLessonNotes());
    }

    @Test
    public void execute_studentNotesWithInvalidIndex_throwsCommandException() throws Exception {
        int lessonCount = model.getFilteredLessonList().size();
        int studentCount = model.getFilteredLessonList().get(0).getStudentList().size();
        int noteCount = model.getFilteredLessonList().get(0).getGeneralNotes().size();
        assertCommandFailure(
                new DeleteNoteCommand(noteIndex, Index.fromZeroBased(lessonCount), studentIndex), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index

        Lesson lessonToDelete = model.getFilteredLessonList().get(0);
        Student studentToDelete = model.getFilteredLessonList().get(0).getStudent(studentIndex);
        assertCommandFailure(
                new DeleteNoteCommand(noteIndex, lessonIndex, Index.fromZeroBased(studentCount)), model,
                Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX); // invalid student index
        assertCommandFailure(
                new DeleteNoteCommand(Index.fromZeroBased(noteCount), lessonIndex, studentIndex), model,
                String.format(Messages.MESSAGE_STUDENT_NOTE_NOT_FOUND, noteCount + 1, studentToDelete, lessonToDelete));
        // invalid note index
    }

    @Test
    public void equals() {
        DeleteNoteCommand overallDeleteNoteCommand = new DeleteNoteCommand(noteIndex, lessonIndex, null);
        DeleteNoteCommand overallDeleteNoteCommandDifferentLesson = new DeleteNoteCommand(noteIndex,
                Index.fromZeroBased(1), null);
        DeleteNoteCommand overallDeleteNoteCommandDifferentNote = new DeleteNoteCommand(Index.fromZeroBased(1),
                lessonIndex, null);

        DeleteNoteCommand studentDeleteNoteCommand = new DeleteNoteCommand(noteIndex, lessonIndex, studentIndex);
        DeleteNoteCommand studentDeleteNoteCommandDifferentLesson = new DeleteNoteCommand(noteIndex,
                Index.fromZeroBased(1), studentIndex);
        DeleteNoteCommand studentDeleteNoteCommandDifferentStudent = new DeleteNoteCommand(noteIndex,
                lessonIndex, Index.fromZeroBased(1));
        DeleteNoteCommand studentDeleteNoteCommandDifferentNote = new DeleteNoteCommand(Index.fromZeroBased(1),
                lessonIndex, studentIndex);

        // same object -> returns true
        assertTrue(overallDeleteNoteCommand.equals(overallDeleteNoteCommand));
        assertTrue(studentDeleteNoteCommand.equals(studentDeleteNoteCommand));

        // different types -> returns false
        assertFalse(overallDeleteNoteCommand.equals(1));
        assertFalse(studentDeleteNoteCommand.equals(1));

        // null -> returns false
        assertFalse(overallDeleteNoteCommand.equals(null));
        assertFalse(studentDeleteNoteCommand.equals(null));

        // same values -> returns true
        DeleteNoteCommand overallDeleteNoteCommandCopy = new DeleteNoteCommand(noteIndex, lessonIndex, null);
        DeleteNoteCommand studentDeleteNoteCommandCopy = new DeleteNoteCommand(noteIndex, lessonIndex, studentIndex);
        assertTrue(overallDeleteNoteCommand.equals(overallDeleteNoteCommandCopy));
        assertTrue(studentDeleteNoteCommand.equals(studentDeleteNoteCommandCopy));

        // different lesson -> returns false
        assertFalse(overallDeleteNoteCommand.equals(overallDeleteNoteCommandDifferentLesson));
        assertFalse(studentDeleteNoteCommand.equals(studentDeleteNoteCommandDifferentLesson));

        // different student -> returns false
        assertFalse(studentDeleteNoteCommand.equals(studentDeleteNoteCommandDifferentStudent));

        // different note -> returns false
        assertFalse(overallDeleteNoteCommand.equals(overallDeleteNoteCommandDifferentNote));
        assertFalse(studentDeleteNoteCommand.equals(studentDeleteNoteCommandDifferentNote));

        // different note type -> returns false
        assertFalse(overallDeleteNoteCommand.equals(studentDeleteNoteCommand));
        assertFalse(studentDeleteNoteCommand.equals(overallDeleteNoteCommand));
    }
}
