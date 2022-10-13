package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.intrack.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.intrack.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.intrack.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.intrack.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static seedu.intrack.logic.commands.CommandTestUtil.POSITION_DESC_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.intrack.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.intrack.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.intrack.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.intrack.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intrack.testutil.TypicalInternships.AMY;
import static seedu.intrack.testutil.TypicalInternships.BOB;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.AddCommand;
import seedu.intrack.model.internship.Address;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.tag.Tag;
import seedu.intrack.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + POSITION_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple status - last status accepted
        assertParseSuccess(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + STATUS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STATUS_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + STATUS_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + POSITION_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + STATUS_DESC_AMY
                        + ADDRESS_DESC_AMY,
                new AddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + STATUS_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + ADDRESS_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_ADDRESS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_POSITION_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_ADDRESS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + STATUS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_STATUS_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Status.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + STATUS_DESC_BOB + INVALID_ADDRESS_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + POSITION_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + STATUS_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
