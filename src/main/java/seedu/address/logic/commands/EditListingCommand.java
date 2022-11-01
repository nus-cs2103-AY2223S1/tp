package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LISTINGS;

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
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.ListingId;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditListingCommand extends Command {

    public static final String COMMAND_WORD = "editL";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the listing identified "
            + "by the id used in the displayed listing list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_LISTING_ID + "ID]"
            + "[" + PREFIX_ADDRESS + "ADDRESS]"
            + "[" + PREFIX_NAME + "NAME]"
            + "[" + PREFIX_ASKING_PRICE + "ASKING PRICE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LISTING_ID + "3412 "
            + PREFIX_ADDRESS + " 100 Charming Avenue "
            + PREFIX_NAME + "Jake Holt "
            + PREFIX_ASKING_PRICE + "1 ";

    public static final String MESSAGE_EDIT_LISTING_SUCCESS = "Edited Listing: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_LISTING = "This Listing already exists in the address book.";

    private final Index index;
    private final EditListingDescriptor editListingDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editListingDescriptor details to edit the person with
     */
    public EditListingCommand(Index index, EditListingDescriptor editListingDescriptor) {
        requireNonNull(index);
        requireNonNull(editListingDescriptor);

        this.index = index;
        this.editListingDescriptor = new EditListingDescriptor(editListingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Listing> lastShownList = model.getFilteredListingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_ID);
        }

        Listing listingToEdit = lastShownList.get(index.getZeroBased());
        Listing editedListing = createEditedListing(listingToEdit, editListingDescriptor);

        if (!listingToEdit.isSameListing(editedListing) && model.hasListing(editedListing)) {
            throw new CommandException(MESSAGE_DUPLICATE_LISTING);
        }

        model.setListing(listingToEdit, editedListing);
        model.updateFilteredListingList(PREDICATE_SHOW_ALL_LISTINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_LISTING_SUCCESS, editedListing));
    }

    /**
     * Creates and returns a {@code Listing} with the details of {@code listingToEdit}
     * edited with {@code editListingDescriptor}.
     */
    private static Listing createEditedListing(Listing listingToEdit, EditListingDescriptor editListingDescriptor) {
        assert listingToEdit != null;

        ListingId updatedId = editListingDescriptor.getId().orElse(listingToEdit.getId());
        Address updatedAddress = editListingDescriptor.getAddress().orElse(listingToEdit.getAddress());
        Name updatedName = editListingDescriptor.getName().orElse(listingToEdit.getName());
        Price updatedAskingPrice = editListingDescriptor.getAskingPrice().orElse(listingToEdit.getAskingPrice());

        return new Listing(updatedId, updatedAddress, updatedName, updatedAskingPrice);
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditListingCommand)) {
            return false;
        }

        // state check
        EditListingCommand e = (EditListingCommand) other;
        return index.equals(e.index)
                && editListingDescriptor.equals(e.editListingDescriptor);
    }

    /**
     * Stores the details to edit the listing with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditListingDescriptor {
        private ListingId id;
        private Name name;
        private Address address;
        private Price askingPrice;
        private Set<Tag> tags;

        public EditListingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditListingDescriptor(EditListingDescriptor toCopy) {
            setId(toCopy.id);
            setName(toCopy.name);
            setAddress(toCopy.address);
            setAskingPrice(toCopy.askingPrice);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(id, name, address, askingPrice, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setId(ListingId id) {
            this.id = id;
        }

        public Optional<ListingId> getId() {
            return Optional.ofNullable(id);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAskingPrice(Price askingPrice) {
            this.askingPrice = askingPrice;
        }

        public Optional<Price> getAskingPrice() {
            return Optional.ofNullable(askingPrice);
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
            if (!(other instanceof EditListingDescriptor)) {
                return false;
            }

            // state check
            EditListingDescriptor e = (EditListingDescriptor) other;

            return getId().equals(e.getId())
                    && getName().equals(e.getName())
                    && getAddress().equals(e.getAddress())
                    && getAskingPrice().equals(e.getAskingPrice());
        }

        public String toString() {
            return this.id + "   "
                    + this.address + "   "
                    + this.name + "   "
                    + this.askingPrice;
        }
    }
}
