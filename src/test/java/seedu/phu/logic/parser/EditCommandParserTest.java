package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.DATE_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.REMARK_DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.REMARK_DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_STOCK;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_TRANSPORT;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_APPLICATION_PROCESS_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_DATE_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_STOCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_WEBSITE_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.WEBSITE_DESC_BLACKROCK;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.index.Index;
import seedu.phu.logic.commands.EditCommand;
import seedu.phu.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.phu.model.internship.Email;
import seedu.phu.model.internship.Name;
import seedu.phu.model.internship.Phone;
import seedu.phu.model.tag.Tag;
import seedu.phu.testutil.EditInternshipDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_APPLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_APPLE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_APPLE, MESSAGE_INVALID_FORMAT);

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
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_APPLE, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BLACKROCK + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Internship} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_STOCK + TAG_DESC_TRANSPORT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_STOCK + TAG_EMPTY + TAG_DESC_TRANSPORT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_STOCK + TAG_DESC_TRANSPORT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                        + VALID_REMARK_APPLE + VALID_PHONE_APPLE, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BLACKROCK + TAG_DESC_TRANSPORT
                + EMAIL_DESC_APPLE + REMARK_DESC_APPLE + NAME_DESC_APPLE + TAG_DESC_STOCK
                + POSITION_DESC_APPLE + APPLICATION_PROCESS_DESC_BLACKROCK + DATE_DESC_BLACKROCK
                + WEBSITE_DESC_BLACKROCK;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_APPLE)
                .withPhone(VALID_PHONE_BLACKROCK).withEmail(VALID_EMAIL_APPLE).withRemark(VALID_REMARK_APPLE)
                .withTags(VALID_TAG_TRANSPORT, VALID_TAG_STOCK).withPosition(VALID_POSITION_APPLE)
                .withApplicationProcess(VALID_APPLICATION_PROCESS_BLACKROCK).withDate(VALID_DATE_BLACKROCK)
                .withWebsite(VALID_WEBSITE_BLACKROCK).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BLACKROCK + EMAIL_DESC_APPLE;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_BLACKROCK)
                .withEmail(VALID_EMAIL_APPLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + NAME_DESC_APPLE;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_APPLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_APPLE;
        descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_APPLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_APPLE;
        descriptor = new EditInternshipDescriptorBuilder().withEmail(VALID_EMAIL_APPLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark
        userInput = targetIndex.getOneBased() + REMARK_DESC_APPLE;
        descriptor = new EditInternshipDescriptorBuilder().withRemark(VALID_REMARK_APPLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_STOCK;
        descriptor = new EditInternshipDescriptorBuilder().withTags(VALID_TAG_STOCK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // position
        userInput = targetIndex.getOneBased() + POSITION_DESC_APPLE;
        descriptor = new EditInternshipDescriptorBuilder().withPosition(VALID_POSITION_APPLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // application process
        userInput = targetIndex.getOneBased() + APPLICATION_PROCESS_DESC_BLACKROCK;
        descriptor = new EditInternshipDescriptorBuilder()
                .withApplicationProcess(VALID_APPLICATION_PROCESS_BLACKROCK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_BLACKROCK;
        descriptor = new EditInternshipDescriptorBuilder().withDate(VALID_DATE_BLACKROCK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // website
        userInput = targetIndex.getOneBased() + WEBSITE_DESC_BLACKROCK;
        descriptor = new EditInternshipDescriptorBuilder().withWebsite(VALID_WEBSITE_BLACKROCK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_APPLE + REMARK_DESC_APPLE + EMAIL_DESC_APPLE
                + TAG_DESC_STOCK + PHONE_DESC_APPLE + REMARK_DESC_APPLE + EMAIL_DESC_APPLE + TAG_DESC_STOCK
                + PHONE_DESC_BLACKROCK + REMARK_DESC_BLACKROCK + EMAIL_DESC_BLACKROCK + TAG_DESC_TRANSPORT;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_BLACKROCK)
                .withEmail(VALID_EMAIL_BLACKROCK).withRemark(VALID_REMARK_BLACKROCK)
                .withTags(VALID_TAG_STOCK, VALID_TAG_TRANSPORT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BLACKROCK;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withPhone(VALID_PHONE_BLACKROCK).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BLACKROCK + INVALID_PHONE_DESC + REMARK_DESC_BLACKROCK
                + PHONE_DESC_BLACKROCK;
        descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_BLACKROCK)
                .withEmail(VALID_EMAIL_BLACKROCK).withRemark(VALID_REMARK_BLACKROCK).build();
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
