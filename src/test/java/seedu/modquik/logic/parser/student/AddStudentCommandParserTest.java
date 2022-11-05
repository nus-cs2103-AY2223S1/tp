package seedu.modquik.logic.parser.student;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.commons.core.Messages.MESSAGE_MISSING_PREFIXES_ALL;
import static seedu.modquik.logic.commands.CommandTestUtil.ATTENDANCE_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.modquik.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.modquik.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.modquik.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.modquik.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.modquik.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.PARTICIPATION_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.modquik.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.modquik.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.modquik.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.modquik.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.TUTORIAL_DESC_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_MODULE_TUT1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_TUT1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.modquik.testutil.TypicalPersons.BOB;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.modquik.logic.commands.student.AddStudentCommand;
import seedu.modquik.logic.parser.CliSyntax;
import seedu.modquik.logic.parser.Prefix;
import seedu.modquik.model.student.Email;
import seedu.modquik.model.student.Name;
import seedu.modquik.model.student.Phone;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.student.StudentId;
import seedu.modquik.model.tag.Tag;
import seedu.modquik.testutil.PersonBuilder;


public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ID_DESC_BOB
                 + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                 + ATTENDANCE_DESC_BOB + PARTICIPATION_DESC_BOB + GRADE_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + ATTENDANCE_DESC_BOB + PARTICIPATION_DESC_BOB + GRADE_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + ATTENDANCE_DESC_BOB + PARTICIPATION_DESC_BOB + GRADE_DESC_BOB
                + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB
                + TUTORIAL_DESC_BOB
                + ATTENDANCE_DESC_BOB + PARTICIPATION_DESC_BOB + GRADE_DESC_BOB
                + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + ATTENDANCE_DESC_BOB + PARTICIPATION_DESC_BOB + GRADE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudentMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new PersonBuilder(BOB).withTags().build();
        assertParseSuccess(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB,
                new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_NAME}),
                AddStudentCommand.MESSAGE_USAGE));

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB
                + VALID_PHONE_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_PHONE}),
                AddStudentCommand.MESSAGE_USAGE));

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + VALID_EMAIL_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{CliSyntax.PREFIX_EMAIL}),
                AddStudentCommand.MESSAGE_USAGE));

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_ID_BOB
                + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_TELEGRAM_BOB + VALID_MODULE_TUT1 + VALID_NAME_TUT1,
                String.format(MESSAGE_MISSING_PREFIXES_ALL, Arrays.toString(new Prefix[]{
                    CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_ID, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                    CliSyntax.PREFIX_TELEGRAM, CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_TUTORIAL}),
                    AddStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB
                + INVALID_PHONE_DESC + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + INVALID_EMAIL_DESC + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ID_DESC
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, StudentId.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ID_DESC
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + ID_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB + MODULE_DESC_BOB + TUTORIAL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
