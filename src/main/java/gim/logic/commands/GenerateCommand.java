package gim.logic.commands;

import static gim.commons.util.CollectionUtil.requireAllNonNull;
import static gim.logic.parser.CliSyntax.PREFIX_LEVEL;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gim.commons.core.Messages;
import gim.commons.core.index.Index;
import gim.logic.commands.exceptions.CommandException;
import gim.logic.generators.Generator;
import gim.logic.generators.GeneratorFactory;
import gim.logic.generators.ValidLevel;
import gim.model.Model;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;

/**
 * Generates a sample workout based on existing PRs of the specified exercises,
 * according to the difficulty level specified.
 * Difficulty levels supported: {easy, medium, hard}.
 */
public class GenerateCommand extends Command {

    public static final String COMMAND_WORD = ":gen";
    public static final String DIFFICULTY_LEVELS = "{easy, medium, hard}";

    public static final String MESSAGE_USAGE = "The generate feature has two variations: \n\n"
            + COMMAND_WORD + " INDEX [, INDEX]... " + PREFIX_LEVEL + "LEVEL\n"
            + "Each INDEX must be a positive integer; LEVEL must be one of " + DIFFICULTY_LEVELS + ".\n"
            + "Example usage: \n" + COMMAND_WORD + " 2, 3 " + PREFIX_LEVEL + "easy\n\n"
            + COMMAND_WORD + " n/NAME [n/NAME]... level/LEVEL\n"
            + "NAME must be of an exercise found in the system; LEVEL must be one of " + DIFFICULTY_LEVELS + ".\n"
            + "Example usage: \n" + COMMAND_WORD + " n/squat n/deadlift " + PREFIX_LEVEL + "easy";

    public static final String MESSAGE_GENERATE_SUCCESS = " workout session generated: \n";

    private final ArrayList<Index> indices;
    private final ValidLevel level;
    private final Set<Name> nameSet;


    /**
     * @param indices of the exercises in the filtered exercise list.
     * @param level difficulty level of the workout generated.
     */
    public GenerateCommand(ArrayList<Index> indices, ValidLevel level) {
        requireAllNonNull(indices, level);
        this.indices = indices;
        this.level = level;
        this.nameSet = null;
    }

    /**
     * @param nameSet containing names of the exercises given by user, not verified as valid.
     * @param level difficulty level of the workout generated.
     */
    public GenerateCommand(Set<Name> nameSet, ValidLevel level) {
        requireAllNonNull(nameSet, level);
        this.nameSet = nameSet;
        this.level = level;
        this.indices = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (nameSet != null) {
            return getCommandResultFromNames(model);
        }
        return getCommandResultFromIndices(model);
    }

    private CommandResult getCommandResultFromIndices(Model model) throws CommandException {
        List<Exercise> lastShownList = model.getFilteredExerciseList();
        StringBuilder fullSuggestion = new StringBuilder();
        HashSet<Name> uniqueNameSet = new HashSet<>();
        for (Index index : indices) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
            }
            Exercise exerciseToEdit = lastShownList.get(index.getZeroBased());
            Name exerciseName = exerciseToEdit.getName();
            if (uniqueNameSet.contains(exerciseName)) {
                continue;
            }
            uniqueNameSet.add(exerciseName);
            Exercise exercisePR = model.getExercisePR(exerciseName);
            Generator generator = GeneratorFactory.getGenerator(exercisePR, level);
            String suggestion = requireNonNull(generator).suggest();
            fullSuggestion.append(suggestion).append("\n");
        }
        return new CommandResult(level + MESSAGE_GENERATE_SUCCESS + fullSuggestion);
    }

    private CommandResult getCommandResultFromNames(Model model) throws CommandException {
        StringBuilder fullSuggestion = new StringBuilder();
        HashSet<Name> uniqueNameSet = new HashSet<>();
        boolean atLeastOneExerciseFound = false;
        for (Name name : nameSet) {
            if (uniqueNameSet.contains(name)) {
                continue;
            }
            uniqueNameSet.add(name);
            Exercise exercisePR = model.getExercisePR(name);
            if (exercisePR == null) { // cannot find name in system
                continue;
            }
            atLeastOneExerciseFound = true;
            Generator generator = GeneratorFactory.getGenerator(exercisePR, level);
            String suggestion = requireNonNull(generator).suggest();
            fullSuggestion.append(suggestion).append("\n");
        }
        if (!atLeastOneExerciseFound) { // failed to find any exercise in model
            throw new CommandException(PrCommand.MESSAGE_FAILURE);
        }
        return new CommandResult(level + MESSAGE_GENERATE_SUCCESS + fullSuggestion);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GenerateCommand)) {
            return false;
        }

        // state check
        GenerateCommand e = (GenerateCommand) other;
        return indices.equals(e.indices)
                && level.equals(e.level);
    }

    @Override
    public String toString() {
        return indices.toString() + " l/" + level;
    }
}

