package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * LinkStatus class represents if a task is linked or unlinked.
 */
public class LinkStatus {
    public static final String RESPONSE_CONSTRAINTS = "Link criteria should be indicated as y or n";
    private static final String LINKED_STRING = "linked";
    private static final String UNLINKED_STRING = "unlinked";

    public static final LinkStatus LINKED = new LinkStatus(LINKED_STRING);
    public static final LinkStatus UNLINKED = new LinkStatus(UNLINKED_STRING);

    public static final String STATUS_CONSTRAINTS = "The link status should be " + LINKED + " or " + UNLINKED;

    public final String status;

    private LinkStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidLinkStatus(status), STATUS_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Constructs a {@code LinkStatus}.
     * It is either linked or unlinked.
     *
     * @param status A valid status.
     */
    public static LinkStatus of(String status) {
        if (status.equals(LINKED_STRING)) {
            return LINKED;
        } else {
            return UNLINKED;
        }
    }

    /**
     * Returns true if the given string is a valid status,
     * false otherwise.
     *
     * @param status string representation of a link status.
     * @return true if the given string is a valid link status, false otherwise.
     */
    public static boolean isValidLinkStatus(String status) {
        return status.equals(LINKED_STRING) || status.equals(UNLINKED_STRING);
    }

    /**
     * Returns true if task status is "linked",
     * false otherwise.
     *
     * @return true if task link status is "linked".
     */
    public boolean isLinked() {
        return status.equals(LINKED_STRING);
    }

    /**
     * Returns a {@code LinkStatus} object based on user response.
     *
     * @param response string of "y" or "n".
     * @return A valid link status/.
     */
    public static LinkStatus getLinkStatusFromResponse(String response) {
        if (response.equals("y")) {
            return LINKED;
        } else {
            return UNLINKED;
        }
    }

    @Override
    public boolean equals(Object otherLinkStatus) {
        return otherLinkStatus == this || (otherLinkStatus instanceof LinkStatus
                && status.equalsIgnoreCase((((LinkStatus) otherLinkStatus).status)));
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

    @Override
    public String toString() {
        return status;
    }
}
