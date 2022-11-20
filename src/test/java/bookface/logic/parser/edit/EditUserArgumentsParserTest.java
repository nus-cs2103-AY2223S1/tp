package bookface.logic.parser.edit;

import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.CommandTestUtil;
import bookface.logic.commands.edit.EditUserCommand;
import bookface.logic.commands.edit.EditUserCommand.EditPersonDescriptor;
import bookface.logic.parser.CommandParserTestUtil;
import bookface.model.person.Email;
import bookface.model.person.Name;
import bookface.model.person.Phone;
import bookface.model.tag.Tag;
import bookface.testutil.EditPersonDescriptorBuilder;
import bookface.testutil.TypicalIndexes;

public class EditUserArgumentsParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserCommand.MESSAGE_USAGE);

    private final EditUserArgumentsParser parser = new EditUserArgumentsParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "1", EditUserCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser, "-5"
                + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser, "0"
                + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        CommandParserTestUtil.assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        CommandParserTestUtil.assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        CommandParserTestUtil.assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        CommandParserTestUtil.assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        CommandParserTestUtil.assertParseFailure(parser, "1"
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.TAG_DESC_HUSBAND
                + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "1"
                + CommandTestUtil.TAG_DESC_FRIEND + TAG_EMPTY
                + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "1"
                + TAG_EMPTY + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, "1"
                        + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.INVALID_EMAIL_DESC
                        + CommandTestUtil.VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_AMY)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_AMY)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND, CommandTestUtil.VALID_TAG_FRIEND).build();
        EditUserCommand expectedCommand = new EditUserCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        EditUserCommand expectedCommand = new EditUserCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_AMY).build();
        EditUserCommand expectedCommand = new EditUserCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + CommandTestUtil.PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_AMY).build();
        expectedCommand = new EditUserCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + CommandTestUtil.EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        expectedCommand = new EditUserCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
        expectedCommand = new EditUserCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_throwsException() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND;

        CommandParserTestUtil.assertParseFailure(parser, userInput, String.format(Messages.MULTIPLE_PREFIXES_ENTERED,
                "p/"));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditUserCommand expectedCommand = new EditUserCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }
}
