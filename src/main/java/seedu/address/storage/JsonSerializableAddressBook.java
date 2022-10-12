package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.client.Person;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;
import seedu.address.model.tag.exceptions.IllegalValueException;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate client(s).";
    private static final String MESSAGE_DUPLICATE_PROJECT = "Projects list contains duplicate project(s).";
    private static final String MESSAGE_DUPLICATE_ISSUE = "Issues list contains duplicate issue(s).";
    private static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedProject> projects = new ArrayList<>();
    private final List<JsonAdaptedIssue> issues = new ArrayList<>();
    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private String projectCount;
    private String issueCount;
    private String clientCount;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("projects") List<JsonAdaptedProject> projects,
                                       @JsonProperty("issues") List<JsonAdaptedIssue> issues,
                                       @JsonProperty("clients") List<JsonAdaptedClient> clients,
                                       @JsonProperty("projectCount") String projectCount,
                                       @JsonProperty("issueCount") String issueCount,
                                       @JsonProperty("clientCount") String clientCount) {
        this.persons.addAll(persons);
        this.projects.addAll(projects);
        this.issues.addAll(issues);
        this.clients.addAll(clients);
        this.projectCount = projectCount;
        this.issueCount = issueCount;
        this.clientCount = clientCount;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        projects.addAll(source.getProjectList().stream().map(JsonAdaptedProject::new).collect(Collectors.toList()));
        issues.addAll(source.getIssueList().stream().map(JsonAdaptedIssue::new).collect(Collectors.toList()));
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
        projectCount = source.getProjectCount();
        issueCount = source.getIssueCount();
        clientCount = source.getClientCount();
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        addressBook.setCounts(clientCount, projectCount, issueCount);
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (addressBook.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            addressBook.addClient(client);
        }

        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (addressBook.hasProject(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECT);
            }
            addressBook.addProject(project);
        }

        for (JsonAdaptedIssue jsonAdaptedIssue : issues) {
            Issue issue = jsonAdaptedIssue.toModelType();
            if (addressBook.hasIssue(issue)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ISSUE);
            }
            addressBook.addIssue(issue);
        }
        return addressBook;
    }

}
