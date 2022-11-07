package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.filename.FileName;
import seedu.address.commons.util.CsvUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Export the current content of the address book as a CSV file with a specified name
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a CSV file that captures the current "
            + "state of the address book\n"
            + "example: " + COMMAND_WORD + " filename";
    public static final String MESSAGE_EXPORT_SUCCESS = "Successfully export address book as CSV file in ";
    public static final String DUPLICATE_FILE_NAME = "A file with this name already exists!";
    public static final String CANNOT_OPEN_FILE = "File created successfully! "
            + "However, there is an error opening the file,\n"
            + "please find the export file at ";
    public final Path exportLocation;
    public ExportCommand(FileName fileName) {
        this.exportLocation = Paths.get("export", fileName.toCsvFormat());
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        File exportFile = new File(exportLocation.toUri());
        if (exportFile.exists()) {
            throw new CommandException(DUPLICATE_FILE_NAME);
        }
        requireNonNull(model);
        CsvUtil.exportAsCsv(model.getAddressBookFilePath(), exportLocation);
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(exportFile);
            } catch (IOException e) {
                throw new CommandException(CANNOT_OPEN_FILE + exportLocation.toAbsolutePath());
            }
        }
        return new CommandResult(MESSAGE_EXPORT_SUCCESS + exportLocation.toAbsolutePath());
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && exportLocation.equals(((ExportCommand) other).exportLocation));
    }
}
