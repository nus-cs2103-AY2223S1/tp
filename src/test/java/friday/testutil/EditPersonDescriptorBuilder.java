package friday.testutil;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import friday.logic.commands.EditCommand.EditPersonDescriptor;
import friday.model.student.Consultation;
import friday.model.student.MasteryCheck;
import friday.model.student.Name;
import friday.model.student.Student;
import friday.model.student.TelegramHandle;
import friday.model.tag.Tag;

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
        descriptor.setTelegramHandle(student.getTelegramHandle());
        descriptor.setConsultation(student.getConsultation());
        descriptor.setMasteryCheck(student.getMasteryCheck());
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
     * Sets the {@code TelegramHandle} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setTelegramHandle(new TelegramHandle(phone));
        return this;
    }

    /**
     * Sets the {@code Consultation} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withConsultation(LocalDate desiredDate) {
        descriptor.setConsultation(new Consultation(desiredDate));
        return this;
    }

    /**
     * Sets the {@code MasteryCheck} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMasteryCheck(LocalDate desiredDate) {
        descriptor.setMasteryCheck(new MasteryCheck(desiredDate));
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

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
