package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Description;
import seedu.address.model.property.Owner;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;

/**
 * Edits the details of an existing property in the property book.
 */
public class EditPropertyCommand extends Command {

    public static final String COMMAND_WORD = "editprop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the property identified "
            + "by the index number used in the displayed property list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_PRICE + " PRICE] "
            + "[" + PREFIX_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_DESCRIPTION + " DESC] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS]"
            + "[" + PREFIX_OWNER_NAME + " OWNER NAME] "
            + "[" + PREFIX_PHONE + " OWNER PHONE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + " 4 Room Heng Mui Keng Condo "
            + PREFIX_PRICE + " 500000 "
            + PREFIX_ADDRESS + " Heng Mui Keng Terrace 22 "
            + PREFIX_DESCRIPTION + " 4 Room Condo with 2 bathrooms "
            + PREFIX_CHARACTERISTICS + " 5-Room; Near School "
            + PREFIX_OWNER_NAME + " John Doe "
            + PREFIX_PHONE + " 8000 1000\n";

    public static final String MESSAGE_EDIT_PROPERTY_SUCCESS = "Edited Property: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in Cobb.";

    private final Index index;
    private final EditPropertyDescriptor editPropertyDescriptor;

    /**
     * @param index of the Property in the filtered Property list to edit
     * @param editPropertyDescriptor details to edit the Property with
     */
    public EditPropertyCommand(Index index, EditPropertyDescriptor editPropertyDescriptor) {
        requireAllNonNull(index, editPropertyDescriptor);

        this.index = index;
        this.editPropertyDescriptor = new EditPropertyDescriptor(editPropertyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Property> lastShownList = model.getFilteredPropertyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToEdit = lastShownList.get(index.getZeroBased());
        Property editedProperty = createEditedProperty(propertyToEdit, editPropertyDescriptor);

        if (!propertyToEdit.isSameProperty(editedProperty) && model.hasProperty(editedProperty)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        model.setProperty(propertyToEdit, editedProperty);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return new CommandResult(String.format(MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty));
    }

    /**
     * Creates and returns a {@code Property} with the details of {@code propertyToEdit}
     * edited with {@code descriptor}.
     */
    private static Property createEditedProperty(Property propertyToEdit, EditPropertyDescriptor descriptor) {
        assert propertyToEdit != null;

        PropertyName updatedPropertyName = descriptor.getName().orElse(propertyToEdit.getPropertyName());
        Price updatedPrice = descriptor.getPrice().orElse(propertyToEdit.getPrice());
        Address updatedAddress = descriptor.getAddress().orElse(propertyToEdit.getAddress());
        Description updatedDescription = descriptor.getDescription().orElse(propertyToEdit.getDescription());
        Characteristics updatedCharacteristics = descriptor
                .getCharacteristics()
                .orElse(propertyToEdit.getCharacteristics().orElse(null));

        // Allows for the changing of owner name and owner phone individually
        Name updatedOwnerName = descriptor.getOwnerName().orElse(propertyToEdit.getOwner().getName());
        Phone updatedOwnerPhone = descriptor.getOwnerPhone().orElse(propertyToEdit.getOwner().getPhone());
        Owner updatedOwner = new Owner(updatedOwnerName, updatedOwnerPhone);


        // Should this be updated
        LocalDateTime entryTime = propertyToEdit.getPropertyEntryTime();

        return new Property(updatedPropertyName, updatedPrice, updatedAddress, updatedDescription,
                updatedCharacteristics, updatedOwner, entryTime);
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

        // state check
        EditPropertyCommand e = (EditPropertyCommand) other;
        return index.equals(e.index)
                && editPropertyDescriptor.equals(e.editPropertyDescriptor);
    }

    /**
     * Stores the details to edit the Property with. Each non-empty field value will replace the
     * corresponding field value of the Property.
     */
    public static class EditPropertyDescriptor {
        private PropertyName name;
        private Price price;
        private Address address;
        private Description description;
        private Characteristics characteristics;
        private Owner owner;
        private Name ownerName;
        private Phone ownerPhone;

        public EditPropertyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPropertyDescriptor(EditPropertyDescriptor toCopy) {
            setName(toCopy.name);
            setPrice(toCopy.price);
            setDescription(toCopy.description);
            setAddress(toCopy.address);
            setCharacteristics(toCopy.characteristics);
            setOwner(toCopy.owner);
            setOwnerName(toCopy.ownerName);
            setOwnerPhone(toCopy.ownerPhone);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, price, address, description, address, characteristics,
                    ownerName, ownerPhone);
        }

        public void setName(PropertyName propertyName) {
            this.name = propertyName;
        }

        public Optional<PropertyName> getName() {
            return Optional.ofNullable(name);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }
        public Optional<Owner> getOwner() {
            return Optional.ofNullable(owner);
        }

        public void setOwnerName(Name name) {
            this.ownerName = name;

        }
        public Optional<Name> getOwnerName() {
            return Optional.ofNullable(ownerName);
        }

        public void setOwnerPhone(Phone phone) {
            this.ownerPhone = phone;
        }
        public Optional<Phone> getOwnerPhone() {
            return Optional.ofNullable(ownerPhone);
        }



        public void setCharacteristics(Characteristics characteristics) {
            this.characteristics = characteristics;
        }

        public Optional<Characteristics> getCharacteristics() {
            return Optional.ofNullable(characteristics);
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
                    && getPrice().equals(e.getPrice())
                    && getDescription().equals(e.getDescription())
                    && getAddress().equals(e.getAddress())
                    && getCharacteristics().equals(e.getCharacteristics())
                    && getOwner().equals(e.getOwner());
        }
    }
}
