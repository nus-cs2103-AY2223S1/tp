package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.filename.FileName;
import seedu.address.logic.commands.ExportCommand;

class ExportCommandParserTest {
    private ExportCommandParser parser = new ExportCommandParser();
    private FileName validFileName = new FileName("test");

    @Test
    void parse_validArgs_returnsExportCommand() {
        assertParseSuccess(parser, "test", new ExportCommand(validFileName));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", FileName.MESSAGE_CONSTRAINTS);
    }
}
