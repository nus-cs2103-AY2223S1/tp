package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.CLASS_DESC_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.CLASS_DESC_CS1231S;
import static seedu.taassist.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_CLASS_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1231S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.EditCommand;
import seedu.taassist.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MODULE_CLASS_EMPTY = " " + PREFIX_MODULE_CLASS;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

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
        assertParseFailure(parser, "1" + INVALID_CLASS_DESC, ModuleClass.MESSAGE_CONSTRAINTS); // invalid class

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_CLASS} alone will reset the classes of the {@code Student} being edited,
        // parsing it together with a valid class results in error
        assertParseFailure(parser, "1" + CLASS_DESC_CS1231S + CLASS_DESC_CS1101S + MODULE_CLASS_EMPTY,
                ModuleClass.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CLASS_DESC_CS1231S + MODULE_CLASS_EMPTY + CLASS_DESC_CS1101S,
                ModuleClass.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MODULE_CLASS_EMPTY + CLASS_DESC_CS1231S + CLASS_DESC_CS1101S,
                ModuleClass.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + CLASS_DESC_CS1101S
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + CLASS_DESC_CS1231S;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withModuleClasses(VALID_CLASS_CS1101S, VALID_CLASS_CS1231S).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // classes
        userInput = targetIndex.getOneBased() + CLASS_DESC_CS1231S;
        descriptor = new EditStudentDescriptorBuilder().withModuleClasses(VALID_CLASS_CS1231S).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + CLASS_DESC_CS1231S + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + CLASS_DESC_CS1231S
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + CLASS_DESC_CS1101S;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withModuleClasses(VALID_CLASS_CS1231S, VALID_CLASS_CS1101S)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetClasses_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + MODULE_CLASS_EMPTY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withModuleClasses().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
