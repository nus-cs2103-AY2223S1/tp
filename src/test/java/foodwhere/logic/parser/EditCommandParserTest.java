package foodwhere.logic.parser;

import static foodwhere.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.DETAIL_DESC_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.DETAIL_DESC_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_DETAIL_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static foodwhere.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DETAIL_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DETAIL_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.EditCommand;
import foodwhere.model.detail.Detail;
import foodwhere.model.stall.Address;
import foodwhere.model.stall.Name;
import foodwhere.model.stall.Phone;
import foodwhere.testutil.EditPersonDescriptorBuilder;
import foodwhere.testutil.TypicalIndexes;

public class EditCommandParserTest {

    private static final String DETAIL_EMPTY = " " + CliSyntax.PREFIX_DETAIL;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

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
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_DETAIL_DESC, Detail.MESSAGE_CONSTRAINTS); // invalid detail

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_DETAIL} alone will reset the details of the {@code Stall} being edited,
        // parsing it together with a valid detail results in error
        assertParseFailure(parser, "1" + DETAIL_DESC_FRIEND + DETAIL_DESC_HUSBAND + DETAIL_EMPTY,
                Detail.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + DETAIL_DESC_FRIEND + DETAIL_EMPTY + DETAIL_DESC_HUSBAND,
                Detail.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + DETAIL_EMPTY + DETAIL_DESC_FRIEND + DETAIL_DESC_HUSBAND,
                Detail.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + DETAIL_DESC_HUSBAND
                + ADDRESS_DESC_AMY + NAME_DESC_AMY + DETAIL_DESC_FRIEND;

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_AMY)
                .withDetails(VALID_DETAIL_HUSBAND, VALID_DETAIL_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB;

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // details
        userInput = targetIndex.getOneBased() + DETAIL_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withDetails(VALID_DETAIL_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + DETAIL_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + DETAIL_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + DETAIL_DESC_HUSBAND;

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withDetails(VALID_DETAIL_FRIEND, VALID_DETAIL_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetDetails_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + DETAIL_EMPTY;

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withDetails().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
