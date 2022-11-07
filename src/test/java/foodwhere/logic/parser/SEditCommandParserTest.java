package foodwhere.logic.parser;

import static foodwhere.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static foodwhere.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static foodwhere.logic.commands.SEditCommand.MESSAGE_INVALID_INDEX_ERROR;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.SEditCommand;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.testutil.EditStallDescriptorBuilder;
import foodwhere.testutil.TypicalIndexes;

public class SEditCommandParserTest {

    private static final String TAG_EMPTY = " " + CliSyntax.PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SEditCommand.MESSAGE_USAGE);

    private SEditCommandParser parser = new SEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_INDEX_ERROR);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX_ERROR);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX_ERROR);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_INDEX_ERROR);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Stall} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_STALL;
        String userInput = targetIndex.getOneBased() + TAG_DESC_HUSBAND
                + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        SEditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        SEditCommand expectedCommand = new SEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_STALL;
        String userInput = targetIndex.getOneBased() + ADDRESS_DESC_BOB;

        SEditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder()
                .withAddress(VALID_ADDRESS_BOB).build();
        SEditCommand expectedCommand = new SEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD_STALL;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        SEditCommand.EditStallDescriptor descriptor =
                new EditStallDescriptorBuilder().withName(VALID_NAME_AMY).build();
        SEditCommand expectedCommand = new SEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditStallDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new SEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // details
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditStallDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new SEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_STALL;
        String userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY
                + TAG_DESC_FRIEND + ADDRESS_DESC_AMY + TAG_DESC_FRIEND
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND;

        SEditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder()
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        SEditCommand expectedCommand = new SEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = TypicalIndexes.INDEX_FIRST_STALL;
        String userInput = targetIndex.getOneBased() + INVALID_ADDRESS_DESC + ADDRESS_DESC_BOB;
        SEditCommand.EditStallDescriptor descriptor =
                new EditStallDescriptorBuilder().withAddress(VALID_ADDRESS_BOB).build();
        SEditCommand expectedCommand = new SEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + ADDRESS_DESC_BOB
                + NAME_DESC_BOB;
        descriptor = new EditStallDescriptorBuilder().withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new SEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetDetails_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD_STALL;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        SEditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder().withTags().build();
        SEditCommand expectedCommand = new SEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
