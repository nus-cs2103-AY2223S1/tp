package seedu.modquik.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.modquik.logic.commands.student.EditStudentCommand.EditStudentDescriptor;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.student.Attendance;
import seedu.modquik.model.student.Email;
import seedu.modquik.model.student.Grade;
import seedu.modquik.model.student.Name;
import seedu.modquik.model.student.Participation;
import seedu.modquik.model.student.Phone;
import seedu.modquik.model.student.Student;
import seedu.modquik.model.student.StudentId;
import seedu.modquik.model.tag.Tag;
import seedu.modquik.model.tutorial.TutorialName;

/**
 * A utility class to help with building EditPersonDescriptor objects.
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
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setId(student.getId());
        descriptor.setTutorialModule(student.getModuleCode());
        descriptor.setTutorialName(student.getTutorialName());
        descriptor.setTelegram(student.getTelegram());
        descriptor.setAttendance(student.getAttendance());
        descriptor.setParticipation(student.getParticipation());
        descriptor.setGrade(student.getGrade());
        descriptor.setTags(student.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code ID} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withId(String id) {
        descriptor.setId(new StudentId(id));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withModule(String module) {
        descriptor.setTutorialModule(new ModuleCode(module));
        return this;
    }

    /**
     * Sets the {@code TutorialName} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withTutorial(String tutorial) {
        descriptor.setTutorialName(new TutorialName(tutorial));
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAttendance(String attendance) {
        descriptor.setAttendance(new Attendance(attendance));
        return this;
    }

    /**
     * Sets the {@code Participation} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withParticipation(String participation) {
        descriptor.setParticipation(new Participation(participation));
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withGrade(String grade) {
        descriptor.setGrade(new Grade(grade));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
