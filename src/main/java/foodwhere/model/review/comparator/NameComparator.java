package foodwhere.model.review.comparator;

import java.util.Comparator;

import foodwhere.model.review.Review;

/**
 * Creates a comparator class {@code NameComparator} that compares the review by name lexicographically.
 */
public class NameComparator implements Comparator<Review> {
    @Override
    public int compare(Review r1, Review r2) {
        return r1.getName().fullName.compareTo(r2.getName().fullName);
    }
}
