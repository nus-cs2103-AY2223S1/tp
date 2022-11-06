package seedu.address.model.internship;

import java.util.Comparator;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an Internship's sort criteria in the findMyIntern.
 */
public enum SortCriteria {

    Applied("applied"),
    Interview("interview");

    public static final String MESSAGE_CONSTRAINTS = "Sort criteria can only be applied or interview.";

    private final String criteria;

    /**
     * Constructs a new SortCriteria.
     *
     * @param criteria Type of command.
     */
    SortCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     * Returns a SortCriteria based on the given input criteria.
     *
     * @param criteria String representation of the input criteria.
     * @return SortCriteria based on the input criteria.
     */
    public static SortCriteria parse(String criteria) throws ParseException {
        for (SortCriteria sortCriteria : SortCriteria.values()) {
            if (sortCriteria.criteria.equals(criteria.toLowerCase())) {
                return sortCriteria;
            }
        }
        throw new ParseException(SortCriteria.MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid sort criteria.
     */
    public static boolean isValidSortCriteria(String test) {
        for (SortCriteria sortCriteria : SortCriteria.values()) {
            if (sortCriteria.criteria.matches(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a Comparator based on the given sort criteria.
     *
     * @param sortCriteria Criteria used to sort the list.
     * @return Comparator based on the sortCriteria.
     */
    public static Comparator<Internship> getComparator(SortCriteria sortCriteria) {
        switch (sortCriteria) {
        case Applied:
            return AppliedDate.getComparator();
        case Interview:
            return InterviewDateTime.getComparator();
        default:
            assert false; // Should not reach here.
            return null;
        }
    }

    @Override
    public String toString() {
        return criteria;
    }

}
