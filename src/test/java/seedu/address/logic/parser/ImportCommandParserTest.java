package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PATH_JERRY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATH_JERRY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATH_JERRY_WITH_SPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_JERRY_JPG;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_JERRY_WITH_SPACE_JPG;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {
    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_validFilePath_success() {
        Path expectedPath1 = PATH_TO_JERRY_JPG;
        Path expectedPath2 = PATH_TO_JERRY_WITH_SPACE_JPG;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_PATH_JERRY, new ImportCommand(expectedPath1));

        // valid path without space
        assertParseSuccess(parser, VALID_PATH_JERRY, new ImportCommand(expectedPath1));

        // valid path with space
        assertParseSuccess(parser, VALID_PATH_JERRY_WITH_SPACE, new ImportCommand(expectedPath2));

    }

    @Test
    public void parse_invalidFilePath_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE);

        // empty string
        assertParseFailure(parser, PREAMBLE_WHITESPACE, expectedMessage);

        // invalid path
        assertParseFailure(parser, INVALID_PATH_JERRY, expectedMessage);
    }
}
