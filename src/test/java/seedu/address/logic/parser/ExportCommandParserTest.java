package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ExportCommand;

class ExportCommandParserTest {
    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    void parse_validArgs_returnsExportCommand() {
        assertParseSuccess(parser, "test", new ExportCommand());
    }
}