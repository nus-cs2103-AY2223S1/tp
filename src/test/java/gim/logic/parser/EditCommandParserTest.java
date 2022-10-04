package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static gim.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static gim.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_SETS_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static gim.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static gim.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static gim.logic.commands.CommandTestUtil.SETS_DESC_AMY;
import static gim.logic.commands.CommandTestUtil.SETS_DESC_BOB;
import static gim.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static gim.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static gim.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static gim.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static gim.logic.commands.CommandTestUtil.WEIGHT_DESC_BOB;
import static gim.logic.parser.CliSyntax.PREFIX_TAG;
import static gim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static gim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static gim.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static gim.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;
import static gim.testutil.TypicalIndexes.INDEX_THIRD_EXERCISE;

import org.junit.jupiter.api.Test;

import gim.commons.core.index.Index;
import gim.logic.commands.EditCommand;
import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.model.exercise.Address;
import gim.model.exercise.Name;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.tag.Tag;
import gim.testutil.EditExerciseDescriptorBuilder;



public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

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
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid weight followed by valid sets
        assertParseFailure(parser, "1" + INVALID_WEIGHT_DESC + SETS_DESC_AMY, Weight.MESSAGE_CONSTRAINTS);

        // valid weight followed by invalid weight. The test case for invalid weight followed by valid weight
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + WEIGHT_DESC_BOB + INVALID_WEIGHT_DESC, Weight.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Exercise} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_SETS_DESC + VALID_ADDRESS_AMY + VALID_WEIGHT_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXERCISE;
        String userInput = targetIndex.getOneBased() + WEIGHT_DESC_BOB + TAG_DESC_HUSBAND
                + SETS_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withName(VALID_NAME_AMY)
                .withWeight(VALID_WEIGHT_BOB).withSets(VALID_SETS_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + WEIGHT_DESC_BOB + SETS_DESC_AMY;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_BOB)
                .withSets(VALID_SETS_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // weight
        userInput = targetIndex.getOneBased() + WEIGHT_DESC_AMY;
        descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // sets
        userInput = targetIndex.getOneBased() + SETS_DESC_AMY;
        descriptor = new EditExerciseDescriptorBuilder().withSets(VALID_SETS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditExerciseDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditExerciseDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + WEIGHT_DESC_AMY + ADDRESS_DESC_AMY + SETS_DESC_AMY
                + TAG_DESC_FRIEND + WEIGHT_DESC_AMY + ADDRESS_DESC_AMY + SETS_DESC_AMY + TAG_DESC_FRIEND
                + WEIGHT_DESC_BOB + ADDRESS_DESC_BOB + SETS_DESC_BOB + TAG_DESC_HUSBAND;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_BOB)
                .withSets(VALID_SETS_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + INVALID_WEIGHT_DESC + WEIGHT_DESC_BOB;
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + SETS_DESC_BOB + INVALID_WEIGHT_DESC + ADDRESS_DESC_BOB
                + WEIGHT_DESC_BOB;
        descriptor = new EditExerciseDescriptorBuilder().withWeight(VALID_WEIGHT_BOB).withSets(VALID_SETS_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
