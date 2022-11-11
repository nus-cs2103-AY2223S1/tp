package seedu.address.testutil;

import seedu.address.logic.commands.EditLinkCommand.EditLinkDescriptor;
import seedu.address.model.team.Link;
import seedu.address.model.team.LinkName;
import seedu.address.model.team.Url;

/**
 * A utility class to help with building EditLinkDescriptor objects.
 */
public class EditLinkDescriptorBuilder {

    private EditLinkDescriptor descriptor;

    public EditLinkDescriptorBuilder() {
        descriptor = new EditLinkDescriptor();
    }

    public EditLinkDescriptorBuilder(EditLinkDescriptor descriptor) {
        this.descriptor = new EditLinkDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditLinkDescriptor} with fields containing {@code link}'s details
     */
    public EditLinkDescriptorBuilder(Link link) {
        descriptor = new EditLinkDescriptor();
        descriptor.setName(link.getDisplayedName());
        descriptor.setUrl(link.getUrl());
    }

    /**
     * Sets the {@code name} of the {@code EditLinkDescriptor} that we are building.
     */
    public EditLinkDescriptorBuilder withName(String name) {
        descriptor.setName(new LinkName(name));
        return this;
    }

    /**
     * Sets the {@code url} of the {@code EditLinkDescriptor} that we are building.
     */
    public EditLinkDescriptorBuilder withUrl(String url) {
        descriptor.setUrl(new Url(url));
        return this;
    }

    public EditLinkDescriptor build() {
        return descriptor;
    }
}
