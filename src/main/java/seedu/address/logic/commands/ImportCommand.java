package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds one or more persons to the address book from an external json source.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds one or more persons to the address book from an external json source. "
            + "Parameters: FILE_PATH [MORE_FILE_PATHS]...\n"
            + "Example: " + COMMAND_WORD + " nus_students.json";

    public static final String MESSAGE_SUCCESS = "New persons added";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Path fileName;

    /**
     * @param filePath to import from.
     */
    public ImportCommand(Path filePath) {
        requireNonNull(filePath);
        fileName = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //if (model.hasPerson(toAdd)) {
        //    throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        //}

        //model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && fileName.equals(((ImportCommand) other).fileName));
    }
}

