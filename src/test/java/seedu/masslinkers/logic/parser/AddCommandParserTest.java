package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_ARGUMENTS;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.masslinkers.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INTEREST_DESC_AI;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INTEREST_DESC_SWE;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_INTEREST_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_MOD_DESC_CS2103;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.MOD_DESC_CS2100;
import static seedu.masslinkers.logic.commands.CommandTestUtil.MOD_DESC_CS2103;
import static seedu.masslinkers.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.masslinkers.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_INTEREST_AI;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_INTEREST_SWE;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_MOD_CS2100;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_MOD_CS2103;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.masslinkers.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.masslinkers.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.masslinkers.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.logic.commands.AddCommand;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.model.student.Telegram;
import seedu.masslinkers.testutil.StudentBuilder;

//@@author
public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withInterests(VALID_INTEREST_AI).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + INTEREST_DESC_AI + MOD_DESC_CS2100,
                new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + INTEREST_DESC_AI + MOD_DESC_CS2100,
                new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + INTEREST_DESC_AI + MOD_DESC_CS2100,
                new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + INTEREST_DESC_AI + MOD_DESC_CS2100,
                new AddCommand(expectedStudent));

        // multiple telegram - last telegram accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_AMY
                + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + INTEREST_DESC_AI + MOD_DESC_CS2100,
                new AddCommand(expectedStudent));

        // multiple interests - all accepted
        Student expectedStudentMultipleInterests = new StudentBuilder(BOB)
                .withInterests(VALID_INTEREST_AI, VALID_INTEREST_SWE)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + GITHUB_DESC_BOB + INTEREST_DESC_SWE + INTEREST_DESC_AI + MOD_DESC_CS2100,
                new AddCommand(expectedStudentMultipleInterests));

        // multiple mods - all accepted
        Student expectedStudentMultipleMods = new StudentBuilder(BOB).withMods(VALID_MOD_CS2100, VALID_MOD_CS2103)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                        + GITHUB_DESC_BOB + INTEREST_DESC_SWE + INTEREST_DESC_AI
                        + MOD_DESC_CS2100 + MOD_DESC_CS2103,
                new AddCommand(expectedStudentMultipleMods));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_MISSING_ARGUMENTS, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB,
                String.format(MESSAGE_INVALID_ARGUMENTS, VALID_NAME_BOB));

        // missing telegram prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + VALID_TELEGRAM_BOB + GITHUB_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_TELEGRAM_BOB + VALID_GITHUB_BOB,
                String.format(MESSAGE_INVALID_ARGUMENTS, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_TELEGRAM_BOB + VALID_GITHUB_BOB));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + GITHUB_DESC_BOB + INTEREST_DESC_SWE + INTEREST_DESC_AI, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + GITHUB_DESC_BOB + INTEREST_DESC_SWE + INTEREST_DESC_AI, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + TELEGRAM_DESC_BOB
                + GITHUB_DESC_BOB + INTEREST_DESC_SWE + INTEREST_DESC_AI, Email.MESSAGE_CONSTRAINTS);
        // invalid interest
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + GITHUB_DESC_BOB + INVALID_INTEREST_DESC + VALID_INTEREST_AI, Interest.MESSAGE_CONSTRAINTS);

        // invalid gitHub
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + INVALID_GITHUB_DESC + INTEREST_DESC_SWE + VALID_INTEREST_AI, GitHub.MESSAGE_CONSTRAINTS);

        // invalid telegram
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_TELEGRAM_DESC
                + GITHUB_DESC_BOB + INTEREST_DESC_SWE + VALID_INTEREST_AI, Telegram.MESSAGE_CONSTRAINTS);

        // invalid mod
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TELEGRAM_DESC_BOB
                + GITHUB_DESC_BOB + INTEREST_DESC_SWE + VALID_INTEREST_AI
                + INVALID_MOD_DESC_CS2103, Mod.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + INTEREST_DESC_AI,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + INTEREST_DESC_SWE + INTEREST_DESC_AI,
                String.format(MESSAGE_INVALID_ARGUMENTS, PREAMBLE_NON_EMPTY));
    }
}
