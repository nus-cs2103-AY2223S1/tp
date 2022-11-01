package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_IMAGE_UPLOAD;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_INTERESTEDCLIENTS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.condonery.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Price;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.utils.ParsePropertyInterestedClients;
import seedu.condonery.model.tag.PropertyStatusEnum;
import seedu.condonery.model.tag.PropertyTypeEnum;
import seedu.condonery.model.tag.Tag;

/**
 * Edits the details of an existing property in the address book.
 */
public class EditPropertyCommand extends Command {

    public static final String COMMAND_WORD = "edit -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the property identified "
            + "by the index number used in the displayed property list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_INTERESTEDCLIENTS + "INTERESTED-CLIENTS]...\n"
            + "[" + PREFIX_IMAGE_UPLOAD + "IMAGE-PATH]...\n"
            + "[" + PREFIX_PROPERTY_TYPE + "PROPERTY TYPE] "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_PROPERTY_SUCCESS = "Property successfully edited: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in the address book.";

    private final Index targetIndex;
    private final EditPropertyDescriptor editPropertyDescriptor;
    private boolean hasImage = false;

    /**
     * Creates a EditPropertyCommand to edit the specific {@code Property} at the specified index
     * @param targetIndex of the property to edit
     * @param editPropertyDescriptor details to edit the property
     */
    public EditPropertyCommand(Index targetIndex, EditPropertyDescriptor editPropertyDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editPropertyDescriptor);

        this.targetIndex = targetIndex;
        this.editPropertyDescriptor = new EditPropertyDescriptor(editPropertyDescriptor);
    }

    /**
     * Overloaded Constructor for hasImage boolean to determine if Command includes image upload.
     * @param targetIndex of the property to edit
     * @param editPropertyDescriptor details to edit the property
     * @param hasImage true if image is uploaded.
     */
    public EditPropertyCommand(Index targetIndex,
                                     EditPropertyDescriptor editPropertyDescriptor,
                                     boolean hasImage) {
        this(targetIndex, editPropertyDescriptor);
        this.hasImage = hasImage;
    }

    public EditPropertyDescriptor getEditPropertyDescriptor() {
        return editPropertyDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Property> lastShownList = model.getFilteredPropertyList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToEdit = lastShownList.get(targetIndex.getZeroBased());
        Path imageDirectoryPath = model.getUserPrefs().getUserImageDirectoryPath();
        Property editedProperty = createEditedProperty(propertyToEdit, editPropertyDescriptor, imageDirectoryPath);
        // Parsed interested clients
        Property newEditedProperty = new ParsePropertyInterestedClients(editedProperty, model).getNewProperty();
        newEditedProperty.setImageDirectoryPath(imageDirectoryPath);

        if (!propertyToEdit.isSameProperty(newEditedProperty) && model.hasProperty(newEditedProperty)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        File existingImage = new File(propertyToEdit.getImagePath().toString());
        if (existingImage.exists()) {
            existingImage.renameTo(new File(editedProperty.getImagePath().toString()));
        }

        model.setProperty(propertyToEdit, newEditedProperty);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);

        if (this.hasImage) {
            return new CommandResult(
                String.format(MESSAGE_EDIT_PROPERTY_SUCCESS, newEditedProperty),
                false,
                false,
                "property-" + newEditedProperty.getCamelCaseName()
            );
        }
        return new CommandResult(String.format(MESSAGE_EDIT_PROPERTY_SUCCESS, newEditedProperty));
    }

    /**
     * Creates and returns a {@code Property} with the details of {@code propertyToEdit}
     * edited with {@code editPropertyDescriptor}.
     */
    private static Property createEditedProperty(Property propertyToEdit,
                                                 EditPropertyDescriptor editPropertyDescriptor,
                                                 Path imageDirectoryPath) {
        assert propertyToEdit != null;

        Name updatedName = editPropertyDescriptor.getName().orElse(propertyToEdit.getName());
        Address updatedAddress = editPropertyDescriptor.getAddress().orElse(propertyToEdit.getAddress());
        Price updatedPrice = editPropertyDescriptor.getPrice().orElse(propertyToEdit.getPrice());
        Set<Tag> updatedTags = editPropertyDescriptor.getTags().orElse(propertyToEdit.getTags());
        Set<Client> updatedInterestedClients = editPropertyDescriptor
                .getInterestedClients()
                .orElse(propertyToEdit.getInterestedClients());
        PropertyTypeEnum updatedPropertyTypeEnum = editPropertyDescriptor
                .getPropertyTypeEnum().orElse(propertyToEdit.getPropertyTypeEnum());
        PropertyStatusEnum updatedPropertyStatusEnum = editPropertyDescriptor
                .getPropertyStatusEnum().orElse(propertyToEdit.getPropertyStatusEnum());
        Path updatedImageDirectoryPath = editPropertyDescriptor
                .getImageDirectoryPath().orElse(propertyToEdit.getImagePath());
        Property updatedProperty = new Property(updatedName, updatedAddress,
                updatedPrice,
                updatedTags,
                updatedInterestedClients,
                updatedPropertyTypeEnum,
                updatedPropertyStatusEnum);
        updatedProperty.setImageDirectoryPath(updatedImageDirectoryPath);
        return updatedProperty;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPropertyCommand)) {
            return false;
        }

        EditPropertyCommand e = (EditPropertyCommand) other;

        if (!this.targetIndex.equals(e.targetIndex)) {
            return false;
        }

        return editPropertyDescriptor.equals(e.getEditPropertyDescriptor());
    }

    /**
     * Stores the details to edit the property with. Each non-empty field value will replace the
     * corresponding field value of the property.
     */
    public static class EditPropertyDescriptor {
        private Name name;
        private Address address;
        private Price price;
        private Set<Tag> tags;
        private Set<Client> interestedClients;
        private Path imageDirectoryPath;
        private PropertyTypeEnum propertyTypeEnum;
        private PropertyStatusEnum propertyStatusEnum;

        public EditPropertyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} and {@code interestedClients} is used internally.
         */
        public EditPropertyDescriptor(EditPropertyDescriptor toCopy) {
            setName(toCopy.name);
            setAddress(toCopy.address);
            setPrice(toCopy.price);
            setTags(toCopy.tags);
            setInterestedClients(toCopy.interestedClients);
            setImageDirectoryPath(toCopy.imageDirectoryPath);
            setPropertyTypeEnum(toCopy.propertyTypeEnum);
            setPropertyStatusEnum(toCopy.propertyStatusEnum);
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

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public Optional<Path> getImageDirectoryPath() {
            return Optional.ofNullable(imageDirectoryPath);
        }

        public void setImageDirectoryPath(Path imageDirectoryPath) {
            this.imageDirectoryPath = imageDirectoryPath;
        }

        /**
         * Sets {@code interestedClients} to this object's {@code interestedClients}.
         * A defensive copy of {@code interestedClients} is used internally.
         */
        public void setInterestedClients(Set<Client> interestedClients) {
            this.interestedClients = (interestedClients != null) ? new HashSet<>(interestedClients) : null;
        }

        /**
         * Returns an unmodifiable client set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code interestedClients} is null.
         */
        public Optional<Set<Client>> getInterestedClients() {
            return (interestedClients != null)
                    ? Optional.of(Collections.unmodifiableSet(interestedClients))
                    : Optional.empty();
        }

        public Optional<PropertyTypeEnum> getPropertyTypeEnum() {
            return Optional.ofNullable(propertyTypeEnum);
        }

        public void setPropertyTypeEnum(PropertyTypeEnum propertyTypeEnum) {
            this.propertyTypeEnum = propertyTypeEnum;
        }

        public Optional<PropertyStatusEnum> getPropertyStatusEnum() {
            return Optional.ofNullable(propertyStatusEnum);

        }

        public void setPropertyStatusEnum(PropertyStatusEnum propertyStatusEnum) {
            this.propertyStatusEnum = propertyStatusEnum;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPropertyDescriptor)) {
                return false;
            }

            // state check
            EditPropertyDescriptor e = (EditPropertyDescriptor) other;

            return getName().equals(e.getName())
                    && getAddress().equals(e.getAddress())
                    && getPrice().equals(e.getPrice())
                    && getTags().equals(e.getTags())
                    && getImageDirectoryPath().equals(e.getImageDirectoryPath())
                    && getInterestedClients().equals(e.getInterestedClients())
                    && getPropertyTypeEnum().equals(e.getPropertyTypeEnum());
        }

        @Override
        public String toString() {
            return "EditPropertyDescriptor{"
                    + "name=" + name
                    + ", address=" + address
                    + ", price=" + price
                    + ", tags=" + tags
                    + ", interested clients=" + interestedClients
                    + ", image path=" + imageDirectoryPath
                    + ", propertyType=" + propertyTypeEnum
                    + '}';
        }

    }

    @Override
    public String toString() {
        return "EditPropertyCommand{"
                + "targetIndex=" + targetIndex
                + ", editPropertyDescriptor=" + editPropertyDescriptor
                + '}';
    }
}

