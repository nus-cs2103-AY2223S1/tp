package seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.storagemodelcommands.StorageModelCommand;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.storage.Storage;

/**
 * Switches the current working file to the file corresponding to the arguments provided.
 */
public class FileSwitchCommand extends FileCommand implements StorageModelCommand {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_SUCCESS = "Successfully switched files.";

    public static final String MESSAGE_NON_EXISTENT_FILE = " does not exist. Please provide an existing file.";

    public FileSwitchCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Storage storage, Model model) throws CommandException {
        requireAllNonNull(storage, model);

        Path originalPath = storage.getResidentBookFilePath();

        try {
            storage.setResidentBookFilePath(filePath);
            Optional<ReadOnlyResidentBook> newResidentBook = storage.readResidentBook();

            if (!newResidentBook.isPresent()) {
                storage.setResidentBookFilePath(originalPath);
                throw new CommandException(filePath.getFileName() + MESSAGE_NON_EXISTENT_FILE);
            }

            model.setResidentBook(newResidentBook.get());
            model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);

            // Set user pref default ResidentBookFilePath to filePath
            model.setResidentBookFilePath(filePath);
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (DataConversionException | IOException e) {
            storage.setResidentBookFilePath(originalPath);
            throw new CommandException(String.format(MESSAGE_FAILED, "switching"));
        }
    }

}
