package friday.testutil;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import friday.logic.commands.EditCommand;
import friday.logic.commands.EditCommand.EditStudentDescriptor;
import friday.model.student.Consultation;
import friday.model.student.MasteryCheck;
import friday.model.student.Name;
import friday.model.student.Student;
import friday.model.student.TelegramHandle;
import friday.model.tag.Tag;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditCommand.EditStudentDescriptor();
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
        descriptor.setTelegramHandle(student.getTelegramHandle());
        descriptor.setConsultation(student.getConsultation());
        descriptor.setMasteryCheck(student.getMasteryCheck());
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
     * Sets the {@code TelegramHandle} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setTelegramHandle(new TelegramHandle(phone));
        return this;
    }

    /**
     * Sets the {@code Consultation} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withConsultation(LocalDate desiredDate) {
        descriptor.setConsultation(new Consultation(desiredDate));
        return this;
    }

    /**
     * Sets the {@code MasteryCheck} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withMasteryCheck(LocalDate desiredDate) {
        descriptor.setMasteryCheck(new MasteryCheck(desiredDate));
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

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
