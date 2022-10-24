package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.ShowCommand;
import seedu.rc4hdb.model.resident.fields.ResidentField;

public class ShowCommandParserTest {
    private final ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_emptyArguments_throwsParseException() {
        assertParseFailure(parser, "", ShowCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_lowerCaseFields_returnsShowCommand() {
        List<String> fieldsToShow = new ArrayList<>(List.of("room", "gender"));
        ShowCommand expectedCommand = new ShowCommand(fieldsToShow);
        assertParseSuccess(parser, "room gender", expectedCommand);
    }

    @Test
    public void parse_mixedCaseFields_returnsShowCommand() {
        List<String> fieldsToShow = new ArrayList<>(List.of("name", "phone"));
        ShowCommand expectedCommand = new ShowCommand(fieldsToShow);
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
        List<String> fieldsToShow = List.of("email");
        ShowCommand expectedCommand = new ShowCommand(fieldsToShow);
        assertParseSuccess(parser, "email email", expectedCommand);
    }

    @Test
    public void parse_allFields_returnsShowCommandWithGlobalFieldList() {
        List<String> fieldsToShow = new ArrayList<>(ResidentField.LOWERCASE_FIELDS);
        ShowCommand expectedCommand = new ShowCommand(fieldsToShow);
        assertParseSuccess(parser, "index name phone email room gender house matric tags", expectedCommand);
    }
}
