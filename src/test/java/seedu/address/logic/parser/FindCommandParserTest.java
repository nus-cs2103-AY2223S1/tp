package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NOK_PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.student.Class;
import seedu.address.model.student.Phone;
import seedu.address.model.student.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.student.predicate.ClassContainsDatePredicate;
import seedu.address.model.student.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.student.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.student.predicate.NokPhoneContainsNumberPredicate;
import seedu.address.model.student.predicate.PhoneContainsNumberPredicate;
import seedu.address.model.student.predicate.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    public static final String ONLY_ONE_PREFIX_MESSAGE = "You can only search with 1 prefix, "
            + "either n/, p/, np/, e/, a/, dt/ or t/";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        assertParseFailure(parser, "     ",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPrefix_failure() {
        // Only name specified without prefix
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // Only phone numbers specified without prefix
        assertParseFailure(parser, "99887766", MESSAGE_INVALID_FORMAT);

        // Empty string parsed
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_multiplePrefixes_failure() {
        // Name and phone prefixes parsed
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY, ONLY_ONE_PREFIX_MESSAGE);

        // Name, phone and email prefixes parsed
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY, ONLY_ONE_PREFIX_MESSAGE);

        // Name, phone, nokPhone and email prefixes parsed
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + NOK_PHONE_DESC_AMY + EMAIL_DESC_AMY,
                ONLY_ONE_PREFIX_MESSAGE);

        assertParseFailure(parser, NAME_DESC_AMY + NAME_DESC_BOB, ONLY_ONE_PREFIX_MESSAGE);
    }

    @Test
    public void parse_validNamePrefix_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Alice    Bob", expectedFindCommand);
    }

    @Test
    public void parse_validPhonePrefix_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneContainsNumberPredicate("81234567"));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " p/81234567", expectedFindCommand);

        // trailing whitespaces
        assertParseSuccess(parser, " p/81234567  ", expectedFindCommand);

        // leading whitespaces
        assertParseSuccess(parser, " p/   81234567  ", expectedFindCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " p/   81234567  ", expectedFindCommand);
    }

    @Test
    public void parse_invalidPhone() {
        // phone number not 8 digits long
        assertParseFailure(parser, " p/8123", Phone.MESSAGE_CONSTRAINTS);

        // phone number does not start with 6/8/9
        assertParseFailure(parser, " p/12345678", Phone.MESSAGE_CONSTRAINTS);

        // empty
        assertParseFailure(parser, " p/", Phone.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validEmailPrefix_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(
                new EmailContainsKeywordsPredicate(Arrays.asList("john_yang@gmail.com")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " e/john_yang@gmail.com", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " e/     john_yang@gmail.com    ", expectedFindCommand);
    }

    @Test
    public void parse_validAddressPrefix_returnsFindCommand() {
        FindCommand expectedSingleWordAddressCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("Tampines", "Labrador")));

        FindCommand expectedMultipleWordAddressCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("Pasir", "Ris", "Bukit", "Timah")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " a/Tampines Labrador", expectedSingleWordAddressCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " a/Tampines             Labrador", expectedSingleWordAddressCommand);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " a/Pasir Ris Bukit Timah", expectedMultipleWordAddressCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " a/Pasir Ris             Bukit Timah", expectedMultipleWordAddressCommand);
    }

    @Test
    public void parse_validNokPhonePrefix_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NokPhoneContainsNumberPredicate("81234564"));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " np/81234564", expectedFindCommand);

        // trailing whitespaces
        assertParseSuccess(parser, " np/81234564  ", expectedFindCommand);

        // leading whitespaces
        assertParseSuccess(parser, " np/   81234564  ", expectedFindCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " np/   81234564  ", expectedFindCommand);
    }
    @Test
    public void parse_invalidNokPhone() {
        // phone number not 8 digits long
        assertParseFailure(parser, " p/8123", Phone.MESSAGE_CONSTRAINTS);

        // phone number does not start with 6/8/9
        assertParseFailure(parser, " p/12345678", Phone.MESSAGE_CONSTRAINTS);

        // empty
        assertParseFailure(parser, " p/", Phone.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validTagPrefix_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("Python")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " t/Python", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " t/   Python  ", expectedFindCommand);
    }

    @Test
    public void parse_validClassDate_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new ClassContainsDatePredicate("2022-10-10"));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " dt/2022-10-10", expectedFindCommand);

        // trailing whitespaces
        assertParseSuccess(parser, " dt/2022-10-10  ", expectedFindCommand);

        // leading whitespaces
        assertParseSuccess(parser, " dt/   2022-10-10  ", expectedFindCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " dt/   2022-10-10  ", expectedFindCommand);
    }

    @Test
    public void parse_invalidDate() {
        // different format
        assertParseFailure(parser, " dt/10 oct", Class.INVALID_FIND_COMMAND_MESSAGE);
        assertParseFailure(parser, " dt/2022-10-20 1500-1600", Class.INVALID_FIND_COMMAND_MESSAGE);
        assertParseFailure(parser, " dt/monday", Class.INVALID_FIND_COMMAND_MESSAGE);

        // incomplete date format
        assertParseFailure(parser, " dt/2022-10", Class.INVALID_FIND_COMMAND_MESSAGE);
        assertParseFailure(parser, " dt/2022", Class.INVALID_FIND_COMMAND_MESSAGE);

        // empty
        assertParseFailure(parser, " dt/", Class.INVALID_FIND_COMMAND_MESSAGE);

        // whitespaces
        assertParseFailure(parser, " dt/   ", Class.INVALID_FIND_COMMAND_MESSAGE);
    }

}
