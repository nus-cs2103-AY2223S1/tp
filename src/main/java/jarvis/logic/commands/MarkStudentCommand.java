package jarvis.logic.commands;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
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
import jarvis.model.exceptions.StudentNotFoundException;

/**
 * Marks a student as present for a given lesson.
 * The lesson is identified using its displayed index from the lesson book.
 * The student is identified using its displayed index in the student list of the specified lesson.
 */
public class MarkStudentCommand extends Command {

    public static final String COMMAND_WORD = "markstudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a student as present for a lesson. "
            + "The lesson is identified by its index number in the displayed lesson list. The student is identified "
            + "using its displayed index in the student list of the specified lesson\n"
            + "Parameters: "
            + PREFIX_LESSON_INDEX + "LESSON_INDEX "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_LESSON_INDEX + "1 " + PREFIX_STUDENT_INDEX + "2";

    public static final String MESSAGE_MARK_STUDENT_SUCCESS = "Marked %1$s as present: %2$s";

    private final Index lessonIndex;
    private final Index studentIndex;

    /**
     * Creates a MarkStudentCommand to mark the specified student in the specified lesson as present.
     */
    public MarkStudentCommand(Index lessonIndex, Index studentIndex) {
        requireAllNonNull(lessonIndex, studentIndex);
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
        Lesson lessonToMark = lastShownLessonList.get(lessonIndex.getZeroBased());
        Student studentToMark = lessonToMark.getStudent(studentIndex);

        try {
            lessonToMark.markAsPresent(studentToMark);
        } catch (StudentNotFoundException snfe) {
            throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOT_FOUND, studentToMark, lessonToMark));
        }
        model.setLesson(lessonToMark, lessonToMark);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(String.format(MESSAGE_MARK_STUDENT_SUCCESS, studentToMark, lessonToMark));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof MarkStudentCommand)) { // instanceof handles nulls
            return false;
        }

        MarkStudentCommand otherMarkStudent = (MarkStudentCommand) other;

        return lessonIndex.equals(otherMarkStudent.lessonIndex)
                && studentIndex.equals(otherMarkStudent.studentIndex);
    }
}
