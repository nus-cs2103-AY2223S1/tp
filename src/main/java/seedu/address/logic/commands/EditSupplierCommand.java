package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.UniqueId;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing supplier.
 */
public class EditSupplierCommand extends EditCommand {

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

        PersonCategory updatedPersonCategory =
                editPersonDescriptor.getPersonCategory().orElse(supplierToEdit.getPersonCategory());
        Name updatedName = editPersonDescriptor.getName().orElse(supplierToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(supplierToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(supplierToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(supplierToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(supplierToEdit.getTags());
        List<UniqueId> pets = supplierToEdit.getPetIds();

        return new Supplier(updatedPersonCategory, updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                pets);
    }

}
