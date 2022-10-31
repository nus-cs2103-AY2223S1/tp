package seedu.classify.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.classify.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;
import seedu.classify.model.student.Student;

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
        descriptor.setStudentName(student.getStudentName());
        descriptor.setClassName(student.getClassName());
        descriptor.setParentName(student.getParentName());
        descriptor.setId(student.getId());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setExams(student.getExams());
    }

    /**
     * Sets the student {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudentName(String name) {
        descriptor.setStudentName(new Name(name));
        return this;
    }

    /**
     * Sets the student {@code Class} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withClassName(String name) {
        descriptor.setClassName(new Class(name));
        return this;
    }

    /**
     * Sets the parent {@code ParentName} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withParentName(String name) {
        descriptor.setParentName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withId(String id) {
        descriptor.setId(new Id(id));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Parses the {@code exams} into a {@code Set<Exam>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withExams(String... exams) {
        Set<Exam> examSet = Stream.of(exams).map(Exam::new).collect(Collectors.toSet());
        descriptor.setExams(examSet);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
