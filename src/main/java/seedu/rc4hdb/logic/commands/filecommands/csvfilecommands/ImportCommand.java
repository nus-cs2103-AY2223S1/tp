package seedu.rc4hdb.logic.commands.filecommands.csvfilecommands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.StorageCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.storage.Storage;

/**
 * Converts the CSV file data into JSON format and create a new JSON file, with the same name as the original CSV file,
 * with the data from the CSV file, in JSON format.
 */
public class ImportCommand extends CsvFileCommand implements StorageCommand {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_SUCCESS = "Successfully imported %s.";

    public static final String MESSAGE_FILE_EXISTS = "%s already exists.";

    public static final String MESSAGE_FILE_DOES_NOT_EXIST = "%s does not exist. Please provide an existing file.";

    public static final String MESSAGE_INVALID_FILE_DATA = "Failed to import from %s.\n%s";

    private Path newJsonFilePath;

    /**
     * Constructs an ImportCommand.
     * @param dir the directory to work in.
     * @param fileName the name of the file to be imported.
     */
    public ImportCommand(Path dir, String fileName) {
        super(dir, fileName);
        newJsonFilePath = dir.resolve(fileName + JSON_POST_FIX);
    }

    @Override
    public CommandResult execute(Storage storage) throws CommandException {
        requireNonNull(storage);

        try {
            ReadOnlyResidentBook residentBook = storage.readCsvFile(folderPath).get();
            storage.createResidentBookFile(newJsonFilePath);
            storage.saveResidentBook(residentBook, newJsonFilePath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, folderName));
        } catch (FileAlreadyExistsException e) {
            throw new CommandException(String.format(MESSAGE_FILE_EXISTS, newJsonFilePath.getFileName()), e);
        } catch (NoSuchElementException e) {
            throw new CommandException(String.format(MESSAGE_FILE_DOES_NOT_EXIST, folderName), e);
        } catch (DataConversionException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE_DATA, folderName, e.getMessage()), e);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "importing"), e);
        }
    }

}
