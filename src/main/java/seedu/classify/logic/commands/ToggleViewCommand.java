package seedu.classify.logic.commands;

import java.util.function.Predicate;

import seedu.classify.model.Model;
import seedu.classify.model.student.Student;

/**
 * Toggles application between showing and hiding students' parent details
 */
public class ToggleViewCommand extends Command {
    public static final String COMMAND_WORD = "toggleView";

    public static final String MESSAGE_SUCCESS_HIDE = "Parent details are hidden";

    public static final String MESSAGE_SUCCESS_SHOW = "Parent details are shown";

    @Override
    public CommandResult execute(Model model) {
        model.toggleStudentListInfoConcise();
        model.updateFilteredStudentList(x -> false);

        // FilteredStudyList should be empty
        assert model.getFilteredStudentList().size() == 0;

        Predicate<Student> prevPredicate = model.getPrevPredicate();
        model.updateFilteredStudentList(prevPredicate);

        if (model.isStudentListInfoConcise()) {
            return new CommandResult(MESSAGE_SUCCESS_HIDE);
        }
        return new CommandResult(MESSAGE_SUCCESS_SHOW);
    }

}
