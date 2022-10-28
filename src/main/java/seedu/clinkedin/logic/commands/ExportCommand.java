package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.FileUtil.exportToCsvFile;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PATH;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.clinkedin.commons.util.JsonUtil;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.ParserUtil.FileType;
import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.storage.JsonSerializableAddressBook;


/**
 * Exports the addressbook.
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the address book.\n"
            + "Parameters: "
            + PREFIX_PATH + "PATH\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATH + "~/Desktop/data.csv";
    public static final String MESSAGE_SUCCESS = "PersonList exported successfully in %s";
    public static final String MESSAGE_WINDOW = "Opening Export Window...";

    private String filePath;
    private FileType fileType;
    private boolean onlyCommand;

    /**
     * Creates an ExportCommand to export the AddressBook
     */
    public ExportCommand(String filePath, FileType fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    /**
     * Creates an ExportCommand to export the AddressBook
     */
    public ExportCommand() {
        this.onlyCommand = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (onlyCommand) {
            return new CommandResult(MESSAGE_WINDOW, false, false, true);
        }
        ObservableList<Person> personList = model.getFilteredPersonList();
        switch (fileType) {
        case CSV:
            toCsv(personList);
            break;
        case JSON:
            toJson(personList);
            break;
        default:
            throw new CommandException("File format invalid or not compatible!");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath));

    }

    /**
     * Convert personList to CSVFormat parsable form.
     */
    public static List<String[]> toCsvFormat(ObservableList<Person> personList) {
        return personList.stream().flatMap(person -> person.getDetailsAsArray().stream()).collect(Collectors.toList());
    }

    /**
     * Export in CSV format.
     */
    public void toCsv(ObservableList<Person> personList) throws CommandException {
        List<String[]> data = toCsvFormat(personList);
        try {
            exportToCsvFile(filePath, data);
        } catch (IOException ioe) {
            throw new CommandException("Couldn't export file! Check file path and try again!");
        }
    }

    /**
     * Export in JSON format.
     */
    public void toJson(ObservableList<Person> personList) throws CommandException {
        AddressBook filteredAddressBook = new AddressBook();
        filteredAddressBook.setPersons(personList);
        try {
            Path path = Paths.get(filePath);
            JsonUtil.saveJsonFile(new JsonSerializableAddressBook(filteredAddressBook), path);
        } catch (IOException | IllegalArgumentException e) {
            throw new CommandException("Couldn't export file! Check file path and try again!");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this.onlyCommand) {
            return other instanceof ExportCommand && ((ExportCommand) other).onlyCommand;
        }
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && filePath.equals(((ExportCommand) other).filePath)
                && fileType == ((ExportCommand) other).fileType);
    }
}
