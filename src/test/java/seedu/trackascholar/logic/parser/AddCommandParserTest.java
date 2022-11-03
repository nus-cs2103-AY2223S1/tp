package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.commands.CommandTestUtil.APPLICATION_STATUS_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.APPLICATION_STATUS_DESC_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_APPLICATION_STATUS_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_SCHOLARSHIP_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.MAJOR_DESC_FRIEND;
import static seedu.trackascholar.logic.commands.CommandTestUtil.MAJOR_DESC_HUSBAND;
import static seedu.trackascholar.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.trackascholar.logic.commands.CommandTestUtil.SCHOLARSHIP_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.SCHOLARSHIP_DESC_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_APPLICATION_STATUS_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_MAJOR_FRIEND;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_MAJOR_HUSBAND;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_BOB;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackascholar.testutil.TypicalApplicants.AMY;
import static seedu.trackascholar.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.AddCommand;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.major.Major;
import seedu.trackascholar.testutil.ApplicantBuilder;

public class AddCommandParserTest {
    private static final AddCommandParser parser = new AddCommandParser();
    private static final String MISSING_FIELD_ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new ApplicantBuilder(BOB).withMajors(VALID_MAJOR_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple scholarships - last scholarship accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_AMY
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple application status - last application status accepted (doesn't work)
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_AMY + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple majors - all accepted
        Applicant expectedApplicantMultipleMajors = new ApplicantBuilder(BOB)
                .withMajors(VALID_MAJOR_FRIEND, VALID_MAJOR_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + MAJOR_DESC_HUSBAND + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_FRIEND,
                new AddCommand(expectedApplicantMultipleMajors));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Applicant expectedApplicant = new ApplicantBuilder(AMY).withMajors().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + SCHOLARSHIP_DESC_AMY
                + APPLICATION_STATUS_DESC_AMY, new AddCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB, MISSING_FIELD_ERROR_MESSAGE);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB, MISSING_FIELD_ERROR_MESSAGE);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB, MISSING_FIELD_ERROR_MESSAGE);

        // missing scholarship prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_SCHOLARSHIP_BOB
                + APPLICATION_STATUS_DESC_BOB, MISSING_FIELD_ERROR_MESSAGE);

        // missing application status prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + VALID_APPLICATION_STATUS_BOB, MISSING_FIELD_ERROR_MESSAGE);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_SCHOLARSHIP_BOB
                + VALID_APPLICATION_STATUS_BOB, MISSING_FIELD_ERROR_MESSAGE);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_HUSBAND + MAJOR_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_HUSBAND + MAJOR_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_HUSBAND + MAJOR_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid scholarship
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_SCHOLARSHIP_DESC
                + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_HUSBAND + MAJOR_DESC_FRIEND,
            Scholarship.MESSAGE_CONSTRAINTS);

        // invalid application status
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + INVALID_APPLICATION_STATUS_DESC + VALID_MAJOR_FRIEND, ApplicationStatus.MESSAGE_CONSTRAINTS);

        // invalid major
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + INVALID_MAJOR_DESC + VALID_MAJOR_FRIEND, Major.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_SCHOLARSHIP_DESC + APPLICATION_STATUS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + MAJOR_DESC_HUSBAND + MAJOR_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
