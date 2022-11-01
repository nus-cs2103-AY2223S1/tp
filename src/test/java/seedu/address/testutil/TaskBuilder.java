package seedu.address.testutil;

import seedu.address.model.exam.Exam;
import seedu.address.model.module.DistinctModuleList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_DESCRIPTION = "test";
    public static final String DEFAULT_MODULE = "CS2030S";


    private TaskDescription taskDescription;
    private Module module;
    private Exam exam;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        Module m = new Module(new ModuleCode(DEFAULT_MODULE));
        DistinctModuleList list =  new DistinctModuleList();
        list.addModule(m);
        taskDescription = new TaskDescription(DEFAULT_DESCRIPTION);
        module = new Module(new ModuleCode(DEFAULT_MODULE));
    }



    public TaskBuilder(Task taskToCopy) {
        taskDescription = taskToCopy.getDescription();
        module = taskToCopy.getModule();
    }


    public TaskBuilder withName(String taskDescription) {
        this.taskDescription = new TaskDescription(taskDescription);
        return this;
    }

    public TaskBuilder withModule(String moduleCode) {
        this.module = new Module(new ModuleCode(moduleCode));
        return this;
    }
    public TaskBuilder withExam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public Task build() {
        return new Task(module, taskDescription);
    }

}
