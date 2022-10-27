package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.filename.FileName;
import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {
    private ImportCommandParser parser = new ImportCommandParser();
    private FileName validFileName = new FileName("test.csv");

    @Test
    void parse_validArgs_returnsImportCommand() {
        assertParseSuccess(parser, "test.csv", new ImportCommand(validFileName));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", FileName.MESSAGE_CONSTRAINTS);
    }
}
