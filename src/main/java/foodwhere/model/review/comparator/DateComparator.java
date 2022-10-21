package foodwhere.model.review.comparator;

import java.util.Comparator;

import foodwhere.model.review.Review;

/**
 * Creates a comparator class {@code DateComparator} that compares the review by date.
 */
public class DateComparator implements Comparator<Review> {
    @Override
    public int compare(Review r1, Review r2) {
        return r1.getDate().compareTo(r2.getDate());
    }
}
