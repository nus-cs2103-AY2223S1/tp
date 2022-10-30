package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_ADDRESS_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_ADDRESS_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_NAME_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_NAME_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_TAG_FRIEND;
import static seedu.condonery.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.condonery.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.condonery.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.condonery.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.condonery.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_ADDRESS_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_ADDRESS_DESC_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_NAME_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_TAGS_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_VALID_ADDRESS_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_VALID_NAME_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_VALID_TAG_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.condonery.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.property.EditPropertyCommand;
import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.tag.Tag;
import seedu.condonery.testutil.EditPropertyDescriptorBuilder;

public class EditPropertyCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPropertyCommand.MESSAGE_USAGE);

    private final EditPropertyCommandParser parser = new EditPropertyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PROPERTY_VALID_NAME_SCOTTS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPropertyCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PROPERTY_NAME_DESC_SCOTTS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PROPERTY_NAME_DESC_SCOTTS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_ADDRESS_DESC,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PROPERTY_TAGS_DESC_SCOTTS
                + PROPERTY_ADDRESS_DESC_SCOTTS + PROPERTY_NAME_DESC_SCOTTS;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(PROPERTY_VALID_NAME_SCOTTS)
            .withAddress(PROPERTY_VALID_ADDRESS_SCOTTS)
            .withTags(PROPERTY_VALID_TAG_SCOTTS).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BOB;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(CLIENT_VALID_NAME_BOB).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(CLIENT_VALID_NAME_AMY).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPropertyDescriptorBuilder().withAddress(CLIENT_VALID_ADDRESS_AMY).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPropertyDescriptorBuilder().withTags(CLIENT_VALID_TAG_FRIEND).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PROPERTY_ADDRESS_DESC_SCOTTS + PROPERTY_NAME_DESC_SCOTTS
            + PROPERTY_TAGS_DESC_SCOTTS + PROPERTY_ADDRESS_DESC_SCOTTS + PROPERTY_ADDRESS_DESC_WHISTLER
            + PROPERTY_ADDRESS_DESC_SCOTTS;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
            .withName(PROPERTY_VALID_NAME_SCOTTS)
            .withAddress(PROPERTY_VALID_ADDRESS_SCOTTS).withTags(PROPERTY_VALID_TAG_SCOTTS)
            .build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_BOB;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(CLIENT_VALID_NAME_BOB).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + ADDRESS_DESC_BOB + NAME_DESC_BOB;
        descriptor = new EditPropertyDescriptorBuilder().withName(CLIENT_VALID_NAME_BOB)
            .withAddress(CLIENT_VALID_ADDRESS_BOB).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withTags().build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
