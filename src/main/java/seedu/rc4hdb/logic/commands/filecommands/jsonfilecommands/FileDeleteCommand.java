package seedu.rc4hdb.logic.commands.filecommands.jsonfilecommands;

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
public class FileDeleteCommand extends JsonFileCommand implements StorageCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_FILE_NON_EXISTENT = "%s does not exist. Please provide a valid file";

    public static final String MESSAGE_SUCCESS = "%s has been deleted.";

    public FileDeleteCommand(Path dir, String fileName) {
        super(dir, fileName);
    }

    @Override
    public CommandResult execute(Storage storage) throws CommandException {
        if (filePath.equals(storage.getResidentBookFilePath())) {
            throw new CommandException(String.format(MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, fileName));
        }
        try {
            storage.deleteResidentBookFile(filePath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
        } catch (NoSuchFileException e) {
            throw new CommandException(String.format(MESSAGE_FILE_NON_EXISTENT, fileName), e);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "deleting"), e);
        }
    }

}
