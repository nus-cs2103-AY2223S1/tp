package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_NOTES;
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

/**
 * Add notes to a lesson.
 * Notes added can be for the lesson or for a specific student in the lesson if the student is specified.
 * The student is identified using its displayed index from the student book.
 * The lesson is identified using its displayed index from the lesson book.
 */
public class AddNotesCommand extends Command {

    public static final String COMMAND_WORD = "addnotes";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds notes to a lesson or for a specific student in the lesson if optional student index is specified."
            + "The student and lesson are identified by their respective index number used in the displayed student "
            + "list and in the displayed lesson list.\n"
            + "Parameters: "
            + PREFIX_NOTES + "NOTES "
            + PREFIX_LESSON_INDEX + "LESSON INDEX "
            + "[" + PREFIX_STUDENT_INDEX + "STUDENT_INDEX] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NOTES + "Get back to jeff on streams "
            + PREFIX_LESSON_INDEX + "1 " + PREFIX_STUDENT_INDEX + "2";

    public static final String MESSAGE_ADD_OVERALL_NOTES_SUCCESS = "Noted for lesson %1$s: %2$s";
    public static final String MESSAGE_ADD_STUDENT_NOTES_SUCCESS = "Noted for %1$s in lesson %2$s: %3$s";

    private final Index lessonIndex;
    private final Index studentIndex;
    private final String notes;

    /**
     * Creates a AddNotesCommand to add notes to a lesson or to a specified student in the lesson.
     *
     * @param lessonIndex
     * @param studentIndex
     * @param notes
     */
    public AddNotesCommand(Index lessonIndex, Index studentIndex, String notes) {
        this.lessonIndex = lessonIndex;
        this.studentIndex = studentIndex;
        this.notes = notes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        if (lessonIndex.getZeroBased() >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        Lesson lessonToAdd = lastShownLessonList.get(lessonIndex.getZeroBased());
        String successMessage;

        if (studentIndex != null) {
            List<Student> lastShownStudentList = model.getFilteredStudentList();
            if (studentIndex.getZeroBased() >= lastShownStudentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            Student studentToAdd = lastShownStudentList.get(studentIndex.getZeroBased());
            lessonToAdd.addStudentNotes(notes, studentToAdd);
            successMessage = String.format(MESSAGE_ADD_STUDENT_NOTES_SUCCESS, studentToAdd, lessonToAdd, notes);
        } else {
            lessonToAdd.addOverallNotes(notes);
            successMessage = String.format(MESSAGE_ADD_OVERALL_NOTES_SUCCESS, lessonToAdd, notes);
        }
        model.setLesson(lessonToAdd, lessonToAdd);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddNotesCommand // instanceof handles nulls
                && lessonIndex.equals(((AddNotesCommand) other).lessonIndex)
                && studentIndex != null && ((AddNotesCommand) other).studentIndex != null
                && studentIndex.equals(((AddNotesCommand) other).studentIndex)
                && notes.equals(((AddNotesCommand) other).notes));
    }
}
