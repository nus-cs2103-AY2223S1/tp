package seedu.realtime.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_OFFER;
import static seedu.realtime.model.Model.PREDICATE_SHOW_ALL_OFFERS;

import java.util.List;
import java.util.Optional;

import seedu.realtime.commons.core.Messages;
import seedu.realtime.commons.core.index.Index;
import seedu.realtime.commons.util.CollectionUtil;
import seedu.realtime.logic.commands.exceptions.CommandException;
import seedu.realtime.model.Model;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Name;

/**
 * Edits the details of an existing offer in the address book.
 */
public class EditOfferCommand extends Command {
    public static final String COMMAND_WORD = "editO";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the offer identified "
        + "by the index number used in the displayed offer list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_LISTING_ID + "ADDRESS LISTING ID] "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_OFFER + "OFFER PRICE]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_OFFER + "650000";

    public static final String MESSAGE_EDIT_OFFER_SUCCESS = "Edited Offer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_OFFER = "This offer already exists in the address book.";

    private final Index index;
    private final EditOfferCommand.EditOfferDescriptor editOfferDescriptor;

    /**
     * @param index of the offer in the filtered offer list to edit
     * @param editOfferDescriptor details to edit the offer with
     */
    public EditOfferCommand(Index index, EditOfferCommand.EditOfferDescriptor editOfferDescriptor) {
        requireNonNull(index);
        requireNonNull(editOfferDescriptor);

        this.index = index;
        this.editOfferDescriptor = new EditOfferCommand.EditOfferDescriptor(editOfferDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Offer> lastShownList = model.getFilteredOfferList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_OFFER_DISPLAYED_INDEX);
        }

        Offer offerToEdit = lastShownList.get(index.getZeroBased());
        Offer editedOffer = createEditedOffer(offerToEdit, editOfferDescriptor);

        if (!offerToEdit.isSameOffer(editedOffer) && model.hasOffer(editedOffer)) {
            throw new CommandException(MESSAGE_DUPLICATE_OFFER);
        }

        model.setOffer(offerToEdit, editedOffer);
        model.updateFilteredOfferList(PREDICATE_SHOW_ALL_OFFERS);
        return new CommandResult(String.format(MESSAGE_EDIT_OFFER_SUCCESS, editedOffer));
    }

    /**
     * Creates and returns an {@code Offer} with the details of {@code offerToEdit}
     * edited with {@code editOfferDescriptor}.
     */
    private static Offer createEditedOffer(Offer offerToEdit,
                                           EditOfferCommand.EditOfferDescriptor editOfferDescriptor) {
        assert offerToEdit != null;

        Name updatedName = editOfferDescriptor.getName().orElse(offerToEdit.getClient());
        ListingId updatedListingId = editOfferDescriptor.getListing().orElse(offerToEdit.getListing());
        Price offerPrice = editOfferDescriptor.getOfferPrice().orElse(offerToEdit.getOfferPrice());

        return new Offer(updatedName, updatedListingId, offerPrice);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditOfferCommand)) {
            return false;
        }

        // state check
        EditOfferCommand e = (EditOfferCommand) other;
        return index.equals(e.index)
            && editOfferDescriptor.equals(e.editOfferDescriptor);
    }

    /**
     * Stores the details to edit the offer with. Each non-empty field value will replace the
     * corresponding field value of the offer.
     */
    public static class EditOfferDescriptor {
        private Name name;
        private ListingId listing;
        private Price offerPrice;

        public EditOfferDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditOfferDescriptor(EditOfferCommand.EditOfferDescriptor toCopy) {
            setName(toCopy.name);
            setListing(toCopy.listing);
            setOfferPrice(toCopy.offerPrice);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, listing, offerPrice);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setListing(ListingId listing) {
            this.listing = listing;
        }

        public Optional<ListingId> getListing() {
            return Optional.ofNullable(listing);
        }

        public void setOfferPrice(Price offerPrice) {
            this.offerPrice = offerPrice;
        }

        public Optional<Price> getOfferPrice() {
            return Optional.ofNullable(offerPrice);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditOfferCommand.EditOfferDescriptor)) {
                return false;
            }

            // state check
            EditOfferCommand.EditOfferDescriptor e = (EditOfferCommand.EditOfferDescriptor) other;

            return getName().equals(e.getName())
                && getListing().equals(e.getListing())
                && getOfferPrice().equals(e.getOfferPrice());
        }
    }
}
