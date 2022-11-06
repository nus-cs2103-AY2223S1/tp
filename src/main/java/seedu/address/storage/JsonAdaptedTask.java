package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskStatus;

/**
 * This class represents a Jackson friendly version of the Task.
 */
public class JsonAdaptedTask {
    public static final String MISSING_TASK_DESCRIPTION = "Description of the task is not present";
    public static final String MISSING_TASK_MODULE = "Module of the task is not present";
    public static final String MISSING_TASK_STATUS = "Completion status of the task is not present";
    public static final String WRONG_EXAM_FORMAT = "The task must either have both exam description"
            + " and exam date or have no exam description and exam date.";
    private final String taskDescription;
    private final String moduleCode;
    private final String status;
    private final String priority;
    private final String deadline;
    private final String examDescription;
    private final String examDate;

    /**
     * Builds a {@code JsonAdaptedTask} with the description and module code.
     *
     * @param taskDescription The description of the task.
     * @param moduleCode The module code of the task.
     * @param status The completion status of the task.
     * @param priority The tag which carries the priority status of the task.
     * @param deadline The deadline to complete the task.
     * @param examDate The date of the exam.
     * @param examDescription The description of the exam.
     */
    public JsonAdaptedTask(@JsonProperty("taskDescription") String taskDescription,
            @JsonProperty("modCode") String moduleCode, @JsonProperty("status") String status,
            @JsonProperty("priority") String priority, @JsonProperty("deadline") String deadline,
            @JsonProperty("examDate") String examDate,
            @JsonProperty("examDescription") String examDescription) {
        this.taskDescription = taskDescription;
        this.moduleCode = moduleCode;
        this.status = status;
        this.priority = priority;
        this.deadline = deadline;
        this.examDate = examDate;
        this.examDescription = examDescription;
    }

    /**
     * Converts an existing task into {@code JsonAdaptedTask} object
     *
     * @param task The task object being converted.
     */
    public JsonAdaptedTask(Task task) {
        taskDescription = task.getDescription().description;
        moduleCode = task.getModule().getModuleCode().moduleCode;
        status = task.getStatus().status;
        priority = task.getPriorityTag() == null ? null : task.getPriorityTag().status;
        deadline = task.getDeadlineTag() == null ? null : task.getDeadlineTag().toString();
        examDate = task.getExam() == null ? null : task.getExam().getExamDate().examDate;
        examDescription = task.getExam() == null ? null : task.getExam().getDescription().description;
    }

    /**
     * Converts this Jackson-friendly task object into a Task object for the model.
     *
     * @return The Task object which has been created.
     * @throws IllegalValueException if the task has invalid fields.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskDescription == null) {
            throw new IllegalValueException(MISSING_TASK_DESCRIPTION);
        }
        if (moduleCode == null) {
            throw new IllegalValueException(MISSING_TASK_MODULE);
        }
        if (status == null) {
            throw new IllegalValueException(MISSING_TASK_STATUS);
        }
        if (!TaskDescription.isValidDescription(taskDescription)) {
            throw new IllegalValueException(TaskDescription.DESCRIPTION_CONSTRAINTS);
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MODULE_CODE_CONSTRAINTS);
        }
        if (!TaskStatus.isValidStatus(status)) {
            throw new IllegalValueException(TaskStatus.STATUS_CONSTRAINTS);
        }
        if (priority != null && !PriorityTag.isValidTag(priority)) {
            throw new IllegalValueException(PriorityTag.PRIORITY_TAG_CONSTRAINTS);
        }

        final LocalDate date;
        if (deadline != null && !DeadlineTag.checkDateFormat(deadline)) {
            throw new IllegalValueException(DeadlineTag.DEADLINE_TAG_FORMAT_CONSTRAINTS);
        }
        try {
            if (deadline != null) {
                //@@author dlimyy-reused
                //Reused from https://stackoverflow.com/questions/32823368/
                //with minor modifications.
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                        .withResolverStyle(ResolverStyle.STRICT);
                //@@author
                date = LocalDate.parse(deadline, dtf);
            } else {
                date = null;
            }
        } catch (DateTimeParseException dtp) {
            throw new IllegalValueException(DeadlineTag.DEADLINE_TAG_INVALID_DATE);
        }
        if (!((examDate == null && examDescription == null)
                || (examDate != null && examDescription != null))) {
            throw new IllegalValueException(WRONG_EXAM_FORMAT);
        }

        if (examDate != null && !ExamDate.isValidDateFormat(examDate)) {
            throw new IllegalValueException(ExamDate.DATE_CONSTRAINTS);
        }
        if (examDescription != null && !ExamDescription.isValidDescription(examDescription)) {
            throw new IllegalValueException(ExamDescription.DESCRIPTION_CONSTRAINTS);
        }
        final ExamDescription descriptionOfExam = examDescription == null
                ? null : new ExamDescription(this.examDescription);
        final ExamDate dateOfExam = examDate == null ? null : new ExamDate(examDate);

        final DeadlineTag deadlineTag = date == null ? null : new DeadlineTag(date);
        final TaskDescription descriptionOfTask = new TaskDescription(taskDescription);
        final ModuleCode modCode = new ModuleCode(moduleCode);
        final Module module = new Module(modCode);
        final Exam exam = examDate == null ? null
                : new Exam(module, descriptionOfExam, dateOfExam);
        final TaskStatus taskStatus = TaskStatus.of(status);
        final PriorityTag priorityTag = priority == null ? null : new PriorityTag(priority);
        return new Task(module, descriptionOfTask, taskStatus, priorityTag, deadlineTag, exam);
    }

}
