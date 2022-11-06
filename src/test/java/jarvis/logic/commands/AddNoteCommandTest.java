package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.BLANK_NOTE;
import static jarvis.logic.commands.CommandTestUtil.EMPTY_NOTE;
import static jarvis.logic.commands.CommandTestUtil.VALID_NOTE;
import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.testutil.TypicalLessons.getTypicalLessonBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import jarvis.model.exceptions.InvalidNoteException;
import jarvis.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddNoteCommand}.
 */
public class AddNoteCommandTest {

    private static final Index lessonIndex = TypicalIndexes.INDEX_FIRST;
    private static final Index studentIndex = TypicalIndexes.INDEX_FIRST;
    private static final int lessonInt = lessonIndex.getZeroBased();
    private static final int studentInt = studentIndex.getZeroBased();

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());
    private LessonNotes expectedNotes = new LessonNotes(model.getFilteredLessonList().get(lessonInt).getStudentList());

    @Test
    public void execute_overallNotesWithValidIndex_success() throws Exception {
        expectedNotes.addNote(VALID_NOTE);

        CommandResult commandResult = new AddNoteCommand(lessonIndex, null, VALID_NOTE).execute(model);

        Lesson lessonToAdd = model.getFilteredLessonList().get(lessonInt);
        assertEquals(String.format(AddNoteCommand.MESSAGE_ADD_OVERALL_NOTE_SUCCESS, lessonToAdd, VALID_NOTE),
                commandResult.getFeedbackToUser());
        assertEquals(expectedNotes.getGeneralNotes(), lessonToAdd.getLessonNotes().getGeneralNotes());

        lessonToAdd.deleteOverallNote(Index.fromZeroBased(0)); // remove the note
    }

    @Test
    public void execute_overallNotesWithInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        assertCommandFailure(
                new AddNoteCommand(Index.fromZeroBased(lessonCount), null, VALID_NOTE), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        assertCommandFailure(
                new AddNoteCommand(Index.fromZeroBased(lessonCount + 100), null, VALID_NOTE), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_overallNotesWithInvalidNote_throwsCommandException() {
        assertThrows(InvalidNoteException.class, () ->
                new AddNoteCommand(lessonIndex, null, EMPTY_NOTE).execute(model));

        assertThrows(InvalidNoteException.class, () ->
                new AddNoteCommand(lessonIndex, null, BLANK_NOTE).execute(model));
    }

    @Test
    public void execute_studentNotesWithValidIndex_success() throws Exception {
        Student studentToAdd = model.getFilteredLessonList().get(lessonInt).getStudent(studentIndex);
        expectedNotes.addNote(studentToAdd, VALID_NOTE);

        CommandResult commandResult = new AddNoteCommand(lessonIndex, studentIndex, VALID_NOTE).execute(model);

        Lesson lessonToAdd = model.getFilteredLessonList().get(lessonInt);
        assertEquals(String.format(AddNoteCommand.MESSAGE_ADD_STUDENT_NOTE_SUCCESS, studentToAdd, lessonToAdd,
                VALID_NOTE), commandResult.getFeedbackToUser());
        assertEquals(expectedNotes.getStudentNotes(studentToAdd),
                lessonToAdd.getLessonNotes().getStudentNotes(studentToAdd));

        lessonToAdd.deleteStudentNote(studentToAdd, Index.fromZeroBased(0)); // remove the note
    }

    @Test
    public void execute_studentNotesWithInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        int studentCount = model.getFilteredLessonList().get(lessonInt).getStudentList().size(); // for the first lesson
        assertCommandFailure(
                new AddNoteCommand(Index.fromZeroBased(lessonCount), studentIndex, VALID_NOTE),
                model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index

        assertCommandFailure(
                new AddNoteCommand(lessonIndex, Index.fromZeroBased(studentCount), VALID_NOTE),
                model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX); // invalid student index
    }

    @Test
    public void execute_studentNotesWithInvalidNote_throwsCommandException() {
        assertThrows(InvalidNoteException.class, () ->
                new AddNoteCommand(lessonIndex, studentIndex, EMPTY_NOTE).execute(model));

        assertThrows(InvalidNoteException.class, () ->
                new AddNoteCommand(lessonIndex, studentIndex, BLANK_NOTE).execute(model));
    }

    @Test
    public void equals() {
        AddNoteCommand overallAddNoteCommand = new AddNoteCommand(lessonIndex, null,
                VALID_NOTE);
        AddNoteCommand overallAddNoteCommandDifferentLesson = new AddNoteCommand(Index.fromZeroBased(lessonInt + 1),
                null, VALID_NOTE);
        AddNoteCommand overallAddNoteCommandDifferentNote = new AddNoteCommand(lessonIndex, null,
                VALID_NOTE + " a different valid note");

        AddNoteCommand studentAddNoteCommand = new AddNoteCommand(lessonIndex, studentIndex,
                VALID_NOTE);
        AddNoteCommand studentAddNoteCommandDifferentLesson = new AddNoteCommand(Index.fromZeroBased(lessonInt + 1),
                studentIndex, VALID_NOTE);
        AddNoteCommand studentAddNoteCommandDifferentStudent = new AddNoteCommand(lessonIndex,
                Index.fromZeroBased(studentInt + 1), VALID_NOTE);
        AddNoteCommand studentAddNoteCommandDifferentNote = new AddNoteCommand(lessonIndex, studentIndex,
                VALID_NOTE + " a different valid note");

        // same object -> returns true
        assertTrue(overallAddNoteCommand.equals(overallAddNoteCommand));
        assertTrue(studentAddNoteCommand.equals(studentAddNoteCommand));

        // different types -> returns false
        assertFalse(overallAddNoteCommand.equals(1));
        assertFalse(studentAddNoteCommand.equals(1));

        // null -> returns false
        assertFalse(overallAddNoteCommand.equals(null));
        assertFalse(studentAddNoteCommand.equals(null));

        // same values -> returns true
        AddNoteCommand overallAddNoteCommandCopy = new AddNoteCommand(lessonIndex, null, VALID_NOTE);
        AddNoteCommand studentAddNoteCommandCopy = new AddNoteCommand(lessonIndex, studentIndex, VALID_NOTE);
        assertTrue(overallAddNoteCommand.equals(overallAddNoteCommandCopy));
        assertTrue(studentAddNoteCommand.equals(studentAddNoteCommandCopy));

        // different lesson -> returns false
        assertFalse(overallAddNoteCommand.equals(overallAddNoteCommandDifferentLesson));
        assertFalse(studentAddNoteCommand.equals(studentAddNoteCommandDifferentLesson));

        // different student -> returns false
        assertFalse(studentAddNoteCommand.equals(studentAddNoteCommandDifferentStudent));

        // different note -> returns false
        assertFalse(overallAddNoteCommand.equals(overallAddNoteCommandDifferentNote));
        assertFalse(studentAddNoteCommand.equals(studentAddNoteCommandDifferentNote));

        // different note type -> returns false
        assertFalse(overallAddNoteCommand.equals(studentAddNoteCommand));
        assertFalse(studentAddNoteCommand.equals(overallAddNoteCommand));
    }

}
