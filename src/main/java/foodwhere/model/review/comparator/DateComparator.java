package foodwhere.model.review.comparator;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import foodwhere.model.review.Review;

/**
 * Creates a comparator class {@code DateComparator} that compares the review by date.
 */
public class DateComparator implements Comparator<Review> {
    @Override
    public int compare(Review r1, Review r2) {
        requireAllNonNull(r1, r2);
        return r1.getDate().compareTo(r2.getDate());
    }

    /**
     * Returns true if both Comparators are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof DateComparator;
    }
}
