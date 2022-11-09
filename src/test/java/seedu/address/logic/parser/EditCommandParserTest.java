package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_PRODUCT1;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_PRODUCT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ELEMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ELEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.product.Product;
import seedu.address.testutil.EditClientDescriptorBuilder;

public class EditCommandParserTest {

    private static final String PRODUCT_EMPTY = " " + PREFIX_PRODUCT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE);

    private EditClientCommandParser parser = new EditClientCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " i/1", EditClientCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, " i/-5" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, " i/0" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, " i/1 some random string", MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, " i/1 i/ string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " i/1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " i/1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, " i/1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, " i/1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, " i/1" + INVALID_PRODUCT_DESC, Product.MESSAGE_CONSTRAINTS); // invalid product

        // invalid phone followed by valid email
        assertParseFailure(parser, " i/1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, " i/1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_PRODUCT} alone will reset the products of the {@code Client} being edited,
        // parsing it together with a valid product results in error
        assertParseFailure(parser, " i/1" + PRODUCT_DESC_PRODUCT1 + PRODUCT_DESC_PRODUCT2 + PRODUCT_EMPTY,
                Product.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " i/1" + PRODUCT_DESC_PRODUCT1 + PRODUCT_EMPTY + PRODUCT_DESC_PRODUCT2,
                Product.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " i/1" + PRODUCT_EMPTY + PRODUCT_DESC_PRODUCT1 + PRODUCT_DESC_PRODUCT2,
                Product.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " i/1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                        + VALID_ADDRESS_AMY + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ELEMENT;
        String userInput = " i/" + targetIndex.getOneBased() + PHONE_DESC_BOB + PRODUCT_DESC_PRODUCT2
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + PRODUCT_DESC_PRODUCT1;

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withProducts(VALID_PRODUCT_2, VALID_PRODUCT_1).build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ELEMENT;
        String userInput = " i/" + targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ELEMENT;
        String userInput = " i/" + targetIndex.getOneBased() + NAME_DESC_AMY;
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = " i/" + targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = " i/" + targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = " i/" + targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditClientDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Products
        userInput = " i/" + targetIndex.getOneBased() + PRODUCT_DESC_PRODUCT1;
        descriptor = new EditClientDescriptorBuilder().withProducts(VALID_PRODUCT_1).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ELEMENT;
        String userInput = " i/" + targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PRODUCT_DESC_PRODUCT1 + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + PRODUCT_DESC_PRODUCT1
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + PRODUCT_DESC_PRODUCT2;

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withProducts(VALID_PRODUCT_1, VALID_PRODUCT_2).build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ELEMENT;
        String userInput = " i/" + targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = " i/" + targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditClientDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditClientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetProducts_success() {
        Index targetIndex = INDEX_THIRD_ELEMENT;
        String userInput = " i/" + targetIndex.getOneBased() + PRODUCT_EMPTY;

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withProducts().build();
        EditClientCommand expectedCommand = new EditClientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
