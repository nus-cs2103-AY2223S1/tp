package seedu.address.model.client;


import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.project.Project;

/**
 * Represents a Client associated with a project.
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
    private Set<Project> projects;

    /**
     * Constructs a client with inputs given by the user.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     */
    public Client(String name, String phone, String email) {
       //to be added
        this.name = new ClientName(name);
        this.phone = new ClientPhone(phone);
        this.email = new ClientEmail(email);
        this.id = ClientId.generateId(this.name);
        this.projects = new HashSet<>();
    }


    /**
     * Adds a project to the set of projects under this client.
     * @param project
     */
    public void addProject(Project project) {
        this.projects.add(project);
    }

    /**
     * Deletes a project from the set of projects under this client.
     * @param project
     */
    public void removeProject(Project project) {
        this.projects.remove(project);
    }

    /**
     * Returns a list of all the projects under this client.
     * @return String representing all projects under this client
     */
    public String listAllProjects() {
        return this.projects.toArray().toString();
    }

    /**
     * Returns the number of projects under this client.
     * @return int representing number of projects under this client.
     */
    public int getProjectCount() {
        return this.projects.size();
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

}
