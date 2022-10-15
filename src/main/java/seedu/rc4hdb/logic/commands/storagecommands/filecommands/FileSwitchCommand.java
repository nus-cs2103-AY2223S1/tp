package seedu.rc4hdb.logic.commands.storagecommands.filecommands;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.StorageModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.storage.Storage;

/**
 * Switches the current working file to the file corresponding to the arguments provided.
 */
public class FileSwitchCommand extends FileCommand implements StorageModelCommand {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_SUCCESS = "Successfully switched working file to %s.";

    public static final String MESSAGE_FILE_DOES_NOT_EXIST = "%s does not exist. Please provide an existing file.";

    public static final String MESSAGE_INVALID_FILE_DATA = "%s contains invalid data."
            + " Please make sure it is a proper JSON file.";

    public FileSwitchCommand(Path filePath) {
        super(filePath);
    }

    @Override
    public CommandResult execute(Storage storage, Model model) throws CommandException {
        requireAllNonNull(storage, model);

        model.setResidentBook(readResidentBook(storage));
        model.setResidentBookFilePath(filePath);
        storage.setResidentBookFilePath(filePath);
        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }

    private ReadOnlyResidentBook readResidentBook(Storage storage) throws CommandException {
        try {
            return storage.readResidentBook(filePath).get();
        } catch (NoSuchElementException e) {
            throw new CommandException(String.format(MESSAGE_FILE_DOES_NOT_EXIST, fileName));
        } catch (DataConversionException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE_DATA, fileName));
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "switching"));
        }
    }

}
