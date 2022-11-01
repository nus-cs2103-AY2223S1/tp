package seedu.address.testutil;

import seedu.address.model.tag.PriorityTag;

public class PriorityTagBuilder {
    public static final String DEFAULT_STATUS = "HIGH";
    private final String status;

    public PriorityTagBuilder() {
        status = DEFAULT_STATUS;
    }

    public PriorityTagBuilder(String status) {
        this.status = status;
    }

    public PriorityTag build() {
        return new PriorityTag(status);
    }
}
