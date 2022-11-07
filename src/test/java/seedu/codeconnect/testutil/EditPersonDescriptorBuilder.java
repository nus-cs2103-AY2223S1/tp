package seedu.codeconnect.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.codeconnect.logic.commands.EditContactCommand.EditPersonDescriptor;
import seedu.codeconnect.model.module.Module;
import seedu.codeconnect.model.person.Address;
import seedu.codeconnect.model.person.Email;
import seedu.codeconnect.model.person.Github;
import seedu.codeconnect.model.person.Name;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.model.person.Phone;
import seedu.codeconnect.model.person.Telegram;
import seedu.codeconnect.model.tag.Tag;

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
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setMods(person.getModules());
        descriptor.setGithub(person.getGithub());
        descriptor.setTelegram(person.getTelegram());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
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

    /**
     * Sets the {@code Mods} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withModules(String... mods) {
        Set<Module> moduleSet = Stream.of(mods).map(Module::new).collect(Collectors.toSet());
        descriptor.setMods(moduleSet);
        return this;
    }

    /**
     * Sets the {@code Github} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGithub(String gh) {
        descriptor.setGithub(new Github(gh));
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTelegram(String tele) {
        descriptor.setTelegram(new Telegram(tele));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
