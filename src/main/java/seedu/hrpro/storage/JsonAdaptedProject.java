package seedu.hrpro.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.hrpro.commons.exceptions.IllegalValueException;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.project.Budget;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectName;
    private final String budget;
    private final String deadline;
    private final List<JsonAdaptedStaff> staffList = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName, @JsonProperty("budget") String budget,
            @JsonProperty("deadline") String deadline, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("stafflist") List<JsonAdaptedStaff> staffList) {
        this.projectName = projectName;
        this.budget = budget;
        this.deadline = deadline;
        if (staffList != null) {
            this.staffList.addAll(staffList);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getProjectName().fullName;
        budget = source.getBudget().value;
        deadline = source.getDeadline().deadline;
        staffList.addAll(source.getStaffList().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedStaff::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Tag> projectTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            projectTags.add(tag.toModelType());
        }

        if (projectName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectName.class.getSimpleName()));
        }
        if (!ProjectName.isValidProjectName(projectName)) {
            throw new IllegalValueException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        final ProjectName modelProjectName = new ProjectName(projectName);

        if (budget == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Budget.class.getSimpleName()));
        }
        if (!Budget.isValidBudget(budget)) {
            throw new IllegalValueException(Budget.MESSAGE_CONSTRAINTS);
        }
        final Budget modelBudget = new Budget(budget);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);
        final Set<Tag> modelTags = new HashSet<>(projectTags);
        Project project = new Project(modelProjectName, modelBudget, modelDeadline, modelTags);
        for (JsonAdaptedStaff staff : staffList) {
            project.getStaffList().add(staff.toModelType());
        }
        return project;
    }
}
