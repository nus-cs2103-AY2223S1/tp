package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_APPLICATION_PROCESS_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_WEBSITE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.phu.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.phu.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.phu.logic.commands.CommandTestUtil.WEBSITE_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.WEBSITE_DESC_BOB;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.phu.testutil.TypicalInternships.AMY;
import static seedu.phu.testutil.TypicalInternships.BOB;

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
        Internship expectedInternship = new InternshipBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_AMY
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple positions - last position accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_AMY + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple application processes - last application process accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_AMY + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB + DATE_DESC_AMY
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple websites - last website accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_AMY + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + REMARK_DESC_AMY
                        + POSITION_DESC_AMY + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY,
                new AddCommand(expectedInternship));

        // no application process
        Internship expectedInternship1 = new InternshipBuilder(AMY).withApplicationProcess("apply").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + REMARK_DESC_AMY
                        + POSITION_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship1));

        // no date
        Internship expectedInternship2 = new InternshipBuilder(AMY)
                .withDate(LocalDate.now().format(Date.DEFAULT_FORMATTER)).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + REMARK_DESC_AMY
                        + POSITION_DESC_AMY + APPLICATION_PROCESS_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship2));

        // no phone
        Internship expectedInternship3 = new InternshipBuilder(AMY).withPhone(Phone.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + REMARK_DESC_AMY + POSITION_DESC_AMY
                        + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship3));

        // no email
        Internship expectedInternship4 = new InternshipBuilder(AMY).withEmail(Email.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + REMARK_DESC_AMY + POSITION_DESC_AMY
                        + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship4));

        // no website
        Internship expectedInternship5 = new InternshipBuilder(AMY).withWebsite(Website.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + REMARK_DESC_AMY + POSITION_DESC_AMY + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship5));

        // no remark
        Internship expectedInternship6 = new InternshipBuilder(AMY).withRemark(Remark.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + POSITION_DESC_AMY
                        + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship6));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + POSITION_DESC_BOB, expectedMessage);

        // missing position prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_POSITION_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_POSITION_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + INVALID_TAG_DESC + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + INVALID_POSITION_DESC + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Position.MESSAGE_CONSTRAINTS);

        // invalid application process
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + INVALID_APPLICATION_PROCESS_DESC
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, ApplicationProcess.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + INVALID_DATE_DESC + WEBSITE_DESC_BOB, Date.MESSAGE_CONSTRAINTS);

        // invalid website
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + INVALID_WEBSITE_DESC, Website.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_DATE_DESC
                + POSITION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + REMARK_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
