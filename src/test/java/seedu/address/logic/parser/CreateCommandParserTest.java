package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_FILEPATH;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_NETWORTH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NETWORTH_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NETWORTH_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NETWORTH_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.NetWorth;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class CreateCommandParserTest {
    private final CreateCommandParser parser = new CreateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withMeetingTimes(VALID_MEETING_TIME_BOB)
                .withFilePath(EMPTY_FILEPATH)
                .withTags(VALID_TAG_AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_BOB, new CreateCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_BOB, new CreateCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_BOB, new CreateCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_BOB, new CreateCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_BOB, new CreateCommand(expectedPerson));

        // multiple networths - last networth accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NETWORTH_DESC_AMY + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_BOB, new CreateCommand(expectedPerson));

        // multiple meeting times - all accepted
        Person expectedPersonMultipleMeetingTimes = new PersonBuilder(BOB)
                .withMeetingTimes(VALID_MEETING_TIME_BOB, VALID_MEETING_TIME_AMY)
                .withTags(VALID_TAG_AMY)
                .withFilePath(EMPTY_FILEPATH).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + NETWORTH_DESC_BOB + MEETING_TIME_DESC_AMY
                        + MEETING_TIME_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_BOB,
                new CreateCommand(expectedPersonMultipleMeetingTimes));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {

        // missing address prefix
        Person noAddressPerson = new PersonBuilder(AMY).withAddress(EMPTY_ADDRESS).buildNoFilePath();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + DESCRIPTION_DESC_AMY + NETWORTH_DESC_AMY + MEETING_TIME_DESC_AMY + TAG_DESC_AMY,
                new CreateCommand(noAddressPerson));

        // missing email prefix
        Person noEmailPerson = new PersonBuilder(AMY).withEmail(EMPTY_EMAIL).buildNoFilePath();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + DESCRIPTION_DESC_AMY + NETWORTH_DESC_AMY + MEETING_TIME_DESC_AMY + TAG_DESC_AMY,
                new CreateCommand(noEmailPerson));

        // missing description prefix
        Person noDescriptionPerson = new PersonBuilder(AMY).withDescription(EMPTY_DESCRIPTION).buildNoFilePath();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + NETWORTH_DESC_AMY + MEETING_TIME_DESC_AMY + TAG_DESC_AMY,
                new CreateCommand(noDescriptionPerson));

        // missing networth prefix
        Person noNetworthPerson = new PersonBuilder(AMY).withNetWorth(EMPTY_NETWORTH).buildNoFilePath();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + DESCRIPTION_DESC_AMY + MEETING_TIME_DESC_AMY + TAG_DESC_AMY,
                new CreateCommand(noNetworthPerson));

        // missing meeting time prefix
        Person noMeetingTimePerson = new PersonBuilder(AMY).withMeetingTimes().buildNoFilePath();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + DESCRIPTION_DESC_AMY + NETWORTH_DESC_AMY + TAG_DESC_AMY,
                new CreateCommand(noMeetingTimePerson));

        // zero tags and no filepath
        Person expectedPerson = new PersonBuilder(AMY).withTags().buildNoFilePath();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + NETWORTH_DESC_AMY + MEETING_TIME_DESC_AMY + DESCRIPTION_DESC_AMY, new CreateCommand(expectedPerson));

        // missing every optional field
        Person noNothingPerson = new PersonBuilder(AMY).withEmail(EMPTY_EMAIL).withDescription(EMPTY_DESCRIPTION)
                .withMeetingTimes().withTags().withAddress(EMPTY_ADDRESS).withNetWorth(EMPTY_NETWORTH)
                .buildNoFilePath();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY, new CreateCommand(noNothingPerson));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DESCRIPTION_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + TAG_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DESCRIPTION_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + TAG_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + DESCRIPTION_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + TAG_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid networth
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DESCRIPTION_DESC_BOB + INVALID_NETWORTH_DESC + MEETING_TIME_DESC_BOB + TAG_DESC_BOB
                + TAG_DESC_AMY, NetWorth.MESSAGE_CONSTRAINTS);

        // invalid meeting time
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DESCRIPTION_DESC_BOB + NETWORTH_DESC_BOB + INVALID_MEETING_TIME + TAG_DESC_BOB
                + VALID_TAG_AMY, MeetingTime.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DESCRIPTION_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + DESCRIPTION_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB , Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DESCRIPTION_DESC_BOB + NETWORTH_DESC_BOB + MEETING_TIME_DESC_BOB
                + TAG_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
    }
}
