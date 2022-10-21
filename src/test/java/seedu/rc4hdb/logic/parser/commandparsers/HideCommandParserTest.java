package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.HideCommand;
import seedu.rc4hdb.model.resident.fields.ResidentField;

public class HideCommandParserTest {
    private final HideCommandParser parser = new HideCommandParser();
    private final List<String> emptyList = new ArrayList<>();
    private final List<String> allFields = ResidentField.FIELDS.stream()
            .map(String::toLowerCase).collect(Collectors.toList());

    @Test
    public void parse_emptyArguments_throwsParseException() {
        assertParseFailure(parser, "", HideCommandParser.INTENDED_USAGE);
    }

    @Test
    public void parse_lowerCaseFields_returnsHideCommand() {
        HideCommand expectedCommand = new HideCommand(List.of("name", "phone"));
        assertParseSuccess(parser, "name phone", expectedCommand);
    }

    @Test
    public void parse_mixedCaseFields_returnsShowCommand() {
        HideCommand expectedCommand = new HideCommand(List.of("name", "phone"));
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
    public void parse_duplicateFields_returnsCommand() {
        HideCommand expectedCommand = new HideCommand(List.of("email"));
        assertParseSuccess(parser, "email email", expectedCommand);
    }

    @Test
    public void parse_allFields_returnsHideCommandWithEmptyList() {
        HideCommand expectedCommand = new HideCommand(allFields);
        assertParseSuccess(parser, "index name phone email room gender house matric tags", expectedCommand);
    }
}
