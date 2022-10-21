package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.ShowCommand;
import seedu.rc4hdb.model.resident.fields.ResidentField;

public class ShowCommandParserTest {
    private final ShowCommandParser parser = new ShowCommandParser();
    private final List<String> emptyList = new ArrayList<>();
    private final List<String> allFields = ResidentField.FIELDS.stream()
            .map(String::toLowerCase).collect(Collectors.toList());

    @Test
    public void parse_emptyArguments_throwsParseException() {
        assertParseFailure(parser, "", ShowCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_lowerCaseFields_returnsShowCommand() {
        List<String> fieldsToHide = new ArrayList<>(allFields);
        fieldsToHide.removeAll(List.of("name", "phone"));
        ShowCommand expectedCommand = new ShowCommand(fieldsToHide);
        assertParseSuccess(parser, "name phone", expectedCommand);
    }

    @Test
    public void parse_mixedCaseFields_returnsShowCommand() {
        List<String> fieldsToHide = new ArrayList<>(allFields);
        fieldsToHide.removeAll(List.of("name", "phone"));
        ShowCommand expectedCommand = new ShowCommand(fieldsToHide);
        assertParseSuccess(parser, "nAmE pHoNe", expectedCommand);
    }

    @Test
    public void parse_invalidFields_throwsParseException() {
        assertParseFailure(parser, "height weight", ShowCommandParser.ERROR_MESSAGE);
    }

    @Test
    public void parse_validFieldsWithSpecifiers_throwsParseException() {
        assertParseFailure(parser, "/all name phone", ShowCommandParser.ERROR_MESSAGE);
    }

    @Test
    public void parse_duplicateFields_returnsShowCommand() {
        List<String> fieldsToHide = new ArrayList<>(allFields);
        fieldsToHide.removeAll(List.of("email"));
        ShowCommand expectedCommand = new ShowCommand(fieldsToHide);
        assertParseSuccess(parser, "email email", expectedCommand);
    }

    @Test
    public void parse_allFields_returnsShowCommandWithEmptyList() {
        List<String> fieldsToHide = new ArrayList<>(emptyList);
        ShowCommand expectedCommand = new ShowCommand(fieldsToHide);
        assertParseSuccess(parser, "index name phone email room gender house matric tags", expectedCommand);
    }
}
