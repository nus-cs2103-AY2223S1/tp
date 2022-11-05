package seedu.hrpro.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.hrpro.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.project.Budget;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.tag.Tag;

/**
 * A utility class to help with building EditProjectDescriptor objects.
 */
public class EditProjectDescriptorBuilder {

    private EditProjectDescriptor descriptor;

    public EditProjectDescriptorBuilder() {
        descriptor = new EditProjectDescriptor();
    }

    public EditProjectDescriptorBuilder(EditProjectDescriptor descriptor) {
        this.descriptor = new EditProjectDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProjectDescriptor} with fields containing {@code project}'s details
     */
    public EditProjectDescriptorBuilder(Project project) {
        descriptor = new EditProjectDescriptor();
        descriptor.setProjectName(project.getProjectName());
        descriptor.setBudget(project.getBudget());
        descriptor.setDeadline(project.getDeadline());
        descriptor.setTags(project.getTags());
    }

    /**
     * Sets the {@code ProjectName} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withName(String name) {
        descriptor.setProjectName(new ProjectName(name));
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withBudget(String budget) {
        descriptor.setBudget(new Budget(budget));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditProjectDescriptor}
     * that we are building.
     */
    public EditProjectDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditProjectDescriptor build() {
        return descriptor;
    }
}
