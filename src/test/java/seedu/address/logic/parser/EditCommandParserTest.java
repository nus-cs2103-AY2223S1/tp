package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_CABE;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_USERNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_USERNAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OFFICE_HOUR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_HOUR_DESC_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.OFFICE_HOUR_DESC_TUESDAY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALISATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALISATION_DESC_GRAPHICS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_CABE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_HOUR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_HOUR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICEHOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String GITHUB_EMPTY = " " + PREFIX_GITHUBUSERNAME;
    private static final String SPECIALISATION_EMPTY = " " + PREFIX_SPECIALISATION;
    private static final String RATING_EMPTY = " " + PREFIX_RATING;
    private static final String YEAR_EMPTY = " " + PREFIX_YEAR;
    private static final String OFFICE_HOUR_EMPTY = " " + PREFIX_OFFICEHOUR;


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        //empty field specified
        assertParseFailure(parser, "n/", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS); // invalid module code
        assertParseFailure(parser, "1" + INVALID_GENDER_DESC, Gender.MESSAGE_CONSTRAINTS); // invalid gender
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS); // invalid rating
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser, "1" + INVALID_OFFICE_HOUR_DESC,
                OfficeHour.MESSAGE_CONSTRAINTS); // invalid office hour
        assertParseFailure(parser, "1" + INVALID_GITHUB_DESC, GithubUsername.MESSAGE_CONSTRAINTS); // invalid username
        assertParseFailure(parser, "1" + INVALID_YEAR_DESC, Year.MESSAGE_CONSTRAINTS); // invalid year

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_MODULE} alone will reset the module of the {@code Person} being edited,
        // parsing it together with a valid module results in error
        assertParseFailure(parser, "1" + MODULE_CODE_DESC_BOB + MODULE_CODE_DESC_AMY
                + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MODULE_CODE_DESC_BOB + INVALID_MODULE_CODE_DESC
                + MODULE_CODE_DESC_AMY, ModuleCode.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC + MODULE_CODE_DESC_BOB
                + MODULE_CODE_DESC_AMY, ModuleCode.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_GENDER_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + GENDER_DESC_CABE + NAME_DESC_AMY + TAG_DESC_FRIEND + MODULE_CODE_DESC_AMY
                + GITHUB_USERNAME_DESC_AMY + LOCATION_DESC_AMY + GENDER_DESC_AMY + OFFICE_HOUR_DESC_MONDAY
                + RATING_DESC_ONE + SPECIALISATION_DESC_GRAPHICS + YEAR_DESC_AMY + MODULE_CODE_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withGender(VALID_GENDER_CABE)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withLocation(VALID_LOCATION_AMY)
                .withModuleCodeSet(VALID_MODULE_CODE_AMY, VALID_MODULE_CODE_BOB)
                .withUsername(VALID_GITHUB_AMY).withGender(VALID_GENDER_AMY)
                .withOfficeHour(VALID_OFFICE_HOUR).withRating(VALID_RATING_ONE)
                .withSpecialisation(VALID_SPECIALISATION).withYear(VALID_YEAR_AMY).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module code
        userInput = targetIndex.getOneBased() + MODULE_CODE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withLocation(VALID_LOCATION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // username
        userInput = targetIndex.getOneBased() + GITHUB_USERNAME_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withUsername(VALID_GITHUB_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // gender
        userInput = targetIndex.getOneBased() + GENDER_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGender(VALID_GENDER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = targetIndex.getOneBased() + RATING_DESC_ONE;
        descriptor = new EditPersonDescriptorBuilder().withRating(VALID_RATING_ONE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // office hour
        userInput = targetIndex.getOneBased() + OFFICE_HOUR_DESC_MONDAY;
        descriptor = new EditPersonDescriptorBuilder().withOfficeHour(VALID_OFFICE_HOUR).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // specialisation
        userInput = targetIndex.getOneBased() + SPECIALISATION_DESC_GRAPHICS;
        descriptor = new EditPersonDescriptorBuilder().withSpecialisation(VALID_SPECIALISATION).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // year
        userInput = targetIndex.getOneBased() + YEAR_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withYear(VALID_YEAR_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;

        String userInput = targetIndex.getOneBased()
                + PHONE_DESC_BOB + TAG_DESC_HUSBAND //first set
                + EMAIL_DESC_AMY + GENDER_DESC_CABE + NAME_DESC_AMY + TAG_DESC_FRIEND + MODULE_CODE_DESC_AMY
                + GITHUB_USERNAME_DESC_AMY + LOCATION_DESC_AMY + GENDER_DESC_AMY + OFFICE_HOUR_DESC_MONDAY
                + RATING_DESC_ONE + SPECIALISATION_DESC_GRAPHICS + YEAR_DESC_AMY + MODULE_CODE_DESC_BOB
                + PHONE_DESC_BOB + TAG_DESC_HUSBAND //second set
                + EMAIL_DESC_AMY + GENDER_DESC_CABE + NAME_DESC_AMY + TAG_DESC_FRIEND + MODULE_CODE_DESC_AMY
                + GITHUB_USERNAME_DESC_AMY + LOCATION_DESC_AMY + GENDER_DESC_AMY + OFFICE_HOUR_DESC_MONDAY
                + RATING_DESC_ONE + SPECIALISATION_DESC_GRAPHICS + YEAR_DESC_AMY + MODULE_CODE_DESC_BOB
                + PHONE_DESC_AMY + TAG_DESC_HUSBAND //third set
                + EMAIL_DESC_BOB + GENDER_DESC_AMY + NAME_DESC_BOB + TAG_DESC_FRIEND + MODULE_CODE_DESC_BOB
                + GITHUB_USERNAME_DESC_BOB + LOCATION_DESC_BOB + GENDER_DESC_AMY + OFFICE_HOUR_DESC_TUESDAY
                + RATING_DESC_TWO + SPECIALISATION_DESC_BOB + YEAR_DESC_BOB + MODULE_CODE_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).withYear(VALID_YEAR_BOB)
                .withModuleCodeSet(VALID_MODULE_CODE_BOB, VALID_MODULE_CODE_AMY).withGender(VALID_GENDER_AMY)
                .withName(VALID_NAME_BOB).withUsername(VALID_GITHUB_BOB).withLocation(VALID_LOCATION_BOB)
                .withOfficeHour(VALID_OFFICE_HOUR_BOB).withRating(VALID_RATING_TWO)
                .withSpecialisation(VALID_SPECIALISATION_BOB).withYear(VALID_YEAR_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_GITHUB_DESC + INVALID_YEAR_DESC + INVALID_OFFICE_HOUR_DESC
                + INVALID_RATING_DESC + INVALID_EMAIL_DESC + INVALID_GENDER_DESC + INVALID_PHONE_DESC
                + PHONE_DESC_AMY + EMAIL_DESC_BOB + GENDER_DESC_AMY + NAME_DESC_BOB + TAG_DESC_FRIEND
                + MODULE_CODE_DESC_BOB + GITHUB_USERNAME_DESC_BOB + LOCATION_DESC_BOB + GENDER_DESC_AMY
                + OFFICE_HOUR_DESC_TUESDAY + RATING_DESC_TWO + SPECIALISATION_DESC_BOB + YEAR_DESC_BOB
                + MODULE_CODE_DESC_AMY;

        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND).withYear(VALID_YEAR_BOB)
                .withModuleCodeSet(VALID_MODULE_CODE_BOB, VALID_MODULE_CODE_AMY).withGender(VALID_GENDER_AMY)
                .withName(VALID_NAME_BOB).withUsername(VALID_GITHUB_BOB).withLocation(VALID_LOCATION_BOB)
                .withOfficeHour(VALID_OFFICE_HOUR_BOB).withRating(VALID_RATING_TWO)
                .withSpecialisation(VALID_SPECIALISATION_BOB).withYear(VALID_YEAR_BOB)
                .build();

        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_resetGithub_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + GITHUB_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withUsername(null).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetSpecialisation_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + SPECIALISATION_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withSpecialisation(null).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetRating_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + RATING_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withRating(null).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetYear_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + YEAR_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withYear(null).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetOfficeHour_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + OFFICE_HOUR_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withOfficeHour(null).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
