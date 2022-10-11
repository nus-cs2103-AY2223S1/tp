package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {

    private EditCommand.EditInternshipDescriptor descriptor;

    public EditInternshipDescriptorBuilder() {
        descriptor = new EditCommand.EditInternshipDescriptor();
    }

    public EditInternshipDescriptorBuilder(EditCommand.EditInternshipDescriptor descriptor) {
        this.descriptor = new EditCommand.EditInternshipDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInternshipDescriptor} with fields containing {@code internship}'s details
     */
    public EditInternshipDescriptorBuilder(Internship internship) {
        descriptor = new EditCommand.EditInternshipDescriptor();
        descriptor.setName(internship.getCompany());
        descriptor.setLink(internship.getLink());
        descriptor.setDescription(internship.getDescription());
        descriptor.setAppliedDate(internship.getAppliedDate());
        descriptor.setTags(internship.getTags());
    }

    /**
     * Sets the {@code Company} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withName(String name) {
        descriptor.setName(new Company(name));
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withPhone(String phone) {
        descriptor.setLink(new Link(phone));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withEmail(String email) {
        descriptor.setDescription(new Description(email));
        return this;
    }

    /**
     * Sets the {@code AppliedDate} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withAddress(String address) {
        descriptor.setAppliedDate(new AppliedDate(address));
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

    public EditCommand.EditInternshipDescriptor build() {
        return descriptor;
    }
}
