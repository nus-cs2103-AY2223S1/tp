package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.PREFIX_TAG;
import static friday.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static friday.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static friday.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import friday.commons.core.index.Index;
import friday.logic.commands.CommandTestUtil;
import friday.logic.commands.EditCommand;
import friday.model.student.Name;
import friday.model.student.TelegramHandle;
import friday.model.tag.Tag;
import friday.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String INVALID_TAG = "*" + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser, "-5" + CommandTestUtil.NAME_DESC_AMY,
                MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser, "0" + CommandTestUtil.NAME_DESC_AMY,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }


    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_TELEGRAMHANDLE_DESC,
                TelegramHandle.MESSAGE_CONSTRAINTS); // invalid phone
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_TELEGRAMHANDLE_DESC
                + CommandTestUtil.CONSULTATION_DESC_AMY, TelegramHandle.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.TELEGRAMHANDLE_DESC_BOB
                + CommandTestUtil.INVALID_TELEGRAMHANDLE_DESC, TelegramHandle.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.TAG_DESC_HUSBAND + INVALID_TAG, Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.TAG_DESC_FRIEND
                + INVALID_TAG + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.TELEGRAMHANDLE_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.CONSULTATION_DESC_AMY
                + CommandTestUtil.MASTERYCHECK_DESC_AMY + CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.TAG_DESC_FRIEND;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_AMY)
                .withPhone(CommandTestUtil.VALID_TELEGRAMHANDLE_BOB)
                .withConsultation(CommandTestUtil.VALID_CONSULTATION_AMY).withMasteryCheck(
                        CommandTestUtil.VALID_MASTERYCHECK_DATE_AMY)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND, CommandTestUtil.VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.TELEGRAMHANDLE_DESC_BOB
                + CommandTestUtil.CONSULTATION_DESC_AMY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withPhone(CommandTestUtil.VALID_TELEGRAMHANDLE_BOB)
                .withConsultation(CommandTestUtil.VALID_CONSULTATION_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_AMY;
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(
                CommandTestUtil.VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + CommandTestUtil.TELEGRAMHANDLE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(CommandTestUtil.VALID_TELEGRAMHANDLE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + CommandTestUtil.CONSULTATION_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder()
                .withConsultation(CommandTestUtil.VALID_CONSULTATION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + CommandTestUtil.MASTERYCHECK_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder()
                .withMasteryCheck(CommandTestUtil.VALID_MASTERYCHECK_DATE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_FRIEND;
        descriptor = new EditStudentDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.TELEGRAMHANDLE_DESC_AMY
                + CommandTestUtil.MASTERYCHECK_DESC_AMY
                + CommandTestUtil.CONSULTATION_DESC_AMY
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.TELEGRAMHANDLE_DESC_AMY
                + CommandTestUtil.MASTERYCHECK_DESC_AMY
                + CommandTestUtil.CONSULTATION_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.TELEGRAMHANDLE_DESC_BOB + CommandTestUtil.MASTERYCHECK_DESC_BOB
                + CommandTestUtil.CONSULTATION_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withPhone(CommandTestUtil.VALID_TELEGRAMHANDLE_BOB)
                .withConsultation(CommandTestUtil.VALID_CONSULTATION_BOB)
                .withMasteryCheck(CommandTestUtil.VALID_MASTERYCHECK_DATE_BOB).withTags(
                        CommandTestUtil.VALID_TAG_FRIEND, CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.INVALID_TELEGRAMHANDLE_DESC
                + CommandTestUtil.TELEGRAMHANDLE_DESC_BOB;
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(
                CommandTestUtil.VALID_TELEGRAMHANDLE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + CommandTestUtil.CONSULTATION_DESC_BOB
                + CommandTestUtil.INVALID_TELEGRAMHANDLE_DESC
                + CommandTestUtil.MASTERYCHECK_DESC_BOB
                + CommandTestUtil.TELEGRAMHANDLE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(CommandTestUtil.VALID_TELEGRAMHANDLE_BOB)
                .withConsultation(CommandTestUtil.VALID_CONSULTATION_BOB)
                .withMasteryCheck(CommandTestUtil.VALID_MASTERYCHECK_DATE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

}
