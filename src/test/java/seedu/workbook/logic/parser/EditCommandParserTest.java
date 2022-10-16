package seedu.workbook.logic.parser;

import static seedu.workbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.workbook.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.workbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.workbook.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.workbook.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.workbook.testutil.TypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.workbook.commons.core.index.Index;
import seedu.workbook.logic.commands.EditCommand;
import seedu.workbook.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.tag.Tag;
import seedu.workbook.testutil.EditInternshipDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag


        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code
        // Internship} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_COMPANY_DESC + INVALID_EMAIL_DESC,
                Company.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + COMPANY_DESC_AMY + TAG_DESC_FRIEND;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_AMY;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withCompany(VALID_COMPANY_AMY)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditInternshipDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditInternshipDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                 + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + INVALID_EMAIL_DESC + EMAIL_DESC_BOB;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder().withPhone(VALID_EMAIL_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB;
        descriptor = new EditInternshipDescriptorBuilder().withEmail(VALID_EMAIL_BOB)
                .build();
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
