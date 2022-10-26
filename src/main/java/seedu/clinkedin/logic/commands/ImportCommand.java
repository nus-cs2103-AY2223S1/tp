package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.FileUtil.importFromCsvFile;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PATH;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.clinkedin.commons.exceptions.EmptyFileException;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.ParserUtil;
import seedu.clinkedin.logic.parser.ParserUtil.FileType;
import seedu.clinkedin.logic.parser.exceptions.InvalidPersonException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.Person;



/**
 * Imports an addressbook.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports the address book.\n"
            + "Parameters: "
            + PREFIX_PATH + "PATH\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATH + "~/Desktop/data.csv";
    public static final String MESSAGE_SUCCESS = "File imported successfully!";
    public static final String MESSAGE_EMPTY_FILE = "There are no CSV row entries detected in %s!"
            + " Please check your CSV file!";
    public static final String MESSAGE_NO_CHANGE =
            "CLInkedIn already contains all the candidates you are importing from %s!";
    public static final String MESSAGE_SOME_CHANGE =
            "Some candidates were ignored as adding them would result in duplicate persons!";
    public static final String MESSAGE_WINDOW = "Opening Import Window...";

    private String filePath;
    private FileType fileType;
    private boolean onlyCommand;


    /**
     * Creates an ImportCommand to import an AddressBook
     */
    public ImportCommand(String fileName, FileType fileType) {
        this.filePath = fileName;
        this.fileType = fileType;
    }

    /**
     * Creates an ImportCommand to import an AddressBook
     */
    public ImportCommand() {
        this.onlyCommand = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (onlyCommand) {
            return new CommandResult(MESSAGE_WINDOW, false, false, false, true);
        }
        ArrayList<ArrayList<String[]>> content;
        try {
            content = importFromCsvFile(filePath);
        } catch (FileNotFoundException e) {
            throw new CommandException(e.getMessage());
        } catch (EmptyFileException e) {
            throw new CommandException(e.getMessage());
        } catch (IOException e) {
            throw new CommandException("Couldn't import file!");
        }

        if (content.size() == 0) {
            throw new CommandException(String.format(MESSAGE_EMPTY_FILE, filePath));
        }
        List<Person> personList = getPersonList(content);

        boolean isUpdated = false;
        boolean isSomeExisting = false;
        for (Person person : personList) {
            if (!model.hasPerson(person)) {
                isUpdated = true;
                model.addPerson(person);
            } else {
                isSomeExisting = true;
            }
        }

        if (isUpdated && isSomeExisting) {
            return new CommandResult(String.format(MESSAGE_SUCCESS) + " " + MESSAGE_SOME_CHANGE);
        }
        if (isUpdated) {
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        }
        return new CommandResult(String.format(MESSAGE_NO_CHANGE, filePath));

    }

    public List<Person> getPersonList(ArrayList<ArrayList<String[]>> stringPersonList) throws CommandException {
        List<Person> personList = new ArrayList<>();
        for (ArrayList<String[]> person: stringPersonList) {
            try {
                personList.add(ParserUtil.parsePerson(person));
            } catch (InvalidPersonException ipe) {
                throw new CommandException(ipe.getMessage());
            }
        }
        return personList;
    }

}
