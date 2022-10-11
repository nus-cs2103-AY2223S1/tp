package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.model.Name;
import seedu.address.model.project.Project;

/**
 * Stub class for Client.
 */
public class Client {
    //Represents the Client's name
    private Name name;

    //Represents the Client's ClientEmail
    private ClientEmail email;

    //Represents the Client's ClientPhone
    private ClientPhone phone;

    //Represents a Collection of projects that the client is responsible for
    private List<Project> projects;

    private ClientId clientId;

    /**
     * Constructs a client with inputs given by the user.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     */
    public Client(Name name, ClientPhone phone, ClientEmail email, List<Project> projects, ClientId clientId) {
        requireAllNonNull(name, phone, email, projects, clientId);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.projects = projects;
        this.clientId = clientId;
    }

    public ClientId getId() {
        return this.clientId;
    }

    /**
     * Returns the client name as is represented in the ClientName object.
     * @return String representing client's name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Returns the client email as is represented in the ClientEmail object.
     * @return String representing client's email.
     */
    public ClientEmail getEmail() {
        return this.email;
    }

    /**
     * Returns the client phone as is represented in the ClientPhone object.
     * @return String representing client's phone.
     */
    public ClientPhone getPhone() {
        return this.phone;
    }

    public List<Project> getProjects() {
        return projects;
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
                && otherClient.getName().equals(getName());
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
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getId().equals(getId())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getProjects().equals(getProjects());
    }
}
