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

    public ClientId getClientId() {
        return this.clientId;
    }

    /**
     * Returns the client name as is represented in the Name object.
     * @return String representing client's name.
     */
    public seedu.address.model.Name getClientName() {
        return this.name;
    }

    /**
     * Returns the client email as is represented in the ClientEmail object.
     * @return String representing client's email.
     */
    public ClientEmail getClientEmail() {
        return this.email;
    }

    /**
     * Returns the client phone as is represented in the ClientPhone object.
     * @return String representing client's phone.
     */
    public ClientPhone getClientPhone() {
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
            boolean hasSameName = this.getClientName().equals(otherClient.getClientName());
            boolean hasSameEmail = this.getClientEmail().equals(otherClient.getClientEmail());
            boolean hasSamePhone = this.getClientPhone().equals(otherClient.getClientPhone());
            return hasSameId && hasSameEmail && hasSamePhone && hasSameName;
        } else {
            return false;
        }
    }


    //    /**
    //     * Returns a list containing the projects the client is associated with.
    //     * @return String representing client's projects.
    //     */
    //    public String getClientProjectList() {
    //        return this.projects.listAllProjects();
    //    }
    //
    //    /**
    //     * Returns the type of the client.
    //     * @return String representing client type
    //     */
    //    public String getClientType() {
    //        return this.type.toString();
    //    }
}
