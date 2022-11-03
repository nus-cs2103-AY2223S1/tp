package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPLIED_DATE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.APPLIED_DATE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DATE_TIME_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLIED_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERVIEW_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LINK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LINK_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.LINK_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_TIME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.InterviewDateTime;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_GOOGLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_DESC_GOOGLE, ParserUtil.MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_DESC_GOOGLE, ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        //invalid arguments and index
        assertParseFailure(parser, "-1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 p/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS); // invalid company
        assertParseFailure(parser, "1" + INVALID_LINK_DESC, Link.MESSAGE_CONSTRAINTS); // invalid link
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        // invalid description
        assertParseFailure(parser, "1" + INVALID_APPLIED_DATE_DESC, AppliedDate.FORMAT_CONSTRAINTS);
        // invalid applied date
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_DATE_TIME_DESC, InterviewDateTime.FORMAT_CONSTRAINTS);
        // invalid interview date time
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid link followed by valid description
        assertParseFailure(parser, "1" + INVALID_LINK_DESC + DESCRIPTION_DESC_GOOGLE, Link.MESSAGE_CONSTRAINTS);

        // valid link followed by invalid link. The test case for invalid link followed by valid link
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + LINK_DESC_TIKTOK + INVALID_LINK_DESC, Link.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Internship} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRONTEND + TAG_DESC_BACKEND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRONTEND + TAG_EMPTY + TAG_DESC_BACKEND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRONTEND + TAG_DESC_BACKEND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC
                        + INVALID_DESCRIPTION_DESC + VALID_APPLIED_DATE_GOOGLE + VALID_LINK_GOOGLE,
                        Company.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + LINK_DESC_TIKTOK + TAG_DESC_BACKEND + DESCRIPTION_DESC_GOOGLE
                + APPLIED_DATE_DESC_GOOGLE + INTERVIEW_DATE_TIME_DESC_GOOGLE + COMPANY_DESC_GOOGLE + TAG_DESC_FRONTEND;

        EditCommand.EditInternshipDescriptor descriptor =
                new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_GOOGLE)
                .withLink(VALID_LINK_TIKTOK).withDescription(VALID_DESCRIPTION_GOOGLE)
                .withAppliedDate(VALID_APPLIED_DATE_GOOGLE).withInterviewDateTime(VALID_INTERVIEW_DATE_TIME_GOOGLE)
                .withTags(VALID_TAG_FRONTEND, VALID_TAG_BACKEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + LINK_DESC_GOOGLE + DESCRIPTION_DESC_GOOGLE;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withLink(VALID_LINK_GOOGLE)
                .withDescription(VALID_DESCRIPTION_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // company
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_GOOGLE;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompany(VALID_COMPANY_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // link
        userInput = targetIndex.getOneBased() + LINK_DESC_GOOGLE;
        descriptor = new EditInternshipDescriptorBuilder().withLink(VALID_LINK_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_GOOGLE;
        descriptor = new EditInternshipDescriptorBuilder().withDescription(VALID_DESCRIPTION_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // applied date
        userInput = targetIndex.getOneBased() + APPLIED_DATE_DESC_GOOGLE;
        descriptor = new EditInternshipDescriptorBuilder().withAppliedDate(VALID_APPLIED_DATE_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interview date time
        userInput = targetIndex.getOneBased() + INTERVIEW_DATE_TIME_DESC_GOOGLE;
        descriptor = new EditInternshipDescriptorBuilder()
                .withInterviewDateTime(VALID_INTERVIEW_DATE_TIME_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_BACKEND;
        descriptor = new EditInternshipDescriptorBuilder().withTags(VALID_TAG_BACKEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + LINK_DESC_GOOGLE + APPLIED_DATE_DESC_GOOGLE
                + DESCRIPTION_DESC_GOOGLE + TAG_DESC_FRONTEND + LINK_DESC_GOOGLE + APPLIED_DATE_DESC_GOOGLE
                + DESCRIPTION_DESC_GOOGLE + TAG_DESC_FRONTEND + LINK_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + TAG_DESC_BACKEND;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withLink(VALID_LINK_TIKTOK)
                .withDescription(VALID_DESCRIPTION_TIKTOK).withAppliedDate(VALID_APPLIED_DATE_TIKTOK)
                .withTags(VALID_TAG_BACKEND, VALID_TAG_FRONTEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_LINK_DESC + LINK_DESC_TIKTOK;
        EditCommand.EditInternshipDescriptor descriptor =
                new EditInternshipDescriptorBuilder().withLink(VALID_LINK_TIKTOK).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_TIKTOK + INVALID_LINK_DESC
                + APPLIED_DATE_DESC_TIKTOK + LINK_DESC_TIKTOK;
        descriptor = new EditInternshipDescriptorBuilder().withLink(VALID_LINK_TIKTOK)
                .withDescription(VALID_DESCRIPTION_TIKTOK).withAppliedDate(VALID_APPLIED_DATE_TIKTOK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
