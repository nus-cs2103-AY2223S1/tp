package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Issue's id.
 */
public class Id {

    private int issueId;

    /**
     * Construct's an issue's id.
     *
     * @param id A valid issue id.
     */
    public Id(int id) {
        requireNonNull(id);
        this.issueId = id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.issueId);
    }

}
