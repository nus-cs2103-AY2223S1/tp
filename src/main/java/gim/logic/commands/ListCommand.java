package gim.logic.commands;

import static gim.model.Model.PREDICATE_SHOW_ALL_EXERCISES;
import static java.util.Objects.requireNonNull;

import gim.model.Model;

/**
 * Lists all exercises in the exercise tracker to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = ":ls";

    public static final String MESSAGE_SUCCESS = "Listed all exercises";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
