package gim.logic.commands;

import static gim.model.Model.PREDICATE_SHOW_ALL_EXERCISES;
import static java.util.Objects.requireNonNull;

import gim.model.Model;

/**
 * Sorts the exercises displayed according to the date of exercises done.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = ":sort";

    public static final String MESSAGE_SUCCESS = "Sorted all exercises by date";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortDisplayedList(PREDICATE_SHOW_ALL_EXERCISES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
