package seedu.address.testutil;

import java.util.Arrays;

import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.task.Assignment;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.FormatDate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {
    private EditTaskCommand.EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
        descriptor.setTitle(task.getTitle());
        descriptor.setDescription(task.getDescription());
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            descriptor.setDate(deadlineTask.getDate());
        } else if (task instanceof Assignment) {
            Assignment assignmentTask = (Assignment) task;
            descriptor.setStudentsToAdd(assignmentTask.getStudents());
        }
    }

    /**
     * Sets the {@code Title} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new TaskTitle(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new TaskDescription(description));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDate(String date) {
        descriptor.setDate(new FormatDate(date));
        return this;
    }

    /**
     * Sets the {@code StudentsToAdd} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withAddStudents(String students) {
        descriptor.setStudentsToAdd(Arrays.asList(students.split(", ")));
        return this;
    }

    /**
     * Sets the {@code StudentsToDelete} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeleteStudents(String students) {
        descriptor.setStudentsToDelete(Arrays.asList(students.split(", ")));
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }

}
