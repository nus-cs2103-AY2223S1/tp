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
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProperties.HOME;
import static seedu.address.testutil.TypicalProperties.PROPERTY1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.AddPropertyCommand;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Description;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.testutil.PropertyBuilder;

public class AddPropertyCommandParserTest {
    private AddPropertyCommandParser parser = new AddPropertyCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Property expectedProperty = new PropertyBuilder(PROPERTY1).build();

        // Valid command
        assertParseSuccess(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1
                + ADDRESS_DESC_PROPERTY1 + DESCRIPTION_DESC_PROPERTY1 + CHARACTERISTICS_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, new AddPropertyCommand(expectedProperty));

        // Multiple names - only last name accepted
        assertParseSuccess(parser, NAME_DESC_HOME + NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1
                + ADDRESS_DESC_PROPERTY1 + DESCRIPTION_DESC_PROPERTY1 + CHARACTERISTICS_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, new AddPropertyCommand(expectedProperty));

        // Multiple prices - only last description accepted
        assertParseSuccess(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_HOME + PRICE_DESC_PROPERTY1
                + ADDRESS_DESC_PROPERTY1 + DESCRIPTION_DESC_PROPERTY1 + CHARACTERISTICS_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, new AddPropertyCommand(expectedProperty));

        // Multiple addresses - only last address accepted
        assertParseSuccess(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + ADDRESS_DESC_HOME
                + ADDRESS_DESC_PROPERTY1 + DESCRIPTION_DESC_PROPERTY1 + CHARACTERISTICS_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, new AddPropertyCommand(expectedProperty));

        // Multiple descriptions - only last description accepted
        assertParseSuccess(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1
                + DESCRIPTION_DESC_HOME + DESCRIPTION_DESC_PROPERTY1 + CHARACTERISTICS_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, new AddPropertyCommand(expectedProperty));

        // Multiple characteristics - only last characteristics accepted
        assertParseSuccess(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1
                + DESCRIPTION_DESC_PROPERTY1 + CHARACTERISTICS_DESC_HOME + CHARACTERISTICS_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, new AddPropertyCommand(expectedProperty));

        // Multiple owner names - only last owner name accepted
        assertParseSuccess(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1
                + DESCRIPTION_DESC_PROPERTY1 + CHARACTERISTICS_DESC_PROPERTY1 + OWNER_NAME_DESC_HOME
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, new AddPropertyCommand(expectedProperty));

        // Multiple owner phone numbers - only last owner phone accepted
        assertParseSuccess(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1
                + DESCRIPTION_DESC_PROPERTY1 + CHARACTERISTICS_DESC_PROPERTY1 + OWNER_NAME_DESC_PROPERTY1
                + OWNER_PHONE_DESC_HOME + OWNER_PHONE_DESC_PROPERTY1, new AddPropertyCommand(expectedProperty));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Property expectedProperty = new PropertyBuilder(HOME).withNoCharacteristics().build();
        assertParseSuccess(parser, NAME_DESC_HOME + PRICE_DESC_HOME + ADDRESS_DESC_HOME
                        + DESCRIPTION_DESC_HOME + OWNER_NAME_DESC_HOME + OWNER_PHONE_DESC_HOME,
                new AddPropertyCommand(expectedProperty));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PRICE_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1 + DESCRIPTION_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, expectedMessage);

        // missing price prefix
        assertParseFailure(parser, NAME_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1 + DESCRIPTION_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + DESCRIPTION_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1
                + OWNER_NAME_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, expectedMessage);

        // missing owner name prefix
        assertParseFailure(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1
                + DESCRIPTION_DESC_PROPERTY1 + OWNER_PHONE_DESC_PROPERTY1, expectedMessage);

        // missing owner phone number prefix
        assertParseFailure(parser, NAME_DESC_PROPERTY1 + PRICE_DESC_PROPERTY1 + ADDRESS_DESC_PROPERTY1
                + DESCRIPTION_DESC_PROPERTY1 + OWNER_NAME_DESC_PROPERTY1, expectedMessage);
    }

    @Test
    public void parse_invalidFieldValueSupplied_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PRICE_DESC_HOME + ADDRESS_DESC_HOME
                + DESCRIPTION_DESC_HOME + CHARACTERISTICS_DESC_HOME + OWNER_NAME_DESC_HOME
                + OWNER_PHONE_DESC_HOME, PropertyName.MESSAGE_CONSTRAINTS);

        // invalid price
        assertParseFailure(parser, NAME_DESC_HOME + INVALID_PRICE_DESC + ADDRESS_DESC_HOME
                + DESCRIPTION_DESC_HOME + CHARACTERISTICS_DESC_HOME + OWNER_NAME_DESC_HOME
                + OWNER_PHONE_DESC_HOME, Price.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_HOME + PRICE_DESC_HOME + INVALID_ADDRESS_DESC
                + DESCRIPTION_DESC_HOME + CHARACTERISTICS_DESC_HOME + OWNER_NAME_DESC_HOME
                + OWNER_PHONE_DESC_HOME, Address.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_HOME + PRICE_DESC_HOME + ADDRESS_DESC_HOME
                + INVALID_DESCRIPTION_DESC + CHARACTERISTICS_DESC_HOME + OWNER_NAME_DESC_HOME
                + OWNER_PHONE_DESC_HOME, Description.MESSAGE_CONSTRAINTS);

        // invalid characteristics
        assertParseFailure(parser, NAME_DESC_HOME + PRICE_DESC_HOME + ADDRESS_DESC_HOME
                + DESCRIPTION_DESC_HOME + INVALID_CHARACTERISTICS_DESC + OWNER_NAME_DESC_HOME
                + OWNER_PHONE_DESC_HOME, Characteristics.MESSAGE_CONSTRAINTS);

        // invalid owner name
        assertParseFailure(parser, NAME_DESC_HOME + PRICE_DESC_HOME + ADDRESS_DESC_HOME
                + DESCRIPTION_DESC_HOME + CHARACTERISTICS_DESC_HOME + INVALID_OWNER_NAME_DESC
                + OWNER_PHONE_DESC_HOME, Name.MESSAGE_CONSTRAINTS);

        // invalid owner phone
        assertParseFailure(parser, NAME_DESC_HOME + PRICE_DESC_HOME + ADDRESS_DESC_HOME
                + DESCRIPTION_DESC_HOME + CHARACTERISTICS_DESC_HOME + OWNER_NAME_DESC_HOME
                + INVALID_OWNER_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, "TESTESTESTESTESTEST " + NAME_DESC_HOME
                + PRICE_DESC_HOME + ADDRESS_DESC_HOME + DESCRIPTION_DESC_HOME
                + CHARACTERISTICS_DESC_HOME + OWNER_NAME_DESC_HOME
                + OWNER_PHONE_DESC_HOME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
    }
}
