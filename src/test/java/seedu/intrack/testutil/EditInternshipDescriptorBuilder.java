package seedu.intrack.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.intrack.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {

    private EditInternshipDescriptor descriptor;

    public EditInternshipDescriptorBuilder() {
        descriptor = new EditInternshipDescriptor();
    }

    public EditInternshipDescriptorBuilder(EditInternshipDescriptor descriptor) {
        this.descriptor = new EditInternshipDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInternshipDescriptor} with fields containing {@code internship}'s details
     */
    public EditInternshipDescriptorBuilder(Internship internship) {
        descriptor = new EditInternshipDescriptor();
        descriptor.setName(internship.getName());
        descriptor.setPosition(internship.getPosition());
        descriptor.setPhone(internship.getPhone());
        descriptor.setEmail(internship.getEmail());
        descriptor.setWebsite(internship.getWebsite());
        descriptor.setTags(internship.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withPosition(String position) {
        descriptor.setPosition(new Position(position));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Website} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withWebsite(String website) {
        descriptor.setWebsite(new Website(website));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditInternshipDescriptor}
     * that we are building.
     */
    public EditInternshipDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditInternshipDescriptor build() {
        return descriptor;
    }
}
