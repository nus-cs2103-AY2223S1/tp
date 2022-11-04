package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.getcommands.GetFloorNumberCommand;
import seedu.address.model.person.FloorNumberPredicate;

public class GetFloorNumberCommandParserTest {

    private GetFloorNumberCommandParser parser = new GetFloorNumberCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFloorNumberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFloorNumber_throwsParseException() {
        // all invalid floor number
        assertParseFailure(parser, "0 -1 asdfghjkl",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFloorNumberCommand.MESSAGE_USAGE));

        // one invalid floor number
        assertParseFailure(parser, "1 5 -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFloorNumberCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGetFloorNumberCommand() {
        // no leading and trailing whitespaces
        GetFloorNumberCommand expectedGetFloorNumberCommand = prepareCommand("1 5");
        assertParseSuccess(parser, "1 5", expectedGetFloorNumberCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n 1 \n \t 5   ", expectedGetFloorNumberCommand);
    }

    private GetFloorNumberCommand prepareCommand(String userInput) {
        String[] st = userInput.split("\\s+");
        List<Integer> floorNumbers = new ArrayList<>();
        for (String s : st) {
            int floorNumber = Integer.parseInt(s);
            floorNumbers.add(floorNumber);
        }
        return new GetFloorNumberCommand(new FloorNumberPredicate(floorNumbers));
    }
}
