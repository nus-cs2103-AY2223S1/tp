package gim.logic.commands;

import static gim.testutil.TypicalExercises.DEADLIFT_HEAVY;
import static gim.testutil.TypicalExercises.DEADLIFT_LIGHT;
import static gim.testutil.TypicalExercises.SQUAT_HEAVY;
import static gim.testutil.TypicalExercises.SQUAT_LIGHT;
import static gim.testutil.TypicalExercises.getDifferentExerciseDifferentWeightsExerciseTracker;
import static gim.testutil.TypicalExercises.getNoExerciseExerciseTracker;
import static gim.testutil.TypicalExercises.getOneExerciseExerciseTracker;
import static gim.testutil.TypicalExercises.getSameExerciseNameDifferentWeightsExerciseTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PRCommandTest.
 */
public class PrCommandTest {
    private Model model;
    private Set<Name> nameSet;
    private ArrayList<Exercise> expectedList;

    @BeforeEach
    public void setUp() {
        nameSet = new HashSet<>();
        expectedList = new ArrayList<>();
    }

    @Test
    public void execute_noExercise_showsNothing() {
        model = new ModelManager(getNoExerciseExerciseTracker(), new UserPrefs());

        CommandResult result = new PrCommand(nameSet).execute(model);

        String expectedMessage = String.format(PrCommand.MESSAGE_SUCCESS,
                PrCommand.prettyStringifyArrayList(expectedList));

        assertEquals(new CommandResult(expectedMessage), result);
    }

    @Test
    public void execute_oneExercise_showsExercise() {
        model = new ModelManager(getOneExerciseExerciseTracker(), new UserPrefs());

        nameSet.add(SQUAT_LIGHT.getName());
        CommandResult result = new PrCommand(nameSet).execute(model);

        expectedList.add(SQUAT_LIGHT);
        String expectedMessage = String.format(PrCommand.MESSAGE_SUCCESS,
                PrCommand.prettyStringifyArrayList(expectedList));

        assertEquals(new CommandResult(expectedMessage), result);
    }

    @Test
    public void execute_sameExerciseDifferentWeights_showsHeavierExercise() {
        model = new ModelManager(getSameExerciseNameDifferentWeightsExerciseTracker(), new UserPrefs());

        nameSet.add(SQUAT_LIGHT.getName());
        nameSet.add(SQUAT_HEAVY.getName());
        CommandResult result = new PrCommand(nameSet).execute(model);

        expectedList.add(SQUAT_HEAVY);
        String expectedMessage = String.format(PrCommand.MESSAGE_SUCCESS,
                PrCommand.prettyStringifyArrayList(expectedList));

        assertEquals(new CommandResult(expectedMessage), result);
    }

    @Test
    public void execute_differentExerciseDifferentWeights_showsBothExercises() {
        model = new ModelManager(getDifferentExerciseDifferentWeightsExerciseTracker(), new UserPrefs());

        nameSet.add(DEADLIFT_LIGHT.getName());
        nameSet.add(SQUAT_LIGHT.getName());
        CommandResult result = new PrCommand(nameSet).execute(model);

        expectedList.add(DEADLIFT_LIGHT);
        expectedList.add(SQUAT_LIGHT);
        String expectedMessage = String.format(PrCommand.MESSAGE_SUCCESS,
                PrCommand.prettyStringifyArrayList(expectedList));

        assertEquals(new CommandResult(expectedMessage), result);
    }
}
