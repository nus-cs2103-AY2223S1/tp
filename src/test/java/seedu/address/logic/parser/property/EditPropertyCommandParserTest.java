package seedu.address.logic.parser.property;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.PropertyCommandTestUtil.ADDRESS_DESC_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.ADDRESS_DESC_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.CHARACTERISTICS_DESC_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.CHARACTERISTICS_DESC_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.DESCRIPTION_DESC_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.DESCRIPTION_DESC_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.PropertyCommandTestUtil.INVALID_CHARACTERISTICS_DESC;
import static seedu.address.logic.commands.PropertyCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.PropertyCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.PropertyCommandTestUtil.INVALID_OWNER_NAME_DESC;
import static seedu.address.logic.commands.PropertyCommandTestUtil.INVALID_OWNER_PHONE_DESC;
import static seedu.address.logic.commands.PropertyCommandTestUtil.INVALID_PRICE_DESC;
import static seedu.address.logic.commands.PropertyCommandTestUtil.NAME_DESC_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.NAME_DESC_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.OWNER_NAME_DESC_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.OWNER_NAME_DESC_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.OWNER_PHONE_DESC_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.OWNER_PHONE_DESC_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.PRICE_DESC_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.PRICE_DESC_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_ADDRESS_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_ADDRESS_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_CHARACTERISTICS_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_CHARACTERISTICS_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_DESCRIPTION_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_DESCRIPTION_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_NAME_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_NAME_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_OWNER_NAME_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_OWNER_NAME_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_OWNER_PHONE_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_OWNER_PHONE_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_PRICE_HOME;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_PRICE_PROPERTY1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Description;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropertyName;
import seedu.address.testutil.EditPropertyDescriptorBuilder;

public class EditPropertyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPropertyCommand.MESSAGE_USAGE);

    private EditPropertyCommandParser parser = new EditPropertyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, NAME_DESC_PROPERTY1, MESSAGE_INVALID_FORMAT);

        // no fields specified
        assertParseFailure(parser, "1", EditPropertyCommand.MESSAGE_NOT_EDITED);

        // no index, no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_PROPERTY1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_PROPERTY1, MESSAGE_INVALID_FORMAT);

        // random text in preamble
        assertParseFailure(parser, "1 some random string" + NAME_DESC_PROPERTY1, MESSAGE_INVALID_FORMAT);

        // overflow index
        assertParseFailure(parser, "99999999999999999999999999" + NAME_DESC_PROPERTY1,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, PropertyName.MESSAGE_CONSTRAINTS);

        // invalid price
        assertParseFailure(parser, "1" + INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);

        // invalid characteristics
        assertParseFailure(parser, "1" + INVALID_CHARACTERISTICS_DESC, Characteristics.MESSAGE_CONSTRAINTS);

        // invalid owner name
        assertParseFailure(parser, "1" + INVALID_OWNER_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid owner phone
        assertParseFailure(parser, "1" + INVALID_OWNER_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // valid field followed by invalid field
        assertParseFailure(parser, "1" + PRICE_DESC_HOME + INVALID_NAME_DESC , Name.MESSAGE_CONSTRAINTS);

        // valid field followed by same field, but invalid
        assertParseFailure(parser, "1" + NAME_DESC_HOME + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_ADDRESS_DESC + INVALID_PRICE_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITEM;
        String userInput = targetIndex.getOneBased() + NAME_DESC_HOME + PRICE_DESC_HOME + ADDRESS_DESC_HOME
                + DESCRIPTION_DESC_HOME + CHARACTERISTICS_DESC_HOME + OWNER_NAME_DESC_HOME + OWNER_PHONE_DESC_HOME;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_HOME)
                .withPrice(VALID_PRICE_HOME).withAddress(VALID_ADDRESS_HOME)
                .withDescription(VALID_DESCRIPTION_HOME).withCharacteristics(VALID_CHARACTERISTICS_HOME)
                .withOwnerName(VALID_OWNER_NAME_HOME).withOwnerPhone(VALID_OWNER_PHONE_HOME).build();

        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1
                + PRICE_DESC_PROPERTY1;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_PROPERTY1)
                .withAddress(VALID_ADDRESS_PROPERTY1).withPrice(VALID_PRICE_PROPERTY1).build();

        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name supplied only
        Index targetIndex = INDEX_THIRD_ITEM;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PROPERTY1;
        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withName(VALID_NAME_PROPERTY1).build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price supplied only
        userInput = targetIndex.getOneBased() + PRICE_DESC_PROPERTY1;
        descriptor = new EditPropertyDescriptorBuilder()
                .withPrice(VALID_PRICE_PROPERTY1).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address supplied only
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_PROPERTY1;
        descriptor = new EditPropertyDescriptorBuilder()
                .withAddress(VALID_ADDRESS_PROPERTY1).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description supplied only
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_PROPERTY1;
        descriptor = new EditPropertyDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_PROPERTY1).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // characteristics supplied only
        userInput = targetIndex.getOneBased() + CHARACTERISTICS_DESC_PROPERTY1;
        descriptor = new EditPropertyDescriptorBuilder()
                .withCharacteristics(VALID_CHARACTERISTICS_PROPERTY1).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // owner name supplied only
        userInput = targetIndex.getOneBased() + OWNER_NAME_DESC_PROPERTY1;
        descriptor = new EditPropertyDescriptorBuilder()
                .withOwnerName(VALID_OWNER_NAME_PROPERTY1).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // owner phone supplied only
        userInput = targetIndex.getOneBased() + OWNER_PHONE_DESC_PROPERTY1;
        descriptor = new EditPropertyDescriptorBuilder()
                .withOwnerPhone(VALID_OWNER_PHONE_PROPERTY1).build();
        expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleValidRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PROPERTY1 + NAME_DESC_HOME;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_HOME).build();

        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_SECOND_ITEM;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_PROPERTY1;

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder().withName(VALID_NAME_PROPERTY1)
                .build();
        EditPropertyCommand expectedCommand = new EditPropertyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
