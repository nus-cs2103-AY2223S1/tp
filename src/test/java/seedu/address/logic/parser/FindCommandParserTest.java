package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAddressCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindNameCommand;
import seedu.address.logic.commands.FindPhoneCommand;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindNameCommand() {
        // no leading and trailing whitespaces
        String userInputFirst = " " + PREFIX_NAME + "Alice Bob";
        FindCommand expectedFindNameCommand =
                new FindNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, userInputFirst, expectedFindNameCommand);

        String userInputSecond = " " + PREFIX_NAME + " \n Alice \n \t Bob  \t";
        // multiple whitespaces between keywords
        assertParseSuccess(parser, userInputSecond, expectedFindNameCommand);
    }

    @Test
    public void parse_validArgs_returnsFindAddressCommand() {
        // no leading and trailing whitespaces
        String userInputFirst = " " + PREFIX_ADDRESS + "Kaki Bukit";
        FindCommand expectedFindAddressCommand =
                new FindAddressCommand(new AddressContainsKeywordsPredicate(Arrays.asList("Kaki", "Bukit")));
        assertParseSuccess(parser, userInputFirst, expectedFindAddressCommand);

        // multiple whitespaces between keywords
        String userInputSecond = " " + PREFIX_ADDRESS + "\n Kaki \n \t Bukit  \t";
        assertParseSuccess(parser, userInputSecond, expectedFindAddressCommand);
    }

    @Test
    public void parse_validArgs_returnsFindPhoneCommand() {
        // no leading and trailing whitespaces
        String userInputFirst = " " + PREFIX_PHONE + "95352563 87652533";
        FindCommand expectedFindPhoneCommand =
                new FindPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("95352563", "87652533")));
        assertParseSuccess(parser, userInputFirst, expectedFindPhoneCommand);

        // multiple whitespaces between keywords
        String userInputSecond = " " + PREFIX_PHONE + "\n 95352563 \n \t 87652533  \t";
        assertParseSuccess(parser, userInputSecond, expectedFindPhoneCommand);
    }
}
