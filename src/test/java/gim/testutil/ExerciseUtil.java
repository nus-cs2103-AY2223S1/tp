package gim.testutil;

import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_REP;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_TAG;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;

import gim.logic.commands.AddCommand;
import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.model.exercise.Exercise;
import gim.model.tag.Tag;



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
        sb.append(PREFIX_REP + exercise.getRep().value + " ");
        exercise.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExerciseDescriptor}'s details.
     */
    public static String getEditExerciseDescriptorDetails(EditExerciseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getWeight().ifPresent(weight -> sb.append(PREFIX_WEIGHT).append(weight.value).append(" "));
        descriptor.getSets().ifPresent(sets -> sb.append(PREFIX_SETS).append(sets.value).append(" "));
        descriptor.getRep().ifPresent(rep -> sb.append(PREFIX_REP).append(rep.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
