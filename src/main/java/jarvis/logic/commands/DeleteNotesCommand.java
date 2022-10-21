package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_NOTES_INDEX;
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
 * Deletes notes from a lesson.
 * Notes deleted can be from the lesson or from a specific student in the lesson if the student is specified.
 * The note is identified using its order in the collection of notes.
 * The student is identified using its displayed index from the student book.
 * The lesson is identified using its displayed index from the lesson book.
 */
public class DeleteNotesCommand extends Command {

    public static final String COMMAND_WORD = "deletenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes notes from a lesson or from a specific student in the lesson if optional student index is "
            + "specified. "
            + "Note is identified using its order in the collection of notes. The student and lesson are identified "
            + "by their respective index number used in the displayed student list and in the displayed lesson list.\n"
            + "Parameters: "
            + PREFIX_NOTES_INDEX + "NOTES INDEX "
            + PREFIX_LESSON_INDEX + "LESSON INDEX "
            + "[" + PREFIX_STUDENT_INDEX + "STUDENT_INDEX] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NOTES_INDEX + "1 " + PREFIX_LESSON_INDEX + "1 "
            + PREFIX_STUDENT_INDEX + "2";

    public static final String MESSAGE_DELETE_OVERALL_NOTES_SUCCESS = "Deleted note for lesson %1$s: %2$s";
    public static final String MESSAGE_DELETE_STUDENT_NOTES_SUCCESS = "Deleted note for %1$s in lesson %2$s: %3$s";

    private final Index noteIndex;
    private final Index lessonIndex;
    private final Index studentIndex;

    /**
     * Creates a DeleteNotesCommand to delete notes from a lesson or from a specified student in the lesson.
     *
     * @param noteIndex
     * @param lessonIndex
     * @param studentIndex
     */
    public DeleteNotesCommand(Index noteIndex, Index lessonIndex, Index studentIndex) {
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
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        if (studentIndex != null && studentIndex.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        try {
            if (studentIndex != null) {
                Student studentToDelete = lastShownStudentList.get(studentIndex.getZeroBased());
                String deletedNote = lessonToDelete.deleteStudentNotes(studentToDelete, noteIndex);
                successMessage = String.format(MESSAGE_DELETE_STUDENT_NOTES_SUCCESS, studentToDelete, lessonToDelete,
                        deletedNote);
            } else {
                String deletedNote = lessonToDelete.deleteOverallNotes(noteIndex);
                successMessage = String.format(MESSAGE_DELETE_OVERALL_NOTES_SUCCESS, lessonToDelete, deletedNote);
            }
        } catch (NoteNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_INDEX);
        } catch (StudentNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        model.setLesson(lessonToDelete, lessonToDelete);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNotesCommand // instanceof handles nulls
                && lessonIndex.equals(((DeleteNotesCommand) other).lessonIndex)
                && studentIndex != null && ((DeleteNotesCommand) other).studentIndex != null
                && studentIndex.equals(((DeleteNotesCommand) other).studentIndex)
                && noteIndex.equals(((DeleteNotesCommand) other).noteIndex));
    }
}
