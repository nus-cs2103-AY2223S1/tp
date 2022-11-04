package seedu.address.logic.parser.getparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.getcommands.GetNameCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class GetNameCommandParserTest {

    private GetNameCommandParser parser = new GetNameCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetNameCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_garbledArg_returnsGetNameCommand() {
        // extra prefixes
        String userInput = "/inp /fn /outp";
        GetNameCommand expectedGetNameCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetNameCommand);

        // garbled input
        userInput = "asdfghjkl";
        expectedGetNameCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetNameCommand);
    }

    @Test
    public void parse_validArgs_returnsGetNameCommand() {
        // no leading and trailing whitespaces
        GetNameCommand expectedGetNameCommand = prepareCommand("Alice Bob");
        assertParseSuccess(parser, "Alice Bob", expectedGetNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n Alice \n \t Bob  \t", expectedGetNameCommand);
    }

    private GetNameCommand prepareCommand(String userInput) {
        String[] st = userInput.split("\\s+");
        List<String> names = new ArrayList<>();
        for (String s : st) {
            names.add(s);
        }
        return new GetNameCommand(new NameContainsKeywordsPredicate(names));
    }
}
