package foodwhere.model.review.comparator;

import java.util.Comparator;

import foodwhere.model.review.Review;

/**
 * A list of supported comparator between reviews.
 */
public enum ReviewsComparatorList {

    NAME("name (A to Z)", new NameComparator()),
    REVERSEDNAME("name (Z to A)", new NameComparator().reversed()),
    DATE("date (Oldest to Newest)", new DateComparator()),
    REVERSEDDATE("date (Newest to Oldest)", new DateComparator().reversed()),
    RATING("rating (Lowest to Highest)", new RatingComparator()),
    REVERSEDRATING("rating (Highest to Lowest)", new RatingComparator().reversed());

    public static final String MESSAGE_CONSTRAINTS = "See user guide for the supported criteria";

    private final String criteria;
    private final Comparator<Review> comparator;

    ReviewsComparatorList(String criteria, Comparator<Review> comparator) {
        this.criteria = criteria;
        this.comparator = comparator;
    }

    public String getCriteria() {
        return criteria;
    }

    public Comparator<Review> getComparator() {
        return comparator;
    }
}
