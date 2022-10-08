package seedu.address.model.client;


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
    private ClientEmail contact;

    //Represents the Client's unique ID
    private ClientId id;

    //Represents a Collection of projects that the client is responsible for
    private Set<Project> projects;

    /**
     * Constructs a client with inputs given by the user.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     * @param address String representing location of the client
     * @param tag String representing the tag associated with the client
     */
    public Client(String name, String phone, String email, String address, String tag) {
       //to be added
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

}
