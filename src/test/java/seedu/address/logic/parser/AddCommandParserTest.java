package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPLIED_DATE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.APPLIED_DATE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DATE_TIME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DATE_TIME_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLIED_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERVIEW_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LINK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LINK_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.LINK_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalInternships.GOOGLE;
import static seedu.address.testutil.TypicalInternships.GOOGLE_NO_INTERVIEW;
import static seedu.address.testutil.TypicalInternships.TIKTOK;
import static seedu.address.testutil.TypicalInternships.TIKTOK_NO_INTERVIEW;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InterviewDateTime;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(TIKTOK_NO_INTERVIEW).withTags(VALID_TAG_FRONTEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple companies - last company accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple links - last link accepted
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_GOOGLE + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_GOOGLE + DESCRIPTION_DESC_TIKTOK
                + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple applied dates - last applied date accepted
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_GOOGLE
                + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple interview dates - last applied date accepted
        Internship expectedInternshipInterviewDateTime = new InternshipBuilder(TIKTOK)
                .withTags(VALID_TAG_FRONTEND).build();
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + INTERVIEW_DATE_TIME_DESC_GOOGLE + INTERVIEW_DATE_TIME_DESC_TIKTOK
                + TAG_DESC_FRONTEND, new AddCommand(expectedInternshipInterviewDateTime));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags =
                new InternshipBuilder(TIKTOK_NO_INTERVIEW).withTags(VALID_TAG_FRONTEND, VALID_TAG_BACKEND).build();
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + TAG_DESC_BACKEND + TAG_DESC_FRONTEND, new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no tags, with interview date time
        Internship expectedInternshipNoTags = new InternshipBuilder(GOOGLE).withTags().build();
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + LINK_DESC_GOOGLE + DESCRIPTION_DESC_GOOGLE
                        + APPLIED_DATE_DESC_GOOGLE + INTERVIEW_DATE_TIME_DESC_GOOGLE,
                new AddCommand(expectedInternshipNoTags));

        // with tag, no interview date time
        Internship expectedInternshipNoInterviewDateTime = new InternshipBuilder(GOOGLE_NO_INTERVIEW)
                .withTags(VALID_TAG_BACKEND).build();
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + LINK_DESC_GOOGLE + DESCRIPTION_DESC_GOOGLE
                        + APPLIED_DATE_DESC_GOOGLE + TAG_DESC_BACKEND,
                new AddCommand(expectedInternshipNoInterviewDateTime));

        // no tags, no interview date time
        Internship expectedInternshipNoTagsNoInterviewDateTime = new InternshipBuilder(GOOGLE_NO_INTERVIEW)
                .withTags().build();
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + LINK_DESC_GOOGLE + DESCRIPTION_DESC_GOOGLE
                        + APPLIED_DATE_DESC_GOOGLE, new AddCommand(expectedInternshipNoTagsNoInterviewDateTime));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company prefix
        assertParseFailure(parser, VALID_COMPANY_TIKTOK + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                        + APPLIED_DATE_DESC_TIKTOK,
                expectedMessage);

        // missing link prefix
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + VALID_LINK_TIKTOK + DESCRIPTION_DESC_TIKTOK
                        + APPLIED_DATE_DESC_TIKTOK,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK + VALID_DESCRIPTION_TIKTOK
                        + APPLIED_DATE_DESC_TIKTOK,
                expectedMessage);

        // missing applied date prefix
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                        + VALID_APPLIED_DATE_TIKTOK,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_TIKTOK + VALID_LINK_TIKTOK + VALID_DESCRIPTION_TIKTOK
                        + VALID_APPLIED_DATE_TIKTOK,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company
        assertParseFailure(parser, INVALID_COMPANY_DESC + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_BACKEND + TAG_DESC_FRONTEND, Company.MESSAGE_CONSTRAINTS);

        // invalid link
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + INVALID_LINK_DESC + DESCRIPTION_DESC_TIKTOK
                + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_BACKEND + TAG_DESC_FRONTEND, Link.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK + INVALID_DESCRIPTION_DESC
                + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_BACKEND + TAG_DESC_FRONTEND, Description.MESSAGE_CONSTRAINTS);

        // invalid applied date
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                + INVALID_APPLIED_DATE_DESC + TAG_DESC_BACKEND + TAG_DESC_FRONTEND, AppliedDate.FORMAT_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                + APPLIED_DATE_DESC_TIKTOK + INVALID_TAG_DESC + TAG_DESC_FRONTEND, Tag.MESSAGE_CONSTRAINTS);

        // invalid interview date time
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_BACKEND + TAG_DESC_FRONTEND
                + INVALID_INTERVIEW_DATE_TIME_DESC, InterviewDateTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMPANY_DESC + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                        + INVALID_APPLIED_DATE_DESC, Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_BACKEND + TAG_DESC_FRONTEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
