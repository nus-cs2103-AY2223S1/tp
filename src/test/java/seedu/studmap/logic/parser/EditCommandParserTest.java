package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_EMAIL;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_NAME;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_PHONE;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_TAG;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.studmap.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.studmap.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.logic.commands.EditCommand;
import seedu.studmap.logic.commands.EditCommand.EditCommandStudentEditor;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.tag.Tag;
import seedu.studmap.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String EMPTY = "";

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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC,
                ParserUtil.getInvalidMessage(Name.MESSAGE_CONSTRAINTS, INVALID_NAME)); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC,
                ParserUtil.getInvalidMessage(Phone.MESSAGE_CONSTRAINTS, INVALID_PHONE)); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC,
                ParserUtil.getInvalidMessage(Email.MESSAGE_CONSTRAINTS, INVALID_EMAIL)); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC,
                ParserUtil.getInvalidMessage(Tag.MESSAGE_CONSTRAINTS, INVALID_TAG)); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                ParserUtil.getInvalidMessage(Phone.MESSAGE_CONSTRAINTS, INVALID_PHONE));

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC,
                ParserUtil.getInvalidMessage(Phone.MESSAGE_CONSTRAINTS, INVALID_PHONE));

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                ParserUtil.getInvalidMessage(Tag.MESSAGE_CONSTRAINTS, EMPTY));
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                ParserUtil.getInvalidMessage(Tag.MESSAGE_CONSTRAINTS, EMPTY));
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                ParserUtil.getInvalidMessage(Tag.MESSAGE_CONSTRAINTS, EMPTY));

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                ParserUtil.getInvalidMessage(Name.MESSAGE_CONSTRAINTS, INVALID_NAME));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommandStudentEditor editor =
                new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        editor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        editor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        editor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        editor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(new SingleIndexGenerator(targetIndex), editor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
