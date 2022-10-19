package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PATH_JERRY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATH_JERRY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_JERRY_JPG;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {
    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_validFilePath_success() {
        Path expectedPath = PATH_TO_JERRY_JPG;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_PATH_JERRY, new ImportCommand(expectedPath));

        // valid path
        assertParseSuccess(parser, VALID_PATH_JERRY, new ImportCommand(expectedPath));
    }

    @Test
    public void parse_invalidFilePath_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE);

        // invalid path
        assertParseFailure(parser, INVALID_PATH_JERRY, expectedMessage);
    }
}
