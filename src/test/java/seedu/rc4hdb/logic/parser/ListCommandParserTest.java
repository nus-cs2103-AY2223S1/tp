package seedu.rc4hdb.logic.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static seedu.rc4hdb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;
import seedu.rc4hdb.logic.parser.commandparsers.ListCommandParser;
import seedu.rc4hdb.model.person.Fields;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();
    private List<String> emptyList = new ArrayList<>();
    private List<String> listOfAllFields = Fields.FIELDS.stream().map(String::toLowerCase).collect(Collectors.toList());

    @Test
    public void parse_incorrectSpecifier_throwsParseException() {
        assertParseFailure(parser, "/gg dot com", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_verboseSpecifier_throwsParseException() {
        assertParseFailure(parser, "/include name address", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_withoutSpecifier_throwsParseException() {
        assertParseFailure(parser, "name address phone", ListCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_emptyArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(emptyList);
        assertParseSuccess(parser, "", expectedListCommand);
    }

    @Test
    public void parse_includeNoFields_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(listOfAllFields);
        assertParseSuccess(parser, "/i", expectedListCommand);
    }

    @Test
    public void parse_excludeNoFields_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(emptyList);
        assertParseSuccess(parser, "/e", expectedListCommand);
    }

    @Test
    public void parse_includeNonExistentField_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("index", "address", "email", "tag"));
        assertParseSuccess(parser, "/i name phone weight", expectedListCommand);
    }

    @Test
    public void parse_includeDuplicateFields_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of( "index", "email", "tag"));
        assertParseSuccess(parser, "/i name phone name phone address", expectedListCommand);
    }

    @Test
    public void parse_excludeNonExistentField_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("name", "phone", "address"));
        assertParseSuccess(parser, "/i index email tag height", expectedListCommand);
    }

    @Test
    public void parse_excludeDuplicateFields_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("index", "email", "tag"));
        assertParseSuccess(parser, "/e index email index tag email", expectedListCommand);
    }

    @Test
    public void parse_includeValidArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("index", "email", "tag"));
        assertParseSuccess(parser, "/i name phone address", expectedListCommand);
    }

    @Test
    public void parse_excludeValidArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("name", "phone", "address"));
        assertParseSuccess(parser, "/e name phone address", expectedListCommand);
    }

    @Test
    public void parse_argsWithUppercaseLetters_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("index", "address", "email", "tag"));
        assertParseSuccess(parser, "/i nAmE pHoNe", expectedListCommand);
    }
}
