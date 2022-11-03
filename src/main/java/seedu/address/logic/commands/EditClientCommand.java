package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.client.Name;
import seedu.address.model.client.NameEqualsKeywordPredicate;
import seedu.address.model.remark.UniqueRemarkList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.TransactionLog;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditClientCommand extends EditCommand {

    public static final String MESSAGE_EDIT_CLIENT_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one Client field to edit must be provided.\n"
            + "E.g. [n/NAME] [a/ADDRESS] [p/PHONE] [e/EMAIL] [t/TAG]";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book.";

    private final Index index;
    private final EditClientDescriptor editClientDescriptor;
    private final String warningMessage;

    /**
     * @param index of the client in the filtered client list to edit.
     * @param editClientDescriptor details to edit the client with.
     */
    public EditClientCommand(Index index, EditClientDescriptor editClientDescriptor, String warningMessage) {
        requireNonNull(index);
        requireNonNull(editClientDescriptor);

        this.index = index;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
        this.warningMessage = warningMessage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = createEditedClient(clientToEdit, editClientDescriptor);

        if (!clientToEdit.isSameClient(editedClient) && model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.setClient(clientToEdit, editedClient);

        if (lastShownList.size() > 1) {
            model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        } else {
            model.updateFilteredClientList(new NameEqualsKeywordPredicate(editedClient));
        }

        return warningMessage.isEmpty()
                ? new CommandResult(String.format(MESSAGE_EDIT_CLIENT_SUCCESS, editedClient))
                : new CommandResult(String.format("WARNING!\n" + warningMessage
                + "\n" + MESSAGE_EDIT_CLIENT_SUCCESS, editedClient));
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit, EditClientDescriptor editClientDescriptor) {
        assert clientToEdit != null;

        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Address updatedAddress = editClientDescriptor.getAddress().orElse(clientToEdit.getAddress());
        ClientPhone updatedPhone = editClientDescriptor.getPhone().orElse(clientToEdit.getPhone());
        ClientEmail updatedEmail = editClientDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Set<Tag> updatedTags = editClientDescriptor.getTags().orElse(clientToEdit.getTags());
        UniqueRemarkList remarks = editClientDescriptor.getUniqueRemarkList().orElse(clientToEdit.getRemarks());
        TransactionLog transactions = clientToEdit.getTransactions();

        return new Client(updatedName, updatedAddress, updatedPhone,
                updatedEmail, updatedTags, remarks, transactions);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditClientCommand)) {
            return false;
        }

        // state check
        EditClientCommand e = (EditClientCommand) other;
        return index.equals(e.index)
                && editClientDescriptor.equals(e.editClientDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditClientDescriptor {
        private Name name;
        private Address address;
        private ClientPhone phone;
        private ClientEmail email;
        private Set<Tag> tags;
        private UniqueRemarkList remarks;
        private TransactionLog transactions;

        public EditClientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setAddress(toCopy.address);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
            setUniqueRemarkList(toCopy.remarks);
            setTransactionLog(toCopy.transactions);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, address, phone, email, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public void setPhone(ClientPhone phone) {
            this.phone = phone;
        }

        public Optional<ClientPhone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(ClientEmail email) {
            this.email = email;
        }

        public Optional<ClientEmail> getEmail() {
            return Optional.ofNullable(email);
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setUniqueRemarkList(UniqueRemarkList remarks) {
            this.remarks = remarks;
        }

        public Optional<UniqueRemarkList> getUniqueRemarkList() {
            return Optional.ofNullable(remarks);
        }

        public void setTransactionLog(TransactionLog transactions) {
            this.transactions = transactions;
        }

        public Optional<TransactionLog> getTransactionLog() {
            return Optional.ofNullable(transactions);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClientDescriptor)) {
                return false;
            }

            // state check
            EditClientDescriptor e = (EditClientDescriptor) other;

            return getName().equals(e.getName())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
