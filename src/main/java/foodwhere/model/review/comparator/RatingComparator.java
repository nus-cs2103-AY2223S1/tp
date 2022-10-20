package foodwhere.model.review.comparator;

import java.util.Comparator;

import foodwhere.model.review.Review;

/**
 * Creates a comparator class {@code RatingComparator} that compares the review by rating.
 */
public class RatingComparator implements Comparator<Review> {
    @Override
    public int compare(Review r1, Review r2) {
        return r1.getRating().value.compareTo(r2.getRating().value);
    }
}
