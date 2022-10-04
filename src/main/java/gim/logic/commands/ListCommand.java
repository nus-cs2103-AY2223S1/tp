package gim.logic.commands;

import static java.util.Objects.requireNonNull;
import static gim.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import gim.model.Model;

/**
 * Lists all exercises in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all exercises";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
