package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.student.EditStudentCommand.EditPersonDescriptor;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialName;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Student student) {
        descriptor = new EditPersonDescriptor();
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
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code ID} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withId(String id) {
        descriptor.setId(new StudentId(id));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withModule(String module) {
        descriptor.setTutorialModule(new ModuleCode(module));
        return this;
    }

    /**
     * Sets the {@code TutorialName} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTutorial(String tutorial) {
        descriptor.setTutorialName(new TutorialName(tutorial));
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAttendance(String attendance) {
        descriptor.setAttendance(new Attendance(attendance));
        return this;
    }

    /**
     * Sets the {@code Participation} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withParticipation(String participation) {
        descriptor.setParticipation(new Participation(participation));
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGrade(String grade) {
        descriptor.setGrade(new Grade(grade));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
