package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_ALL;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find the personal record of each inputted exercise.\n"
            + "Parameters: " + PREFIX_NAME
            + "NAME " + "[" + PREFIX_NAME
            + "NAME2" + " + " + PREFIX_NAME
            + "NAME3 ... ]" + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "squat " + PREFIX_NAME + "bench press\n"
            + "OR \n"
            + "List personal records for all exercises.\n"
            + "Parameters: " + PREFIX_ALL + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ALL;

    public static final String MESSAGE_SUCCESS = "Listing PRs:\n%s";

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
        if (nameSet.isEmpty()) {
            ArrayList<Exercise> allExercisePRs = model.getAllExercisePRs();
            for (Exercise exercise : allExercisePRs) {
                outputHashmap.put(exercise.getName(), exercise.getWeight());
            }
        } else {
            for (Name name : nameSet) {
                Exercise exerciseWithPR = model.getExercisePR(name);
                if (exerciseWithPR != null) {
                    outputHashmap.put(exerciseWithPR.getName(), exerciseWithPR.getWeight());
                }
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, outputHashmap));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrCommand // instanceof handles nulls
                && nameSet.equals(((PrCommand) other).nameSet));
    }
}
