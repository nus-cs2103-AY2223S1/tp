package seedu.studmap.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.studmap.logic.commands.EditCommand.EditCommandStudentEditor;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.GitName;
import seedu.studmap.model.student.Module;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentID;
import seedu.studmap.model.student.TeleHandle;
import seedu.studmap.model.tag.Tag;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditCommandStudentEditor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditCommandStudentEditor();
    }

    public EditStudentDescriptorBuilder(EditCommandStudentEditor descriptor) {
        this.descriptor = new EditCommandStudentEditor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditCommandStudentEditor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setModule(student.getModule());
        descriptor.setId(student.getId());
        descriptor.setGitName(student.getGitName());
        descriptor.setHandle(student.getTeleHandle());
        descriptor.setTags(student.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
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
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withModule(String module) {
        descriptor.setModule(new Module(module));
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withId(String id) {
        descriptor.setId(new StudentID(id));
        return this;
    }

    /**
     * Sets the {@code Git} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withGitName(String name) {
        descriptor.setGitName(new GitName(name));
        return this;
    }

    /**
     * Sets the {@code Handle} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withTeleHandle(String teleHandle) {
        descriptor.setHandle(new TeleHandle(teleHandle));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommandStudentEditor build() {
        return descriptor;
    }
}
