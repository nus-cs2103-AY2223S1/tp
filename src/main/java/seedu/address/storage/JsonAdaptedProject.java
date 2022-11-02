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

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String name;
    private final String repository;
    private final String deadline;
    private final String projectId;
    private final String pin;


    private final JsonAdaptedClient client;


    private final List<JsonAdaptedIssue> issues = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("name") String name, @JsonProperty("repository") String repository,
                              @JsonProperty("deadline") String deadline,
                              @JsonProperty("client") JsonAdaptedClient client,
                              @JsonProperty("issues") List<JsonAdaptedIssue> issues,
                              @JsonProperty("projectId") String projectId,
                              @JsonProperty("pin") String pin) {
        this.name = name;
        this.repository = repository;
        this.deadline = deadline;
        this.client = client;
        this.projectId = projectId;
        if (issues != null) {
            this.issues.addAll(issues);
        }
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
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        Repository modelRepository;
        if (repository == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Repository.class.getSimpleName()));
        }
        if (repository.isEmpty()) {
            modelRepository = Repository.EmptyRepository.EMPTY_REPOSITORY;
        } else {
            if (!Repository.isValidRepository(repository)) {
                throw new IllegalValueException(Repository.MESSAGE_CONSTRAINTS);
            }
            modelRepository = new Repository(repository);
        }

        Deadline modelDeadline;
        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (deadline.isEmpty()) {
            modelDeadline = Deadline.EmptyDeadline.EMPTY_DEADLINE;
        } else {
            if (!Deadline.isValidDeadline(deadline)) {
                throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
            }
            modelDeadline = new Deadline(deadline);
        }

        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Project.class.getSimpleName()));
        }

        final Client modelClient = client.toModelType();

        final List<Issue> modelIssues = new ArrayList<>();

        Pin modelPin;
        if (pin == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Pin.class.getSimpleName()));
        }
        if (!Pin.isValidPin(pin)) {
            throw new IllegalValueException(Pin.MESSAGE_CONSTRAINTS);
        }
        modelPin = new Pin(Boolean.parseBoolean(pin));

        if (projectId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectId.class.getSimpleName()));
        }
        if (!ProjectId.isValidProjectId(projectId)) {
            throw new IllegalValueException(ProjectId.MESSAGE_CONSTRAINTS);
        }
        final ProjectId modelProjectId = new ProjectId(Integer.parseInt(projectId));

        assert modelProjectId.getIdInt() >= 0 : "Project ID should be positive";

        return new Project(modelName, modelRepository, modelDeadline,
                modelClient, modelIssues, modelProjectId, modelPin);
    }

}
