package jarvis.testutil;

import jarvis.logic.commands.EditStudentCommand.EditStudentDescriptor;
import jarvis.model.MatricNum;
import jarvis.model.Student;
import jarvis.model.StudentName;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setMatricNum(student.getMatricNum());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new StudentName(name));
        return this;
    }

    /**
     * Sets the {@code MatricNum} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withMatricNum(String matricNum) {
        descriptor.setMatricNum(new MatricNum(matricNum));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
