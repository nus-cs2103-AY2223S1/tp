package soconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import soconnect.model.Model;

/**
 * Lists all persons in the SoConnect to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all contacts";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
