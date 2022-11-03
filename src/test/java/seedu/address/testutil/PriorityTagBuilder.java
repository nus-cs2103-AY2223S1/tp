package seedu.address.testutil;

import seedu.address.model.tag.PriorityTag;

/**
 * PriorityTagBuilder builds the priority tag
 */
public class PriorityTagBuilder {
    private static final String DEFAULT_STATUS = "HIGH";
    private String status;

    /**
     * Initialises the PriorityTagBuilder with the default status.
     */
    public PriorityTagBuilder() {
        status = DEFAULT_STATUS;
    }

    /**
     * Initialises the status of the PriorityTagBuilder with the given
     * priority tag.
     *
     * @param priorityTag The priority tag given.
     */
    public PriorityTagBuilder(PriorityTag priorityTag) {
        this.status = priorityTag.status;
    }

    /**
     * Sets the status of the priority tag to the provided status.
     *
     * @param status The new priority status provided.
     * @return PriorityTagBuilder containing the new priority status.
     */
    public PriorityTagBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public PriorityTag build() {
        return new PriorityTag(status);
    }
}
