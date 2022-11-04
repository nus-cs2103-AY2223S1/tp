package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE_PATH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getFilePathInSandboxFolder;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;

public class ExportCommandParserTest {

    private ExportCommandParser parser = new ExportCommandParser();

    private Path validPath = getFilePathInSandboxFolder("foobar.csv");

    @Test
    public void parse_validArgs_returnsExportCommand() {
        assertParseSuccess(parser, "src/test/data/sandbox/foobar.csv", new ExportCommand(validPath));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_FILE_PATH, ExportCommand.MESSAGE_USAGE));
    }
}
