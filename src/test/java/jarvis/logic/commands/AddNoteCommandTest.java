package jarvis.logic.commands;

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

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddNoteCommand}.
 */
public class AddNoteCommandTest {

    private static final String validNote = "This is a valid note";
    private static final String emptyNote = "";
    private static final String blankNote = "    ";

    private static final Index lessonIndex = Index.fromZeroBased(0);
    private static final Index studentIndex = Index.fromZeroBased(0);

    private Model model = new ModelManager(getTypicalLessonBook(), new UserPrefs());

    @Test
    public void execute_overallNotesWithValidIndex_success() throws Exception {
        LessonNotes expectedNotes = new LessonNotes(model.getFilteredLessonList().get(0).getStudentList());
        expectedNotes.addNote(validNote);

        CommandResult commandResult = new AddNoteCommand(lessonIndex, null, validNote).execute(model);

        Lesson lessonToAdd = model.getFilteredLessonList().get(0);
        assertEquals(String.format(AddNoteCommand.MESSAGE_ADD_OVERALL_NOTE_SUCCESS, lessonToAdd, validNote),
                commandResult.getFeedbackToUser());
        assertEquals(expectedNotes, model.getFilteredLessonList().get(0).getLessonNotes());
    }

    @Test
    public void execute_overallNotesWithInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        assertCommandFailure(
                new AddNoteCommand(Index.fromZeroBased(lessonCount), null, validNote), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        assertCommandFailure(
                new AddNoteCommand(Index.fromZeroBased(lessonCount + 100), null, validNote), model,
                Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_overallNotesWithInvalidNote_throwsCommandException() {
        assertCommandFailure(
                new AddNoteCommand(lessonIndex, null, emptyNote), model, "Note cannot be empty");

        assertCommandFailure(
                new AddNoteCommand(lessonIndex, null, blankNote), model, "Note cannot be empty");
    }

    @Test
    public void execute_studentNotesWithValidIndex_success() throws Exception {
        LessonNotes expectedNotes = new LessonNotes(model.getFilteredLessonList().get(0).getStudentList());
        Student studentToAdd = model.getFilteredLessonList().get(0).getStudent(studentIndex);
        expectedNotes.addNote(studentToAdd, validNote);

        CommandResult commandResult = new AddNoteCommand(lessonIndex, studentIndex, validNote).execute(model);

        Lesson lessonToAdd = model.getFilteredLessonList().get(0);
        assertEquals(String.format(AddNoteCommand.MESSAGE_ADD_STUDENT_NOTE_SUCCESS, studentToAdd, lessonToAdd,
                        validNote), commandResult.getFeedbackToUser());
        assertEquals(expectedNotes, model.getFilteredLessonList().get(0).getLessonNotes());
    }

    @Test
    public void execute_studentNotesWithInvalidIndex_throwsCommandException() {
        int lessonCount = model.getFilteredLessonList().size();
        int studentCount = model.getFilteredLessonList().get(0).getStudentList().size(); // for the first lesson
        assertCommandFailure(
                new AddNoteCommand(Index.fromZeroBased(lessonCount), studentIndex, validNote),
                model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX); // invalid lesson index

        assertCommandFailure(
                new AddNoteCommand(lessonIndex, Index.fromZeroBased(studentCount), validNote),
                model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX); // invalid student index
    }

    @Test
    public void execute_studentNotesWithInvalidNote_throwsCommandException() {
        assertCommandFailure(
                new AddNoteCommand(lessonIndex, studentIndex, emptyNote), model, "Note cannot be empty");

        assertCommandFailure(
                new AddNoteCommand(lessonIndex, studentIndex, blankNote), model, "Note cannot be empty");
    }

    @Test
    public void equals() {
        AddNoteCommand overallAddNoteCommand = new AddNoteCommand(lessonIndex, null,
                "test note");
        AddNoteCommand overallAddNoteCommandDifferentLesson = new AddNoteCommand(Index.fromZeroBased(1), null,
                "test note");
        AddNoteCommand overallAddNoteCommandDifferentNote = new AddNoteCommand(lessonIndex, null,
                "a different test note");

        AddNoteCommand studentAddNoteCommand = new AddNoteCommand(lessonIndex, studentIndex,
                "test note");
        AddNoteCommand studentAddNoteCommandDifferentLesson = new AddNoteCommand(Index.fromZeroBased(1), studentIndex,
                "test note");
        AddNoteCommand studentAddNoteCommandDifferentStudent = new AddNoteCommand(lessonIndex, Index.fromZeroBased(1),
                "test note");
        AddNoteCommand studentAddNoteCommandDifferentNote = new AddNoteCommand(lessonIndex, studentIndex,
                "a different test note");

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
        AddNoteCommand overallAddNoteCommandCopy = new AddNoteCommand(lessonIndex, null, "test note");
        AddNoteCommand studentAddNoteCommandCopy = new AddNoteCommand(lessonIndex, studentIndex, "test note");
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
