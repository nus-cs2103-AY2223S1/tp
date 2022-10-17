package seedu.travelr.model.event;

import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;

/**
 * Tests that an {@code Event}'s is found in Travelr's bucket list.
 */
public class AllInBucketListPredicate implements Predicate<Event> {
    private FilteredList<Event> bucketList;

    public AllInBucketListPredicate(FilteredList<Event> bucketList) {
        this.bucketList = bucketList;
    }

    @Override
    public boolean test(Event event) {
        return bucketList.contains(event);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AllInBucketListPredicate); // state check
    }
}
