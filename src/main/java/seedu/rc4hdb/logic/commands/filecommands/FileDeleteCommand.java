package seedu.rc4hdb.logic.commands.filecommands;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.StorageCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.storage.Storage;

/**
 * Deletes the file corresponding to the argument.
 */
public class FileDeleteCommand extends FileCommand implements StorageCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_FILE_NON_EXISTENT = "%s does not exist. Please provide a valid file";

    public static final String MESSAGE_SUCCESS = "%s has been deleted.";

    public FileDeleteCommand(Path dataDir, String subDirName) {
        super(dataDir.resolve(subDirName));
    }

    @Override
    public CommandResult execute(Storage storage) throws CommandException {
        if (folderPath.equals(storage.getDataStorageFolderPath())) {
            throw new CommandException(String.format(MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, folderName));
        }
        try {
            storage.deleteDataFolder(folderPath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, folderName));
        } catch (NoSuchFileException e) {
            throw new CommandException(e.getMessage(), e);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "deleting"), e);
        }
    }

}
