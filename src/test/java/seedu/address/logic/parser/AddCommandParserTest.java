package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + ADDRESS_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedClient, ""));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB
                + ADDRESS_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedClient, ""));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB
                + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_FRIEND
                + PHONE_DESC_BOB, new AddCommand(expectedClient, ""));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB
                + ADDRESS_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedClient, ""));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedClient, ""));

        // multiple tags - all accepted
        Client expectedClientMultipleTags = new ClientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();

        assertParseSuccess(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedClientMultipleTags, ""));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Client expectedClient = new ClientBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + ADDRESS_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new AddCommand(expectedClient, ""));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + ADDRESS_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_ADDRESS_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_ADDRESS_BOB
                        + VALID_PHONE_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ADDRESS_DESC
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ADDRESS_DESC
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                        + ADDRESS_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
