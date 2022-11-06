package seedu.address.storage;

import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.list.NotFoundException;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;

/**
 * Utility class for parsing objects from storage.
 */
public class StorageUtil {

    public static final String MISSING_PROJECT_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";
    public static final String MISSING_ISSUE_FIELD_MESSAGE_FORMAT = "Issue's %s field is missing!";
    public static final String MISSING_CLIENT_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";
    public static final String MESSAGE_DUPLICATE_PROJECT = "Projects list contains duplicate project(s).";
    public static final String MESSAGE_DUPLICATE_ISSUE = "Issues list contains duplicate issue(s).";
    public static final String MESSAGE_INVALID_CLIENT = "Clients list contains invalid client(s).";

    /**
     * Parses project name string from storage.
     */
    public static Name readNameFromStorage(String name, String className) throws IllegalValueException {
        String missingFieldMessage = "";
        switch (className) {
        case "Project":
            missingFieldMessage = MISSING_PROJECT_FIELD_MESSAGE_FORMAT;
            break;
        case "Client":
            missingFieldMessage = MISSING_CLIENT_FIELD_MESSAGE_FORMAT;
            break;
        default:
            assert false : "Entity type should not be accessing method";
            break;
        }
        if (name == null) {
            throw new IllegalValueException(String.format(missingFieldMessage, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    /**
     * Parses repository string from storage.
     */
    public static Repository readRepositoryFromStorage(String repository) throws IllegalValueException {
        if (repository == null) {
            throw new IllegalValueException(String.format(MISSING_PROJECT_FIELD_MESSAGE_FORMAT,
                    Repository.class.getSimpleName()));
        }
        if (repository.isEmpty()) {
            return Repository.EmptyRepository.EMPTY_REPOSITORY;
        } else {
            if (!Repository.isValidRepository(repository)) {
                throw new IllegalValueException(Repository.MESSAGE_CONSTRAINTS);
            }
            return new Repository(repository);
        }
    }

    /**
     * Parses deadline string from storage.
     */
    public static Deadline readDeadlineFromStorage(String deadline, String className) throws IllegalValueException {
        String missingFieldMessage = "";
        switch (className) {
        case "Project":
            missingFieldMessage = MISSING_PROJECT_FIELD_MESSAGE_FORMAT;
            break;
        case "Issue":
            missingFieldMessage = MISSING_ISSUE_FIELD_MESSAGE_FORMAT;
            break;
        default:
            assert false : "Entity type should not be accessing method";
            break;
        }
        if (deadline == null) {
            throw new IllegalValueException(String.format(missingFieldMessage,
                    Deadline.class.getSimpleName()));
        }
        if (deadline.isEmpty()) {
            return Deadline.EmptyDeadline.EMPTY_DEADLINE;
        } else {
            if (!Deadline.isValidDeadline(deadline)) {
                throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
            }
            return new Deadline(deadline);
        }
    }

    /**
     * Parses client json object from storage.
     */
    public static Client readClientFromStorage(JsonAdaptedClient client) throws IllegalValueException {
        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_PROJECT_FIELD_MESSAGE_FORMAT,
                    Project.class.getSimpleName()));
        }

        return client.toModelType();
    }

    /**
     * Parses pin string from storage.
     */
    public static Pin readPinFromStorage(String pin, String className) throws IllegalValueException {
        String missingFieldMessage = "";
        switch (className) {
        case "Project":
            missingFieldMessage = MISSING_PROJECT_FIELD_MESSAGE_FORMAT;
            break;
        case "Issue":
            missingFieldMessage = MISSING_ISSUE_FIELD_MESSAGE_FORMAT;
            break;
        case "Client":
            missingFieldMessage = MISSING_CLIENT_FIELD_MESSAGE_FORMAT;
            break;
        default:
            assert false : "Entity type does not exist";
            break;
        }
        if (pin == null) {
            throw new IllegalValueException(String.format(missingFieldMessage,
                    Pin.class.getSimpleName()));
        }
        if (!Pin.isValidPin(pin)) {
            throw new IllegalValueException(Pin.MESSAGE_CONSTRAINTS);
        }
        return new Pin(Boolean.parseBoolean(pin));
    }

    /**
     * Parses project ID string from storage.
     */
    public static ProjectId readProjectIdFromStorage(String projectId) throws IllegalValueException {
        if (projectId == null) {
            throw new IllegalValueException(String.format(MISSING_PROJECT_FIELD_MESSAGE_FORMAT,
                    ProjectId.class.getSimpleName()));
        }
        if (!ProjectId.isValidProjectId(projectId)) {
            throw new IllegalValueException(ProjectId.MESSAGE_CONSTRAINTS);
        }
        final ProjectId modelProjectId = new ProjectId(Integer.parseInt(projectId));

        assert modelProjectId.getIdInt() >= 0 : "Project ID should be positive";

        return modelProjectId;
    }

    /**
     * Parses title string from storage.
     */
    public static Title readTitleFromStorage(String title) throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_ISSUE_FIELD_MESSAGE_FORMAT,
                    Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(title);
    }

    /**
     * Parses urgency string from storage.
     */
    public static Urgency readUrgencyFromStorage(String urgency) throws IllegalValueException {
        if (urgency == null) {
            throw new IllegalValueException(String.format(MISSING_ISSUE_FIELD_MESSAGE_FORMAT,
                    Urgency.class.getSimpleName()));
        }

        if (!Urgency.isValidUrgencyString(urgency)) {
            throw new IllegalValueException(Urgency.MESSAGE_CONSTRAINTS);
        }

        return Urgency.valueOf(urgency);
    }

    /**
     * Parses status string from storage.
     */
    public static Status readStatusFromStorage(String status) throws IllegalValueException {
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_ISSUE_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(Boolean.valueOf(status));
    }

    /**
     * Parses project object from storage.
     */
    public static Project readProjectFromStorage(String project,
                                                 AddressBook addressBook) throws IllegalValueException {
        if (project == null) {
            throw new IllegalValueException(String.format(MISSING_ISSUE_FIELD_MESSAGE_FORMAT,
                    Project.class.getSimpleName()));
        }
        try {
            return HasIntegerIdentifier.getElementById(
                    addressBook.getProjectList(), Integer.parseInt(project));
        } catch (NotFoundException | NumberFormatException e) {
            throw new IllegalValueException(ProjectId.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses issue id string from storage.
     */
    public static IssueId readIssueIdFromStorage(String issueId) throws IllegalValueException {
        if (issueId == null) {
            throw new IllegalValueException(String.format(MISSING_ISSUE_FIELD_MESSAGE_FORMAT,
                    IssueId.class.getSimpleName()));
        }
        if (!IssueId.isValidIssueId(issueId)) {
            throw new IllegalValueException(IssueId.MESSAGE_CONSTRAINTS);
        }
        final IssueId modelIssueId = new IssueId(Integer.parseInt(issueId));

        assert modelIssueId.getIdInt() >= 0 : "Issue ID should be positive";
        return modelIssueId;
    }

    /**
     * Parses client mobile string from storage.
     */
    public static ClientMobile readMobileFromStorage(String mobile) throws IllegalValueException {
        if (mobile == null) {
            throw new IllegalValueException(String.format(MISSING_CLIENT_FIELD_MESSAGE_FORMAT,
                    ClientMobile.class.getSimpleName()));
        }

        if (mobile.isEmpty()) {
            return ClientMobile.EmptyClientMobile.EMPTY_MOBILE;
        } else {
            if (!ClientMobile.isValidClientMobile(mobile)) {
                throw new IllegalValueException(ClientMobile.MESSAGE_CONSTRAINTS);
            }
            return new ClientMobile(mobile);
        }
    }

    /**
     * Parses client email string from storage.
     */
    public static ClientEmail readEmailFromStorage(String email) throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_CLIENT_FIELD_MESSAGE_FORMAT,
                    ClientEmail.class.getSimpleName()));
        }

        if (email.isEmpty()) {
            return ClientEmail.EmptyEmail.EMPTY_EMAIL;
        } else {
            if (!ClientEmail.isValidEmail(email)) {
                throw new IllegalValueException(ClientEmail.MESSAGE_CONSTRAINTS);
            }
            return new ClientEmail(email);
        }
    }

    /**
     * Parses client id string from storage.
     */
    public static ClientId readClientIdFromStorage(String clientId) throws IllegalValueException {
        if (clientId == null) {
            throw new IllegalValueException(String.format(MISSING_CLIENT_FIELD_MESSAGE_FORMAT,
                    ClientId.class.getSimpleName()));
        }

        if (!ClientId.isValidClientId(clientId)) {
            throw new IllegalValueException(ClientId.MESSAGE_CONSTRAINTS);
        }

        final ClientId modelClientId = new ClientId(Integer.parseInt(clientId));

        assert modelClientId.getIdInt() >= 0 : "Client ID should be positive";
        return modelClientId;
    }

    /**
     * Parses issue list from storage.
     */
    public static void readIssueListFromStorage(List<JsonAdaptedIssue> issues,
                                                AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedIssue jsonAdaptedIssue : issues) {
            Issue issue = jsonAdaptedIssue.toModelType(addressBook);
            if (addressBook.hasIssue(issue) || addressBook.hasIssueId(issue.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ISSUE);
            }
            addressBook.addIssue(issue);
        }
    }

    /**
     * Parses project list from storage.
     */
    public static void readProjectListFromStorage(List<JsonAdaptedProject> projects,
                                                AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (addressBook.hasProject(project) || addressBook.hasProjectId(project.getId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECT);
            }
            addressBook.addProject(project);
            Client projectClient = project.getClient();
            if (projectClient.isEmpty()) {
                continue;
            }

            if (!addressBook.hasClient(projectClient) && !addressBook.hasClientId(projectClient.getId())) {
                projectClient.addProjects(project);
                addressBook.addClient(projectClient);
                continue;
            }

            if (addressBook.hasClientId(projectClient.getId())) {
                Client existingClient = addressBook.getClientById(projectClient.getId());
                if (!existingClient.hasSameDetails(projectClient)) {
                    throw new IllegalValueException(MESSAGE_INVALID_CLIENT);
                }
                project.setClient(existingClient);
                existingClient.addProjects(project);
            } else {
                throw new IllegalValueException(MESSAGE_INVALID_CLIENT);
            }
        }
    }
}
