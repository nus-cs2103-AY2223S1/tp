package seedu.address.testutil;

import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;

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
        descriptor.setTelegram(student.getTelegram());
        descriptor.setEmail(student.getEmail());
        descriptor.setAttendance(student.getAttendance());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new StuName(name));
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withTelegram(String telegram) {
        descriptor.setTelegram(new Telegram(telegram));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new StuEmail(email));
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAttendance(String attendance) {
        descriptor.setAttendance(new Attendance(attendance));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}

