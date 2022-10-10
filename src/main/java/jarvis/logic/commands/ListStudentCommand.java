package jarvis.logic.commands;

import static jarvis.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import jarvis.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "liststudent";

    public static final String MESSAGE_SUCCESS = "Listed all students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
