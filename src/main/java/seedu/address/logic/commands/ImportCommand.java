package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_IMPORT_ERROR;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.CsvAdaptedPerson;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Imports data from the JSON or CSV file located at the specified path into FinBook.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports data from the JSON or CSV file located at the specified path.\n"
            + "Parameters: path to JSON or CSV file\n"
            + "Examples: \n"
            + COMMAND_WORD + " ./data.json\n"
            + "- imports data from the file \"data.json\""
            + " which is located in the same directory as the FinBook executable\n"
            + COMMAND_WORD + " ../data.csv\n"
            + "- imports data from the file \"data.csv\""
            + " which is located one level outside the directory of the FinBook executable";

    public static final String MESSAGE_IMPORT_DATA_SUCCESS = "Imported data: %1$s";

    private final Path targetPath;

    public ImportCommand(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            if (targetPath.toString().toLowerCase().endsWith(".json")) {
                JsonAddressBookStorage tempStorage = new JsonAddressBookStorage(targetPath);
                tempStorage.readAddressBook().ifPresent(x -> x.getPersonList().forEach(model::addPerson));
            } else {
                List<CsvAdaptedPerson> beans = new CsvToBeanBuilder(new FileReader(targetPath.toFile()))
                        .withType(CsvAdaptedPerson.class).build().parse();
                beans.forEach(x -> {
                    try {
                        model.addPerson(x.toModelType());
                    } catch (IllegalValueException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (Exception e) {
            throw new CommandException(String.format(MESSAGE_IMPORT_ERROR, e.getMessage()));
        }
        return new CommandResult(String.format(MESSAGE_IMPORT_DATA_SUCCESS, targetPath.getFileName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && targetPath.equals(((ImportCommand) other).targetPath)); // state check
    }
}
