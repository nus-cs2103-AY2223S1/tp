package seedu.address.model.person.client;

import static seedu.address.model.person.Name.DEFAULT_NAME;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.project.Project;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client associated with a project.
 */
public class Client extends Person {

    //Represents a Collection of projects that the client is responsible for
    private Set<Project> projects = new HashSet<>();

    /**
     * Constructs a client with name given by user and the rest of the fields set to default values.
     * @param name String representing name of the client
     */
    public Client(String name) {
        super(new Name(name), Phone.makeDefaultPhone(), Email.makeDefaultEmail(), Address.makeDefaultAddress(),
                Set.of(Tag.makeDefaultTag()));
    }

    /**
     * Represents an Empty Client for the project.
     */
    private static class EmptyClient extends Client {
        private static final Client EMPTY_CLIENT = new EmptyClient();
        private EmptyClient() {
            super(DEFAULT_NAME);
        }
    }

    /**
     * Constructs a client with name, phone number given by user and the rest of the fields set to default values.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     */
    public Client(String name, String phone) {
        super(new Name(name), new Phone(phone), Email.makeDefaultEmail(), Address.makeDefaultAddress(),
                Set.of(Tag.makeDefaultTag()));
    }

    /**
     * Constructs a client with name, phone number, email given by user and the rest of the fields set to default
     * values.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     */
    public Client(String name, String phone, String email) {
        super(new Name(name), new Phone(phone), new Email(email), Address.makeDefaultAddress(),
                Set.of(Tag.makeDefaultTag()));
    }

    /**
     * Constructs a client with name, phone number, email, address given by user and the rest of the fields set to
     * default values.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     * @param address String representing location of the client
     */
    public Client(String name, String phone, String email, String address) {
        super(new Name(name), new Phone(phone), new Email(email), new Address(address),
                Set.of(Tag.makeDefaultTag()));
    }

    /**
     * Constructs a client with inputs given by the user.
     * @param name String representing name of the client
     * @param phone String representing phone number of the client
     * @param email String representing email address of the client
     * @param address String representing location of the client
     * @param tag String representing the tag associated with the client
     */
    public Client(String name, String phone, String email, String address, String tag) {
        super(new Name(name), new Phone(phone), new Email(email), new Address(address),
                Set.of(new Tag(tag)));
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
