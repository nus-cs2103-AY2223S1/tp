package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_USERNAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALISATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ProfCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ProfessorBuilder;


public class ProfCommandParserTest {

    private final ProfCommandParser profParser = new ProfCommandParser();

    private final StudentCommandParser studentParser = new StudentCommandParser();

    private final TaCommandParser taParser = new TaCommandParser();

    @Test
    public void studentParse_allFieldsPresent_success() {
        Person expectedPerson = new ProfessorBuilder(BOB).withRating(VALID_RATING_BOB)
                .withSpecialisation(VALID_SPECIALISATION_BOB)
                .withModuleCode(VALID_MODULE_CODE_BOB).withGithubUsername(VALID_GITHUB_BOB).withTags(VALID_TAG_FRIEND)
                .withGender(VALID_GENDER_BOB).withLocation(VALID_LOCATION_BOB).build();

        // multiple names - last name accepted
        assertParseSuccess(profParser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + RATING_DESC_BOB + GENDER_DESC_BOB + MODULE_CODE_DESC_BOB + GITHUB_USERNAME_DESC_BOB
                + SPECIALISATION_DESC_BOB + TAG_DESC_FRIEND
                + LOCATION_DESC_BOB,
                new ProfCommand((Professor) expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(profParser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GENDER_DESC_BOB + MODULE_CODE_DESC_BOB + RATING_DESC_BOB
                + GITHUB_USERNAME_DESC_BOB + SPECIALISATION_DESC_BOB
                + TAG_DESC_FRIEND + LOCATION_DESC_BOB,
                new ProfCommand((Professor) expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(profParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + GENDER_DESC_BOB + TAG_DESC_FRIEND + MODULE_CODE_DESC_BOB + GITHUB_USERNAME_DESC_BOB
                + SPECIALISATION_DESC_BOB + RATING_DESC_BOB
                + LOCATION_DESC_BOB,
                new ProfCommand((Professor) expectedPerson));

        // multiple gender - last gender accepted
        assertParseSuccess(profParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_AMY
                        + GENDER_DESC_BOB + RATING_DESC_BOB + MODULE_CODE_DESC_BOB + GITHUB_USERNAME_DESC_BOB
                        + SPECIALISATION_DESC_BOB + TAG_DESC_FRIEND + LOCATION_DESC_BOB,
                new ProfCommand((Professor) expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new ProfessorBuilder(BOB).withRating(VALID_RATING_BOB)
                .withSpecialisation(VALID_SPECIALISATION_BOB)
                .withModuleCode(VALID_MODULE_CODE_BOB).withGithubUsername(VALID_GITHUB_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).withLocation(VALID_LOCATION_BOB).build();
        assertParseSuccess(profParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + RATING_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_CODE_DESC_BOB + LOCATION_DESC_BOB
                + GITHUB_USERNAME_DESC_BOB + SPECIALISATION_DESC_BOB,
                new ProfCommand((Professor) expectedPersonMultipleTags));
    }


    @Test
    public void parse_optionalTagFieldMissing_success() {
        // zero tags
        Person expectedPerson = new ProfessorBuilder(BOB).withRating(VALID_RATING_BOB)
                .withSpecialisation(VALID_SPECIALISATION_BOB)
                .withModuleCode(VALID_MODULE_CODE_BOB).withGender(VALID_GENDER_BOB)
                .withGithubUsername(VALID_GITHUB_BOB).withTags()
                .withLocation(VALID_LOCATION_BOB).build();
        assertParseSuccess(profParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GENDER_DESC_BOB + SPECIALISATION_DESC_BOB + MODULE_CODE_DESC_BOB + RATING_DESC_BOB
                        + GITHUB_USERNAME_DESC_BOB + LOCATION_DESC_BOB,
                new ProfCommand((Professor) expectedPerson));
    }

    @Test
    public void parse_optionalGithubFieldMissing_success() {
        // no github username input
        Person expectedPerson = new ProfessorBuilder(BOB).withRating(VALID_RATING_BOB)
                .withSpecialisation(VALID_SPECIALISATION_BOB).withModuleCode(VALID_MODULE_CODE_BOB)
                .withTags(VALID_TAG_FRIEND).withLocation(VALID_LOCATION_BOB).withGender(VALID_GENDER_BOB).build();
        assertParseSuccess(profParser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB
                        + RATING_DESC_BOB + SPECIALISATION_DESC_BOB + MODULE_CODE_DESC_BOB + TAG_DESC_FRIEND
                        + LOCATION_DESC_BOB,
                new ProfCommand((Professor) expectedPerson));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(profParser, VALID_NAME_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GENDER_DESC_BOB,
            expectedMessage);

        // missing Module prefix
        assertParseFailure(profParser, NAME_DESC_BOB + VALID_MODULE_CODE_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GENDER_DESC_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + GENDER_DESC_BOB,
            expectedMessage);

        // missing email prefix
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + GENDER_DESC_BOB,
            expectedMessage);

        // missing gender prefix
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_GENDER_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(profParser, NAME_DESC_BOB + VALID_GENDER_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_GENDER_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(profParser, INVALID_NAME_DESC + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND,
            Name.MESSAGE_CONSTRAINTS);

        // invalid Module Code
        assertParseFailure(profParser, NAME_DESC_BOB + INVALID_MODULE_CODE_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + TAG_DESC_HUSBAND, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + TAG_DESC_HUSBAND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + GENDER_DESC_BOB
                + TAG_DESC_HUSBAND, Email.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_GENDER_DESC
                + TAG_DESC_HUSBAND, Gender.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(profParser, INVALID_NAME_DESC + INVALID_MODULE_CODE_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GENDER_DESC_BOB
                + TAG_DESC_HUSBAND, Name.MESSAGE_CONSTRAINTS);

        // Invalid Github Username
        assertParseFailure(profParser, NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GENDER_DESC_BOB + INVALID_GITHUB_DESC
                + TAG_DESC_HUSBAND, GithubUsername.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(profParser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + MODULE_CODE_DESC_BOB
                        + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + GENDER_DESC_BOB
                        + VALID_TAG_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfCommand.MESSAGE_USAGE));
    }
}
