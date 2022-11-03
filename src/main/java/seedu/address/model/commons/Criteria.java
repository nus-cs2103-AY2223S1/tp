package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Criteria class which represents the criteria that the user is sorting
 * the task list by.
 */
public class Criteria {
    public static final String CRITERIA_CONSTRAINTS =
            "The sorting criteria should be either priority, deadline, module or description";
    private final String criteria;
    /**
     * The constructor of the criteria class. Sets the criteria which
     * will be used for sorting.
     *
     * @param criteria The criteria which is used for sorting.
     */
    public Criteria(String criteria) {
        requireNonNull(criteria);
        checkArgument(isValidCriteria(criteria), CRITERIA_CONSTRAINTS);
        this.criteria = criteria;
    }

    /**
     * Checks whether the criteria given by the user is valid.
     *
     * @param criteria The criteria that is being checked for validity.
     * @return true if the criteria is valid; else return false.
     */
    public static boolean isValidCriteria(String criteria) {
        requireNonNull(criteria);
        return criteria.equalsIgnoreCase("priority")
                || criteria.equalsIgnoreCase("deadline")
                || criteria.equalsIgnoreCase("module")
                || criteria.equalsIgnoreCase("description");
    }

    public String getCriteria() {
        return criteria;
    }

    @Override
    public boolean equals(Object otherCriteria) {
        return otherCriteria instanceof Criteria &&
                criteria.equalsIgnoreCase(((Criteria) otherCriteria).criteria);
    }
}
