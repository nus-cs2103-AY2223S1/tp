package seedu.address.logic.commands.editcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.UniqueId;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Edits the details of an existing Deliverer.
 */
public class EditDelivererCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit-d";
    public EditDelivererCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        super(index, editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Object> lastShownList = model.getFilteredCurrList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Object o = lastShownList.get(index.getZeroBased());
        if (!(o instanceof Deliverer)) {
            throw new CommandException(String.format(Messages.INVALID_DELIVERER, index.getOneBased()));
        }

        Deliverer delivererToEdit = (Deliverer) o;
        Deliverer editedDeliverer = createEditedDeliverer(delivererToEdit, editPersonDescriptor);

        if (!delivererToEdit.isSamePerson(editedDeliverer) && model.hasDeliverer(editedDeliverer)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setDeliverer(delivererToEdit, editedDeliverer);
        model.updateFilteredDelivererList(Model.PREDICATE_SHOW_ALL_DELIVERERS);
        model.switchToDelivererList();
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedDeliverer));
    }

    /**
     * Creates and returns a {@code Deliverer} with the details of {@code delivererToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Deliverer createEditedDeliverer(
            Deliverer delivererToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert delivererToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(delivererToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(delivererToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(delivererToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(delivererToEdit.getAddress());
        Location updatedLocation = editPersonDescriptor.getLocation().orElse(delivererToEdit.getLocation());
        List<UniqueId> orders = delivererToEdit.getOrders();

        return new Deliverer(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedLocation, orders);
    }

}
