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
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Supplier;

/**
 * Edits the details of an existing supplier.
 */
public class EditSupplierCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit-s";
    public EditSupplierCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        super(index, editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Supplier> lastShownList = model.getFilteredSupplierList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Supplier supplierToEdit = lastShownList.get(index.getZeroBased());
        Supplier editedSupplier = createEditedSupplier(supplierToEdit, editPersonDescriptor);

        if (!supplierToEdit.isSamePerson(editedSupplier) && model.hasSupplier(editedSupplier)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setSupplier(supplierToEdit, editedSupplier);
        model.updateFilteredSupplierList(Model.PREDICATE_SHOW_ALL_SUPPLIERS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedSupplier));
    }

    /**
     * Creates and returns a {@code Supplier} with the details of {@code supplierToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Supplier createEditedSupplier(Supplier supplierToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert supplierToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(supplierToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(supplierToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(supplierToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(supplierToEdit.getAddress());
        List<UniqueId> pets = supplierToEdit.getPetIds();

        return new Supplier(updatedName, updatedPhone, updatedEmail, updatedAddress, pets);
    }

}
