package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.commands.CommandTestUtil.EMAIL_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.EMAIL_DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_SALARY_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_WEBSITE_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.NAME_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.SALARY_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.SALARY_DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.TAG_DESC_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.TAG_DESC_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_SALARY_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_SALARY_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.WEBSITE_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.WEBSITE_DESC_MSFT;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.EditCommand;
import seedu.intrack.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Salary;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;
import seedu.intrack.testutil.EditInternshipDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "", EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AAPL, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AAPL, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_SALARY_DESC, Salary.MESSAGE_CONSTRAINTS); // invalid salary
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_WEBSITE_DESC, Website.MESSAGE_CONSTRAINTS); // invalid website
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid website followed by valid salary
        assertParseFailure(parser, INVALID_WEBSITE_DESC + SALARY_DESC_MSFT, Website.MESSAGE_CONSTRAINTS);

        // valid salary followed by invalid salary. The test case for invalid salary followed by valid salary
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, SALARY_DESC_MSFT + INVALID_SALARY_DESC, Salary.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Internship} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, TAG_DESC_REMOTE + TAG_DESC_URGENT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, TAG_DESC_REMOTE + TAG_EMPTY + TAG_DESC_URGENT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, TAG_EMPTY + TAG_DESC_REMOTE + TAG_DESC_URGENT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_WEBSITE_AAPL
                + VALID_SALARY_AAPL, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = SALARY_DESC_AAPL + TAG_DESC_URGENT + EMAIL_DESC_AAPL + WEBSITE_DESC_AAPL
                + NAME_DESC_AAPL + TAG_DESC_REMOTE;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_AAPL)
                .withSalary(VALID_SALARY_AAPL).withEmail(VALID_EMAIL_AAPL).withWebsite(VALID_WEBSITE_AAPL)
                .withTags(VALID_TAG_URGENT, VALID_TAG_REMOTE).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = SALARY_DESC_MSFT + EMAIL_DESC_AAPL;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withSalary(VALID_SALARY_MSFT)
                .withEmail(VALID_EMAIL_AAPL).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_AAPL;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_AAPL).build();
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // salary
        userInput = SALARY_DESC_AAPL;
        descriptor = new EditInternshipDescriptorBuilder().withSalary(VALID_SALARY_AAPL).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = EMAIL_DESC_AAPL;
        descriptor = new EditInternshipDescriptorBuilder().withEmail(VALID_EMAIL_AAPL).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // website
        userInput = WEBSITE_DESC_AAPL;
        descriptor = new EditInternshipDescriptorBuilder().withWebsite(VALID_WEBSITE_AAPL).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = TAG_DESC_REMOTE;
        descriptor = new EditInternshipDescriptorBuilder().withTags(VALID_TAG_REMOTE).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = SALARY_DESC_AAPL + WEBSITE_DESC_AAPL + EMAIL_DESC_AAPL + TAG_DESC_REMOTE
                + SALARY_DESC_AAPL + WEBSITE_DESC_AAPL + EMAIL_DESC_AAPL + TAG_DESC_REMOTE
                + SALARY_DESC_MSFT + WEBSITE_DESC_MSFT + EMAIL_DESC_MSFT + TAG_DESC_URGENT;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withSalary(VALID_SALARY_MSFT)
                .withEmail(VALID_EMAIL_MSFT).withWebsite(VALID_WEBSITE_MSFT)
                .withTags(VALID_TAG_REMOTE, VALID_TAG_URGENT)
                .build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = INVALID_SALARY_DESC + SALARY_DESC_MSFT;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withSalary(VALID_SALARY_MSFT).build();
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = EMAIL_DESC_MSFT + INVALID_SALARY_DESC + WEBSITE_DESC_MSFT + SALARY_DESC_MSFT;
        descriptor = new EditInternshipDescriptorBuilder().withSalary(VALID_SALARY_MSFT).withEmail(VALID_EMAIL_MSFT)
                .withWebsite(VALID_WEBSITE_MSFT).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = TAG_EMPTY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
