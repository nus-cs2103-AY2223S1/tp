package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.issue.Issue;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;
import seedu.address.model.tag.exceptions.IllegalValueException;




/**
 * Jackson-friendly version of {@link Project}.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "projectId")
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String name;
    private final String repository;
    private final String deadline;
    private final String projectId;


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
                              @JsonProperty("projectId") String projectId) {
        this.name = name;
        this.repository = repository;
        this.deadline = deadline;
        this.client = client;
        this.projectId = projectId;
        if (issues != null) {
            this.issues.addAll(issues);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        name = source.getProjectName().toString();
        repository = source.getRepository().toString();
        deadline = source.getDeadline().toString();
        client = new JsonAdaptedClient(source.getClient());
        projectId = source.getId().toString();
        //issues.addAll(source.getIssueList().stream()
        //        .map(JsonAdaptedIssue::new)
        //        .collect(Collectors.toList()));
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

        if (repository == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Repository.class.getSimpleName()));
        }
        if (!Repository.isValidRepository(repository)) {
            throw new IllegalValueException(Repository.MESSAGE_CONSTRAINTS);
        }
        final Repository modelRepository = new Repository(repository);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Project.class.getSimpleName()));
        }

        final Client modelClient = client.toModelType();
        final List<Issue> modelIssues = new ArrayList<>();
        //for (JsonAdaptedIssue issue : issues) {
        //    modelIssues.add(issue.toModelType());
        //}

        if (projectId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectId.class.getSimpleName()));
        }
        if (!ProjectId.isValidProjectId(projectId)) {
            throw new IllegalValueException(ProjectId.MESSAGE_CONSTRAINTS);
        }
        final ProjectId modelProjectId = new ProjectId(Integer.parseInt(projectId));

        return new Project(modelName, modelRepository, modelDeadline, modelClient, modelIssues, modelProjectId);
    }

}
