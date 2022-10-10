package seedu.address.model.client;

import static java.util.Objects.requireNonNull;


/**
 * Represents a Client associated with a project. This is modelled after the AB3 Person.
 */
public class Client {

    //Represents the Client's name
    private ClientName name;

    //Represents the Client's Email
    private ClientEmail email;

    //Represents the Client's Phone
    private ClientPhone phone;

    //Represents the Client's unique ID
    private ClientId id;

    //Represents a Collection of projects that the client is responsible for
    private ClientProjectList projects;

    //Represents the client type
    private Type type;

    /**
     * Constructs a client with inputs given by the user.
     * @param name Name representing name of the client
     * @param phone Phone representing phone number of the client
     * @param email Email representing email address of the client
     * @param type Type representing the type of the client
     */
    public Client(ClientName name, ClientPhone phone, ClientEmail email, Type type) {
        //to be added
        requireNonNull(name);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.projects = new ClientProjectList();
        this.type = type;
    }

    /**
     * Returns the client name as is represented in the ClientName object.
     * @return String representing client's name.
     */
    public String getClientName() {
        return this.name.getFullNameRepresentation();
    }

    /**
     * Returns the client Id as is represented in the ClientId object.
     * @return String representing client's Id.
     */
    public String getClientId() {
        return this.id.getIdentifierRepresentation();
    }

    /**
     * Returns the client email as is represented in the ClientEmail object.
     * @return String representing client's email.
     */
    public String getClientEmail() {
        return this.email.getEmailRepresentation();
    }

    /**
     * Returns the client phone as is represented in the ClientPhone object.
     * @return String representing client's phone.
     */
    public String getClientPhone() {
        return this.phone.getPhoneRepresentation();
    }

    /**
     * Returns a list containing the projects the client is associated with.
     * @return String representing client's projects.
     */
    public String getClientProjectList() {
        return this.projects.listAllProjects();
    }

    /**
     * Returns the type of the client.
     * @return String representing client type
     */
    public String getClientType() {
        return this.type.toString();
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getClientName().equals(getClientName());
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
        } else if (other instanceof Client) {
            Client otherClient = (Client) other;
            boolean hasSameId = this.getClientId().equals(otherClient.getClientId());
            boolean hasSameName = this.getClientId().equals(otherClient.getClientName());
            boolean hasSameEmail = this.getClientId().equals(otherClient.getClientEmail());
            boolean hasSamePhone = this.getClientId().equals(otherClient.getClientPhone());
            return hasSameId && hasSameEmail && hasSamePhone && hasSameName;
        } else {
            return false;
        }
    }

}
