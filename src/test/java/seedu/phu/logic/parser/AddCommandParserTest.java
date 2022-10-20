package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.DATE_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.DATE_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_APPLICATION_PROCESS_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_WEBSITE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.phu.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.phu.logic.commands.CommandTestUtil.REMARK_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.REMARK_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_STOCK;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_STOCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.phu.logic.commands.CommandTestUtil.WEBSITE_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.WEBSITE_DESC_BLACKROCK;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.phu.testutil.TypicalInternships.APPLE;
import static seedu.phu.testutil.TypicalInternships.BLACKROCK;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.AddCommand;
import seedu.phu.model.internship.ApplicationProcess;
import seedu.phu.model.internship.Date;
import seedu.phu.model.internship.Email;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.Name;
import seedu.phu.model.internship.Phone;
import seedu.phu.model.internship.Position;
import seedu.phu.model.internship.Remark;
import seedu.phu.model.internship.Website;
import seedu.phu.model.tag.Tag;
import seedu.phu.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(BLACKROCK).withTags(VALID_TAG_STOCK).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK
                + EMAIL_DESC_BLACKROCK + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK + POSITION_DESC_BLACKROCK
                + APPLICATION_PROCESS_DESC_BLACKROCK + DATE_DESC_BLACKROCK
                + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_APPLE + NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK
                + EMAIL_DESC_BLACKROCK + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BLACKROCK + PHONE_DESC_APPLE + PHONE_DESC_BLACKROCK
                + EMAIL_DESC_BLACKROCK + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_APPLE
                + EMAIL_DESC_BLACKROCK + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_APPLE + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple positions - last position accepted
        assertParseSuccess(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK + POSITION_DESC_APPLE
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple application processes - last application process accepted
        assertParseSuccess(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK + POSITION_DESC_BLACKROCK
                + APPLICATION_PROCESS_DESC_APPLE + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK + POSITION_DESC_BLACKROCK
                + APPLICATION_PROCESS_DESC_BLACKROCK + DATE_DESC_APPLE
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple websites - last website accepted
        assertParseSuccess(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK + POSITION_DESC_BLACKROCK
                + APPLICATION_PROCESS_DESC_BLACKROCK + DATE_DESC_BLACKROCK
                + WEBSITE_DESC_APPLE + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(BLACKROCK)
                .withTags(VALID_TAG_STOCK, VALID_TAG_TRANSPORT).build();
        assertParseSuccess(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_TRANSPORT + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(APPLE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_APPLE + PHONE_DESC_APPLE + EMAIL_DESC_APPLE + REMARK_DESC_APPLE
                        + POSITION_DESC_APPLE + APPLICATION_PROCESS_DESC_APPLE + DATE_DESC_APPLE + WEBSITE_DESC_APPLE,
                new AddCommand(expectedInternship));

        // no application process
        Internship expectedInternship1 = new InternshipBuilder(APPLE).withApplicationProcess("apply").build();
        assertParseSuccess(parser, NAME_DESC_APPLE + PHONE_DESC_APPLE + EMAIL_DESC_APPLE + REMARK_DESC_APPLE
                        + POSITION_DESC_APPLE + DATE_DESC_APPLE + WEBSITE_DESC_APPLE + TAG_DESC_STOCK,
                new AddCommand(expectedInternship1));

        // no date
        Internship expectedInternship2 = new InternshipBuilder(APPLE)
                .withDate(LocalDate.now().format(Date.DEFAULT_FORMATTER)).build();
        assertParseSuccess(parser, NAME_DESC_APPLE + PHONE_DESC_APPLE + EMAIL_DESC_APPLE + REMARK_DESC_APPLE
                        + POSITION_DESC_APPLE + APPLICATION_PROCESS_DESC_APPLE + WEBSITE_DESC_APPLE + TAG_DESC_STOCK,
                new AddCommand(expectedInternship2));

        // no phone
        Internship expectedInternship3 = new InternshipBuilder(APPLE).withPhone(Phone.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_APPLE + EMAIL_DESC_APPLE + REMARK_DESC_APPLE + POSITION_DESC_APPLE
                        + APPLICATION_PROCESS_DESC_APPLE + DATE_DESC_APPLE + WEBSITE_DESC_APPLE + TAG_DESC_STOCK,
                new AddCommand(expectedInternship3));

        // no email
        Internship expectedInternship4 = new InternshipBuilder(APPLE).withEmail(Email.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_APPLE + PHONE_DESC_APPLE + REMARK_DESC_APPLE + POSITION_DESC_APPLE
                        + APPLICATION_PROCESS_DESC_APPLE + DATE_DESC_APPLE + WEBSITE_DESC_APPLE + TAG_DESC_STOCK,
                new AddCommand(expectedInternship4));

        // no website
        Internship expectedInternship5 = new InternshipBuilder(APPLE).withWebsite(Website.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_APPLE + PHONE_DESC_APPLE + EMAIL_DESC_APPLE
                        + REMARK_DESC_APPLE + POSITION_DESC_APPLE + APPLICATION_PROCESS_DESC_APPLE + DATE_DESC_APPLE
                        + TAG_DESC_STOCK,
                new AddCommand(expectedInternship5));

        // no remark
        Internship expectedInternship6 = new InternshipBuilder(APPLE).withRemark(Remark.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_APPLE + PHONE_DESC_APPLE + EMAIL_DESC_APPLE + POSITION_DESC_APPLE
                        + APPLICATION_PROCESS_DESC_APPLE + DATE_DESC_APPLE + WEBSITE_DESC_APPLE + TAG_DESC_STOCK,
                new AddCommand(expectedInternship6));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BLACKROCK + POSITION_DESC_BLACKROCK, expectedMessage);

        // missing position prefix
        assertParseFailure(parser, NAME_DESC_BLACKROCK + VALID_POSITION_BLACKROCK, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BLACKROCK + VALID_POSITION_BLACKROCK, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BLACKROCK + INVALID_PHONE_DESC + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + INVALID_EMAIL_DESC
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + INVALID_TAG_DESC + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, Tag.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + INVALID_POSITION_DESC + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, Position.MESSAGE_CONSTRAINTS);

        // invalid application process
        assertParseFailure(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + INVALID_APPLICATION_PROCESS_DESC
                + DATE_DESC_BLACKROCK + WEBSITE_DESC_BLACKROCK, ApplicationProcess.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + INVALID_DATE_DESC + WEBSITE_DESC_BLACKROCK, Date.MESSAGE_CONSTRAINTS);

        // invalid website
        assertParseFailure(parser, NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + REMARK_DESC_BLACKROCK + TAG_DESC_STOCK
                + POSITION_DESC_BLACKROCK + APPLICATION_PROCESS_DESC_BLACKROCK
                + DATE_DESC_BLACKROCK + INVALID_WEBSITE_DESC, Website.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK
                + INVALID_DATE_DESC + POSITION_DESC_BLACKROCK, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BLACKROCK + PHONE_DESC_BLACKROCK
                        + EMAIL_DESC_BLACKROCK + REMARK_DESC_BLACKROCK
                        + TAG_DESC_TRANSPORT + TAG_DESC_STOCK + POSITION_DESC_BLACKROCK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
