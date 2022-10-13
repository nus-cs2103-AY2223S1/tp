package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.INTERNSHIP_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.INTERNSHIP_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_APPLICATION_PROCESS_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_INTERNSHIP_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_INTERNSHIP_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_WEBSITE_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.WEBSITE_DESC_BOB;
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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Internship} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                        + VALID_INTERNSHIP_AMY + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + INTERNSHIP_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND
                + POSITION_DESC_AMY + APPLICATION_PROCESS_DESC_BOB + DATE_DESC_BOB
                + WEBSITE_DESC_BOB;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withInternship(VALID_INTERNSHIP_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withPosition(VALID_POSITION_AMY)
                .withApplicationProcess(VALID_APPLICATION_PROCESS_BOB).withDate(VALID_DATE_BOB)
                .withWebsite(VALID_WEBSITE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // internship
        userInput = targetIndex.getOneBased() + INTERNSHIP_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withInternship(VALID_INTERNSHIP_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditInternshipDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // position
        userInput = targetIndex.getOneBased() + POSITION_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withPosition(VALID_POSITION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // application process
        userInput = targetIndex.getOneBased() + APPLICATION_PROCESS_DESC_BOB;
        descriptor = new EditInternshipDescriptorBuilder()
                .withApplicationProcess(VALID_APPLICATION_PROCESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_BOB;
        descriptor = new EditInternshipDescriptorBuilder().withDate(VALID_DATE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // website
        userInput = targetIndex.getOneBased() + WEBSITE_DESC_BOB;
        descriptor = new EditInternshipDescriptorBuilder().withWebsite(VALID_WEBSITE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + INTERNSHIP_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + INTERNSHIP_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + INTERNSHIP_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withInternship(VALID_INTERNSHIP_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + INTERNSHIP_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withInternship(VALID_INTERNSHIP_BOB).build();
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
