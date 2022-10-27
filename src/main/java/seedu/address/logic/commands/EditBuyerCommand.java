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
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing buyer.
 */
public class EditBuyerCommand extends EditCommand {

    public EditBuyerCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        super(index, editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Buyer buyerToEdit = lastShownList.get(index.getZeroBased());
        Buyer editedBuyer = createEditedBuyer(buyerToEdit, editPersonDescriptor);

        if (!buyerToEdit.isSamePerson(editedBuyer) && model.hasBuyer(editedBuyer)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setBuyer(buyerToEdit, editedBuyer);
        model.updateFilteredBuyerList(Model.PREDICATE_SHOW_ALL_BUYERS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer));
    }

    /**
     * Creates and returns a {@code Buyer} with the details of {@code buyerToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Buyer createEditedBuyer(Buyer buyerToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert buyerToEdit != null;

        PersonCategory updatedPersonCategory =
                editPersonDescriptor.getPersonCategory().orElse(buyerToEdit.getPersonCategory());
        Name updatedName = editPersonDescriptor.getName().orElse(buyerToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(buyerToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(buyerToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(buyerToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(buyerToEdit.getTags());
        List<UniqueId> updateOrders = buyerToEdit.getOrderIds();

        return new Buyer(updatedPersonCategory, updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updateOrders);
    }

}
