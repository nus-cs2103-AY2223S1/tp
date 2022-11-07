package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CAP_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CAP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GRADUATION_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GRADUATION_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CAP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADUATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UNIVERSITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOB_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MAJOR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MAJOR_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_KIV;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REJECTED;
import static seedu.address.logic.commands.CommandTestUtil.UNIVERSITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.UNIVERSITY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADUATION_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_KIV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REJECTED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_REJECTED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple genders - last gender accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_AMY
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple CAPs - last CAP accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_AMY
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple graduation dates - last graduationDate accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_AMY
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple universities - last university accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_AMY
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple majors - last major accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + CAP_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_AMY
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple job IDs - last id accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_AMY
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple job titles - last title accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_AMY
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_REJECTED, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_REJECTED, VALID_TAG_KIV)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY
                + CAP_DESC_AMY
                + GENDER_DESC_AMY
                + GRADUATION_DATE_DESC_AMY
                + UNIVERSITY_DESC_AMY
                + MAJOR_DESC_AMY
                + JOB_ID_DESC_AMY
                + JOB_TITLE_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_ADDRESS_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing CAP prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + VALID_CAP_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + VALID_GENDER_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing graduation date prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + VALID_GRADUATION_DATE_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing university prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + VALID_UNIVERSITY_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing major prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + VALID_MAJOR_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing job id prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + VALID_JOB_ID_BOB
                + JOB_TITLE_DESC_BOB, expectedMessage);

        // missing job title prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + VALID_JOB_TITLE_BOB, expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_ADDRESS_BOB
                + VALID_CAP_BOB
                + VALID_GENDER_BOB
                + VALID_GRADUATION_DATE_BOB
                + VALID_UNIVERSITY_BOB
                + VALID_MAJOR_BOB
                + VALID_JOB_ID_BOB
                + VALID_JOB_TITLE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Address.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + INVALID_GENDER_DESC
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Gender.MESSAGE_CONSTRAINTS);

        // invalid graduation date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + INVALID_GRADUATION_DESC
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, GraduationDate.MESSAGE_CONSTRAINTS);

        // invalid CAP
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + INVALID_CAP_DESC
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Cap.MESSAGE_CONSTRAINTS);

        // invalid university
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + INVALID_UNIVERSITY_DESC
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, University.MESSAGE_CONSTRAINTS);

        // invalid major
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + CAP_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + INVALID_MAJOR_DESC
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Major.MESSAGE_CONSTRAINTS);

        // invalid job ID
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + INVALID_JOB_ID_DESC
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Id.MESSAGE_CONSTRAINTS);

        // invalid title
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + INVALID_JOB_TITLE_DESC
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED, Title.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + INVALID_TAG_DESC
                + VALID_TAG_REJECTED, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + CAP_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV
                + TAG_DESC_REJECTED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
