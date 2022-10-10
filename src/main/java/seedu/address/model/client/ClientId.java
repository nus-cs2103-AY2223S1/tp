package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

/**
 * Represents the unique ID that a client is identified by.
 */

public class ClientId {
    private String nameIdentifier;
    private String identifier;

    /**
     * Constructs a unique Client Id when given a String representing client's name.
     * @param clientName
     */
    private ClientId(String clientName, String inputId) {
        this.nameIdentifier = clientName;
        this.identifier = nameIdentifier + inputId;
    }

    /**
     * Generates the Client Id depending on the Client Name given by the user.
     * @param clientName Name of the Client whose Id is generated
     * @param id String representing an identifier
     * @return Id the unique Id of the Client
     */
    public static ClientId generateId(ClientName clientName, String id) {
        requireNonNull(clientName);
        String name = clientName.getFullNameRepresentation().replaceAll(" ", "").toLowerCase();
        return new ClientId(name, id);
    }

    /**
     * Returns the unique identifier generated
     * @return String representing the unique identifier generated
     */
    public String getIdentifierRepresentation() {
        return this.identifier;
    }

    /**
     * Returns the String representation of the Client Id.
     * @return String representing the client's Id.
     */
    public String toString() {
        return this.identifier;
    }


    /**
     * Checks if an object equals this.
     * @param other Object to be checked
     * @return boolean true if this is equal to other and false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ClientId) {
            ClientId otherId = (ClientId) other;
            return this.identifier.equals(otherId.identifier);
        } else {
            return false;
        }
    }
}
