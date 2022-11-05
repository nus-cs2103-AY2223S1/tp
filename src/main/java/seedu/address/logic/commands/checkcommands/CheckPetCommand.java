package seedu.address.logic.commands.checkcommands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;

/**
 * Checks the Supplier of a pet.
 */
public class CheckPetCommand extends CheckCommand {
    public static final String MESSAGE_SUCCESS = "Checking pet %1$s";

    /**
     * Constructs a CheckPetCommand with index specified.
     */
    public CheckPetCommand(Index index) {
        super(index);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Object> lastShownList = model.getFilteredCurrList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Object o = lastShownList.get(index.getZeroBased());
        if (!(o instanceof Pet)) {
            throw new CommandException(String.format(Messages.INVALID_PET, index.getOneBased()));
        }

        Pet pet = (Pet) o;
        model.checkSupplierOfPet(pet);
        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()));
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object instanceof CheckPetCommand) {
            CheckPetCommand other = (CheckPetCommand) object;
            return other.index.equals(this.index);
        }

        return false;
    }
}
