package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.filename.FileName;
import seedu.address.commons.util.CsvUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Import a CSV file containing address book data into the address book.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports a CSV file containing "
            + "addresses into the current address book "
            + "example: " + COMMAND_WORD + " filename";
    public static final String MESSAGE_IMPORT_SUCCESS = "Successfully import address book!";
    public static final String FILE_DOES_NOTE_EXITS = "A file with this name does not exists!";
    public final Path importLocation;
    public ImportCommand(FileName fileName) {
        this.importLocation = Paths.get("import", fileName.toString());
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        File importFile = new File(importLocation.toUri());
        if (!importFile.exists()) {
            throw new CommandException(FILE_DOES_NOTE_EXITS);
        }
        requireNonNull(model);
        CsvUtil.importCsv(importFile).stream().forEach(person -> {
            if (!model.hasPerson(person)) {
                model.addPerson(person);
            }
        }
        );
        model.updatePieChart();
        return new CommandResult(MESSAGE_IMPORT_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && importLocation.equals(((ImportCommand) other).importLocation));
    }
}
