package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.profile.EditProfileCommand.EditProfileDescriptor;
import seedu.address.model.profile.Email;
import seedu.address.model.profile.Name;
import seedu.address.model.profile.Phone;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.Telegram;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditProfileDescriptor objects.
 */
public class EditProfileDescriptorBuilder {

    private EditProfileDescriptor descriptor;

    public EditProfileDescriptorBuilder() {
        descriptor = new EditProfileDescriptor();
    }

    public EditProfileDescriptorBuilder(EditProfileDescriptor descriptor) {
        this.descriptor = new EditProfileDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProfileDescriptor} with fields containing {@code profile}'s details
     */
    public EditProfileDescriptorBuilder(Profile profile) {
        descriptor = new EditProfileDescriptor();
        descriptor.setName(profile.getName());
        descriptor.setPhone(profile.getPhone());
        descriptor.setEmail(profile.getEmail());
        descriptor.setTelegram(profile.getTelegram());
        descriptor.setTags(profile.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditProfileDescriptor} that we are building.
     */
    public EditProfileDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditProfileDescriptor} that we are building.
     */
    public EditProfileDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditProfileDescriptor} that we are building.
     */
    public EditProfileDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code EditProfileDescriptor} that we are building.
     */
    public EditProfileDescriptorBuilder withTelegram(String telegram) {
        descriptor.setTelegram(new Telegram(telegram));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditProfileDescriptor}
     * that we are building.
     */
    public EditProfileDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditProfileDescriptor build() {
        return descriptor;
    }
}
