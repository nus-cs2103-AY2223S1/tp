package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.getcommands.GetWardNumberCommand;
import seedu.address.model.person.WardNumberPredicate;

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
        String userInput = "1 f312 d102";
        GetWardNumberCommand expectedGetWardNumberCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetWardNumberCommand);

        // one invalid ward number
        userInput = "F312 D102 A01";
        expectedGetWardNumberCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetWardNumberCommand);
    }

    @Test
    public void parse_validArgs_returnsGetWardNumberCommand() {
        // no leading and trailing whitespaces
        GetWardNumberCommand expectedGetWardNumberCommand = prepareCommand("F312 D102");
        assertParseSuccess(parser, "F312 D102", expectedGetWardNumberCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n F312 \n \t D102  \t", expectedGetWardNumberCommand);
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
