package jarvis.logic.commands;

import static jarvis.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static java.util.Objects.requireNonNull;

import jarvis.model.Model;
import jarvis.ui.DisplayedList;

/**
 * Lists all lessons in the lesson book to the user.
 */
public class ListLessonCommand extends Command {

    public static final String COMMAND_WORD = "listlesson";

    public static final String MESSAGE_SUCCESS = "Listed all lessons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        return new CommandResult(MESSAGE_SUCCESS, DisplayedList.EXP_LESSON_LIST);
    }
}

