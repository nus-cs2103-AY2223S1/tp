package seedu.address.testutil;

import seedu.address.model.commons.Criteria;

public class CriteriaBuilder {
    public static final String DEFAULT_CRITERIA = "module";

    private String criteria;

    public CriteriaBuilder() {
        criteria = DEFAULT_CRITERIA;
    }

    public CriteriaBuilder(Criteria criteria) {
        this.criteria = criteria.getCriteria();
    }

    public CriteriaBuilder withCriteria(String criteria) {
        this.criteria = criteria;
        return this;
    }

    public Criteria build() {
        return new Criteria(criteria);
    }
}
