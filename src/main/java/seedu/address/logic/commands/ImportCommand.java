package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Adds one or more persons to the address book from an external json source.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds one or more persons to the address book from an external json source. "
            + "Parameters: FILE_PATH\n"
            + "Example: " + COMMAND_WORD + " nus_students.json";

    public static final String MESSAGE_SUCCESS = "New persons imported";
    public static final String MESSAGE_FILE_DOES_NOT_EXIST = "The specified file does not exist";
    public static final String MESSAGE_PATH_IS_DIRECTORY = "The specified path must be a file, not a directory";
    public static final String MESSAGE_FILE_UNREADABLE =
            "The specified file cannot be accessed due to insufficient privileges";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "The specified file has persons duplicate to the address book";
    public static final String MESSAGE_CONSTRAINTS_UNSATISFIED =
            "The specified file contains incorrect format, invalid value and/or duplicate persons";

    private final Path filePath;

    /**
     * @param filePath to import from.
     */
    public ImportCommand(Path filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        checkValidFilePath();
        AddressBook toAppend = createAppendableAddressBook();
        if (model.hasPersons(toAppend)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.appendAddressBook(toAppend);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    /**
     * Checks if the provided filePath is a valid and readable.
     *
     * @throws CommandException if the file path is invalid or unreadable.
     */
    private void checkValidFilePath() throws CommandException {
        if (FileUtil.isDirectory(filePath)) {
            throw new CommandException(MESSAGE_PATH_IS_DIRECTORY);
        }
        if (!FileUtil.isFileExists(filePath)) {
            throw new CommandException(MESSAGE_FILE_DOES_NOT_EXIST);
        }
        if (!FileUtil.isReadable(filePath)) {
            throw new CommandException(MESSAGE_FILE_UNREADABLE);
        }
    }

    /**
     * Converts the json file from the specified file path into an appendable {@code AddressBook}.
     *
     * @throws CommandException if the data content or structure does not conform to the constraints.
     */
    private AddressBook createAppendableAddressBook() throws CommandException {
        JsonAddressBookStorage appendableJsonStorage = new JsonAddressBookStorage(filePath);
        Optional<ReadOnlyAddressBook> importedJsonNewPersons;
        try {
            importedJsonNewPersons = appendableJsonStorage.readAddressBook();
        } catch (DataConversionException ive) {
            throw new CommandException(MESSAGE_CONSTRAINTS_UNSATISFIED);
        }

        AddressBook appendableAddressBook = new AddressBook(importedJsonNewPersons.get());
        return appendableAddressBook;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && filePath.equals(((ImportCommand) other).filePath));
    }
}

