package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.INVALID_FILE_NAME_BACKSLASH;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.INVALID_FILE_NAME_FORWARD_SLASH;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.INVALID_FILE_NAME_FULL_STOP;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.INVALID_FILE_NAME_WHITESPACE;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.VALID_FILE_NAME_STRING;
import static seedu.rc4hdb.logic.commands.filecommands.FileCommand.MESSAGE_INVALID_FILE_NAME;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseFailure;
import static seedu.rc4hdb.logic.parser.commandparsers.CommandParserTestUtil.assertParseSuccess;
import static seedu.rc4hdb.logic.parser.commandparsers.FileCommandParser.DATA_DIR_PATH;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.filecommands.FileCreateCommand;
import seedu.rc4hdb.logic.commands.filecommands.FileDeleteCommand;
import seedu.rc4hdb.logic.commands.filecommands.FileSwitchCommand;
import seedu.rc4hdb.logic.commands.filecommands.ImportCommand;
import seedu.rc4hdb.logic.commands.misccommands.HelpCommand;

/**
 * Unit tests for {@link FileCommandParser}
 */
public class FileCommandParserTest {

    private static final String INVALID_FILE_SECOND_COMMAND = "remove";

    private static final String PARTIAL_INPUT_WITHOUT_FILENAME = FileCreateCommand.COMMAND_WORD + " ";

    private final FileCommandParser parser = new FileCommandParser();

    @Test
    public void parse_validFileCreateCommand() {
        String fileCreateCommand = FileCreateCommand.COMMAND_WORD + " " + VALID_FILE_NAME_STRING;
        assertParseSuccess(parser, fileCreateCommand, new FileCreateCommand(DATA_DIR_PATH, VALID_FILE_NAME_STRING));
    }

    @Test
    public void parse_validFileDeleteCommand() {
        assertParseSuccess(parser, FileDeleteCommand.COMMAND_WORD + " " + VALID_FILE_NAME_STRING,
                new FileDeleteCommand(DATA_DIR_PATH, VALID_FILE_NAME_STRING));
    }

    @Test
    public void parse_validFileSwitchCommand() {
        assertParseSuccess(parser, FileSwitchCommand.COMMAND_WORD + " " + VALID_FILE_NAME_STRING,
                new FileSwitchCommand(DATA_DIR_PATH, VALID_FILE_NAME_STRING));
    }

    @Test
    public void parse_validImportCommand() {
        assertParseSuccess(parser, ImportCommand.COMMAND_WORD + " " + VALID_FILE_NAME_STRING,
                new ImportCommand(DATA_DIR_PATH, VALID_FILE_NAME_STRING));
    }

    @Test
    public void parse_invalidFileName() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_FILE_NAME);
        assertParseFailure(parser, PARTIAL_INPUT_WITHOUT_FILENAME + INVALID_FILE_NAME_FULL_STOP, expectedMessage);
        assertParseFailure(parser, PARTIAL_INPUT_WITHOUT_FILENAME + INVALID_FILE_NAME_WHITESPACE, expectedMessage);
        assertParseFailure(parser, PARTIAL_INPUT_WITHOUT_FILENAME + INVALID_FILE_NAME_FORWARD_SLASH, expectedMessage);
        assertParseFailure(parser, PARTIAL_INPUT_WITHOUT_FILENAME + INVALID_FILE_NAME_BACKSLASH, expectedMessage);
    }

    @Test
    public void parse_invalidSecondCommand() {
        assertParseFailure(parser, INVALID_FILE_SECOND_COMMAND + " " + VALID_FILE_NAME_STRING, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void parse_noFileName() {
        assertParseFailure(parser, FileCreateCommand.COMMAND_WORD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noWhiteSpace() {
        assertParseFailure(parser, FileCreateCommand.COMMAND_WORD + VALID_FILE_NAME_STRING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}
