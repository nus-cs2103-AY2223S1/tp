package seedu.address.testutil;

import seedu.address.logic.commands.grade.GradeEditCommand;
import seedu.address.model.grade.Grade;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditGradeDescriptorBuilder {

    private final GradeEditCommand.EditGradeDescriptor descriptor;

    public EditGradeDescriptorBuilder() {
        descriptor = new GradeEditCommand.EditGradeDescriptor();
    }

    public EditGradeDescriptorBuilder(GradeEditCommand.EditGradeDescriptor descriptor) {
        this.descriptor = new GradeEditCommand.EditGradeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditGradeDescriptor} with fields containing {@code grade}'s details
     */
    public EditGradeDescriptorBuilder(Grade grade) {
        descriptor = new GradeEditCommand.EditGradeDescriptor();
        descriptor.setGrade(grade);
    }

    /**
     * Sets the {@code GradeState} of the {@code EditGradeDescriptor} that we are building.
     */
    public EditGradeDescriptorBuilder withGradeState(Grade grade) {
        descriptor.setGrade(grade);
        return this;
    }

    public GradeEditCommand.EditGradeDescriptor build() {
        return descriptor;
    }
}
