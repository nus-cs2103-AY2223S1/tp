package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.BuyerCommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESIRED_CHARACTERISTICS_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_DESIRED_CHARACTERISTICS_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PRICE_RANGE_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PRICE_RANGE_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.TAG_DESC_PRIORITY_HIGH;
import static seedu.address.logic.commands.BuyerCommandTestUtil.TAG_DESC_PRIORITY_LOW;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_DESIRED_CHARACTERISTICS_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRICE_RANGE_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_LOW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.buyer.EditBuyerCommand;
import seedu.address.logic.commands.buyer.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.logic.parser.buyer.EditBuyerCommandParser;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.testutil.EditBuyerDescriptorBuilder;

public class EditBuyerCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_PRIORITY;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBuyerCommand.MESSAGE_USAGE);

    private EditBuyerCommandParser parser = new EditBuyerCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_INDEX);

        // no field specified
        assertParseFailure(parser, "1", EditBuyerCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_PRICE_RANGE_DESC, PriceRange.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_DESIRED_CHARACTERISTICS_DESC,
                Characteristics.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        //        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Buyer} being edited,
        //        // parsing it together with a valid tag results in error
        //        assertParseFailure(parser, "1" +
        //        TAG_DESC_PRIORITY_HIGH + TAG_DESC_PRIORITY_LOW + TAG_EMPTY, Priority.MESSAGE_CONSTRAINTS);
        //        assertParseFailure(parser, "1" +
        //        TAG_DESC_PRIORITY_HIGH + TAG_EMPTY + TAG_DESC_PRIORITY_LOW, Priority.MESSAGE_CONSTRAINTS);
        //        assertParseFailure(parser, "1" +
        //        TAG_EMPTY + TAG_DESC_PRIORITY_HIGH + TAG_DESC_PRIORITY_LOW, Priority.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITEM;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_PRIORITY_LOW
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY
                + PRICE_RANGE_DESC_AMY + DESIRED_CHARACTERISTICS_DESC_AMY + TAG_DESC_PRIORITY_HIGH;

        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withPriceRange(VALID_PRICE_RANGE_AMY).withDesiredCharacteristics(VALID_DESIRED_CHARACTERISTICS_AMY)
                .withPriority(VALID_PRIORITY_HIGH).build();
        EditBuyerCommand expectedCommand = new EditBuyerCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditBuyerCommand expectedCommand = new EditBuyerCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditBuyerCommand expectedCommand = new EditBuyerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditBuyerDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditBuyerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditBuyerDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditBuyerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditBuyerDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditBuyerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_PRIORITY_HIGH;
        descriptor = new EditBuyerDescriptorBuilder().withPriority(VALID_PRIORITY_HIGH).build();
        expectedCommand = new EditBuyerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_PRIORITY_HIGH
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_PRIORITY_LOW;

        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withPriority(VALID_PRIORITY_LOW)
                .build();
        EditBuyerCommand expectedCommand = new EditBuyerCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditBuyerCommand expectedCommand = new EditBuyerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditBuyerDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditBuyerCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
