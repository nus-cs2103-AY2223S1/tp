package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Lists all students in the address book to the user.
 */
public class StudentListCommand extends Command {

    public static final String COMMAND_WORD = "student list";

    public static final String MESSAGE_SUCCESS = "Listed all students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> students = model.getFilteredStudentList();
        String display = "";

        for (int i = 0; i < students.size(); i++) {
            display += students.get(i).toString() + "\n";
        }

        return new CommandResult(display + MESSAGE_SUCCESS);
    }
}
