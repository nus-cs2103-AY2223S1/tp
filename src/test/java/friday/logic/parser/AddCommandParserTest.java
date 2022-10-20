package friday.logic.parser;

/*
import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.commands.CommandTestUtil.CONSULTATION_DESC_AMY;
import static friday.logic.commands.CommandTestUtil.CONSULTATION_DESC_BOB;
import static friday.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static friday.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static friday.logic.commands.CommandTestUtil.INVALID_TELEGRAMHANDLE_DESC;
import static friday.logic.commands.CommandTestUtil.MASTERYCHECK_DESC_AMY;
import static friday.logic.commands.CommandTestUtil.MASTERYCHECK_DESC_BOB;
import static friday.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static friday.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static friday.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static friday.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static friday.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static friday.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static friday.logic.commands.CommandTestUtil.TELEGRAMHANDLE_DESC_AMY;
import static friday.logic.commands.CommandTestUtil.TELEGRAMHANDLE_DESC_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_CONSULTATION_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_MASTERYCHECK_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static friday.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static friday.logic.commands.CommandTestUtil.VALID_TELEGRAMHANDLE_BOB;
import static friday.logic.parser.CommandParserTestUtil.assertParseFailure;
import static friday.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static friday.testutil.TypicalStudents.AMY;
import static friday.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import friday.logic.commands.AddCommand;
import friday.model.student.Name;
import friday.model.student.Student;
import friday.model.student.TelegramHandle;
import friday.model.tag.Tag;
import friday.testutil.StudentBuilder;

 */

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    /*
    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB
                + CONSULTATION_DESC_BOB + MASTERYCHECK_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB
                + CONSULTATION_DESC_BOB + MASTERYCHECK_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAMHANDLE_DESC_AMY + TELEGRAMHANDLE_DESC_BOB
                + CONSULTATION_DESC_BOB + MASTERYCHECK_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB + CONSULTATION_DESC_AMY
                + CONSULTATION_DESC_BOB + MASTERYCHECK_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB + CONSULTATION_DESC_BOB
                + MASTERYCHECK_DESC_AMY + MASTERYCHECK_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB + CONSULTATION_DESC_BOB
                + MASTERYCHECK_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedStudentMultipleTags));
    }

     */

    /*
    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + TELEGRAMHANDLE_DESC_AMY + CONSULTATION_DESC_AMY
                        + MASTERYCHECK_DESC_AMY, new AddCommand(expectedStudent));
    }

     */

    /*
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + TELEGRAMHANDLE_DESC_BOB + CONSULTATION_DESC_BOB
                        + MASTERYCHECK_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_TELEGRAMHANDLE_BOB + CONSULTATION_DESC_BOB
                        + MASTERYCHECK_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB + VALID_CONSULTATION_BOB
                        + MASTERYCHECK_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB + CONSULTATION_DESC_BOB
                        + VALID_MASTERYCHECK_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_TELEGRAMHANDLE_BOB + VALID_CONSULTATION_BOB
                        + VALID_MASTERYCHECK_BOB, expectedMessage);
    }

     */

    /*
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + TELEGRAMHANDLE_DESC_BOB + CONSULTATION_DESC_BOB
                + MASTERYCHECK_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_TELEGRAMHANDLE_DESC + CONSULTATION_DESC_BOB
                + MASTERYCHECK_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, TelegramHandle.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB + CONSULTATION_DESC_BOB
                + MASTERYCHECK_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + TELEGRAMHANDLE_DESC_BOB
                        + CONSULTATION_DESC_BOB + MASTERYCHECK_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

     */
}
