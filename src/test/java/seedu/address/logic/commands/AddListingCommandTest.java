package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.ListingBuilder;
import seedu.address.testutil.ModelStub;

public class AddListingCommandTest {

    @Test
    public void constructor_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddListingCommand(null, null, null, null));
    }

    @Test
    public void execute_listingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingListingAdded modelStub = new ModelStubAcceptingListingAdded();
        Listing validListing = new ListingBuilder().build();
        CommandResult commandResult = new AddListingCommand(validListing.getId(),
            validListing.getAddress(), validListing.getName(), validListing.getAskingPrice()).execute(modelStub);

        assertEquals(String.format(AddListingCommand.MESSAGE_SUCCESS, validListing), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateListing_throwsCommandException() {
        Listing validListing = new ListingBuilder().build();
        AddListingCommand addListingCommand = new AddListingCommand(validListing.getId(),
            validListing.getAddress(), validListing.getName(), validListing.getAskingPrice());
        ModelStub modelStub = new ModelStubWithListing(validListing);

        assertThrows(CommandException.class,
            AddListingCommand.MESSAGE_DUPLICATE_LISTING, (
            ) -> addListingCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Listing alice = new ListingBuilder().withId("alice").build();
        Listing bob = new ListingBuilder().withId("bob").build();
        AddListingCommand addAliceCommand = new AddListingCommand(
            alice.getId(), alice.getAddress(), alice.getName(), alice.getAskingPrice());
        AddListingCommand addBobCommand = new AddListingCommand(
            bob.getId(), bob.getAddress(), bob.getName(), bob.getAskingPrice());

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddListingCommand addAliceCommandCopy = new AddListingCommand(
            alice.getId(), alice.getAddress(), alice.getName(), alice.getAskingPrice());
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different listing -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single listing.
     */
    private class ModelStubWithListing extends ModelStub {
        private final Listing listing;

        ModelStubWithListing(Listing listing) {
            requireNonNull(listing);
            this.listing = listing;
        }

        @Override
        public boolean hasListing(Listing listing) {
            requireNonNull(listing);
            return this.listing.isSameListing(listing);
        }
    }

    /**
     * A Model stub that always accept the listing being added.
     */
    private class ModelStubAcceptingListingAdded extends ModelStub {
        final ArrayList<Listing> listingsAdded = new ArrayList<>();

        @Override
        public boolean hasListing(Listing listing) {
            requireNonNull(listing);
            return listingsAdded.stream().anyMatch(listing::isSameListing);
        }

        @Override
        public void addListing(Listing listing) {
            requireNonNull(listing);
            listingsAdded.add(listing);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
