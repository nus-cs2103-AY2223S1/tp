package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Client's id.
 */
public class ClientId {

    public static final String MESSAGE_CONSTRAINTS = "Client ID must be a valid integer";
    private int clientId;

    /**
     * Construct's an client's id.
     *
     * @param id A valid client id.
     */
    public ClientId(int id) {
        requireNonNull(id);
        this.clientId = id;
    }

    public static boolean isValidClientId(String clientId) {
        try {
            Integer.parseInt(clientId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String uiRepresentation() {
        return "(#" + toString() + ")";
    }
    @Override
    public String toString() {
        return String.valueOf(this.clientId);
    }

}
