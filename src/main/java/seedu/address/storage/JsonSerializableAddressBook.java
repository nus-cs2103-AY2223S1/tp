package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    private static final String MESSAGE_DUPLICATE_PROJECT = "Projects list contains duplicate project(s).";
    private static final String MESSAGE_DUPLICATE_ISSUE = "Issues list contains duplicate issue(s).";

    private final List<JsonAdaptedProject> projects = new ArrayList<>();
    private final List<JsonAdaptedIssue> issues = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook}.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("projects") List<JsonAdaptedProject> projects,
                                       @JsonProperty("issues") List<JsonAdaptedIssue> issues) {
        this.projects.addAll(projects);
        this.issues.addAll(issues);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        projects.addAll(source.getProjectList().stream().map(JsonAdaptedProject::new).collect(Collectors.toList()));
        issues.addAll(source.getIssueList().stream().map(JsonAdaptedIssue::new).collect(Collectors.toList()));
    }

    /**
     * Converts this project book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (addressBook.hasProject(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECT);
            }
            addressBook.addProject(project);
            if (!project.getClient().isEmpty()) {

                if (!addressBook.hasClient(project.getClient())) {
                    project.getClient().addProjects(project);
                    addressBook.addClient(project.getClient());
                } else {
                    Client existingClient = addressBook.getClientById(project.getClient().getID());
                    existingClient.addProjects(project);
                }
            }
        }

        for (JsonAdaptedIssue jsonAdaptedIssue : issues) {
            Issue issue = jsonAdaptedIssue.toModelType(addressBook);
            if (addressBook.hasIssue(issue)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ISSUE);
            }
            addressBook.addIssue(issue);
        }

        addressBook.sortAllLists();

        return addressBook;
    }

}
