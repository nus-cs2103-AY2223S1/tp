package seedu.rc4hdb.logic.commands.storagecommands.filecommands;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.storagecommands.StorageCommand;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.storage.Storage;

/**
 * Creates a new data file corresponding to the arguments provided.
 */
public class FileCreateCommand extends FileCommand implements StorageCommand {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_FILE_EXISTS = " already exists.";

    public static final String MESSAGE_SUCCESS = " successfully created.";

    public FileCreateCommand(Path filePath) {
        super(filePath);
    }

    @Override
    public CommandResult execute(Storage storage) throws CommandException {
        try {
            storage.saveResidentBook(new ResidentBook(), filePath);
            return new CommandResult(fileName + MESSAGE_SUCCESS);
        } catch (FileAlreadyExistsException e) {
            throw new CommandException(fileName + MESSAGE_FILE_EXISTS);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "creating"));
        }
    }

}
