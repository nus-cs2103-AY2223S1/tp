package gim.logic.commands;

import static gim.logic.commands.CommandTestUtil.VALID_LEVEL_EASY;
import static gim.logic.commands.CommandTestUtil.VALID_LEVEL_HARD;
import static gim.logic.commands.CommandTestUtil.VALID_LEVEL_MEDIUM;
import static gim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static gim.logic.commands.GenerateCommand.MESSAGE_GENERATE_SUCCESS;
import static gim.testutil.TypicalExercises.BICEP_CURLS;
import static gim.testutil.TypicalExercises.DEADLIFT;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static gim.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static gim.testutil.TypicalIndexes.INDEX_LIST_FIRST_SECOND;
import static gim.testutil.TypicalIndexes.INDEX_LIST_SECOND_THIRD;
import static gim.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import gim.logic.generators.Generator;
import gim.logic.generators.GeneratorFactory;
import gim.model.ExerciseTracker;
import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;



/**
 * Contains integration tests (interaction with the Model) and unit tests for GenerateCommand.
 */
class GenerateCommandTest {

    private Model model = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());
    private final Exercise firstExercise = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
    private final Exercise secondExercise = model.getFilteredExerciseList().get(INDEX_SECOND_EXERCISE.getZeroBased());
    private final Name firstExerciseName = firstExercise.getName();
    private final Name secondExerciseName = secondExercise.getName();
    private final Exercise firstExercisePR = model.getExercisePR(firstExerciseName);
    private final Exercise secondExercisePR = model.getExercisePR(secondExerciseName);

    @Test
    public void execute_generateUnfilteredList_success() {
        Generator firstGenerator = GeneratorFactory.getGenerator(firstExercisePR, VALID_LEVEL_EASY);
        String suggestion = requireNonNull(firstGenerator).suggest();
        StringBuilder fullSuggestion = new StringBuilder().append(suggestion).append("\n");


        if (!firstExercise.getName().equals(secondExercise.getName())) {
            Generator secondGenerator = GeneratorFactory.getGenerator(secondExercisePR, VALID_LEVEL_EASY);
            fullSuggestion.append(requireNonNull(secondGenerator).suggest()).append("\n");
        }

        GenerateCommand generateCommand = new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_EASY);
        String expectedMessage = VALID_LEVEL_EASY + MESSAGE_GENERATE_SUCCESS + fullSuggestion;
        Model expectedModel = new ModelManager(new ExerciseTracker(model.getExerciseTracker()), new UserPrefs());

        assertCommandSuccess(generateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_generateFromNames_success() {
        Generator firstGenerator = GeneratorFactory.getGenerator(BICEP_CURLS, VALID_LEVEL_EASY);
        String suggestion = requireNonNull(firstGenerator).suggest();
        StringBuilder fullSuggestion = new StringBuilder().append(suggestion).append("\n");
        Generator secondGenerator = GeneratorFactory.getGenerator(DEADLIFT, VALID_LEVEL_EASY);
        fullSuggestion.append(requireNonNull(secondGenerator).suggest()).append("\n");

        Set<Name> names = new HashSet<>();
        names.add(BICEP_CURLS.getName());
        names.add(DEADLIFT.getName());

        GenerateCommand generateCommand = new GenerateCommand(names, VALID_LEVEL_EASY);
        String expectedMessage = VALID_LEVEL_EASY + MESSAGE_GENERATE_SUCCESS + fullSuggestion;
        Model expectedModel = new ModelManager(new ExerciseTracker(model.getExerciseTracker()), new UserPrefs());

        assertCommandSuccess(generateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final GenerateCommand standardEasyCommand = new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_EASY);
        final GenerateCommand standardMediumCommand = new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_MEDIUM);
        final GenerateCommand standardHardCommand = new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_HARD);

        final Set<Name> nameSet = new HashSet<>();
        nameSet.add(firstExerciseName);
        nameSet.add(secondExerciseName);
        final GenerateCommand easyCommandWithNames = new GenerateCommand(nameSet, VALID_LEVEL_EASY);

        // same values -> returns true
        GenerateCommand commandWithSameValues = new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_EASY);
        assertTrue(standardEasyCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardEasyCommand.equals(standardEasyCommand));

        // same object -> returns true
        assertTrue(standardMediumCommand.equals(standardMediumCommand));

        // same object -> returns true
        assertTrue(standardHardCommand.equals(standardHardCommand));

        // same exercise names and level -> returns true
        final Set<Name> names = new HashSet<>();
        names.add(firstExerciseName);
        names.add(secondExerciseName);
        assertTrue(easyCommandWithNames.equals(new GenerateCommand(names, VALID_LEVEL_EASY)));

        // null -> returns false
        assertFalse(standardEasyCommand.equals(null));

        // different types -> returns false
        assertFalse(standardEasyCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardEasyCommand.equals(new GenerateCommand(INDEX_LIST_SECOND_THIRD, VALID_LEVEL_EASY)));

        // different level -> returns false
        assertFalse(standardEasyCommand.equals(new GenerateCommand(INDEX_LIST_FIRST_SECOND, VALID_LEVEL_MEDIUM)));


    }
}
