package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.INVALID_SCHOLARSHIP_DESC;
import static seedu.trackascholar.logic.commands.CommandTestUtil.MAJOR_DESC_COMPUTER_SCIENCE;
import static seedu.trackascholar.logic.commands.CommandTestUtil.MAJOR_DESC_MATHEMATICS;
import static seedu.trackascholar.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.SCHOLARSHIP_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.SCHOLARSHIP_DESC_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_MAJOR_COMPUTER_SCIENCE;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_MAJOR_MATHEMATICS;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_BOB;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;
import static seedu.trackascholar.testutil.TypicalIndexes.INDEX_THIRD_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.logic.commands.EditCommand;
import seedu.trackascholar.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.major.Major;
import seedu.trackascholar.testutil.EditApplicantDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MAJOR_EMPTY = " " + PREFIX_MAJOR;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid phone
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        // invalid email
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);
        // invalid scholarship
        assertParseFailure(parser, "1" + INVALID_SCHOLARSHIP_DESC, Scholarship.MESSAGE_CONSTRAINTS);
        // invalid major
        assertParseFailure(parser, "1" + INVALID_MAJOR_DESC, Major.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_MAJOR} alone will reset the tags of the {@code Applicant} being edited,
        // parsing it together with a valid major results in error
        assertParseFailure(parser, "1" + MAJOR_DESC_MATHEMATICS + MAJOR_DESC_COMPUTER_SCIENCE + MAJOR_EMPTY,
            Major.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MAJOR_DESC_MATHEMATICS + MAJOR_EMPTY + MAJOR_DESC_COMPUTER_SCIENCE,
            Major.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MAJOR_EMPTY + MAJOR_DESC_MATHEMATICS + MAJOR_DESC_COMPUTER_SCIENCE,
            Major.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_SCHOLARSHIP_AMY
                + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICANT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + MAJOR_DESC_COMPUTER_SCIENCE
                + EMAIL_DESC_AMY + SCHOLARSHIP_DESC_AMY + NAME_DESC_AMY + MAJOR_DESC_MATHEMATICS;

        EditCommand.EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withScholarship(VALID_SCHOLARSHIP_AMY)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE, VALID_MAJOR_MATHEMATICS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICANT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditCommand.EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_APPLICANT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditApplicantDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // scholarship
        userInput = targetIndex.getOneBased() + SCHOLARSHIP_DESC_AMY;
        descriptor = new EditApplicantDescriptorBuilder().withScholarship(VALID_SCHOLARSHIP_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + MAJOR_DESC_MATHEMATICS;
        descriptor = new EditApplicantDescriptorBuilder().withMajors(VALID_MAJOR_MATHEMATICS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICANT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + SCHOLARSHIP_DESC_AMY + EMAIL_DESC_AMY
                + MAJOR_DESC_MATHEMATICS + PHONE_DESC_AMY + SCHOLARSHIP_DESC_AMY + EMAIL_DESC_AMY
                + MAJOR_DESC_MATHEMATICS + PHONE_DESC_BOB + SCHOLARSHIP_DESC_BOB + EMAIL_DESC_BOB
                + MAJOR_DESC_COMPUTER_SCIENCE;

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withScholarship(VALID_SCHOLARSHIP_BOB)
                .withMajors(VALID_MAJOR_MATHEMATICS, VALID_MAJOR_COMPUTER_SCIENCE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPLICANT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + SCHOLARSHIP_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditApplicantDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withScholarship(VALID_SCHOLARSHIP_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetMajors_success() {
        Index targetIndex = INDEX_THIRD_APPLICANT;
        String userInput = targetIndex.getOneBased() + MAJOR_EMPTY;

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withMajors().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
