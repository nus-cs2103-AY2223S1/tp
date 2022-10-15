package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();
    private List<String> emptyList = new ArrayList<>();
    private List<String> listOfAllFields = ResidentFields.FIELDS.stream()
            .map(String::toLowerCase).collect(Collectors.toList());

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
        ListCommand expectedListCommand = new ListCommand(List.of("index", "phone", "email", "tags", "room"));
        assertParseSuccess(parser, "/i name matric gender house weight", expectedListCommand);
    }

    @Test
    public void parse_includeDuplicateFields_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("index", "phone", "email", "tags", "room"));
        assertParseSuccess(parser, "/i name matric gender house name", expectedListCommand);
    }

    @Test
    public void parse_excludeNonExistentField_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("name", "matric", "gender", "house"));
        assertParseSuccess(parser, "/e name matric gender house weight", expectedListCommand);
    }

    @Test
    public void parse_excludeDuplicateFields_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("name", "matric", "gender", "house"));
        assertParseSuccess(parser, "/e name matric gender house weight", expectedListCommand);
    }

    @Test
    public void parse_includeValidArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("index", "gender", "tags", "email"));
        assertParseSuccess(parser, "/i name phone room matric house", expectedListCommand);
    }

    @Test
    public void parse_excludeValidArgs_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("name", "phone", "room", "matric", "house"));
        assertParseSuccess(parser, "/e name phone room matric house", expectedListCommand);
    }

    @Test
    public void parse_argsWithUppercaseLetters_returnsListCommand() {
        ListCommand expectedListCommand = new ListCommand(List.of("room", "matric", "house", "gender"));
        assertParseSuccess(parser, "/i nAmE pHoNe eMaIl tAgS iNdEx", expectedListCommand);
    }
}
