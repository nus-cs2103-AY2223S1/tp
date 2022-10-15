package seedu.travelr.model.event;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.list.Itineraries;

import java.util.function.Predicate;

public class AllInBucketListPredicate  implements Predicate<Event> {
    FilteredList<Event> bucketList;

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
