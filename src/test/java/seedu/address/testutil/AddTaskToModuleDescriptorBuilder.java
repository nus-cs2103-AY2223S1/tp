package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_A;

import seedu.address.logic.commands.AddTaskCommand.AddTaskToModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.module.task.Task;

/**
 * A utility class to help with building {@code AddTaskToModuleDescriptor} objects.
 */
public class AddTaskToModuleDescriptorBuilder {
    public static final Task VALID_TASK = new Task(VALID_TASK_A);

    private AddTaskToModuleDescriptor descriptor;

    public AddTaskToModuleDescriptorBuilder(AddTaskToModuleDescriptor descriptor) {
        this.descriptor = new AddTaskToModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code AddTaskToModuleDescriptor} with the given
     * {@code module} as the target module.
     */
    public AddTaskToModuleDescriptorBuilder(Module module) {
        descriptor = new AddTaskToModuleDescriptor();
        descriptor.setModuleCodeOfModuleToAddTaskTo(module.getModuleCode());
        descriptor.setNewTask(VALID_TASK);
    }

    /**
     * Returns an {@code AddTaskToModuleDescriptor} with the given
     * {@code module} as the target module and create a new task with
     * the description given.
     */
    public AddTaskToModuleDescriptorBuilder(Module module, String taskDescription) {
        descriptor = new AddTaskToModuleDescriptor();
        descriptor.setModuleCodeOfModuleToAddTaskTo(module.getModuleCode());
        descriptor.setNewTask(new Task(taskDescription));
    }

    public AddTaskToModuleDescriptor build() {
        return descriptor;
    }
}
