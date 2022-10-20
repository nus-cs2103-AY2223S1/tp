package foodwhere.model.stall.comparator;

import java.util.Comparator;

import foodwhere.model.stall.Stall;

/**
 * A list of supported comparator between stalls.
 */
public enum StallsComparatorList {

    NAME("name (A to Z)", new NameComparator()),
    REVERSEDNAME("name (Z to A)", new NameComparator().reversed());

    public static final String MESSAGE_CONSTRAINTS = "See user guide for the supported criteria";

    private final String criteria;
    private final Comparator<Stall> comparator;

    StallsComparatorList(String criteria, Comparator<Stall> comparator) {
        this.criteria = criteria;
        this.comparator = comparator;
    }

    public String getCriteria() {
        return criteria;
    }

    public Comparator<Stall> getComparator() {
        return comparator;
    }
}
