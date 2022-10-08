package seedu.address.model.client;

import seedu.address.model.person.Name;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

/**
 * Represents the unique ID that a client is identified by.
 */

public class ClientId {
    private String nameIdentifier;
    private UUID uuidIdentifier;
    private String identifier;

    /**
     * Constructs a unique Client Id when given a String representing client's name.
     * @param clientName
     */
    private ClientId(String clientName) {
        this.uuidIdentifier = UUID.randomUUID();
        this.nameIdentifier = clientName;
        this.identifier = nameIdentifier + uuidIdentifier.variant();
    }

    /**
     * Generates the Client Id depending on the Client Name given by the user.
     * @param clientName Name of the Client whose Id is generated
     * @return Id the unique Id of the Client
     */
    public ClientId generateId(Name clientName) {
        requireNonNull(clientName);
        String name = clientName.toString().replaceAll(" ", "").toLowerCase();
        return new ClientId(name);
    }

    /**
     * Returns the unique identifier generated
     * @return String representing the unique identifier generated
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns the String representation of the Client Id.
     * @return String representing the client's Id.
     */
    public String toString() {
        return "Client Id: " + this.identifier;
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
        } else if (other instanceof ClientId){
            ClientId otherId = (ClientId) other;
            return this.identifier.equals(otherId.identifier);
        } else {
            return false;
        }
    }
}
