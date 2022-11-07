package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.CONDITION_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.CONDITION_DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_CONDITION_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.uninurse.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.uninurse.logic.commands.CommandTestUtil.TAG_DESC_RISK;
import static seedu.uninurse.logic.commands.CommandTestUtil.TAG_DESC_ROOM;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_CONDITION_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_CONDITION_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_TAG_RISK;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_TAG_ROOM;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.testutil.TypicalPatients.AMY;
import static seedu.uninurse.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.AddPatientCommand;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.testutil.PatientBuilder;

public class AddPatientCommandParserTest {
    private final AddPatientCommandParser parser = new AddPatientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPerson = new PatientBuilder(BOB).withTags(VALID_TAG_RISK).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_RISK, new AddPatientCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_RISK, new AddPatientCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_RISK, new AddPatientCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_RISK, new AddPatientCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_RISK, new AddPatientCommand(expectedPerson));

        // multiple tags - all accepted
        Patient expectedPersonMultipleTags = new PatientBuilder(BOB).withTags(VALID_TAG_RISK, VALID_TAG_ROOM).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_RISK + TAG_DESC_ROOM, new AddPatientCommand(expectedPersonMultipleTags));

        // multiple conditions - all accepted
        Patient expectedPersonMultipleConditions = new PatientBuilder(BOB)
                .withConditions(VALID_CONDITION_BOB, VALID_CONDITION_AMY).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + CONDITION_DESC_BOB + CONDITION_DESC_AMY + TAG_DESC_ROOM + TAG_DESC_RISK,
                new AddPatientCommand(expectedPersonMultipleConditions));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Patient expectedPerson = new PatientBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddPatientCommand(expectedPerson));

        // zero conditions
        expectedPerson = new PatientBuilder(AMY).withConditions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + TAG_DESC_RISK, new AddPatientCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + CONDITION_DESC_BOB + TAG_DESC_ROOM + TAG_DESC_RISK, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + CONDITION_DESC_BOB + TAG_DESC_ROOM + TAG_DESC_RISK, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + CONDITION_DESC_BOB + TAG_DESC_ROOM + TAG_DESC_RISK, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + CONDITION_DESC_BOB + TAG_DESC_ROOM + TAG_DESC_RISK, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + CONDITION_DESC_BOB + INVALID_TAG_DESC + TAG_DESC_RISK, Tag.MESSAGE_CONSTRAINTS);

        // invalid condition
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_CONDITION_DESC + TAG_DESC_ROOM + TAG_DESC_RISK, Condition.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + CONDITION_DESC_BOB + TAG_DESC_ROOM + TAG_DESC_RISK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
    }
}
