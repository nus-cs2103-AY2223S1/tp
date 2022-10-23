package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.FileUtil.exportToCsvFile;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PATH;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.ParserUtil.FileType;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.Person;



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

    private String filePath;
    private FileType fileType;

    /**
     * Creates an ExportCommand to export the AddressBook
     */
    public ExportCommand(String filePath, FileType fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<String[]> data = toCsvFormat(model.getFilteredPersonList());

        try {
            exportToCsvFile(filePath, data);
        } catch (IOException ioe) {
            throw new CommandException(ioe.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, filePath));
    }

    /**
     * Convert personList to CSVFormat parsable form.
     */
    public static List<String[]> toCsvFormat(ObservableList<Person> personList) {
        return personList.stream().flatMap(person -> person.getDetailsAsArray().stream()).collect(Collectors.toList());
    }

}
