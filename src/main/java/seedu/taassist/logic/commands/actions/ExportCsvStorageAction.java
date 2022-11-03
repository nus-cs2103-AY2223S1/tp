package seedu.taassist.logic.commands.actions;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import seedu.taassist.logic.commands.CommandResult;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.storage.Storage;

/**
 * Represents a storage action to export a CSV file.
 */
public class ExportCsvStorageAction implements StorageAction {

    public static final String MESSAGE_SUCCESS = "Class [ %1$s ] successfully exported to 'data' folder.";
    public static final String MESSAGE_EXPORT_FAILED = "Failed to export [ %1$s ].";

    private final String fileName;
    private final String fileData;

    /**
     * Creates an ExportCsvStorageAction to export a CSV file with
     * the specified {@code fileName} and {@code fileData}.
     */
    public ExportCsvStorageAction(String fileName, String fileData) {
        requireNonNull(fileName);
        requireNonNull(fileData);
        this.fileName = fileName;
        this.fileData = fileData;
    }

    @Override
    public CommandResult act(Storage storage) throws CommandException {
        String feedback;
        try {
            storage.exportAsCsv(fileName, fileData);
            feedback = String.format(MESSAGE_SUCCESS, fileName);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_EXPORT_FAILED, fileName));
        }
        return new CommandResult(feedback);
    }
}
