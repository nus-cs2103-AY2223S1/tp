package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

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
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setAddress(student.getAddress());
        descriptor.setTags(student.getTags());
        descriptor.setId(student.getId());
        descriptor.setTelegramHandle(student.getTelegramHandle());
        descriptor.setStudentModuleInfo(student.getStudentModuleInfo());
        descriptor.setTeachingAssistantInfo(student.getTeachingAssistantInfo());
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
     * Sets the {@code Address} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
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

    /**
     * Sets the {@code StudentId} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudentId(String id) {
        descriptor.setId(new StudentId(id));
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withTelegramHandle(String handle) {
        descriptor.setTelegramHandle(new TelegramHandle(handle));
        return this;
    }

    /**
     * Parses the {@code Module codes} into a {@code Set<ModuleCode>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withStudentModuleInfo(String... moduleInfo) {
        Set<ModuleCode> moduleSet = Stream.of(moduleInfo).map(ModuleCode::new).collect(Collectors.toSet());
        descriptor.setStudentModuleInfo(moduleSet);
        return this;
    }

    /**
     * Parses the {@code Module codes} into a {@code Set<ModuleCode>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTeachingAssistantInfo(String... moduleInfo) {
        Set<ModuleCode> moduleSet = Stream.of(moduleInfo).map(ModuleCode::new).collect(Collectors.toSet());
        descriptor.setTeachingAssistantInfo(moduleSet);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}

