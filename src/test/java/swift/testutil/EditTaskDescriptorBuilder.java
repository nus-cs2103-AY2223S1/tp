package swift.testutil;

import swift.commons.core.index.Index;
import swift.logic.commands.EditTaskCommand.EditTaskDescriptor;
import swift.model.task.Task;
import swift.model.task.TaskName;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setTaskName(task.getTaskName());
        descriptor.setContactIndex(task.getContactIndex());
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setTaskName(new TaskName(name));
        return this;
    }

    /**
     * Sets the {@code Contact Index} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withContactIndex(int contactIndex) {
        descriptor.setContactIndex(Index.fromZeroBased(contactIndex));
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
