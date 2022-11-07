package gim.testutil;

import static gim.logic.parser.CliSyntax.PREFIX_DATE;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_REPS;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;

import gim.logic.commands.AddCommand;
import gim.model.exercise.Exercise;


/**
 * A utility class for Exercise.
 */
public class ExerciseUtil {

    /**
     * Returns an add command string for adding the {@code exercise}.
     */
    public static String getAddCommand(Exercise exercise) {
        return AddCommand.COMMAND_WORD + " " + getExerciseDetails(exercise);
    }

    /**
     * Returns the part of command string for the given {@code exercise}'s details.
     */
    public static String getExerciseDetails(Exercise exercise) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + exercise.getName().fullName + " ");
        sb.append(PREFIX_WEIGHT + exercise.getWeight().value + " ");
        sb.append(PREFIX_SETS + exercise.getSets().value + " ");
        sb.append(PREFIX_REPS + exercise.getReps().value + " ");
        sb.append(PREFIX_DATE + exercise.getDateString() + " ");
        return sb.toString();
    }

}
