package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Renames the currently stored folder name to a new name
 */
public class RenameCommand extends Command {
    public static final String COMMAND_WORD = "rename";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Renames the current folder "
            + "by the input string.\n"
            + "Parameters: NAME (must be an alphanumeric String with only '-' and '_' allowed)";

    public static final String MESSAGE_RENAME_SUCCESS = "Renamed successfully!";
    public static final String MESSAGE_RENAME_FAILURE = "There is an error creating the file!\n"
            + "The file has to be in alphanumeric format with only '-' and '_' allowed\n"
            + "The file cannot be a duplicate";

    private final String name;

    /**
     * @param name of the new book
     */
    public RenameCommand(String name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.renameAddressBook(this.name);
            return new CommandResult(MESSAGE_RENAME_SUCCESS, null, false, false, false, false);
        } catch (IOException e) {
            return new CommandResult(MESSAGE_RENAME_FAILURE, null, false, false, false, false);
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RenameCommand)) {
            return false;
        }

        // state check
        RenameCommand s = (RenameCommand) other;
        return name.equals(s.name);
    }
}
