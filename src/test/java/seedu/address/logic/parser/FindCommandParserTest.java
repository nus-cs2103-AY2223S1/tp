package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.PersonMatchesPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsForName_returnsFindCommand() {
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setNamesList(Arrays.asList("Alice", "Bob"));
        FindCommand expectedFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsForEmail_returnsFindCommand() {
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setEmailsList(Arrays.asList("alice@example.com", "alex@example.com"));
        FindCommand expectedFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser, " " + PREFIX_EMAIL + "alice@example.com alex@example.com ",
                expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_EMAIL + " \n alice@example.com \n \t"
                + " alex@example.com  \t", expectedFindCommand);
    }

}
