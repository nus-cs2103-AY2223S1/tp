package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.VALID_NOTE;
import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.testutil.TypicalLessons.getTypicalLessonBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.model.Lesson;
import jarvis.model.LessonNotes;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.UserPrefs;
import jarvis.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteNoteCommand}.
 */
public class DeleteNoteCommandTest {

    private static final Index lessonIndex = TypicalIndexes.INDEX_FIRST_LESSON;
    private static final Index studentIndex = TypicalIndexes.INDEX_FIRST_STUDENT;
    private static final Index noteIndex = TypicalIndexes.INDEX_FIRST_NOTE;
    private static final int lessonInt = lessonIndex.getZeroBased();
    private static final int studentInt = studentIndex.getZeroBased();
    private static final int noteInt = noteIndex.getZeroBased();

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());
    private LessonNotes expectedNotes = new LessonNotes(model.getFilteredLessonList().get(lessonInt).getStudentList());

    @Test
    public void execute_overallNotesWithValidIndex_success() throws Exception {
        addOverallNote();
        expectedNotes.deleteNote(noteInt);

        Lesson lessonToDelete = model.getFilteredLessonList().get(lessonInt);
        CommandResult commandResult = new DeleteNoteCommand(noteIndex, lessonIndex, null).execute(model);
        assertEquals(String.format(DeleteNoteCommand.MESSAGE_DELETE_OVERALL_NOTE_SUCCESS, lessonToDelete, VALID_NOTE),
                commandResult.getFeedbackToUser());
        assertEquals(expectedNotes.getGeneralNotes(), lessonToDelete.getLessonNotes().getGeneralNotes());
    }

    @Test
    public void execute_overallNotesWithInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        int noteCount = model.getFilteredLessonList().get(lessonInt).getGeneralNotes().size();
        assertCommandFailure(
                new DeleteNoteCommand(noteIndex, Index.fromZeroBased(lessonCount), null), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index

        Lesson lessonToDelete = model.getFilteredLessonList().get(lessonInt);
        assertCommandFailure(
                new DeleteNoteCommand(Index.fromZeroBased(noteCount), lessonIndex, null), model,
                String.format(Messages.MESSAGE_OVERALL_NOTE_NOT_FOUND, noteCount + 1, lessonToDelete));
        // invalid note index
    }

    @Test
    public void execute_studentNotesWithValidIndex_success() throws Exception {
        addStudentNote();
        Student studentToDelete = model.getFilteredLessonList().get(lessonInt).getStudent(studentIndex);
        expectedNotes.deleteNote(studentToDelete, noteInt);

        CommandResult commandResult = new DeleteNoteCommand(noteIndex, lessonIndex, studentIndex).execute(model);

        Lesson lessonToDelete = model.getFilteredLessonList().get(lessonInt);
        assertEquals(String.format(DeleteNoteCommand.MESSAGE_DELETE_STUDENT_NOTE_SUCCESS, studentToDelete,
                lessonToDelete, VALID_NOTE), commandResult.getFeedbackToUser());
        assertEquals(expectedNotes.getStudentNotes(studentToDelete),
                lessonToDelete.getLessonNotes().getStudentNotes(studentToDelete));
    }

    @Test
    public void execute_studentNotesWithInvalidIndex_throwsCommandException() throws Exception {
        Lesson lessonToDelete = model.getFilteredLessonList().get(lessonInt);
        Student studentToDelete = model.getFilteredLessonList().get(lessonInt).getStudent(studentIndex);

        int lessonCount = model.getFilteredLessonList().size();
        int studentCount = model.getFilteredLessonList().get(lessonInt).getStudentList().size();
        int noteCount = model.getFilteredLessonList().get(lessonInt).getStudentNotes().get(studentInt).size();
        assertCommandFailure(
                new DeleteNoteCommand(noteIndex, Index.fromZeroBased(lessonCount), studentIndex), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index

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
                Index.fromZeroBased(lessonInt + 1), null);
        DeleteNoteCommand overallDeleteNoteCommandDifferentNote = new DeleteNoteCommand(Index.fromZeroBased(1),
                lessonIndex, null);

        DeleteNoteCommand studentDeleteNoteCommand = new DeleteNoteCommand(noteIndex, lessonIndex, studentIndex);
        DeleteNoteCommand studentDeleteNoteCommandDifferentLesson = new DeleteNoteCommand(noteIndex,
                Index.fromZeroBased(lessonInt + 1), studentIndex);
        DeleteNoteCommand studentDeleteNoteCommandDifferentStudent = new DeleteNoteCommand(noteIndex,
                lessonIndex, Index.fromZeroBased(studentInt + 1));
        DeleteNoteCommand studentDeleteNoteCommandDifferentNote = new DeleteNoteCommand(
                Index.fromZeroBased(noteInt + 1), lessonIndex, studentIndex);

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

    public void addOverallNote() {
        model.getFilteredLessonList().get(lessonInt).getLessonNotes().addNote(VALID_NOTE);
        expectedNotes.addNote(VALID_NOTE);
    }

    public void addStudentNote() throws Exception {
        Student studentToAdd = model.getFilteredLessonList().get(lessonInt).getStudent(studentIndex);
        model.getFilteredLessonList().get(lessonInt).getLessonNotes().addNote(studentToAdd, VALID_NOTE);
        expectedNotes.addNote(studentToAdd, VALID_NOTE);
    }
}
