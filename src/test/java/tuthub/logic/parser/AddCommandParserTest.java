package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static tuthub.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static tuthub.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static tuthub.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static tuthub.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static tuthub.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static tuthub.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static tuthub.logic.commands.CommandTestUtil.INVALID_STUDENTID_DESC;
import static tuthub.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static tuthub.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static tuthub.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static tuthub.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static tuthub.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static tuthub.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static tuthub.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static tuthub.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static tuthub.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static tuthub.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static tuthub.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static tuthub.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.YEAR_DESC_BOB;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tuthub.testutil.TypicalTutors.AMY;
import static tuthub.testutil.TypicalTutors.BOB;

import org.junit.jupiter.api.Test;

import tuthub.logic.commands.AddCommand;
import tuthub.model.tutor.Address;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.Year;
import tuthub.model.tag.Tag;
import tuthub.testutil.TutorBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tutor expectedTutor = new TutorBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTutor));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTutor));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTutor));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTutor));

        // multiple modules - last module accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_AMY
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTutor));

        // multiple years - last year accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
            + YEAR_DESC_AMY + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTutor));

        // multiple student ids - last student id accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + MODULE_DESC_BOB
                + YEAR_DESC_AMY + YEAR_DESC_BOB + STUDENTID_DESC_AMY + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedTutor));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedTutor));

        // multiple tags - all accepted
        Tutor expectedTutorMultipleTags = new TutorBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedTutorMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Tutor expectedTutor = new TutorBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + MODULE_DESC_AMY + YEAR_DESC_AMY + STUDENTID_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedTutor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_MODULE_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // missing year prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + VALID_YEAR_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // missing student id prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + MODULE_DESC_BOB + YEAR_DESC_BOB + VALID_STUDENTID_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_MODULE_BOB + VALID_YEAR_BOB + VALID_STUDENTID_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
            + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Email.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + INVALID_MODULE_DESC + YEAR_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Module.MESSAGE_CONSTRAINTS);

        // invalid year
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + MODULE_DESC_BOB + INVALID_YEAR_DESC + STUDENTID_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Year.MESSAGE_CONSTRAINTS);

        // invalid student id
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + INVALID_STUDENTID_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
            + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
            + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
            Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_DESC_BOB + YEAR_DESC_BOB + STUDENTID_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
