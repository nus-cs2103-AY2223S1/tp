package seedu.codeconnect.logic.parser;

import static seedu.codeconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codeconnect.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_MODS_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_COMBINED;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_CS1101;
import static seedu.codeconnect.logic.commands.CommandTestUtil.MODULE_DESC_CS2030S;
import static seedu.codeconnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.codeconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.codeconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.codeconnect.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.codeconnect.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_MODULE_CS1101;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_MODULE_CS2030S;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.codeconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.codeconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codeconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.codeconnect.testutil.TypicalPersons.BOB;
import static seedu.codeconnect.testutil.TypicalPersons.EMPTY_OPTIONAL_AMY;

import org.junit.jupiter.api.Test;

import seedu.codeconnect.logic.commands.AddContactCommand;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.person.Address;
import seedu.codeconnect.model.person.Email;
import seedu.codeconnect.model.person.Github;
import seedu.codeconnect.model.person.Name;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.model.person.Phone;
import seedu.codeconnect.model.person.Telegram;
import seedu.codeconnect.model.tag.Tag;
import seedu.codeconnect.testutil.PersonBuilder;

public class AddContactCommandParserTest {
    private AddContactCommandParser parser = new AddContactCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND)
                .withModules(VALID_MODULE_CS2030S).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withModules(VALID_MODULE_CS2030S).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPersonMultipleTags));

        // multiple modules - all accepted
        Person expectedPersonMultipleModules = new PersonBuilder(BOB).withTags(VALID_TAG_HUSBAND)
                .withModules(VALID_MODULE_CS2030S, VALID_MODULE_CS1101).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + MODULE_DESC_CS1101 + MODULE_DESC_CS2030S
                + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPersonMultipleModules));

        // multiple modules written in one string - all accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + MODULE_DESC_COMBINED
                        + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPersonMultipleModules));

        // multiple githubs - last github accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MODULE_DESC_CS2030S
                        + GITHUB_DESC_AMY + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPerson));

        // multiple telegrams - last telegram accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MODULE_DESC_CS2030S
                        + GITHUB_DESC_BOB + TELEGRAM_DESC_AMY + TELEGRAM_DESC_BOB,
                new AddContactCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero of the optional fields
        Person expectedPerson = new PersonBuilder(EMPTY_OPTIONAL_AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY,
                        new AddContactCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MODULE_DESC_CS2030S, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                        + VALID_MODULE_CS2030S, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid mods
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + INVALID_MODS_DESC + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                Module.MESSAGE_CONSTRAINTS);

        // invalid Github
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + MODULE_DESC_CS2030S
                        + INVALID_GITHUB_DESC + TELEGRAM_DESC_BOB,
                Github.MESSAGE_CONSTRAINTS);

        // invalid Telegram
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + MODULE_DESC_CS2030S
                        + GITHUB_DESC_BOB + INVALID_TELEGRAM_DESC,
                Telegram.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + MODULE_DESC_CS2030S + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MODULE_DESC_CS2030S
                        + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE));
    }
}
