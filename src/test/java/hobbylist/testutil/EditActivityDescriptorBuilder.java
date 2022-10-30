package hobbylist.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hobbylist.logic.commands.EditCommand;
import hobbylist.logic.commands.EditCommand.EditActivityDescriptor;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Description;
import hobbylist.model.activity.Name;
import hobbylist.model.activity.Status;
import hobbylist.model.tag.Tag;

/**
 * A utility class to help with building EditActivityDescriptor objects.
 */
public class EditActivityDescriptorBuilder {

    private EditCommand.EditActivityDescriptor descriptor;

    public EditActivityDescriptorBuilder() {
        descriptor = new EditCommand.EditActivityDescriptor();
    }

    public EditActivityDescriptorBuilder(EditActivityDescriptor descriptor) {
        this.descriptor = new EditCommand.EditActivityDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditActivityDescriptor} with fields containing {@code activity}'s details
     */
    public EditActivityDescriptorBuilder(Activity activity) {
        descriptor = new EditCommand.EditActivityDescriptor();
        descriptor.setName(activity.getName());
        descriptor.setDescription(activity.getDescription());
        descriptor.setTags(activity.getTags());
        if (activity.getDate().isPresent()) {
            descriptor.setDate(activity.getDate());
        } else {
            descriptor.setDate(null);
        }
        if (activity.getStatus().status != Status.State.NONE) {
            descriptor.setStatus(activity.getStatus());
        } else {
            descriptor.setStatus(null);
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditActivityDescriptor} that we are building.
     */
    public EditActivityDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditActivityDescriptor} that we are building.
     */
    public EditActivityDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditActivityDescriptor}
     * that we are building.
     */
    public EditActivityDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditActivityDescriptor build() {
        return descriptor;
    }
}
