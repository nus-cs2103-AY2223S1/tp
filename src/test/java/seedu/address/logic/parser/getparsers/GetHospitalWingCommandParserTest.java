package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.getcommands.GetHospitalWingCommand;
import seedu.address.model.person.HospitalWingContainsKeywordsPredicate;

public class GetHospitalWingCommandParserTest {
    private GetHospitalWingCommandParser parser = new GetHospitalWingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetHospitalWingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_garbledArg_returnsGetHospitalWingCommand() {
        // extra prefixes
        String userInput = "/inp /outp";
        GetHospitalWingCommand expectedGetHospitalWingCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetHospitalWingCommand);

        // garbled input
        userInput = "abcdefg";
        expectedGetHospitalWingCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetHospitalWingCommand);
    }

    @Test
    public void parse_invalidHospitalWing_returnsGetHospitalWingCommand() {
        // all invalid hospital wing
        String userInput = "souths nroths";
        GetHospitalWingCommand expectedGetHospitalWingCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetHospitalWingCommand);

        // one invalid hospital wing
        userInput = "south north ea";
        expectedGetHospitalWingCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetHospitalWingCommand);
    }

    @Test
    public void parse_validArgs_returnsGetHospitalWingCommand() {
        // no leading and trailing whitespaces
        GetHospitalWingCommand expectedGetHospitalWingCommand = prepareCommand("south north");
        assertParseSuccess(parser, "south north", expectedGetHospitalWingCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n south \n \t north  \t", expectedGetHospitalWingCommand);
    }

    private GetHospitalWingCommand prepareCommand(String userInput) {
        String[] st = userInput.split("\\s+");
        List<String> hospitalWings = new ArrayList<>();
        for (String s : st) {
            hospitalWings.add(s);
        }
        return new GetHospitalWingCommand(new HospitalWingContainsKeywordsPredicate(hospitalWings));
    }
}
