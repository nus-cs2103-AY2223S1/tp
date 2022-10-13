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
import static seedu.taassist.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.taassist.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1231S;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taassist.testutil.TypicalStudents.AMY;
import static seedu.taassist.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.AddCommand;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.model.student.Student;
import seedu.taassist.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withModuleClasses(VALID_CLASS_CS1231S).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLASS_DESC_CS1231S, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLASS_DESC_CS1231S, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLASS_DESC_CS1231S, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLASS_DESC_CS1231S, new AddCommand(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + CLASS_DESC_CS1231S, new AddCommand(expectedStudent));

        // multiple classes - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB)
                .withModuleClasses(VALID_CLASS_CS1231S, VALID_CLASS_CS1101S)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + CLASS_DESC_CS1101S + CLASS_DESC_CS1231S, new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero classes
        Student expectedStudentNoTag = new StudentBuilder(AMY).withModuleClasses().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedStudentNoTag));

        // no address
        Student expectedStudentNoAddr = new StudentBuilder(AMY).withAddress("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + CLASS_DESC_CS1231S,
                new AddCommand(expectedStudentNoAddr));

        // no phone
        Student expectedStudentNoPhone = new StudentBuilder(AMY).withPhone("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + CLASS_DESC_CS1231S,
                new AddCommand(expectedStudentNoPhone));

        // no email
        Student expectedStudentNoEmail = new StudentBuilder(AMY).withEmail("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + CLASS_DESC_CS1231S,
                new AddCommand(expectedStudentNoEmail));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + CLASS_DESC_CS1101S + CLASS_DESC_CS1231S, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + CLASS_DESC_CS1101S + CLASS_DESC_CS1231S, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + CLASS_DESC_CS1101S + CLASS_DESC_CS1231S, Email.MESSAGE_CONSTRAINTS);

        // invalid class
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_CLASS_DESC + VALID_CLASS_CS1231S, ModuleClass.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CLASS_DESC_CS1101S + CLASS_DESC_CS1231S,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
