package seedu.application.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.application.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.application.model.application.Application;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;
import seedu.application.model.application.Status;
import seedu.application.model.tag.Tag;

/**
 * A utility class to help with building EditApplicationDescriptor objects.
 */
public class EditApplicationDescriptorBuilder {

    private EditApplicationDescriptor descriptor;

    public EditApplicationDescriptorBuilder() {
        descriptor = new EditApplicationDescriptor();
    }

    public EditApplicationDescriptorBuilder(EditApplicationDescriptor descriptor) {
        this.descriptor = new EditApplicationDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicationDescriptor} with fields containing {@code application}'s details
     */
    public EditApplicationDescriptorBuilder(Application application) {
        descriptor = new EditApplicationDescriptor();
        descriptor.setCompany(application.getCompany());
        descriptor.setContact(application.getContact());
        descriptor.setDate(application.getDate());
        descriptor.setEmail(application.getEmail());
        descriptor.setPosition(application.getPosition());
        descriptor.setStatus(application.getStatus());
        descriptor.setTags(application.getTags());
    }

    /**
     * Sets the {@code Company} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withCompany(String company) {
        descriptor.setCompany(new Company(company));
        return this;
    }

    /**
     * Sets the {@code Contact} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withContact(String contact) {
        descriptor.setContact(new Contact(contact));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withPosition(String position) {
        descriptor.setPosition(new Position(position));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(Status.getStatus(status));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditApplicationDescriptor}
     * that we are building.
     */
    public EditApplicationDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditApplicationDescriptor build() {
        return descriptor;
    }
}
