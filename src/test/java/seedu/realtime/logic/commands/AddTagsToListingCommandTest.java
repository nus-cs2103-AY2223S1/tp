package seedu.realtime.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.realtime.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.RealTime;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.tag.Tag;
import seedu.realtime.testutil.ListingBuilder;
import seedu.realtime.testutil.ModelStub;

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
        public ReadOnlyRealTime getRealTime() {
            return new RealTime();
        }
    }
}
