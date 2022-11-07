package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ZAC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ZAC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ZAC;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UserCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.User;
import seedu.address.testutil.UserBuilder;

public class UserCommandParserTest {

    private UserCommandParser parser = new UserCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        User expectedUser = new UserBuilder(ZAC).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC
                + ADDRESS_DESC_ZAC + GITHUB_DESC_ZAC, new UserCommand(expectedUser));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC
                + ADDRESS_DESC_ZAC + GITHUB_DESC_ZAC, new UserCommand(expectedUser));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_ZAC + PHONE_DESC_AMY + PHONE_DESC_ZAC + EMAIL_DESC_ZAC
                + ADDRESS_DESC_ZAC + GITHUB_DESC_ZAC, new UserCommand(expectedUser));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_AMY + EMAIL_DESC_ZAC
                + ADDRESS_DESC_ZAC + GITHUB_DESC_ZAC, new UserCommand(expectedUser));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + ADDRESS_DESC_AMY
                + ADDRESS_DESC_ZAC + GITHUB_DESC_ZAC, new UserCommand(expectedUser));

        // multiple github usernames - last github username accepted
        assertParseSuccess(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + ADDRESS_DESC_ZAC
                + GITHUB_DESC_AMY + GITHUB_DESC_ZAC, new UserCommand(expectedUser));

    }

    @Test
    public void parse_optionalFieldMissing_success() {

        // missing github
        User expectedUser = new UserBuilder(ZAC).withGithub("").build();
        assertParseSuccess(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + ADDRESS_DESC_ZAC,
                new UserCommand(expectedUser));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + ADDRESS_DESC_ZAC,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_ZAC + VALID_PHONE_ZAC + EMAIL_DESC_ZAC + ADDRESS_DESC_ZAC,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + VALID_EMAIL_ZAC + ADDRESS_DESC_ZAC,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + VALID_ADDRESS_ZAC,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_ZAC + VALID_PHONE_ZAC + VALID_EMAIL_ZAC + VALID_ADDRESS_ZAC,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + ADDRESS_DESC_ZAC
                + GITHUB_DESC_ZAC, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_ZAC + INVALID_PHONE_DESC + EMAIL_DESC_ZAC + ADDRESS_DESC_ZAC
                + GITHUB_DESC_ZAC, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + INVALID_EMAIL_DESC + ADDRESS_DESC_ZAC
                + GITHUB_DESC_ZAC, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + INVALID_ADDRESS_DESC
                + GITHUB_DESC_ZAC, Address.MESSAGE_CONSTRAINTS);

        // invalid github
        assertParseFailure(parser, NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + ADDRESS_DESC_ZAC
                + INVALID_GITHUB_DESC, Github.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC + INVALID_ADDRESS_DESC
                + GITHUB_DESC_ZAC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_ZAC + PHONE_DESC_ZAC + EMAIL_DESC_ZAC
                        + ADDRESS_DESC_ZAC + GITHUB_DESC_ZAC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UserCommand.MESSAGE_USAGE));
    }
}
