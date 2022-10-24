package seedu.rc4hdb.logic.commands.filecommands;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.StorageModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.storage.Storage;

/**
 * Switches the current working file to the file corresponding to the arguments provided.
 */
public class FileSwitchCommand extends FileCommand implements StorageModelCommand {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_SUCCESS = "Successfully switched working file to %s.";

    public static final String MESSAGE_FOLDER_DOES_NOT_EXIST = "%s does not exist. Please provide an existing folder.";

    public static final String MESSAGE_INVALID_FILE_DATA = "%s contains invalid data."
            + " Please make sure it is in the proper format.";

    public FileSwitchCommand(Path dataDir, String subDirName) {
        super(dataDir.resolve(subDirName));
    }

    @Override
    public CommandResult execute(Storage storage, Model model) throws CommandException {
        requireAllNonNull(storage, model);
        if (folderPath.equals(storage.getDataStorageFilePath())) {
            throw new CommandException(String.format(MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, folderName));
        }
        try {
            model.setResidentBook(storage.readResidentBook(folderPath).get());
            model.setVenueBook(storage.readVenueBook(folderPath).get());
            model.setUserPrefDataFilePath(folderPath);
            storage.setDataStorageFilePath(folderPath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, folderName));
        } catch (NoSuchElementException e) {
            throw new CommandException(String.format(MESSAGE_FOLDER_DOES_NOT_EXIST, folderName), e);
        } catch (DataConversionException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE_DATA, folderName), e);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "switching"), e);
        }
    }

}
