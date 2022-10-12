package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.commands.CommandTestUtil.DATE_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_REPS_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_SETS_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static gim.logic.commands.CommandTestUtil.NAME_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.REPS_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.REPS_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.SETS_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.SETS_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.WEIGHT_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.WEIGHT_DESC_BENCH_PRESS;
import static gim.logic.parser.CliSyntax.PREFIX_DATE;
import static gim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static gim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static gim.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static gim.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;
import static gim.testutil.TypicalIndexes.INDEX_THIRD_EXERCISE;

import org.junit.jupiter.api.Test;

import gim.commons.core.index.Index;
import gim.logic.commands.EditCommand;
import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.date.Date;
import gim.testutil.EditExerciseDescriptorBuilder;


public class EditCommandParserTest {

    private static final String DATE_EMPTY = " " + PREFIX_DATE;

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ARM_CURLS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ARM_CURLS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ARM_CURLS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_WEIGHT_DESC, Weight.MESSAGE_CONSTRAINTS); // invalid weight
        assertParseFailure(parser, "1" + INVALID_SETS_DESC, Sets.MESSAGE_CONSTRAINTS); // invalid sets

        assertParseFailure(parser, "1" + INVALID_REPS_DESC, Reps.MESSAGE_CONSTRAINTS); // invalid reps
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date

        // invalid weight followed by valid sets
        assertParseFailure(parser, "1" + INVALID_WEIGHT_DESC + SETS_DESC_ARM_CURLS, Weight.MESSAGE_CONSTRAINTS);

        // valid weight followed by invalid weight. The test case for invalid weight followed by valid weight
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + WEIGHT_DESC_BENCH_PRESS + INVALID_WEIGHT_DESC, Weight.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_SETS_DESC + VALID_REPS_ARM_CURLS
                        + VALID_WEIGHT_ARM_CURLS, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXERCISE;
        String userInput = targetIndex.getOneBased() + WEIGHT_DESC_BENCH_PRESS + DATE_DESC
                + SETS_DESC_ARM_CURLS + REPS_DESC_ARM_CURLS + NAME_DESC_ARM_CURLS + DATE_DESC;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withName(VALID_NAME_ARM_CURLS)
                .withWeight(VALID_WEIGHT_BENCH_PRESS).withSets(VALID_SETS_ARM_CURLS).withRep(VALID_REPS_ARM_CURLS)
                .withDates(VALID_DATE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + WEIGHT_DESC_BENCH_PRESS + SETS_DESC_ARM_CURLS;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_BENCH_PRESS)
                .withSets(VALID_SETS_ARM_CURLS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ARM_CURLS;
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withName(VALID_NAME_ARM_CURLS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // weight
        userInput = targetIndex.getOneBased() + WEIGHT_DESC_ARM_CURLS;
        descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_ARM_CURLS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // sets
        userInput = targetIndex.getOneBased() + SETS_DESC_ARM_CURLS;
        descriptor = new EditExerciseDescriptorBuilder().withSets(VALID_SETS_ARM_CURLS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + REPS_DESC_ARM_CURLS;
        descriptor = new EditExerciseDescriptorBuilder().withRep(VALID_REPS_ARM_CURLS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC;
        descriptor = new EditExerciseDescriptorBuilder().withDates(VALID_DATE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXERCISE;

        String userInput = targetIndex.getOneBased() + WEIGHT_DESC_ARM_CURLS + REPS_DESC_ARM_CURLS + SETS_DESC_ARM_CURLS
                + DATE_DESC + WEIGHT_DESC_ARM_CURLS + REPS_DESC_ARM_CURLS + SETS_DESC_ARM_CURLS + DATE_DESC
                + WEIGHT_DESC_BENCH_PRESS + REPS_DESC_BENCH_PRESS + SETS_DESC_BENCH_PRESS + DATE_DESC;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_BENCH_PRESS)
                .withSets(VALID_SETS_BENCH_PRESS).withRep(VALID_REPS_BENCH_PRESS)
                .withDates(VALID_DATE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + INVALID_WEIGHT_DESC + WEIGHT_DESC_BENCH_PRESS;
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder()
                .withWeight(VALID_WEIGHT_BENCH_PRESS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + SETS_DESC_BENCH_PRESS + INVALID_WEIGHT_DESC + REPS_DESC_BENCH_PRESS
                + WEIGHT_DESC_BENCH_PRESS;
        descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_BENCH_PRESS)
                .withSets(VALID_SETS_BENCH_PRESS).withRep(VALID_REPS_BENCH_PRESS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + DATE_DESC;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withDates(VALID_DATE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
