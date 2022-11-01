package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NON_EXISTING_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;

/**
 * Edits the details of an existing client in MyInsuRec.
 */
public class EditClientCommand extends Command {

    public static final String COMMAND_WORD = "editClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
            + "by their index number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + PREFIX_INDEX + "INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_PRODUCT + "PRODUCT(S)]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CLIENT_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in MyInsuRec.";

    private final Index index;
    private final EditClientDescriptor editClientDescriptor;

    /**
     * @param index of the client in the filtered client list to edit
     * @param editClientDescriptor details to edit the client with
     */
    public EditClientCommand(Index index, EditClientDescriptor editClientDescriptor) {
        requireNonNull(index);
        requireNonNull(editClientDescriptor);

        this.index = index;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Client> lastShownList = model.getFilteredClientList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = createEditedClient(clientToEdit, editClientDescriptor);

        if (!clientToEdit.isSameClient(editedClient) && model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        if (editedClient.getProducts()
                .stream()
                .map(model::hasProduct)
                .anyMatch(entry -> !entry)) {
            throw new CommandException(MESSAGE_NON_EXISTING_PRODUCT);
        }

        model.setClient(clientToEdit, editedClient);
        return new CommandResult(String.format(MESSAGE_EDIT_CLIENT_SUCCESS, editedClient), CommandSpecific.CLIENT);
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code EditClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit, EditClientDescriptor editClientDescriptor) {
        assert clientToEdit != null;

        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Phone updatedPhone = editClientDescriptor.getPhone().orElse(clientToEdit.getPhone());
        Optional<Email> updatedEmail = editClientDescriptor.getEmail().isEmpty()
                ? clientToEdit.getEmail()
                : editClientDescriptor.getEmail();
        Optional<Address> updatedAddress = editClientDescriptor.getAddress().isEmpty()
                ? clientToEdit.getAddress()
                : editClientDescriptor.getAddress();
        Optional<Birthday> updatedBirthday = editClientDescriptor.getBirthday().isEmpty()
            ? clientToEdit.getBirthday()
            : editClientDescriptor.getBirthday();
        Set<Product> updatedProducts = editClientDescriptor.getProducts().orElse(clientToEdit.getProducts());

        Client client = new Client(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedBirthday, updatedProducts);

        // update client in each meeting this client has
        List<Meeting> meetings = clientToEdit.getMeetings();

        Meeting meeting;
        Meeting updatedMeeting;
        for (int i = 0; i < meetings.size(); i++) {
            meeting = meetings.get(i);
            updatedMeeting = new Meeting(client, meeting.getDescription(), meeting.getMeetingDate(),
                    meeting.getMeetingStartTime(), meeting.getMeetingEndTime());
            client.addMeeting(updatedMeeting);
        }
        return client;
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
        private Phone phone;
        private Optional<Email> email;
        private Optional<Address> address;
        private Optional<Birthday> birthday;
        private Set<Product> products;

        public EditClientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code Products} is used internally.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            name = toCopy.name;
            phone = toCopy.phone;
            email = toCopy.email;
            address = toCopy.address;
            birthday = toCopy.birthday;
            products = toCopy.products;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, birthday, products);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Optional<Email> email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return email == null
                    ? Optional.empty()
                    : email;
        }

        public void setAddress(Optional<Address> address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return address == null
                    ? Optional.empty()
                    : address;
        }

        public void setBirthday(Optional<Birthday> birthday) {
            this.birthday = birthday;
        }
        public Optional<Birthday> getBirthday() {
            return birthday == null
                    ? Optional.empty()
                    : birthday;
        }

        /**
         * Sets {@code products} to this object's {@code products}.
         * A defensive copy of {@code products} is used internally.
         */
        public void setProducts(Set<Product> products) {
            this.products = (products != null) ? new HashSet<>(products) : null;
        }

        /**
         * Returns an unmodifiable product set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code products} is null.
         */
        public Optional<Set<Product>> getProducts() {
            return (products != null) ? Optional.of(Collections.unmodifiableSet(products)) : Optional.empty();
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
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getBirthday().equals(e.getBirthday())
                    && getProducts().equals(e.getProducts());
        }
    }
}
