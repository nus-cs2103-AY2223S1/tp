package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Client's id.
 */
public class ClientId {

    public static final String MESSAGE_CONSTRAINTS = "Client ID must exist in Client list";
    public static final String MESSAGE_INVALID = "Client ID must be an integer";
    private int clientId;

    /**
     * Construct's an client's id.
     *
     * @param id A valid client id.
     */
    public ClientId(int id) {
        requireNonNull(id);
        if (!isValidClientId(Integer.toString(id))) {
            throw new IllegalArgumentException();
        }

        this.clientId = id;
    }

    public int getIdInt() {
        return this.clientId;
    }

    /**
     * Checks if this ClientID is empty.
     * @return false since the ClientID is not empty.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Represents an Empty ClientID.
     */
    public static class EmptyClientId extends ClientId {
        public static final ClientId EMPTY_CLIENT_ID = new EmptyClientId();
        public static final int ID = Integer.MAX_VALUE;
        public EmptyClientId() {
            super(ID);
        }

        /**
         * Checks if this ClientID is empty.
         * @return true since the ClientID is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "";
        }
    }

    /**
     * Checks whether the client ID string is valid.
     * @param clientId
     * @return Boolean value representing the validity of the string.
     */
    public static boolean isValidClientId(String clientId) {
        try {
            int pid = Integer.parseInt(clientId);
            return pid > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public String uiRepresentation() {
        return "(#" + toString() + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientId // instanceof handles nulls
                && this.clientId == ((ClientId) other).clientId);
    }

    @Override
    public String toString() {
        return String.valueOf(this.clientId);
    }

}
