package seedu.address.logic.commands.editcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.UniqueId;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Edits the details of an existing buyer.
 */
public class EditBuyerCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit-b";

    public EditBuyerCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
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
        if (!(o instanceof Buyer)) {
            throw new CommandException(String.format(Messages.INVALID_BUYER, index.getOneBased()));
        }

        Buyer buyerToEdit = (Buyer) o;
        Buyer editedBuyer = createEditedBuyer(buyerToEdit, editPersonDescriptor);

        if (!buyerToEdit.isSamePerson(editedBuyer) && model.hasBuyer(editedBuyer)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setBuyer(buyerToEdit, editedBuyer);
        model.updateFilteredBuyerList(Model.PREDICATE_SHOW_ALL_BUYERS);
        model.switchToBuyerList();
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedBuyer));
    }

    /**
     * Creates and returns a {@code Buyer} with the details of {@code buyerToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Buyer createEditedBuyer(Buyer buyerToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert buyerToEdit != null;
        Name updatedName = editPersonDescriptor.getName().orElse(buyerToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(buyerToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(buyerToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(buyerToEdit.getAddress());
        List<UniqueId> updateOrders = buyerToEdit.getOrderIds();

        return new Buyer(updatedName, updatedPhone, updatedEmail, updatedAddress, updateOrders);
    }

}
