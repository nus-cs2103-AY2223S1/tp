package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.watson.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.watson.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.watson.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.watson.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.watson.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.watson.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.watson.logic.commands.CommandTestUtil.REMARK_ICE_CREAM;
import static seedu.watson.logic.commands.CommandTestUtil.STUDENTCLASS_DUMMY;
import static seedu.watson.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.watson.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_REMARK_ICE_CREAM;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_SUBJECTHANDLER;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.watson.testutil.TypicalPersons.AMY;
import static seedu.watson.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.AddCommand;
import seedu.watson.model.person.Address;
import seedu.watson.model.person.Email;
import seedu.watson.model.person.Name;
import seedu.watson.model.person.Person;
import seedu.watson.model.person.Phone;
import seedu.watson.model.tag.Tag;
import seedu.watson.testutil.PersonBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // New fields are added to the back of the userInput parameter to avoid confusion
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY + REMARK_ICE_CREAM,
                           new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY + REMARK_ICE_CREAM,
                           new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY + REMARK_ICE_CREAM,
                           new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY + REMARK_ICE_CREAM,
                           new AddCommand(expectedPerson));

        // multiple addresses - last watson accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                                   + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY + REMARK_ICE_CREAM,
                           new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                                                                  .withRemarks(VALID_REMARK_ICE_CREAM).build();

        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                   + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY + REMARK_ICE_CREAM,
                           new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().withRemarks()
                .withSubjectHandler(VALID_SUBJECTHANDLER).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                                   + STUDENTCLASS_DUMMY,
                           new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                           expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                           expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                           expectedMessage);

        // missing watson prefix
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
                                   + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                   + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY,
                           Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                                   + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY,
                           Email.MESSAGE_CONSTRAINTS);

        // invalid watson
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                                   + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY,
                           Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                   + INVALID_TAG_DESC + VALID_TAG_FRIEND + STUDENTCLASS_DUMMY, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                                   + STUDENTCLASS_DUMMY,
                           Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                                   + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + STUDENTCLASS_DUMMY,
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
