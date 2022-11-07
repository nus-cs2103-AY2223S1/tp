package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.commands.CommandTestUtil.DATE_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_REPS_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_SETS_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static gim.logic.commands.CommandTestUtil.NAME_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.NAME_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static gim.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static gim.logic.commands.CommandTestUtil.REPS_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.REPS_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.SETS_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.SETS_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.WEIGHT_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.WEIGHT_DESC_BENCH_PRESS;
import static gim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static gim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static gim.testutil.TypicalExercises.ARM_CURLS_WITHOUT_DATE;
import static gim.testutil.TypicalExercises.BENCH_PRESS;

import org.junit.jupiter.api.Test;

import gim.logic.commands.AddCommand;
import gim.model.date.Date;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.testutil.ExerciseBuilder;


public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Exercise expectedExercise = new ExerciseBuilder(BENCH_PRESS).withDate(VALID_DATE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BENCH_PRESS
                + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REPS_DESC_BENCH_PRESS + DATE_DESC, new AddCommand(expectedExercise));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ARM_CURLS + NAME_DESC_BENCH_PRESS
                + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REPS_DESC_BENCH_PRESS + DATE_DESC, new AddCommand(expectedExercise));

        // multiple weights - last weight accepted
        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_ARM_CURLS
                + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REPS_DESC_BENCH_PRESS + DATE_DESC, new AddCommand(expectedExercise));

        // multiple sets - last sets accepted
        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_ARM_CURLS + SETS_DESC_BENCH_PRESS
                + REPS_DESC_BENCH_PRESS + DATE_DESC, new AddCommand(expectedExercise));
    }

    @Test
    public void parse_optionalDateMissing_success() {
        Exercise expectedExercise = new ExerciseBuilder(ARM_CURLS_WITHOUT_DATE).build();
        assertParseSuccess(parser, NAME_DESC_ARM_CURLS + WEIGHT_DESC_ARM_CURLS
                        + SETS_DESC_ARM_CURLS + REPS_DESC_ARM_CURLS,
                new AddCommand(expectedExercise));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + REPS_DESC_BENCH_PRESS,
                expectedMessage);

        // missing weight prefix
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + VALID_WEIGHT_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + REPS_DESC_BENCH_PRESS,
                expectedMessage);

        // missing sets prefix
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + VALID_SETS_BENCH_PRESS + REPS_DESC_BENCH_PRESS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BENCH_PRESS + VALID_WEIGHT_BENCH_PRESS
                + VALID_SETS_BENCH_PRESS + VALID_REPS_BENCH_PRESS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REPS_DESC_BENCH_PRESS + DATE_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid weight
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + INVALID_WEIGHT_DESC + SETS_DESC_BENCH_PRESS
                + REPS_DESC_BENCH_PRESS + DATE_DESC, Weight.MESSAGE_CONSTRAINTS);

        // invalid sets
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS + INVALID_SETS_DESC
                + REPS_DESC_BENCH_PRESS + DATE_DESC, Sets.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REPS_DESC_BENCH_PRESS + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS_FORMAT);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + INVALID_REPS_DESC + DATE_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + REPS_DESC_BENCH_PRESS + VALID_DATE + INVALID_DATE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
