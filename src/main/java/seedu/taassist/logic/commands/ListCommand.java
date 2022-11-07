package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.predicate.IsPartOfClassPredicate;

/**
 * Lists all students in TA-Assist or in the currently focused class.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_ALL_SUCCESS = "Listed all students.";
    public static final String MESSAGE_CLASS_SUCCESS = "Listed all students in [ %1$s ].";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.isInFocusMode()) {
            ModuleClass focusedClass = model.getFocusedClass();
            IsPartOfClassPredicate predicate = new IsPartOfClassPredicate(focusedClass);

            model.setFilteredListPredicate(predicate);
            return new CommandResult(String.format(MESSAGE_CLASS_SUCCESS, focusedClass));
        }
        model.setFilteredListPredicate(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_ALL_SUCCESS);
    }
}
