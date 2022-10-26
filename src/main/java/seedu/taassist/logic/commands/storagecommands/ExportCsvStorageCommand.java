package seedu.taassist.logic.commands.storagecommands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import seedu.taassist.logic.commands.CommandResult;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.storage.Storage;

/**
 * Export the module class as a CSV file containing student's grade information.
 */
public class ExportCsvStorageCommand extends StorageCommand {

    public static final String MESSAGE_SUCCESS = "Class %1$s successfully exported to %2$s.";
    public static final String MESSAGE_EXPORT_FAILED = "Failed to export %1$s.";

    private String fileName;
    private List<List<String>> fileData;

    /**
     * Creates a {@code ExportStorageCommand} to export the given {@code fileData} as a CSV file.
     * @param fileName
     * @param fileData
     */
    public ExportCsvStorageCommand(String fileName, List<List<String>> fileData) {
        this.fileName = fileName;
        this.fileData = fileData;
    }

    @Override
    public CommandResult execute(Storage storage) throws CommandException {
        String feedback = "";
        try {
            Path filePath = storage.saveAsCsvFile(fileName, fileData);
            feedback += String.format(MESSAGE_SUCCESS, fileName, filePath);
        } catch (IOException e) {
            feedback += String.format(MESSAGE_EXPORT_FAILED, fileName);
        }
        return new CommandResult(feedback);
    }
}
