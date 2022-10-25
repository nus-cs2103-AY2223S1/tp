package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;

/**
 * Deletes a Pet identified using it's displayed index from the address book.
 */
public class DeletePetCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Pet identified by index used in the displayed Pet list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PET_SUCCESS = "Deleted Pet: %1$s";

    public static final String MESSAGE_DELETE_PET_FAILURE = "Unable to execute DeletePetCommand.";

    private final Index targetIndex;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person}.
     */
    public DeletePetCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Pet> lastShownList = model.getFilteredPetList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Pet petToDelete = lastShownList.get(targetIndex.getZeroBased());

        model.deletePet(petToDelete);


        return new CommandResult(String.format(MESSAGE_DELETE_PET_SUCCESS, petToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePetCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePetCommand) other).targetIndex)); // state check
    }
}

