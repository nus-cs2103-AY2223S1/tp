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
import static seedu.trackascholar.logic.commands.CommandTestUtil.MAJOR_DESC_COMPUTER_SCIENCE;
import static seedu.trackascholar.logic.commands.CommandTestUtil.MAJOR_DESC_MATHEMATICS;
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
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_MAJOR_COMPUTER_SCIENCE;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_MAJOR_MATHEMATICS;
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
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new ApplicantBuilder(BOB).withMajors(VALID_MAJOR_MATHEMATICS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_MATHEMATICS,
                new AddCommand(expectedApplicant));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_MATHEMATICS,
                new AddCommand(expectedApplicant));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_MATHEMATICS,
                new AddCommand(expectedApplicant));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_MATHEMATICS,
                new AddCommand(expectedApplicant));

        // multiple scholarships - last scholarship accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_AMY
                + SCHOLARSHIP_DESC_BOB + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_MATHEMATICS,
                new AddCommand(expectedApplicant));

        // multiple application status - last application status accepted (doesn't work)
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_AMY + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_MATHEMATICS,
                new AddCommand(expectedApplicant));

        // multiple majors - all accepted
        Applicant expectedApplicantMultipleMajors = new ApplicantBuilder(BOB)
                .withMajors(VALID_MAJOR_MATHEMATICS, VALID_MAJOR_COMPUTER_SCIENCE).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + MAJOR_DESC_COMPUTER_SCIENCE + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_MATHEMATICS,
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
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB, expectedMessage);

        // missing scholarship prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_SCHOLARSHIP_BOB
                + APPLICATION_STATUS_DESC_BOB, expectedMessage);

        // missing application status prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + VALID_APPLICATION_STATUS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_SCHOLARSHIP_BOB
                + VALID_APPLICATION_STATUS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_COMPUTER_SCIENCE + MAJOR_DESC_MATHEMATICS,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_COMPUTER_SCIENCE + MAJOR_DESC_MATHEMATICS,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_COMPUTER_SCIENCE + MAJOR_DESC_MATHEMATICS,
                Email.MESSAGE_CONSTRAINTS);

        // invalid scholarship
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_SCHOLARSHIP_DESC
                + APPLICATION_STATUS_DESC_BOB + MAJOR_DESC_COMPUTER_SCIENCE + MAJOR_DESC_MATHEMATICS,
            Scholarship.MESSAGE_CONSTRAINTS);

        // invalid application status
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + INVALID_APPLICATION_STATUS_DESC + VALID_MAJOR_MATHEMATICS, ApplicationStatus.MESSAGE_CONSTRAINTS);

        // invalid major
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + SCHOLARSHIP_DESC_BOB
                + APPLICATION_STATUS_DESC_BOB + INVALID_MAJOR_DESC + VALID_MAJOR_MATHEMATICS,
                Major.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_SCHOLARSHIP_DESC + APPLICATION_STATUS_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + SCHOLARSHIP_DESC_BOB + MAJOR_DESC_COMPUTER_SCIENCE + MAJOR_DESC_MATHEMATICS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
