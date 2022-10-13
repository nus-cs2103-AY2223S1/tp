package seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.misccommands.MiscCommand;

/**
 * Deletes the file corresponding to the argument.
 */
public class FileDeleteCommand extends FileCommand implements MiscCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_FILE_NON_EXISTENT = " does not exist. Please provide a valid file";

    public static final String MESSAGE_SUCCESS = " has been deleted.";

    public FileDeleteCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (!FileUtil.isFileExists(filePath)) {
            throw new CommandException(filePath.getFileName() + MESSAGE_FILE_NON_EXISTENT);
        }

        try {
            FileUtil.deleteFile(filePath);
            return new CommandResult(filePath.getFileName() + MESSAGE_SUCCESS);
        } catch (NoSuchFileException e) {
            throw new CommandException(filePath.getFileName() + MESSAGE_FILE_NON_EXISTENT);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "deleting"));
        }
    }

}
