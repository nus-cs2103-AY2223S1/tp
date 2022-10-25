package jarvis.logic.commands;

import static jarvis.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Lesson;
import jarvis.model.Model;

/**
 * Marks a lesson as complete.
 * The lesson is identified using its displayed index from the lesson book.
 */
public class MarkLessonCommand extends Command {

    public static final String COMMAND_WORD = "marklesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a lesson as completed. The lesson is identified by its index number in the displayed "
            + "lesson list.\n"
            + "Parameters: INDEX(must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_STUDENT_SUCCESS = "Marked %1$s as complete.";

    private final Index lessonIndex;

    /**
     * Creates a MarkLessonCommand to mark the lesson at the specified index as completed.
     */
    public MarkLessonCommand(Index lessonIndex) {
        requireNonNull(lessonIndex);
        this.lessonIndex = lessonIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        if (lessonIndex.getZeroBased() >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToMark = lastShownLessonList.get(lessonIndex.getZeroBased());
        lessonToMark.markAsCompleted();
        model.setLesson(lessonToMark, lessonToMark);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(String.format(MESSAGE_MARK_STUDENT_SUCCESS, lessonToMark));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof MarkLessonCommand)) { // instanceof handles nulls
            return false;
        }

        MarkLessonCommand otherMarkLesson = (MarkLessonCommand) other;

        return lessonIndex.equals(otherMarkLesson.lessonIndex);
    }
}
