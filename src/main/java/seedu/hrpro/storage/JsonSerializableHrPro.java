package seedu.hrpro.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.hrpro.commons.exceptions.IllegalValueException;
import seedu.hrpro.model.HrPro;
import seedu.hrpro.model.ReadOnlyHrPro;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.task.Task;

/**
 * An Immutable HrPro that is serializable to JSON format.
 */
@JsonRootName(value = "hrpro")
class JsonSerializableHrPro {

    public static final String MESSAGE_DUPLICATE_PROJECT = "Projects list contains duplicate project(s).";

    public static final String MESSAGE_DUPLICATE_STAFF = "Staff list contains duplicate staff(s).";

    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";

    private final List<JsonAdaptedProject> projects = new ArrayList<>();

    private final List<JsonAdaptedStaff> staff = new ArrayList<>();

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHrPro} with the given projects and tasks.
     */
    @JsonCreator
    public JsonSerializableHrPro(@JsonProperty("projects") List<JsonAdaptedProject> projects,
                                       @JsonProperty("staff") List<JsonAdaptedStaff> staff,
                                       @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.projects.addAll(projects);
        this.staff.addAll(staff);
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyHrPro} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHrPro}.
     */
    public JsonSerializableHrPro(ReadOnlyHrPro source) {
        projects.addAll(source.getProjectList().stream().map(JsonAdaptedProject::new).collect(Collectors.toList()));
        staff.addAll(source.getStaffList().stream().map(JsonAdaptedStaff::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this hr pro into the model's {@code HrPro} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HrPro toModelType() throws IllegalValueException {
        HrPro hrPro = new HrPro();
        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (hrPro.hasProject(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECT);
            }
            hrPro.addProject(project);
        }

        for (JsonAdaptedStaff jsonAdaptedStaff : staff) {
            Staff staff = jsonAdaptedStaff.toModelType();
            if (hrPro.hasStaff(staff)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STAFF);
            }
            hrPro.addStaff(staff);
        }

        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (hrPro.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            hrPro.addTask(task);
        }
        return hrPro;
    }

}
