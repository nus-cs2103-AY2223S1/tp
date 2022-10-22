package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Resets any filter applied to student list.
 */
public class StudentResetFilterCommand extends Command {
    public static final String COMMAND_WORD = "studentUnfilter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Revert back to unfiltered state.";

    public static final String MESSAGE_SUCCESS = "Students filtered in this tutorial group: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the address book";
    private List<Student> students;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        students = model.getFilteredStudentList();
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        String result = "";
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            result += student + "\n";
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS) + "\n" + result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentResetFilterCommand // instanceof handles nulls
                && students.equals(((StudentResetFilterCommand) other).students));
    }
}
