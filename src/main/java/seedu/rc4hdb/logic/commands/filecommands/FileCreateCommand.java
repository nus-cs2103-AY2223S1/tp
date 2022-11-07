package seedu.rc4hdb.logic.commands.filecommands;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.StorageCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.storage.Storage;

/**
 * Creates a new data file corresponding to the arguments provided.
 */
public class FileCreateCommand extends FileCommand implements StorageCommand {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_SUCCESS = "%s successfully created.";

    public FileCreateCommand(Path dataDir, String subDirName) {
        super(dataDir.resolve(subDirName));
    }

    @Override
    public CommandResult execute(Storage storage) throws CommandException {
        if (folderPath.equals(storage.getDataStorageFolderPath())) {
            throw new CommandException(String.format(MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, folderName));
        }
        try {
            storage.createDataFolder(folderPath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, folderName));
        } catch (FileAlreadyExistsException e) {
            throw new CommandException(e.getMessage(), e);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "creating"), e);
        }
    }

}
