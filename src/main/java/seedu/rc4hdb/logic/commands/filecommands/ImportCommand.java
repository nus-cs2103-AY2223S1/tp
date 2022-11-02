package seedu.rc4hdb.logic.commands.filecommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.core.FilePostfixes.CSV_POSTFIX;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.StorageCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.resident.exceptions.DuplicateResidentException;
import seedu.rc4hdb.storage.Storage;

/**
 * Converts the CSV file data into JSON format and create a new JSON file, with the same name as the original CSV file,
 * with the data from the CSV file, in JSON format.
 */
public class ImportCommand extends FileCommand implements StorageCommand {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_SUCCESS = "Successfully imported %s.";

    public static final String MESSAGE_FILE_EXISTS = "%s already exists.";

    public static final String MESSAGE_FILE_DOES_NOT_EXIST = "%s does not exist. Please provide an existing file.";

    public static final String MESSAGE_INVALID_FILE_DATA = "Failed to import from %s.\n%s";

    private final Path csvFilePath;
    private final String csvFileName;

    /**
     * Constructs an ImportCommand.
     * @param dataDir the directory to work in.
     * @param csvFileName the name of the file to be imported.
     */
    public ImportCommand(Path dataDir, String csvFileName) {
        super(dataDir.resolve(csvFileName));
        this.csvFileName = csvFileName + CSV_POSTFIX;
        csvFilePath = dataDir.resolve(csvFileName);
    }

    @Override
    public CommandResult execute(Storage storage) throws CommandException {
        requireNonNull(storage);

        try {
            ReadOnlyResidentBook residentBook = storage.readCsvFile(csvFilePath).get();
            storage.createDataFolder(folderPath);
            storage.saveResidentBook(residentBook, folderPath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, csvFileName));
        } catch (FileAlreadyExistsException e) {
            throw new CommandException(String.format(MESSAGE_FILE_EXISTS, folderName), e);
        } catch (NoSuchElementException e) {
            throw new CommandException(String.format(MESSAGE_FILE_DOES_NOT_EXIST, csvFileName), e);
        } catch (DataConversionException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILE_DATA, csvFileName, e.getMessage()), e);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_FAILED, "importing"), e);
        } catch (DuplicateResidentException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }

}
