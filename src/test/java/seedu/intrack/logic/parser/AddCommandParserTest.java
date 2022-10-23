package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.commands.CommandTestUtil.EMAIL_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.EMAIL_DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_WEBSITE_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.NAME_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.NAME_DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.PHONE_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.PHONE_DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.POSITION_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.POSITION_DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.intrack.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.intrack.logic.commands.CommandTestUtil.TAG_DESC_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.TAG_DESC_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.WEBSITE_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.WEBSITE_DESC_MSFT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intrack.testutil.TypicalInternships.MSFT;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.AddCommand;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;
import seedu.intrack.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(MSFT).withTags(VALID_TAG_REMOTE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT
                + EMAIL_DESC_MSFT + WEBSITE_DESC_MSFT + TAG_DESC_REMOTE, new AddCommand(expectedInternship));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AAPL + NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT
                + EMAIL_DESC_MSFT + WEBSITE_DESC_MSFT + TAG_DESC_REMOTE, new AddCommand(expectedInternship));

        // multiple positions - last name accepted
        assertParseSuccess(parser, NAME_DESC_MSFT + POSITION_DESC_AAPL + POSITION_DESC_MSFT + PHONE_DESC_MSFT
                + EMAIL_DESC_MSFT + WEBSITE_DESC_MSFT + TAG_DESC_REMOTE, new AddCommand(expectedInternship));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_AAPL + PHONE_DESC_MSFT
                + EMAIL_DESC_MSFT + WEBSITE_DESC_MSFT + TAG_DESC_REMOTE, new AddCommand(expectedInternship));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_AAPL
                + EMAIL_DESC_MSFT + WEBSITE_DESC_MSFT + TAG_DESC_REMOTE, new AddCommand(expectedInternship));

        // multiple websites - last website accepted
        assertParseSuccess(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + WEBSITE_DESC_AAPL + WEBSITE_DESC_MSFT + TAG_DESC_REMOTE, new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(MSFT)
                .withTags(VALID_TAG_REMOTE, VALID_TAG_URGENT).build();
        assertParseSuccess(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + WEBSITE_DESC_MSFT + TAG_DESC_REMOTE + TAG_DESC_URGENT,
                new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(MSFT).withTags().build();
        assertParseSuccess(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT
                + EMAIL_DESC_MSFT + WEBSITE_DESC_MSFT, new AddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + WEBSITE_DESC_MSFT, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, NAME_DESC_MSFT + VALID_POSITION_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + WEBSITE_DESC_MSFT, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + VALID_PHONE_MSFT + EMAIL_DESC_MSFT
                + WEBSITE_DESC_MSFT, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + VALID_EMAIL_MSFT
                + WEBSITE_DESC_MSFT, expectedMessage);

        // missing website prefix
        assertParseFailure(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + VALID_WEBSITE_MSFT, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MSFT + VALID_POSITION_MSFT + VALID_PHONE_MSFT + VALID_EMAIL_MSFT
                + VALID_WEBSITE_MSFT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + WEBSITE_DESC_MSFT + TAG_DESC_URGENT + TAG_DESC_REMOTE, Name.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_MSFT + INVALID_POSITION_DESC + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + WEBSITE_DESC_MSFT + TAG_DESC_URGENT + TAG_DESC_REMOTE, Position.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + INVALID_PHONE_DESC + EMAIL_DESC_MSFT
                + WEBSITE_DESC_MSFT + TAG_DESC_URGENT + TAG_DESC_REMOTE, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + INVALID_EMAIL_DESC
                + WEBSITE_DESC_MSFT + TAG_DESC_URGENT + TAG_DESC_REMOTE, Email.MESSAGE_CONSTRAINTS);

        // invalid website
        assertParseFailure(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + INVALID_WEBSITE_DESC + TAG_DESC_URGENT + TAG_DESC_REMOTE,
                Website.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + WEBSITE_DESC_MSFT + INVALID_TAG_DESC + VALID_TAG_REMOTE, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_MSFT + PHONE_DESC_MSFT + EMAIL_DESC_MSFT
                + INVALID_WEBSITE_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MSFT + POSITION_DESC_MSFT + PHONE_DESC_MSFT
                + EMAIL_DESC_MSFT + WEBSITE_DESC_MSFT + TAG_DESC_URGENT + TAG_DESC_REMOTE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
