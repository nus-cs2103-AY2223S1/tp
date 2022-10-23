package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.commands.CommandTestUtil.EMAIL_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.EMAIL_DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.INVALID_WEBSITE_DESC;
import static seedu.intrack.logic.commands.CommandTestUtil.NAME_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.PHONE_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.PHONE_DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.TAG_DESC_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.TAG_DESC_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.WEBSITE_DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.WEBSITE_DESC_MSFT;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.EditCommand;
import seedu.intrack.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
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
        // no index specified
        assertParseFailure(parser, VALID_NAME_AAPL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_WEBSITE_DESC, Website.MESSAGE_CONSTRAINTS); // invalid website
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AAPL, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_MSFT + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Internship} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_REMOTE + TAG_DESC_URGENT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_REMOTE + TAG_EMPTY + TAG_DESC_URGENT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_REMOTE + TAG_DESC_URGENT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_WEBSITE_AAPL + VALID_PHONE_AAPL,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_MSFT + TAG_DESC_URGENT
                + EMAIL_DESC_AAPL + WEBSITE_DESC_AAPL + NAME_DESC_AAPL + TAG_DESC_REMOTE;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_AAPL)
                .withPhone(VALID_PHONE_MSFT).withEmail(VALID_EMAIL_AAPL).withWebsite(VALID_WEBSITE_AAPL)
                .withTags(VALID_TAG_URGENT, VALID_TAG_REMOTE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_MSFT + EMAIL_DESC_AAPL;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_MSFT)
                .withEmail(VALID_EMAIL_AAPL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AAPL;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_AAPL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AAPL;
        descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_AAPL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AAPL;
        descriptor = new EditInternshipDescriptorBuilder().withEmail(VALID_EMAIL_AAPL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // website
        userInput = targetIndex.getOneBased() + WEBSITE_DESC_AAPL;
        descriptor = new EditInternshipDescriptorBuilder().withWebsite(VALID_WEBSITE_AAPL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_REMOTE;
        descriptor = new EditInternshipDescriptorBuilder().withTags(VALID_TAG_REMOTE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AAPL + WEBSITE_DESC_AAPL + EMAIL_DESC_AAPL
                + TAG_DESC_REMOTE + PHONE_DESC_AAPL + WEBSITE_DESC_AAPL + EMAIL_DESC_AAPL + TAG_DESC_REMOTE
                + PHONE_DESC_MSFT + WEBSITE_DESC_MSFT + EMAIL_DESC_MSFT + TAG_DESC_URGENT;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_MSFT)
                .withEmail(VALID_EMAIL_MSFT).withWebsite(VALID_WEBSITE_MSFT)
                .withTags(VALID_TAG_REMOTE, VALID_TAG_URGENT)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_MSFT;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_MSFT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_MSFT + INVALID_PHONE_DESC + WEBSITE_DESC_MSFT
                + PHONE_DESC_MSFT;
        descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_MSFT).withEmail(VALID_EMAIL_MSFT)
                .withWebsite(VALID_WEBSITE_MSFT).build();
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
