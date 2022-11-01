package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.listing.Listing;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ListingBuilder;
import seedu.address.testutil.ModelStub;

public class AddTagsToListingCommandTest {

    @Test
    public void constructor_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddTagsToListingCommand(null, null));
    }

    @Test
    public void execute_tagsAcceptedByModel_addSuccessful() throws Exception {
        AddTagsToListingCommandTest.ModelStubAcceptingListingAdded modelStub =
            new AddTagsToListingCommandTest.ModelStubAcceptingListingAdded();
        Listing validListing = new ListingBuilder().build();
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("test"));
        CommandResult commandResult = new AddTagsToListingCommand(tags, validListing.getId()).execute(modelStub);

        assertEquals(String.format(AddTagsToListingCommand.MESSAGE_SUCCESS, tags), commandResult.getFeedbackToUser());
    }

    /**
     * A Model stub that contains a single listing.
     */
    private class ModelStubWithTags extends ModelStub {
        private final Listing listing;

        ModelStubWithTags(Listing listing) {
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
