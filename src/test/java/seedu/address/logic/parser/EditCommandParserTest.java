package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REMARK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOODS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOODS_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_BUY_ORANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditRemarkCommand;
import seedu.address.logic.commands.EditTransactionCommand;
import seedu.address.model.client.Address;
import seedu.address.model.client.Name;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.EditTransactionDescriptorBuilder;
import seedu.address.testutil.RemarkBuilder;

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
        assertParseFailure(parser, "1 m/client", EditClientCommand.MESSAGE_NOT_EDITED);

        assertParseFailure(parser, "1 m/transaction", EditTransactionCommand.MESSAGE_NOT_EDITED);

        assertParseFailure(parser, "1 m/remark", EditRemarkCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 m/client" + NAME_DESC_AMY, MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);

        // negative index
        assertParseFailure(parser, "-5 m/transaction", MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);

        // negative index
        assertParseFailure(parser, "-5 m/remark" + NAME_DESC_AMY, MESSAGE_INVALID_REMARK_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1 m/client" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1 m/client" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1 m/client" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Client} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1 m/client" + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 m/client" + TAG_DESC_FRIEND
                + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 m/client" + TAG_EMPTY
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1 m/client" + INVALID_NAME_DESC + VALID_ADDRESS_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_clientAllFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInput = targetIndex.getOneBased() + " m/client" + TAG_DESC_HUSBAND + ADDRESS_DESC_AMY
                + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor, "");

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_CLIENT;
        String userInput = targetIndex.getOneBased() + " m/client" + NAME_DESC_AMY;
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_AMY).build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor, "");
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + " m/client" + ADDRESS_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor, "");
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + " m/client" + PHONE_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder()
                .withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor, "");
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + " m/client" + EMAIL_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder()
                .withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor, "");
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + " m/client" + TAG_DESC_FRIEND;
        descriptor = new EditClientDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor, "");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecifiedTransaction_success() {
        // goods
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + " m/transaction" + VALID_GOODS;
        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withGoods(VALID_GOODS_BUY_ORANGE).build();
        EditTransactionCommand expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + " m/transaction" + VALID_QUANTITY;
        descriptor = new EditTransactionDescriptorBuilder().withQuantity(VALID_QUANTITY_BUY_ORANGE).build();
        expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price
        userInput = targetIndex.getOneBased() + " m/transaction" + VALID_PRICE;
        descriptor = new EditTransactionDescriptorBuilder()
                .withPrice(VALID_PRICE_BUY_ORANGE).build();
        expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + " m/transaction" + VALID_DATE_WITH_PREFIX;
        descriptor = new EditTransactionDescriptorBuilder()
                .withDate(VALID_DATE).build();
        expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // No fields
        userInput = targetIndex.getOneBased() + " m/transaction";
        assertParseFailure(parser, userInput, EditTransactionCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_oneFieldRemark_success() {
        Remark editedRemark = new RemarkBuilder().withText("TestRemark").build();
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + " m/remark " + editedRemark.toString();
        System.out.println(userInput);
        EditRemarkCommand expectedCommand = new EditRemarkCommand(targetIndex, editedRemark);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = targetIndex.getOneBased() + " m/client" + ADDRESS_DESC_AMY
                + TAG_DESC_FRIEND + ADDRESS_DESC_AMY + TAG_DESC_FRIEND
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND;

        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor, "");

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_CLIENT;
        String userInput = targetIndex.getOneBased() + " m/client" + TAG_EMPTY;

        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withTags().build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor, "");

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noModeTag_invalidCommand() {
        Index targetIndex = INDEX_THIRD_CLIENT;
        String userInput = targetIndex.getOneBased() + VALID_PRICE;

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }
}
