package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LISTINGS;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.listing.Listing;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditListingCommand extends Command {

    public static final String COMMAND_WORD = "edit listing";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the listing identified "
            + "by the id used in the displayed listing list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_ID + "ID]"
            + "[" + PREFIX_ADDRESS + "ADDRESS]"
            + "[" + PREFIX_NAME + "NAME]"
            + "[" + PREFIX_ASKING_PRICE + "ASKING PRICE] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "3412 "
            + PREFIX_ADDRESS + " 100 Charming Avenue "
            + PREFIX_NAME + "Jake Holt "
            + PREFIX_ASKING_PRICE + "1 ";

    public static final String MESSAGE_EDIT_LISTING_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_LISTING = "This person already exists in the address book.";

    private final String id;
    private final EditListingDescriptor editListingDescriptor;

    /**
     * @param id of the person in the filtered person list to edit
     * @param editListingDescriptor details to edit the person with
     */
    public EditListingCommand(String id, EditListingDescriptor editListingDescriptor) {
        requireNonNull(editListingDescriptor);

        this.id = id;
        this.editListingDescriptor = new EditListingDescriptor(editListingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Listing listingToEdit = model.getListing(id);

        if (!model.hasListing(listingToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_LISTING_ID);
        }

        Listing editedListing = createEditedPerson(listingToEdit, editListingDescriptor, model);

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
    private static Listing createEditedPerson(Listing listingToEdit, EditListingDescriptor editListingDescriptor,
                                              Model model) {
        assert listingToEdit != null;

        String updatedId = editListingDescriptor.getId().orElse(listingToEdit.getId());
        Name updatedName = editListingDescriptor.getName().orElse(listingToEdit.getName());
        Person updatedPerson = model.getPerson(updatedName);
        Address updatedAddress = editListingDescriptor.getAddress().orElse(listingToEdit.getAddress());
        int updatedAskingPrice = editListingDescriptor.getAskingPrice().orElse(listingToEdit.getAskingPrice());

        return new Listing(updatedId, updatedAddress, updatedPerson, updatedAskingPrice);
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
        return id.equals(e.id)
                && editListingDescriptor.equals(e);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditListingDescriptor {
        private String id;
        private Name name;
        private Address address;
        private int askingPrice;

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
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(id, name, address, askingPrice);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setId(String id) {
            this.id = id;
        }

        public Optional<String> getId() {
            return Optional.ofNullable(id);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAskingPrice(int askingPrice) {
            this.askingPrice = askingPrice;
        }

        public Optional<Integer> getAskingPrice() {
            return Optional.of(askingPrice);
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
    }
}

