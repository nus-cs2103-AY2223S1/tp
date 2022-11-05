package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.getcommands.GetNextOfKinCommand;
import seedu.address.model.person.NextOfKinPredicate;

public class GetNextOfKinCommandParserTest {

    private GetNextOfKinCommandParser parser = new GetNextOfKinCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetNextOfKinCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_garbledArg_returnsGetNextOfKinCommand() {
        // extra prefixes
        String userInput = "/inp /outp";
        GetNextOfKinCommand expectedGetNextOfKinCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetNextOfKinCommand);
    }

    @Test
    public void parse_invalidNextOfKin_returnsGetNextOfKinCommand() {
        // numbers
        String userInput = "12345";
        GetNextOfKinCommand expectedGetNextOfKinCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetNextOfKinCommand);

        // mix of strings and symbols
        userInput = "Kartik!!";
        expectedGetNextOfKinCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetNextOfKinCommand);
    }

    @Test
    public void parse_validArgs_returnsGetNextOfKinCommand() {
        // no leading and trailing whitespaces
        GetNextOfKinCommand expectedGetNextOfKinCommand = prepareCommand("harvey norman");
        assertParseSuccess(parser, "harvey norman", expectedGetNextOfKinCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n harvey \n \t norman  \t", expectedGetNextOfKinCommand);
    }

    private GetNextOfKinCommand prepareCommand(String userInput) {
        String[] st = userInput.split("\\s+");
        List<String> nextOfKins = new ArrayList<>();
        for (String s : st) {
            nextOfKins.add(s);
        }
        return new GetNextOfKinCommand(new NextOfKinPredicate(nextOfKins));
    }
}
