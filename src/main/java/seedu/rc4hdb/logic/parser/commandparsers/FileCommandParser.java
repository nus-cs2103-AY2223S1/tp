package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.rc4hdb.logic.commands.storagecommands.filecommands.FileCommand.MESSAGE_INVALID_FILE_NAME;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.rc4hdb.logic.commands.misccommands.HelpCommand;
import seedu.rc4hdb.logic.commands.storagecommands.filecommands.FileCommand;
import seedu.rc4hdb.logic.commands.storagecommands.filecommands.jsonfilecommands.FileCreateCommand;
import seedu.rc4hdb.logic.commands.storagecommands.filecommands.jsonfilecommands.FileDeleteCommand;
import seedu.rc4hdb.logic.commands.storagecommands.filecommands.jsonfilecommands.FileSwitchCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FileCommand object.
 */
public class FileCommandParser implements CommandParser<FileCommand> {

    public static final Path DATA_DIR_PATH = Paths.get("data");

    private static final Pattern FILE_COMMAND_FORMAT = Pattern.compile("(?<secondCommandWord>\\S+) (?<arguments>.+)");

    @Override
    public FileCommand parse(String args) throws ParseException {
        final Matcher matcher = FILE_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String secondCommandWord = matcher.group("secondCommandWord");
        final String fileName = matcher.group("arguments").trim();
        if (!FileCommand.isValidPath(fileName)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_FILE_NAME));
        }

        switch (secondCommandWord) {

        case FileSwitchCommand.COMMAND_WORD:
            return new FileSwitchCommand(DATA_DIR_PATH, fileName);

        case FileCreateCommand.COMMAND_WORD:
            return new FileCreateCommand(DATA_DIR_PATH, fileName);

        case FileDeleteCommand.COMMAND_WORD:
            return new FileDeleteCommand(DATA_DIR_PATH, fileName);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
