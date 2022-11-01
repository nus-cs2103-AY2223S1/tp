package seedu.condonery.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_INTERESTEDPROPERTIES;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.condonery.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.commons.util.CollectionUtil;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.utils.ParseClientInterestedProperties;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.Tag;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditClientCommand extends Command {

    public static final String COMMAND_WORD = "edit -c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the specified "
            + "client in Condonery. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Alice Tan"
            + PREFIX_ADDRESS + "Cantonment Rd, #1G, S085301 "
            + PREFIX_TAG + "Condo"
            + PREFIX_INTERESTEDPROPERTIES + "duxton";


    public static final String MESSAGE_EDIT_CLIENT_SUCCESS = "Client successfully edited: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book.";

    private final Index targetIndex;
    private final EditClientDescriptor editClientDescriptor;
    private boolean hasImage = false;

    /**
     * Creates a EditClientCommand to edit the specific {@code Client} at the specified index
     * @param targetIndex index of the client to edit
     * @param editClientDescriptor details to edit the client
     */
    public EditClientCommand(Index targetIndex, EditClientDescriptor editClientDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editClientDescriptor);

        this.targetIndex = targetIndex;
        this.editClientDescriptor = editClientDescriptor;
    }

    /**
     * Overloaded Constructor to specify if user is editting the Client image.
     * @param targetIndex index of the client to edit
     * @param editClientDescriptor details to edit the client
     * @param hasImage boolean image is being uploaded
     */
    public EditClientCommand(Index targetIndex, EditClientDescriptor editClientDescriptor, boolean hasImage) {
        this(targetIndex, editClientDescriptor);
        this.hasImage = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Client> lastShownList = model.getFilteredClientList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(targetIndex.getZeroBased());
        Path imageDirectoryPath = model.getUserPrefs().getUserImageDirectoryPath();
        Client editedClient = createEditedClient(clientToEdit, editClientDescriptor, imageDirectoryPath);
        // Parsed interested properties
        Client newEditedClient = new ParseClientInterestedProperties(editedClient, model).getNewClient();
        newEditedClient.setImageDirectoryPath(imageDirectoryPath);

        if (!clientToEdit.isSameClient(editedClient) && model.hasClient(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        File existingImage = new File(clientToEdit.getImagePath().toString());
        if (existingImage.exists()) {
            existingImage.renameTo(new File(newEditedClient.getImagePath().toString()));
        }

        model.setClient(clientToEdit, newEditedClient);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);

        if (this.hasImage) {
            return new CommandResult(
                String.format(MESSAGE_EDIT_CLIENT_SUCCESS, editedClient),
                false,
                false,
                "client-" + newEditedClient.getCamelCaseName()
            );
        }
        return new CommandResult(String.format(MESSAGE_EDIT_CLIENT_SUCCESS, newEditedClient));
    }

    public EditClientDescriptor getEditClientDescriptor() {
        return editClientDescriptor;
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit,
                                             EditClientDescriptor editClientDescriptor,
                                             Path imageDirectoryPath) {
        assert clientToEdit != null;

        Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getName());
        Address updatedAddress = editClientDescriptor.getAddress().orElse(clientToEdit.getAddress());
        Set<Tag> updatedTags = editClientDescriptor.getTags().orElse(clientToEdit.getTags());
        Set<Property> updatedInterestedProperties = editClientDescriptor
                .getInterestedProperties()
                .orElse(clientToEdit.getInterestedProperties());

        Client updatedClient = new Client(updatedName, updatedAddress, updatedTags, updatedInterestedProperties);
        updatedClient.setImageDirectoryPath(imageDirectoryPath);
        return updatedClient;
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

        EditClientCommand e = (EditClientCommand) other;

        if (!this.targetIndex.equals(e.targetIndex)) {
            return false;
        }

        return editClientDescriptor.equals(e.getEditClientDescriptor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetIndex, editClientDescriptor);
    }

    /**
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditClientDescriptor {
        private Name name;
        private Address address;
        private Set<Tag> tags;
        private Set<Property> interestedProperties;

        public EditClientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} and {@code interestedProperties} is used internally.
         */
        public EditClientDescriptor(EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setInterestedProperties(toCopy.interestedProperties);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, address, tags);
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

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
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

        /**
         * Sets {@code properies} to this object's {@code properties}.
         * A defensive copy of {@code properties} is used internally.
         */
        public void setInterestedProperties(Set<Property> properties) {
            this.interestedProperties = (interestedProperties != null)
                    ? new HashSet<>(interestedProperties)
                    : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code properties} is null.
         */
        public Optional<Set<Property>> getInterestedProperties() {
            return (interestedProperties != null)
                    ? Optional.of(Collections.unmodifiableSet(interestedProperties))
                    : Optional.empty();
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
                    && getTags().equals(e.getTags())
                    && getInterestedProperties().equals(e.getInterestedProperties());
        }

        @Override
        public String toString() {
            return "EditPropertyDescriptor{"
                    + "name=" + name
                    + ", address=" + address
                    + ", tags=" + tags
                    + '}';
        }

    }

}
