package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.boba.logic.commands.FindCommand;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.MultiSearchPredicate;
import seedu.boba.model.customer.Phone;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new MultiSearchPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validPhone_success() {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setPhone(new Phone(VALID_PHONE_AMY));
        FindCommand expectedFindCommand = new FindCommand(findPersonDescriptor);

        String userInput = " " + PHONE_DESC_AMY;
        assertParseSuccess(parser, userInput, expectedFindCommand);
    }

    @Test
    public void parse_validEmail_success() {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setEmail(new Email(VALID_EMAIL_AMY));
        FindCommand expectedFindCommand = new FindCommand(findPersonDescriptor);

        String userInput = " " + EMAIL_DESC_AMY;
        assertParseSuccess(parser, userInput, expectedFindCommand);
    }
}
