package jarvis.logic.commands;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static jarvis.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Lesson;
import jarvis.model.Model;
import jarvis.model.Student;
import jarvis.model.exceptions.NoteNotFoundException;
import jarvis.model.exceptions.StudentNotFoundException;

/**
 * Deletes a note from a lesson.
 * Note deleted can be for the lesson or for a specific student in the lesson if the student is specified.
 * The note is identified using its displayed index in the lesson notes or in the student notes in the lesson.
 * The lesson is identified using its displayed index from the lesson book.
 * The student is identified using its displayed index in the student list of the specified lesson.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "deletenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a note from a lesson or from a specific student in the lesson if optional student index is "
            + "specified. "
            + "Note is identified using its displayed index in the list of notes. The lesson is identified by its index"
            + " number in the displayed lesson list. The student is identified using its displayed index in the "
            + "student list of the specified lesson\n"
            + "Parameters: "
            + PREFIX_NOTE_INDEX + "NOTE_INDEX "
            + PREFIX_LESSON_INDEX + "LESSON_INDEX "
            + "[" + PREFIX_STUDENT_INDEX + "STUDENT_INDEX] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NOTE_INDEX + "1 " + PREFIX_LESSON_INDEX + "1 "
            + PREFIX_STUDENT_INDEX + "2";

    public static final String MESSAGE_DELETE_OVERALL_NOTE_SUCCESS = "Deleted note for lesson %1$s: %2$s";
    public static final String MESSAGE_DELETE_STUDENT_NOTE_SUCCESS = "Deleted note for %1$s in lesson %2$s: %3$s";

    private final Index noteIndex;
    private final Index lessonIndex;
    private final Index studentIndex;

    /**
     * Creates a DeleteNoteCommand to delete a note from a specified lesson or from a specified student in the lesson.
     */
    public DeleteNoteCommand(Index noteIndex, Index lessonIndex, Index studentIndex) {
        requireAllNonNull(noteIndex, lessonIndex);
        this.noteIndex = noteIndex;
        this.lessonIndex = lessonIndex;
        this.studentIndex = studentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        if (lessonIndex.getZeroBased() >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        Lesson lessonToDelete = lastShownLessonList.get(lessonIndex.getZeroBased());
        String successMessage;

        if (studentIndex == null) {
            successMessage = executeDeleteOverallNote(lessonToDelete);
        } else {
            successMessage = executeDeleteStudentNote(lessonToDelete);
        }
        model.setLesson(lessonToDelete, lessonToDelete);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(successMessage);
    }

    private String executeDeleteOverallNote(Lesson lessonToDelete) throws CommandException {
        try {
            String deletedNote = lessonToDelete.deleteOverallNote(noteIndex);
            return String.format(MESSAGE_DELETE_OVERALL_NOTE_SUCCESS, lessonToDelete, deletedNote);
        } catch (NoteNotFoundException nnfe) {
            throw new CommandException(String.format(Messages.MESSAGE_OVERALL_NOTE_NOT_FOUND, noteIndex.getOneBased(),
                    lessonToDelete));
        }
    }

    private String executeDeleteStudentNote(Lesson lessonToDelete) throws CommandException {
        Student studentToDelete = lessonToDelete.getStudent(studentIndex);
        try {
            String deletedNote = lessonToDelete.deleteStudentNote(studentToDelete, noteIndex);
            return String.format(MESSAGE_DELETE_STUDENT_NOTE_SUCCESS, studentToDelete, lessonToDelete, deletedNote);
        } catch (StudentNotFoundException snfe) {
            throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOT_FOUND, studentToDelete,
                    lessonToDelete));
        } catch (NoteNotFoundException nnfe) {
            throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOTE_NOT_FOUND, noteIndex.getOneBased(),
                    studentToDelete, lessonToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof DeleteNoteCommand)) { // instanceof handles nulls
            return false;
        }

        DeleteNoteCommand otherDeleteNote = (DeleteNoteCommand) other;

        boolean studentIndexEquality;
        if (studentIndex == null) {
            studentIndexEquality = otherDeleteNote.studentIndex == null;
        } else {
            studentIndexEquality = studentIndex.equals(otherDeleteNote.studentIndex);
        }
        return noteIndex.equals(otherDeleteNote.noteIndex)
                && lessonIndex.equals(otherDeleteNote.lessonIndex)
                && studentIndexEquality;
    }
}
