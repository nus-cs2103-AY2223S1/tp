package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();
    private List<String> emptyList = new ArrayList<>();

    @Test
    public void parse_incorrectSpecifier_throwsParseException() {
        assertParseFailure(parser, "/gg dot com", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_verboseSpecifier_throwsParseException() {
        assertParseFailure(parser, "/include name phone", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_withoutSpecifier_throwsParseException() {
        assertParseFailure(parser, "name email phone", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_emptyArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand();
        assertParseSuccess(parser, "", expectedListCommand);
    }

    @Test
    public void parse_includeNoFields_throwsParseException() {
        assertParseFailure(parser, "/i", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_excludeNoFields_throwsParseException() {
        assertParseFailure(parser, "/e", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_includeNonExistentField_throwsParseException() {
        assertParseFailure(parser, "/i name matric gender house weight", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_includeDuplicateFields_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("name", "matric", "gender", "house"),
                List.of("index", "phone", "email", "tags", "room"));
        assertParseSuccess(parser, "/i name matric gender house name", expectedListCommand);
    }

    @Test
    public void parse_excludeNonExistentField_throwsParseException() {
        assertParseFailure(parser, "/e name matric gender house weight", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_excludeDuplicateFields_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("index", "phone", "email", "tags", "room"),
                List.of("name", "matric", "gender", "house"));
        assertParseSuccess(parser, "/e name matric gender house name", expectedListCommand);
    }

    @Test
    public void parse_includeValidArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("name", "phone", "room", "matric", "house"),
                List.of("index", "gender", "tags", "email"));
        assertParseSuccess(parser, "/i name phone room matric house", expectedListCommand);
    }

    @Test
    public void parse_excludeValidArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("index", "gender", "tags", "email"),
                List.of("name", "phone", "room", "matric", "house"));
        assertParseSuccess(parser, "/e name phone room matric house", expectedListCommand);
    }

    @Test
    public void parse_argsWithUppercaseLetters_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("name", "phone", "email", "tags", "index"),
                List.of("room", "matric", "house", "gender"));
        assertParseSuccess(parser, "/i nAmE pHoNe eMaIl tAgS iNdEx", expectedListCommand);
    }
}
