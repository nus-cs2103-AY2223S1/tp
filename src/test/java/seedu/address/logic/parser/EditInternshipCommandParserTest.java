package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipCommandTestUtil.COMPANY_NAME_DESC_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.COMPANY_NAME_DESC_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INTERVIEW_DESC_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INTERVIEW_DESC_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_INTERVIEW_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.ROLE_DESC_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.ROLE_DESC_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.STATUS_DESC_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.STATUS_DESC_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_INTERVIEW_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_INTERVIEW_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_BOBBY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_STATUS_ABC;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_STATUS_BOBBY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditInternshipCommand;
import seedu.address.logic.commands.EditInternshipCommand.EditInternshipDescriptor;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

public class EditInternshipCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInternshipCommand.MESSAGE_USAGE);

    private final EditInternshipCommandParser parser = new EditInternshipCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_NAME_ABC, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditInternshipCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_NAME_DESC_ABC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_NAME_DESC_ABC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC, CompanyName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, InternshipRole.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, InternshipStatus.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_DESC, InterviewDate.MESSAGE_CONSTRAINTS);

        //  invalid status followed by valid interview date
        assertParseFailure(parser,
                "1" + INVALID_STATUS_DESC + INTERVIEW_DESC_ABC, InternshipStatus.MESSAGE_CONSTRAINTS);

        // valid interview date followed by invalid interview date.
        // The test case for invalid interview date followed by valid interview date
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser,
                "1" + INTERVIEW_DESC_ABC + INVALID_INTERVIEW_DESC, InterviewDate.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + INVALID_ROLE_DESC + INTERVIEW_DESC_ABC,
                CompanyName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_BOBBY + ROLE_DESC_BOBBY
                + STATUS_DESC_BOBBY + INTERVIEW_DESC_BOBBY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BOBBY)
                .withInternshipRole(VALID_ROLE_BOBBY)
                .withInternshipStatus(VALID_STATUS_BOBBY)
                .withInterviewDate(VALID_INTERVIEW_BOBBY).build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INTERVIEW_DESC_ABC + STATUS_DESC_ABC;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withInterviewDate(VALID_INTERVIEW_ABC)
                .withInternshipStatus(VALID_STATUS_ABC).build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // companyName
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_ABC;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_ABC).build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // internshipRole
        userInput = targetIndex.getOneBased() + ROLE_DESC_ABC;
        descriptor = new EditInternshipDescriptorBuilder().withInternshipRole(VALID_ROLE_ABC).build();
        expectedCommand = new EditInternshipCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // internshipStatus
        userInput = targetIndex.getOneBased() + STATUS_DESC_ABC;
        descriptor = new EditInternshipDescriptorBuilder().withInternshipStatus(VALID_STATUS_ABC).build();
        expectedCommand = new EditInternshipCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interviewDate
        userInput = targetIndex.getOneBased() + INTERVIEW_DESC_ABC;
        descriptor = new EditInternshipDescriptorBuilder().withInterviewDate(VALID_INTERVIEW_ABC).build();
        expectedCommand = new EditInternshipCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_ABC + INTERVIEW_DESC_ABC + ROLE_DESC_ABC
                + INTERVIEW_DESC_ABC + INTERVIEW_DESC_BOBBY + ROLE_DESC_BOBBY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withInternshipRole(VALID_ROLE_BOBBY)
                .withInterviewDate(VALID_INTERVIEW_BOBBY)
                .build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_STATUS_DESC + STATUS_DESC_ABC;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withInternshipStatus(VALID_STATUS_ABC).build();
        EditInternshipCommand expectedCommand = new EditInternshipCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_BOBBY + INVALID_INTERVIEW_DESC + INTERVIEW_DESC_BOBBY;
        descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BOBBY)
                .withInterviewDate(VALID_INTERVIEW_BOBBY).build();
        expectedCommand = new EditInternshipCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
