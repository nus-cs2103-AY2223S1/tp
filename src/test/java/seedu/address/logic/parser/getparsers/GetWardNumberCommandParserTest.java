package seedu.address.logic.parser.getparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.getcommands.GetWardNumberCommand;
import seedu.address.model.person.WardNumberPredicate;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class GetWardNumberCommandParserTest {

    private GetWardNumberCommandParser parser = new GetWardNumberCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetWardNumberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_garbledArg_returnsGetWardNumberCommand() {
        // extra prefixes
        String userInput = "/inp /fn /outp";
        GetWardNumberCommand expectedGetWardNumberCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetWardNumberCommand);

        // garbled input
        userInput = "asdfghjkl";
        expectedGetWardNumberCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetWardNumberCommand);
    }

    @Test
    public void parse_invalidWardNumber_returnsGetWardNumberCommand() {
        // all invalid ward number
        String userInput = "1 d456 f78";
        GetWardNumberCommand expectedGetWardNumberCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetWardNumberCommand);

        // one invalid ward number
        userInput = "D329 B567 A01";
        expectedGetWardNumberCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetWardNumberCommand);
    }

    @Test
    public void parse_validArgs_returnsGetWardNumberCommand() {
        // no leading and trailing whitespaces
        GetWardNumberCommand expectedGetWardNumberCommand = prepareCommand("D312 F419");
        assertParseSuccess(parser, "D312 F419", expectedGetWardNumberCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n D312 \n \t F419  \t", expectedGetWardNumberCommand);
    }

    private GetWardNumberCommand prepareCommand(String userInput) {
        String[] st = userInput.split("\\s+");
        List<String> wardNumbers = new ArrayList<>();
        for (String s : st) {
            wardNumbers.add(s);
        }
        return new GetWardNumberCommand(new WardNumberPredicate(wardNumbers));
    }
}
