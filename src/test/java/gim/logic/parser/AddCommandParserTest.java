package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_REP_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_SETS_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static gim.logic.commands.CommandTestUtil.NAME_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.NAME_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static gim.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static gim.logic.commands.CommandTestUtil.REP_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.REP_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.SETS_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.SETS_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static gim.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_REP_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.WEIGHT_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.WEIGHT_DESC_BENCH_PRESS;
import static gim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static gim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static gim.testutil.TypicalExercises.ARM_CURLS;
import static gim.testutil.TypicalExercises.BENCH_PRESS;

import org.junit.jupiter.api.Test;

import gim.logic.commands.AddCommand;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Rep;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.tag.Tag;
import gim.testutil.ExerciseBuilder;




public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Exercise expectedExercise = new ExerciseBuilder(BENCH_PRESS).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BENCH_PRESS
                + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REP_DESC_BENCH_PRESS + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ARM_CURLS + NAME_DESC_BENCH_PRESS
                + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REP_DESC_BENCH_PRESS + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple weights - last weight accepted
        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_ARM_CURLS
                + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REP_DESC_BENCH_PRESS + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple setss - last sets accepted
        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_ARM_CURLS + SETS_DESC_BENCH_PRESS
                + REP_DESC_BENCH_PRESS + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + REP_DESC_ARM_CURLS
                + REP_DESC_BENCH_PRESS + TAG_DESC_FRIEND, new AddCommand(expectedExercise));

        // multiple tags - all accepted
        Exercise expectedExerciseMultipleTags = new ExerciseBuilder(BENCH_PRESS)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + REP_DESC_BENCH_PRESS
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedExerciseMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Exercise expectedExercise = new ExerciseBuilder(ARM_CURLS).withTags().build();
        assertParseSuccess(parser, NAME_DESC_ARM_CURLS + WEIGHT_DESC_ARM_CURLS
                        + SETS_DESC_ARM_CURLS + REP_DESC_ARM_CURLS,
                new AddCommand(expectedExercise));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + REP_DESC_BENCH_PRESS,
                expectedMessage);

        // missing weight prefix
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + VALID_WEIGHT_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + REP_DESC_BENCH_PRESS,
                expectedMessage);

        // missing sets prefix
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + VALID_SETS_BENCH_PRESS + REP_DESC_BENCH_PRESS,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + VALID_REP_BENCH_PRESS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BENCH_PRESS + VALID_WEIGHT_BENCH_PRESS
                + VALID_SETS_BENCH_PRESS + VALID_REP_BENCH_PRESS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REP_DESC_BENCH_PRESS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid weight
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + INVALID_WEIGHT_DESC + SETS_DESC_BENCH_PRESS
                + REP_DESC_BENCH_PRESS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Weight.MESSAGE_CONSTRAINTS);

        // invalid sets
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS + INVALID_SETS_DESC
                + REP_DESC_BENCH_PRESS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Sets.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + INVALID_REP_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Rep.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + REP_DESC_BENCH_PRESS + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS
                + INVALID_REP_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BENCH_PRESS + WEIGHT_DESC_BENCH_PRESS
                + SETS_DESC_BENCH_PRESS + REP_DESC_BENCH_PRESS + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
