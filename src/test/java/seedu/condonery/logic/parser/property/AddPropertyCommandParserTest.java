package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_ADDRESS_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.CLIENT_VALID_NAME_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.condonery.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.condonery.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.condonery.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.condonery.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.condonery.logic.commands.CommandTestUtil.PRICE_DESC_BOB;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_ADDRESS_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_ADDRESS_DESC_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_NAME_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_NAME_DESC_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_PRICE_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_PRICE_DESC_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_STATUS_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_TAGS_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_TAGS_DESC_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_TYPE_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_TYPE_DESC_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_VALID_TAG_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_VALID_TAG_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.condonery.testutil.TypicalProperties.SCOTTS;
import static seedu.condonery.testutil.TypicalProperties.WHISTLER;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.AddPropertyCommand;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.Tag;
import seedu.condonery.testutil.PropertyBuilder;

public class AddPropertyCommandParserTest {
    private final AddPropertyCommandParser parser = new AddPropertyCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Property expectedProperty = new PropertyBuilder(SCOTTS).withTags(PROPERTY_VALID_TAG_SCOTTS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PROPERTY_NAME_DESC_SCOTTS + PROPERTY_PRICE_DESC_SCOTTS
            + PROPERTY_ADDRESS_DESC_SCOTTS + PROPERTY_TAGS_DESC_SCOTTS + PROPERTY_TYPE_DESC_SCOTTS
                + PROPERTY_STATUS_DESC_SCOTTS,
                new AddPropertyCommand(expectedProperty));

        // multiple names - last name accepted
        assertParseSuccess(parser, PROPERTY_NAME_DESC_SCOTTS + PROPERTY_NAME_DESC_WHISTLER
                + PROPERTY_NAME_DESC_SCOTTS
                + PROPERTY_PRICE_DESC_SCOTTS
                + PROPERTY_ADDRESS_DESC_SCOTTS
                + PROPERTY_TAGS_DESC_SCOTTS
                + PROPERTY_STATUS_DESC_SCOTTS
                + PROPERTY_TYPE_DESC_SCOTTS, new AddPropertyCommand(expectedProperty));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, PROPERTY_NAME_DESC_SCOTTS
                + PROPERTY_ADDRESS_DESC_WHISTLER
                + PROPERTY_ADDRESS_DESC_SCOTTS
                + PROPERTY_TAGS_DESC_SCOTTS
                + PROPERTY_PRICE_DESC_SCOTTS
                + PROPERTY_TYPE_DESC_SCOTTS
                + PROPERTY_STATUS_DESC_SCOTTS, new AddPropertyCommand(expectedProperty));

        // multiple tags - all accepted
        Property expectedPropertyMultipleTags =
                new PropertyBuilder(WHISTLER).withTags(PROPERTY_VALID_TAG_WHISTLER, PROPERTY_VALID_TAG_SCOTTS).build();
        assertParseSuccess(parser, PROPERTY_NAME_DESC_WHISTLER
                + PROPERTY_ADDRESS_DESC_WHISTLER
                + PROPERTY_PRICE_DESC_WHISTLER
                + PROPERTY_TAGS_DESC_WHISTLER
                + PROPERTY_TYPE_DESC_WHISTLER
                + PROPERTY_TAGS_DESC_SCOTTS
                + PROPERTY_STATUS_DESC_SCOTTS, new AddPropertyCommand(expectedPropertyMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Property expectedProperty = new PropertyBuilder(SCOTTS).withTags().build();
        assertParseSuccess(parser, PROPERTY_NAME_DESC_SCOTTS + PROPERTY_ADDRESS_DESC_SCOTTS
                        + PROPERTY_PRICE_DESC_SCOTTS + PROPERTY_TYPE_DESC_SCOTTS
                        + PROPERTY_STATUS_DESC_SCOTTS,
            new AddPropertyCommand(expectedProperty));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CLIENT_VALID_NAME_BOB + ADDRESS_DESC_BOB + PRICE_DESC_BOB,
            expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + CLIENT_VALID_ADDRESS_BOB + PRICE_DESC_BOB,
            expectedMessage);

        // missing price prefix
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + VALID_PRICE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CLIENT_VALID_NAME_BOB + CLIENT_VALID_ADDRESS_BOB + VALID_PRICE_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PROPERTY_ADDRESS_DESC_SCOTTS + PROPERTY_PRICE_DESC_SCOTTS
            + PROPERTY_TAGS_DESC_SCOTTS + PROPERTY_TYPE_DESC_SCOTTS, Name.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, PROPERTY_NAME_DESC_SCOTTS + INVALID_ADDRESS_DESC + PROPERTY_PRICE_DESC_SCOTTS
            + PROPERTY_TAGS_DESC_SCOTTS + PROPERTY_TYPE_DESC_SCOTTS, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, PROPERTY_NAME_DESC_SCOTTS + PROPERTY_ADDRESS_DESC_SCOTTS + PROPERTY_PRICE_DESC_SCOTTS
            + INVALID_TAG_DESC + PROPERTY_TYPE_DESC_SCOTTS, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ADDRESS_DESC + PROPERTY_PRICE_DESC_SCOTTS
                        + PROPERTY_TAGS_DESC_SCOTTS + PROPERTY_TYPE_DESC_SCOTTS,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PROPERTY_NAME_DESC_SCOTTS
                + PROPERTY_ADDRESS_DESC_SCOTTS + PROPERTY_PRICE_DESC_SCOTTS + PROPERTY_TAGS_DESC_SCOTTS,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
    }
}
