package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    private final String name;
    private final String repository;
    private final String deadline;
    private final String projectId;
    private final String pin;


    private final JsonAdaptedClient client;

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("name") String name, @JsonProperty("repository") String repository,
                              @JsonProperty("deadline") String deadline,
                              @JsonProperty("client") JsonAdaptedClient client,
                              @JsonProperty("projectId") String projectId,
                              @JsonProperty("pin") String pin) {
        this.name = name;
        this.repository = repository;
        this.deadline = deadline;
        this.client = client;
        this.projectId = projectId;
        this.pin = pin;
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        name = source.getProjectName().toString();
        repository = source.getRepository().toString();
        deadline = source.getDeadline().toString();
        client = new JsonAdaptedClient(source.getClient());
        projectId = source.getProjectId().toString();
        pin = String.valueOf(source.isPinned());
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final Name modelName = StorageUtil.readNameFromStorage(name, Project.class.getSimpleName());
        final Repository modelRepository = StorageUtil.readRepositoryFromStorage(repository);
        final Deadline modelDeadline = StorageUtil.readDeadlineFromStorage(deadline, Project.class.getSimpleName());
        final Client modelClient = StorageUtil.readClientFromStorage(client);
        final Pin modelPin = StorageUtil.readPinFromStorage(pin, Project.class.getSimpleName());
        final ProjectId modelProjectId = StorageUtil.readProjectIdFromStorage(projectId);
        final List<Issue> modelIssues = new ArrayList<>();
        return new Project(modelName, modelRepository, modelDeadline,
                modelClient, modelIssues, modelProjectId, modelPin);
    }

}
