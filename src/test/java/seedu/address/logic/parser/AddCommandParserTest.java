package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_HOUR_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CABE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ProfCommand;
import seedu.address.logic.commands.TaCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
// import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ProfessorBuilder;
import seedu.address.testutil.TeachingAssistantBuilder;

public class AddCommandParserTest {

    private final ProfCommandParser profParser = new ProfCommandParser();

    private final StudentCommandParser studentParser = new StudentCommandParser();

    private final TaCommandParser taParser = new TaCommandParser();

    //    @Test
    //    public void studentParse_allFieldsPresent_success() {
    //        Person expectedPerson = new StudentBuilder(AMY).withTags(VALID_TAG_FRIEND).build();
    //
    //        // whitespace only preamble
    //        assertParseSuccess(studentParser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + PHONE_DESC_AMY
    //                + EMAIL_DESC_AMY + GENDER_DESC_AMY + TAG_DESC_FRIEND,
    //                new StudentCommand((Student) expectedPerson));
    //
    //        // multiple names - last name accepted
    //        assertParseSuccess(studentParser, NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
    //                + GENDER_DESC_AMY + TAG_DESC_FRIEND, new StudentCommand((Student) expectedPerson));
    //
    //        // multiple phones - last phone accepted
    //        assertParseSuccess(studentParser, NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_BOB + PHONE_DESC_AMY
    //                + EMAIL_DESC_AMY + GENDER_DESC_AMY + TAG_DESC_FRIEND,
    //                new StudentCommand((Student) expectedPerson));
    //
    //        // multiple emails - last email accepted
    //        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_BOB + EMAIL_DESC_AMY
    //                + GENDER_DESC_AMY + TAG_DESC_FRIEND, new StudentCommand((Student) expectedPerson));
    //
    //        // multiple addresses - last gender accepted
    //        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
    //                + GENDER_DESC_BOB + GENDER_DESC_AMY + TAG_DESC_FRIEND,
    //                new StudentCommand((Student) expectedPerson));
    //
    //        // multiple tags - all accepted
    //        Person expectedPersonMultipleTags = new StudentBuilder(AMY).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
    //                .build();
    //        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_AMY
    //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new StudentCommand((Student) expectedPersonMultipleTags));
    //    }

    public void profParse_allFieldsPresent_success() {
        Professor expectedPerson = (Professor) new ProfessorBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(profParser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + MODULE_CODE_DESC_BOB
            + PHONE_DESC_BOB
            + EMAIL_DESC_BOB + VALID_GENDER_BOB + TAG_DESC_FRIEND, new ProfCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(profParser, NAME_DESC_AMY + NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
            + EMAIL_DESC_BOB + VALID_GENDER_BOB + TAG_DESC_FRIEND, new ProfCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
            + EMAIL_DESC_BOB + VALID_GENDER_BOB + TAG_DESC_FRIEND, new ProfCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
            + EMAIL_DESC_BOB + VALID_GENDER_BOB + TAG_DESC_FRIEND, new ProfCommand(expectedPerson));

        // multiple addresses - last Gender accepted
        assertParseSuccess(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + VALID_GENDER_AMY + VALID_GENDER_BOB + TAG_DESC_FRIEND, new ProfCommand(expectedPerson));

        // multiple tags - all accepted
        Professor expectedPersonMultipleTags = (Professor) new ProfessorBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(profParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new ProfCommand(expectedPersonMultipleTags));
    }

    public void taParse_allFieldsPresent_success() {
        Person expectedPerson = new TeachingAssistantBuilder(CABE).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(taParser, PREAMBLE_WHITESPACE + NAME_DESC_CABE + MODULE_CODE_DESC_CABE
                + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + VALID_GENDER_CABE + TAG_DESC_FRIEND,
            new TaCommand((TeachingAssistant) expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(taParser, NAME_DESC_AMY + NAME_DESC_CABE + MODULE_CODE_DESC_CABE + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + VALID_GENDER_CABE + TAG_DESC_FRIEND,
            new TaCommand((TeachingAssistant) expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + PHONE_DESC_AMY + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + VALID_GENDER_CABE + TAG_DESC_FRIEND,
            new TaCommand((TeachingAssistant) expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + PHONE_DESC_CABE + EMAIL_DESC_AMY
                + EMAIL_DESC_CABE + VALID_GENDER_CABE + TAG_DESC_FRIEND,
            new TaCommand((TeachingAssistant) expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(taParser, NAME_DESC_BOB + MODULE_CODE_DESC_CABE + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_GENDER_BOB + TAG_DESC_FRIEND,
            new TaCommand((TeachingAssistant) expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new TeachingAssistantBuilder(CABE).withTags(VALID_TAG_FRIEND,
                VALID_TAG_HUSBAND)
            .build();
        assertParseSuccess(taParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            new TaCommand((TeachingAssistant) expectedPersonMultipleTags));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(profParser, VALID_NAME_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GENDER_DESC_BOB,
            expectedMessage);

        // missing Module prefix
        assertParseFailure(profParser, NAME_DESC_BOB + VALID_MODULE_CODE_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + GENDER_DESC_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + GENDER_DESC_BOB,
            expectedMessage);

        // missing email prefix
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + GENDER_DESC_BOB,
            expectedMessage);

        // missing address prefix
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_GENDER_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(profParser, VALID_NAME_BOB + VALID_GENDER_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_GENDER_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(profParser, INVALID_NAME_DESC + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
            + EMAIL_DESC_BOB + GENDER_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + OFFICE_HOUR_MONDAY, Name.MESSAGE_CONSTRAINTS);

        // invalid Module Code
        assertParseFailure(profParser, NAME_DESC_BOB + INVALID_MODULE_CODE_DESC + PHONE_DESC_BOB
            + EMAIL_DESC_BOB + GENDER_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + OFFICE_HOUR_MONDAY, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + INVALID_PHONE_DESC
            + EMAIL_DESC_BOB + GENDER_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + OFFICE_HOUR_MONDAY, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
            + INVALID_EMAIL_DESC + GENDER_DESC_BOB
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + OFFICE_HOUR_MONDAY, Email.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
            + EMAIL_DESC_BOB + INVALID_GENDER_DESC
            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + OFFICE_HOUR_MONDAY, Gender.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
            + EMAIL_DESC_BOB + GENDER_DESC_BOB
            + INVALID_TAG_DESC + VALID_TAG_FRIEND + OFFICE_HOUR_MONDAY, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(profParser, INVALID_NAME_DESC + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_GENDER_DESC + OFFICE_HOUR_MONDAY,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(profParser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + MODULE_CODE_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND + OFFICE_HOUR_MONDAY,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfCommand.MESSAGE_USAGE));
    }
}
