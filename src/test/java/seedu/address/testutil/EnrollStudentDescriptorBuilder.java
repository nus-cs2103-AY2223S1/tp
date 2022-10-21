package seedu.address.testutil;

import seedu.address.logic.commands.student.StudentEnrollCommand.EditStudentDescriptor;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;



/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EnrollStudentDescriptorBuilder {

    private final EditStudentDescriptor descriptor;

    public EnrollStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EnrollStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EnrollStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setTutorialGroup(student.getTutorialGroup());

    }


    /**
     * Sets the {@code TutorialGroup} of the {@code EditPersonDescriptor} that we are building.
     */
    public EnrollStudentDescriptorBuilder withTutorialGroup(String tutorialGroup) {
        descriptor.setTutorialGroup(new TutorialGroup(tutorialGroup));
        return this;
    }


    public EditStudentDescriptor build() {
        return descriptor;
    }
}
