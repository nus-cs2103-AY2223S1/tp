package foodwhere.model.review.comparator;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import foodwhere.model.review.Review;

/**
 * Creates a comparator class {@code NameComparator} that compares the review by name lexicographically.
 */
public class NameComparator implements Comparator<Review> {
    @Override
    public int compare(Review r1, Review r2) {
        requireAllNonNull(r1, r2);
        return r1.getName().fullName.compareToIgnoreCase(r2.getName().fullName);
    }

    /**
     * Returns true if both Comparators are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof NameComparator;
    }
}
