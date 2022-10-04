package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.address.model.Model;
import seedu.address.model.moduleclass.ModuleClass;
import seedu.address.model.student.IsPartOfClassPredicate;

/**
 * Lists all students in the address book or class to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_ALL_STUDENTS_SUCCESS = "Listed all students";
    public static final String MESSAGE_CLASS_STUDENTS_SUCCESS = "Listed all students in %s";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.isInFocusMode()) {
            ModuleClass focusedClass = model.getFocusedClass();
            IsPartOfClassPredicate predicate = new IsPartOfClassPredicate(focusedClass);
            model.updateFilteredStudentList(predicate);
            return new CommandResult(String.format(MESSAGE_CLASS_STUDENTS_SUCCESS, focusedClass));
        }
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_ALL_STUDENTS_SUCCESS);
    }
}
