package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICATION_PROCESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEBSITE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.WEBSITE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WEBSITE_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.ApplicationProcess;
import seedu.address.model.person.Date;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Position;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Website;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple remarks - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_AMY
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple positions - last position accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_AMY + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple application processes - last application process accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_AMY + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB + DATE_DESC_AMY
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple websites - last website accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_AMY + WEBSITE_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + REMARK_DESC_AMY
                        + POSITION_DESC_AMY + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY,
                new AddCommand(expectedPerson));

        // no application process
        Person expectedPerson1 = new PersonBuilder(AMY).withApplicationProcess("apply").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + REMARK_DESC_AMY
                        + POSITION_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson1));

        // no date
        Person expectedPerson2 = new PersonBuilder(AMY)
                .withDate(LocalDate.now().format(Date.DEFAULT_FORMATTER)).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + REMARK_DESC_AMY
                        + POSITION_DESC_AMY + APPLICATION_PROCESS_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson2));

        // no phone
        Person expectedPerson3 = new PersonBuilder(AMY).withPhone(Phone.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + REMARK_DESC_AMY + POSITION_DESC_AMY
                        + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson3));

        // no email
        Person expectedPerson4 = new PersonBuilder(AMY).withEmail(Email.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + REMARK_DESC_AMY + POSITION_DESC_AMY
                        + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson4));

        // no website
        Person expectedPerson5 = new PersonBuilder(AMY).withWebsite(Website.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + REMARK_DESC_AMY + POSITION_DESC_AMY + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson5));

        // no remark
        Person expectedPerson6 = new PersonBuilder(AMY).withRemark(Remark.DEFAULT_VALUE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + POSITION_DESC_AMY
                        + APPLICATION_PROCESS_DESC_AMY + DATE_DESC_AMY + WEBSITE_DESC_AMY + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson6));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + POSITION_DESC_BOB, expectedMessage);

        // missing position prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_POSITION_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_POSITION_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + REMARK_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + INVALID_TAG_DESC + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + INVALID_POSITION_DESC + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, Position.MESSAGE_CONSTRAINTS);

        // invalid application process
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + INVALID_APPLICATION_PROCESS_DESC
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, ApplicationProcess.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + INVALID_DATE_DESC + WEBSITE_DESC_BOB, Date.MESSAGE_CONSTRAINTS);

        // invalid website
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + REMARK_DESC_BOB
                + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + INVALID_WEBSITE_DESC, Website.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_DATE_DESC
                + POSITION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + REMARK_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
