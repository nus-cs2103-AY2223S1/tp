package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_CONFIRM;
import static java.util.Objects.requireNonNull;

import gim.model.ExerciseTracker;
import gim.model.Model;
import gim.model.exercise.ExerciseHashMap;

/**
 * Clears the exercise tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = ":clear";

    public static final String MESSAGE_USAGE = "Please confirm that you want to clear the exercise tracker.\n"
                    + "Parameters: " + PREFIX_CONFIRM + "\n"
                    + "Example usage:\n" + COMMAND_WORD
                    + " " + PREFIX_CONFIRM;

    public static final String MESSAGE_SUCCESS = "Exercise tracker has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ExerciseHashMap cleared = model.getExerciseHashMap().clearExerciseHashMap();
        model.setExerciseTracker(new ExerciseTracker(cleared));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ClearCommand; // instanceof handles nulls
    }
}
