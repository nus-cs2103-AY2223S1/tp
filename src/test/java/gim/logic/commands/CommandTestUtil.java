package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_DATE;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_REPS;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gim.commons.core.index.Index;
import gim.logic.commands.exceptions.CommandException;
import gim.model.ExerciseTracker;
import gim.model.Model;
import gim.model.exercise.Exercise;
import gim.model.exercise.NameContainsKeywordsPredicate;
import gim.testutil.EditExerciseDescriptorBuilder;



/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_ARM_CURLS = "Arm Curls";
    public static final String VALID_NAME_BENCH_PRESS = "Bench Press";
    public static final String VALID_WEIGHT_ARM_CURLS = "100";
    public static final String VALID_WEIGHT_BENCH_PRESS = "200";
    public static final String VALID_SETS_ARM_CURLS = "3";
    public static final String VALID_SETS_BENCH_PRESS = "5";
    public static final String VALID_REPS_ARM_CURLS = "1";
    public static final String VALID_REPS_BENCH_PRESS = "2";
    public static final String VALID_DATE = "02/05/2022";
    public static final String VALID_DATE_2 = "12/01/2007";

    public static final String NAME_DESC_ARM_CURLS = " " + PREFIX_NAME + VALID_NAME_ARM_CURLS;
    public static final String NAME_DESC_BENCH_PRESS = " " + PREFIX_NAME + VALID_NAME_BENCH_PRESS;
    public static final String WEIGHT_DESC_ARM_CURLS = " " + PREFIX_WEIGHT + VALID_WEIGHT_ARM_CURLS;
    public static final String WEIGHT_DESC_BENCH_PRESS = " " + PREFIX_WEIGHT + VALID_WEIGHT_BENCH_PRESS;
    public static final String SETS_DESC_ARM_CURLS = " " + PREFIX_SETS + VALID_SETS_ARM_CURLS;
    public static final String SETS_DESC_BENCH_PRESS = " " + PREFIX_SETS + VALID_SETS_BENCH_PRESS;
    public static final String REPS_DESC_ARM_CURLS = " " + PREFIX_REPS + VALID_REPS_ARM_CURLS;
    public static final String REPS_DESC_BENCH_PRESS = " " + PREFIX_REPS + VALID_REPS_BENCH_PRESS;
    public static final String DATE_DESC = " " + PREFIX_DATE + VALID_DATE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_WEIGHT_DESC = " " + PREFIX_WEIGHT + "911a"; // 'a' not allowed in weights
    public static final String INVALID_SETS_DESC = " " + PREFIX_SETS; // empty string not allowed for sets
    public static final String INVALID_REPS_DESC = " " + PREFIX_REPS; // empty string not allowed for reps
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "02/05/2022z"; // 'z' not allowed in date
    // date can be empty, it will default to today's date

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditExerciseDescriptor DESC_ARM_CURLS;
    public static final EditCommand.EditExerciseDescriptor DESC_BENCH_PRESS;

    static {
        DESC_ARM_CURLS = new EditExerciseDescriptorBuilder().withName(VALID_NAME_ARM_CURLS)
                .withWeight(VALID_WEIGHT_ARM_CURLS).withSets(VALID_SETS_ARM_CURLS).withRep(VALID_REPS_ARM_CURLS)
                .withDates(VALID_DATE).build();
        DESC_BENCH_PRESS = new EditExerciseDescriptorBuilder().withName(VALID_NAME_BENCH_PRESS)
                .withWeight(VALID_WEIGHT_BENCH_PRESS).withSets(VALID_SETS_BENCH_PRESS).withRep(VALID_REPS_BENCH_PRESS)
                .withDates(VALID_DATE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the exercise tracker, filtered exercise list and selected exercise in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExerciseTracker expectedExerciseTracker = new ExerciseTracker(actualModel.getExerciseTracker());
        List<Exercise> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExerciseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedExerciseTracker, actualModel.getExerciseTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredExerciseList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the exercise at the given {@code targetIndex} in the
     * {@code model}'s exercise tracker.
     */
    public static void showExerciseAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExerciseList().size());

        Exercise exercise = model.getFilteredExerciseList().get(targetIndex.getZeroBased());
        final String[] splitName = exercise.getName().fullName.split("\\s+");
        model.updateFilteredExerciseList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredExerciseList().size());
    }

}
