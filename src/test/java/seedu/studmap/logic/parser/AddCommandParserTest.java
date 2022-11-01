package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.GIT_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.GIT_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.HANDLE_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.HANDLE_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.ID_DESC_CHARLIE;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_EMAIL;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_NAME;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_PHONE;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_TAG;
import static seedu.studmap.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.studmap.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.MODULE_DESC_CHARLIE;
import static seedu.studmap.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.NAME_DESC_CHARLIE;
import static seedu.studmap.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.studmap.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.studmap.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.studmap.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_ID_CHARLIE;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_MODULE_CHARLIE;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_NAME_CHARLIE;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.studmap.logic.parser.AddCommandParser.getMissingPrefixesMessage;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.studmap.logic.parser.ParserUtil.getInvalidMessage;
import static seedu.studmap.testutil.TypicalStudents.AMY;
import static seedu.studmap.testutil.TypicalStudents.BOB;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.studmap.logic.commands.AddCommand;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.tag.Tag;
import seedu.studmap.testutil.StudentBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                                                                     .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {

        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + MODULE_DESC_AMY
                        + ID_DESC_AMY + GIT_DESC_AMY + HANDLE_DESC_AMY,
                new AddCommand(expectedStudent));

        Student minimalStudent = new StudentBuilder()
                .withName(VALID_NAME_CHARLIE).withId(VALID_ID_CHARLIE).withModule(VALID_MODULE_CHARLIE).build();
        assertParseSuccess(parser, NAME_DESC_CHARLIE + MODULE_DESC_CHARLIE + ID_DESC_CHARLIE,
                new AddCommand(minimalStudent));


    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name
        assertParseFailure(parser, PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage
                + "\n" + getMissingPrefixesMessage(List.of(PREFIX_NAME)));

        // missing module
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage
                + "\n" + getMissingPrefixesMessage(List.of(PREFIX_MODULE)));

        // missing student id
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage
                + "\n" + getMissingPrefixesMessage(List.of(PREFIX_ID)));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, getInvalidMessage(Name.MESSAGE_CONSTRAINTS, INVALID_NAME));

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, getInvalidMessage(Phone.MESSAGE_CONSTRAINTS, INVALID_PHONE));

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, getInvalidMessage(Email.MESSAGE_CONSTRAINTS, INVALID_EMAIL));

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                + INVALID_TAG_DESC, getInvalidMessage(Tag.MESSAGE_CONSTRAINTS, INVALID_TAG));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB,
                getInvalidMessage(Name.MESSAGE_CONSTRAINTS, INVALID_NAME));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + MODULE_DESC_BOB + ID_DESC_BOB + GIT_DESC_BOB + HANDLE_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
