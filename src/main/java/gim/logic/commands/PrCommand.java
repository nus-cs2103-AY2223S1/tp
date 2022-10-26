package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_ALL;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

import gim.model.Model;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;

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

    /**
     * Returns the pretty PrCommand output for an individual Exercise
     * @param exercise Exercise
     * @return Display PrCommand Exercise output.
     */
    public static String prExerciseStringify(Exercise exercise) {
        return exercise.getName() + ": " + exercise.getWeight() + "kg" + "\n";
    }

    /**
     * Given the appropriate ArrayList, returns a prettier String output for PrCommand.
     * @param list ArrayList.
     * @return Display PrCommand overall output.
     */
    public static String prettyStringifyArrayList(ArrayList<Exercise> list) {
        list.sort(Comparator.comparing(Exercise::getName)); // Sort the List by Name (Alphabetically)
        StringBuilder returnString = new StringBuilder();
        for (Exercise exercise : list) {
            returnString.append(prExerciseStringify(exercise));
        }
        return returnString.toString();
    }

    /**
     * Generate an ArrayList containing Exercises which are PRs.
     * @param nameSet nameSet.
     * @param model Model.
     * @return ArrayList containing Exercise PRs.
     */
    public ArrayList<Exercise> generateOutputArrayList(Set<Name> nameSet, Model model) {
        if (nameSet.isEmpty()) {
            return model.getAllExercisePRs();
        } else {
            ArrayList<Exercise> outputList = new ArrayList<>();
            for (Name name : nameSet) {
                Exercise exerciseWithPR = model.getExercisePR(name);
                outputList.add(exerciseWithPR);
            }
            return outputList;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ArrayList<Exercise> outputList = generateOutputArrayList(nameSet, model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, prettyStringifyArrayList(outputList)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrCommand // instanceof handles nulls
                && nameSet.equals(((PrCommand) other).nameSet));
    }
}
