package gim.logic.commands;

import static java.util.Objects.requireNonNull;

import gim.model.ExerciseTracker;
import gim.model.Model;
import gim.model.exercise.ExerciseHashMap;

/**
 * Clears the exercise tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Exercise tracker has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ExerciseHashMap cleared = model.getExerciseHashMap().clearExerciseHashMap();
        model.setExerciseTracker(new ExerciseTracker(cleared));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
