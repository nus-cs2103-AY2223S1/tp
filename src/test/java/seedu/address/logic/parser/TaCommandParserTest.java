package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_USERNAME_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.CABE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TaCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TeachingAssistantBuilder;


public class TaCommandParserTest {

    private final ProfCommandParser profParser = new ProfCommandParser();

    private final StudentCommandParser studentParser = new StudentCommandParser();

    private final TaCommandParser taParser = new TaCommandParser();

    @Test
    public void taParse_allFieldsPresent_success() {
        Person expectedPerson = new TeachingAssistantBuilder(CABE).withRating(VALID_RATING_CABE)
                .withModuleCode(VALID_MODULE_CODE_CABE).withGithubUsername(VALID_GITHUB_CABE).withTags(VALID_TAG_FRIEND)
                .withLocation(VALID_LOCATION_CABE).build();

        // multiple names - last name accepted
        assertParseSuccess(taParser, NAME_DESC_BOB + NAME_DESC_CABE + PHONE_DESC_CABE + EMAIL_DESC_CABE
                + GENDER_DESC_CABE + MODULE_CODE_DESC_CABE + RATING_DESC_CABE + GITHUB_USERNAME_DESC_CABE
                + TAG_DESC_FRIEND + LOCATION_DESC_CABE,
                new TaCommand((TeachingAssistant) expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(taParser, NAME_DESC_CABE + PHONE_DESC_BOB + PHONE_DESC_CABE
                        + EMAIL_DESC_CABE + GENDER_DESC_CABE + MODULE_CODE_DESC_CABE + RATING_DESC_CABE
                        + GITHUB_USERNAME_DESC_CABE + TAG_DESC_FRIEND + LOCATION_DESC_CABE,
                new TaCommand((TeachingAssistant) expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(taParser, NAME_DESC_CABE + PHONE_DESC_CABE + EMAIL_DESC_BOB + EMAIL_DESC_CABE
                + GENDER_DESC_CABE + TAG_DESC_FRIEND + MODULE_CODE_DESC_CABE + RATING_DESC_CABE
                + GITHUB_USERNAME_DESC_CABE + LOCATION_DESC_CABE,
                new TaCommand((TeachingAssistant) expectedPerson));

        // multiple gender - last gender accepted
        assertParseSuccess(taParser, NAME_DESC_CABE + PHONE_DESC_CABE + EMAIL_DESC_CABE + GENDER_DESC_AMY
                        + GENDER_DESC_CABE + MODULE_CODE_DESC_CABE + GITHUB_USERNAME_DESC_CABE + RATING_DESC_CABE
                        + TAG_DESC_FRIEND + LOCATION_DESC_CABE,
                new TaCommand((TeachingAssistant) expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new TeachingAssistantBuilder(CABE).withRating(VALID_RATING_CABE)
                .withModuleCode(VALID_MODULE_CODE_CABE).withGithubUsername(VALID_GITHUB_CABE)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).withLocation(VALID_LOCATION_CABE).build();
        assertParseSuccess(taParser, NAME_DESC_CABE + PHONE_DESC_CABE + EMAIL_DESC_CABE + GENDER_DESC_CABE
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_CODE_DESC_CABE + RATING_DESC_CABE + LOCATION_DESC_CABE
                + GITHUB_USERNAME_DESC_CABE, new TaCommand((TeachingAssistant) expectedPersonMultipleTags));
    }


    @Test
    public void parse_optionalTagFieldMissing_success() {
        // zero tags
        Person expectedPerson = new TeachingAssistantBuilder(CABE).withRating(VALID_RATING_CABE)
                .withModuleCode(VALID_MODULE_CODE_CABE).withGithubUsername(VALID_GITHUB_CABE)
                .withLocation(VALID_LOCATION_CABE).withTags().build();
        assertParseSuccess(taParser, NAME_DESC_CABE + PHONE_DESC_CABE + EMAIL_DESC_CABE
                        + GENDER_DESC_CABE + MODULE_CODE_DESC_CABE + RATING_DESC_CABE + GITHUB_USERNAME_DESC_CABE
                        + LOCATION_DESC_CABE,
                new TaCommand((TeachingAssistant) expectedPerson));
    }

    @Test
    public void parse_optionalGithubFieldMissing_success() {
        Person expectedPerson = new TeachingAssistantBuilder(CABE).withRating(VALID_RATING_CABE)
                .withModuleCode(VALID_MODULE_CODE_CABE).withTags(VALID_TAG_FRIEND).withGithubUsername("")
                .withLocation(VALID_LOCATION_CABE).build();
        System.out.println(expectedPerson.getUsername());
        assertParseSuccess(taParser, NAME_DESC_CABE + PHONE_DESC_CABE + EMAIL_DESC_CABE + GENDER_DESC_CABE
                        + MODULE_CODE_DESC_CABE + RATING_DESC_CABE + TAG_DESC_FRIEND + LOCATION_DESC_CABE,
                new TaCommand((TeachingAssistant) expectedPerson));
    }

    @Test
    public void parse_optionalLocationMissing_success() {
        // no year input
        Person expectedPerson = new TeachingAssistantBuilder(CABE).withRating(VALID_RATING_CABE)
                .withModuleCode(VALID_MODULE_CODE_CABE).withGithubUsername(VALID_GITHUB_CABE)
                .withTags(VALID_TAG_FRIEND).build();
        assertParseSuccess(taParser, NAME_DESC_CABE + PHONE_DESC_CABE + EMAIL_DESC_CABE + GENDER_DESC_CABE
                        + MODULE_CODE_DESC_CABE + RATING_DESC_CABE + TAG_DESC_FRIEND + GITHUB_USERNAME_DESC_CABE,
                new TaCommand((TeachingAssistant) expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(taParser, VALID_NAME_CABE + MODULE_CODE_DESC_CABE + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + GENDER_DESC_CABE,
            expectedMessage);

        // missing Module prefix
        assertParseFailure(taParser, NAME_DESC_CABE + VALID_MODULE_CODE_CABE + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + GENDER_DESC_CABE,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + VALID_PHONE_CABE
                + EMAIL_DESC_CABE + GENDER_DESC_CABE,
            expectedMessage);

        // missing email prefix
        assertParseFailure(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + PHONE_DESC_CABE
                + VALID_EMAIL_CABE + GENDER_DESC_CABE,
            expectedMessage);

        // missing gender prefix
        assertParseFailure(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + VALID_GENDER_CABE,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(taParser, NAME_DESC_CABE + VALID_GENDER_CABE + VALID_PHONE_CABE
                + VALID_EMAIL_CABE + VALID_GENDER_CABE,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(taParser, INVALID_NAME_DESC + MODULE_CODE_DESC_CABE + RATING_DESC_CABE
            + PHONE_DESC_CABE + EMAIL_DESC_CABE + GENDER_DESC_CABE
            + TAG_DESC_HUSBAND, Name.MESSAGE_CONSTRAINTS);

        // invalid Module Code
        assertParseFailure(taParser, NAME_DESC_CABE + INVALID_MODULE_CODE + RATING_DESC_CABE + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + GENDER_DESC_CABE
                + TAG_DESC_HUSBAND, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + RATING_DESC_CABE
                + INVALID_PHONE_DESC + EMAIL_DESC_CABE + GENDER_DESC_CABE
                + TAG_DESC_HUSBAND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + RATING_DESC_CABE
                + PHONE_DESC_CABE + INVALID_EMAIL_DESC + GENDER_DESC_CABE
                + TAG_DESC_HUSBAND, Email.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + RATING_DESC_CABE
                + PHONE_DESC_CABE + EMAIL_DESC_CABE + INVALID_GENDER_DESC
                + TAG_DESC_HUSBAND, Gender.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + RATING_DESC_CABE
                + PHONE_DESC_CABE + EMAIL_DESC_CABE + GENDER_DESC_CABE
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(taParser, INVALID_NAME_DESC + INVALID_MODULE_CODE + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + GENDER_DESC_CABE
                + TAG_DESC_HUSBAND, Name.MESSAGE_CONSTRAINTS);

        // Invalid Github Username
        assertParseFailure(taParser, NAME_DESC_CABE + MODULE_CODE_DESC_CABE + PHONE_DESC_CABE
                + EMAIL_DESC_CABE + GENDER_DESC_CABE + INVALID_GITHUB_DESC
                + TAG_DESC_HUSBAND, GithubUsername.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(taParser, PREAMBLE_NON_EMPTY + NAME_DESC_CABE + MODULE_CODE_DESC_CABE
                        + PHONE_DESC_CABE
                        + EMAIL_DESC_CABE + GENDER_DESC_CABE
                        + VALID_TAG_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaCommand.MESSAGE_USAGE));
    }
}
