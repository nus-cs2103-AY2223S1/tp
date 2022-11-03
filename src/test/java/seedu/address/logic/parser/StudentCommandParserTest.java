package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_USERNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StudentCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;



public class StudentCommandParserTest {

    private final ProfCommandParser profParser = new ProfCommandParser();

    private final StudentCommandParser studentParser = new StudentCommandParser();

    private final TaCommandParser taParser = new TaCommandParser();

    @Test
    public void studentParse_allFieldsPresent_success() {
        Person expectedPerson = new StudentBuilder(AMY).withModuleCodes(VALID_MODULE_CODE_AMY)
                .withYear(VALID_YEAR_AMY).withGithubUsername(VALID_GITHUB_AMY).withTags(VALID_TAG_FRIEND)
                .withLocation(VALID_LOCATION_AMY).build();

        // multiple names - last name accepted
        assertParseSuccess(studentParser, NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + GENDER_DESC_AMY + MODULE_CODE_DESC_AMY + YEAR_DESC_AMY + GITHUB_USERNAME_DESC_AMY + TAG_DESC_FRIEND
                + LOCATION_DESC_AMY,
                new StudentCommand((Student) expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_BOB + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + GENDER_DESC_AMY + MODULE_CODE_DESC_AMY + YEAR_DESC_AMY
                        + GITHUB_USERNAME_DESC_AMY + TAG_DESC_FRIEND + LOCATION_DESC_AMY,
                new StudentCommand((Student) expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_BOB + EMAIL_DESC_AMY
                + GENDER_DESC_AMY + TAG_DESC_FRIEND + MODULE_CODE_DESC_AMY + YEAR_DESC_AMY + GITHUB_USERNAME_DESC_AMY
                + LOCATION_DESC_AMY,
                new StudentCommand((Student) expectedPerson));

        // multiple gender - last gender accepted
        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_BOB
                        + GENDER_DESC_AMY + MODULE_CODE_DESC_AMY + YEAR_DESC_AMY + GITHUB_USERNAME_DESC_AMY
                        + TAG_DESC_FRIEND + LOCATION_DESC_AMY,
                new StudentCommand((Student) expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new StudentBuilder(AMY).withModuleCodes(VALID_MODULE_CODE_AMY)
                .withYear(VALID_YEAR_AMY).withGithubUsername(VALID_GITHUB_AMY)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).withLocation(VALID_LOCATION_AMY).build();
        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_AMY
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_CODE_DESC_AMY + YEAR_DESC_AMY + LOCATION_DESC_AMY
                + GITHUB_USERNAME_DESC_AMY, new StudentCommand((Student) expectedPersonMultipleTags));
    }


    @Test
    public void parse_optionalTagFieldMissing_success() {
        // zero tags
        Person expectedPerson = new StudentBuilder(AMY).withModuleCodes(VALID_MODULE_CODE_AMY)
                .withYear(VALID_YEAR_AMY).withGithubUsername(VALID_GITHUB_AMY).withLocation(VALID_LOCATION_AMY).build();
        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_BOB
                        + GENDER_DESC_AMY + MODULE_CODE_DESC_AMY + YEAR_DESC_AMY + GITHUB_USERNAME_DESC_AMY
                        + LOCATION_DESC_AMY,
                new StudentCommand((Student) expectedPerson));
    }

    @Test
    public void parse_optionalGithubFieldMissing_success() {
        // no github username input
        Person expectedPerson = new StudentBuilder(AMY).withModuleCodes(VALID_MODULE_CODE_AMY)
                .withYear(VALID_YEAR_AMY).withTags(VALID_TAG_FRIEND).withLocation(VALID_LOCATION_AMY).build();

        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_BOB
                        + GENDER_DESC_AMY + MODULE_CODE_DESC_AMY + YEAR_DESC_AMY + TAG_DESC_FRIEND + LOCATION_DESC_AMY,
                new StudentCommand((Student) expectedPerson));
    }

    @Test
    public void parse_optionalYearMissing_success() {
        // no year input
        Person expectedPerson = new StudentBuilder(AMY).withModuleCodes(VALID_MODULE_CODE_AMY)
                .withGithubUsername(VALID_GITHUB_AMY).withTags(VALID_TAG_FRIEND)
                .withLocation(VALID_LOCATION_AMY).build();

        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_BOB
                        + GENDER_DESC_AMY + MODULE_CODE_DESC_AMY + TAG_DESC_FRIEND + GITHUB_USERNAME_DESC_AMY
                        + GITHUB_USERNAME_DESC_AMY + LOCATION_DESC_AMY,
                new StudentCommand((Student) expectedPerson));
    }

    @Test
    public void parse_optionalLocationMissing_success() {
        // no year input
        Person expectedPerson = new StudentBuilder(AMY).withModuleCodes(VALID_MODULE_CODE_AMY)
                .withGithubUsername(VALID_GITHUB_AMY).withTags(VALID_TAG_FRIEND).build();

        assertParseSuccess(studentParser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_BOB
                        + GENDER_DESC_AMY + MODULE_CODE_DESC_AMY + TAG_DESC_FRIEND + GITHUB_USERNAME_DESC_AMY,
                new StudentCommand((Student) expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(studentParser, VALID_NAME_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + GENDER_DESC_AMY,
            expectedMessage);

        // missing Module prefix
        assertParseFailure(studentParser, NAME_DESC_AMY + VALID_MODULE_CODE_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + GENDER_DESC_AMY,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + VALID_PHONE_AMY
                + EMAIL_DESC_AMY + GENDER_DESC_AMY,
            expectedMessage);

        // missing email prefix
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + VALID_EMAIL_AMY + GENDER_DESC_AMY,
            expectedMessage);

        // missing gender prefix
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + VALID_GENDER_AMY,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(studentParser, NAME_DESC_AMY + VALID_GENDER_AMY + VALID_PHONE_AMY + VALID_EMAIL_AMY
                + VALID_GENDER_AMY,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(studentParser, INVALID_NAME_DESC + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
            + EMAIL_DESC_AMY + GENDER_DESC_AMY
            + TAG_DESC_HUSBAND, Name.MESSAGE_CONSTRAINTS);

        // invalid Module Code
        assertParseFailure(studentParser, NAME_DESC_AMY + INVALID_MODULE_CODE_DESC + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + GENDER_DESC_AMY
                + TAG_DESC_HUSBAND, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + INVALID_PHONE_DESC
                + EMAIL_DESC_AMY + GENDER_DESC_AMY
                + TAG_DESC_HUSBAND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + INVALID_EMAIL_DESC + GENDER_DESC_AMY
                + TAG_DESC_HUSBAND, Email.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + INVALID_GENDER_DESC
                + TAG_DESC_HUSBAND, Gender.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + GENDER_DESC_AMY
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(studentParser, INVALID_NAME_DESC + INVALID_MODULE_CODE_DESC + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + GENDER_DESC_AMY
                + TAG_DESC_HUSBAND, Name.MESSAGE_CONSTRAINTS);

        // Invalid Github Username
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + GENDER_DESC_AMY + INVALID_GITHUB_DESC
                + TAG_DESC_HUSBAND, GithubUsername.MESSAGE_CONSTRAINTS);

        // Invalid Year
        assertParseFailure(studentParser, NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + GENDER_DESC_AMY + INVALID_YEAR_DESC
                + TAG_DESC_HUSBAND, Year.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(studentParser, PREAMBLE_NON_EMPTY + NAME_DESC_AMY + MODULE_CODE_DESC_AMY
                        + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + GENDER_DESC_AMY
                        + VALID_TAG_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentCommand.MESSAGE_USAGE));
    }
}
