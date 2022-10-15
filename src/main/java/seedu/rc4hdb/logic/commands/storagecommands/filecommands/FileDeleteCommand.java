package seedu.rc4hdb.logic.commands.storagecommands.filecommands;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.storagecommands.StorageCommand;
import seedu.rc4hdb.storage.Storage;

/**
 * Deletes the file corresponding to the argument.
 */
public class FileDeleteCommand extends FileCommand implements StorageCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_FILE_NON_EXISTENT = " does not exist. Please provide a valid file";

    public static final String MESSAGE_SUCCESS = " has been deleted.";

    public FileDeleteCommand(Path filePath) {
        super(filePath);
    }

    @Override
    public CommandResult execute(Storage storage) throws CommandException {
        try {
            storage.deleteResidentBook(filePath);
            return new CommandResult(fileName + MESSAGE_SUCCESS);
        } catch (NoSuchFileException e) {
            throw new CommandException(fileName + MESSAGE_FILE_NON_EXISTENT);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "deleting"));
        }
    }

}
