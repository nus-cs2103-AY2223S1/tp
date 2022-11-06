package seedu.realtime.model.offer;

import java.util.List;
import java.util.function.Predicate;

import seedu.realtime.commons.util.StringUtil;

/**
 * Tests that a {@code Offer}'s {@code ListingId} matches the ListingId given.
 */
public class OfferContainsListingIdPredicate implements Predicate<Offer> {
    private final List<String> listingId;

    public OfferContainsListingIdPredicate(List<String> listingId) {
        this.listingId = listingId;
    }

    @Override
    public boolean test(Offer offer) {
        return listingId.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(offer.getListing().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OfferContainsListingIdPredicate // instanceof handles nulls
                && listingId.equals(((OfferContainsListingIdPredicate) other).listingId)); // state check
    }
}
