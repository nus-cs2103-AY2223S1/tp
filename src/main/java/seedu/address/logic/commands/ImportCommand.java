package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_IMPORT_ERROR;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.opencsv.bean.CsvToBeanBuilder;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
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

    public static final String MESSAGE_DUPLICATE_PERSONS = "Duplicate persons detected, updating with imported data.\n";

    private final Path targetPath;

    public ImportCommand(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AtomicBoolean hasDuplicatePerson = new AtomicBoolean(false);
        try {
            if (targetPath.toString().toLowerCase().endsWith(".json")) {
                JsonAddressBookStorage tempStorage = new JsonAddressBookStorage(targetPath);
                tempStorage.readAddressBook().ifPresent(x -> x.getPersonList().forEach(y -> {
                    try {
                        model.addPerson(y);
                    } catch (DuplicatePersonException e) {
                        hasDuplicatePerson.set(true);
                        model.deletePerson(model.getAddressBook().getPersonList().stream()
                                .filter(z -> z.isSamePerson(y)).findFirst().get());
                        model.addPerson(y);
                    }
                }));
            } else {
                Reader reader = new FileReader(targetPath.toFile());
                List<CsvAdaptedPerson> beans = new CsvToBeanBuilder(reader)
                        .withType(CsvAdaptedPerson.class).build().parse();
                reader.close();
                beans.forEach(x -> {
                    try {
                        model.addPerson(x.toModelType());
                    } catch (DuplicatePersonException e) {
                        try {
                            Person y = x.toModelType();
                            hasDuplicatePerson.set(true);
                            model.deletePerson(model.getAddressBook().getPersonList().stream()
                                    .filter(z -> z.isSamePerson(y)).findFirst().get());
                            model.addPerson(y);
                        } catch (IllegalValueException ex) {
                            throw new RuntimeException(ex.getMessage());
                        }
                    } catch (IllegalValueException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            throw new CommandException(String.format(MESSAGE_IMPORT_ERROR, e.getMessage()));
        }
        if (hasDuplicatePerson.get()) {
            return new CommandResult(String.format(MESSAGE_DUPLICATE_PERSONS + MESSAGE_IMPORT_DATA_SUCCESS,
                    targetPath.getFileName()));
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
