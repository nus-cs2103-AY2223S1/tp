package seedu.address.testutil;

import seedu.address.model.Criteria;

/**
 * Builds the {@code Criteria} using the criteria keyword given.
 */
public class CriteriaBuilder {
    public static final String DEFAULT_CRITERIA = "module";

    private String criteria;

    /**
     * Initialises the CriteriaBuilder with the default criteria.
     */
    public CriteriaBuilder() {
        criteria = DEFAULT_CRITERIA;
    }

    /**
     * Initialises the CriteriaBuilder with the criteria string from
     * Criteria object.
     *
     * @param criteria The Criteria object used.
     */
    public CriteriaBuilder(Criteria criteria) {
        this.criteria = criteria.getCriteria();
    }

    /**
     * Sets the criteria of the CriteriaBuilder with the criteria string given.
     *
     * @param criteria The criteria string given.
     * @return The CriteriaBuilder containing the new criteria.
     */
    public CriteriaBuilder withCriteria(String criteria) {
        this.criteria = criteria;
        return this;
    }

    public Criteria build() {
        return new Criteria(criteria);
    }
}
