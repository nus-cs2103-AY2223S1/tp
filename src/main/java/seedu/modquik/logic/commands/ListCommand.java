package seedu.modquik.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modquik.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.modquik.model.ModelType.STUDENT;

import seedu.modquik.model.Model;

/**
 * Lists all persons in the ModQuik to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS, STUDENT);
    }
}
