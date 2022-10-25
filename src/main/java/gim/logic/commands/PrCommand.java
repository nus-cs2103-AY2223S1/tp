package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Set;

import gim.model.Model;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Weight;

/**
 * For all exercises whose name contains any of the argument keywords, find the personal record (highest weight
 * achieved) of each exercise.
 * Keyword matching is case-insensitive.
 */
public class PrCommand extends Command {

    public static final String COMMAND_WORD = ":pr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find the personal record of each exercise and "
            + "display the weight in the console.\n"
            + "Parameters: " + PREFIX_NAME
            + "NAME " + "[" + PREFIX_NAME
            + "NAME2" + " + " + PREFIX_NAME
            + "NAME3 ... ]" + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "squat " + PREFIX_NAME + "bench press";

    public static final String MESSAGE_SUCCESS = "Listing PRs for %s:\n%s";

    private final Set<Name> nameSet;

    /**
     * Creates a PRCommand to add the specified {@code Exercise}
     */
    public PrCommand(Set<Name> nameSet) {
        requireNonNull(nameSet);
        this.nameSet = nameSet;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        HashMap<Name, Weight> outputHashmap = new HashMap<>();
        for (Name name : nameSet) {
            Exercise exerciseWithPR = model.getExercisePR(name);
            if (exerciseWithPR != null) {
                outputHashmap.put(exerciseWithPR.getName(), exerciseWithPR.getWeight());
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, nameSet, outputHashmap));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrCommand // instanceof handles nulls
                && nameSet.equals(((PrCommand) other).nameSet));
    }
}
