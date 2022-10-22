package seedu.rc4hdb.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.tag.Tag;

/**
 * A utility class to help with building ResidentStringDescriptor objects.
 */
public class ResidentStringDescriptorBuilder {

    private ResidentStringDescriptor descriptor;

    public ResidentStringDescriptorBuilder() {
        descriptor = new ResidentStringDescriptor();
    }

    public ResidentStringDescriptorBuilder(ResidentStringDescriptor descriptor) {
        this.descriptor = new ResidentStringDescriptor(descriptor);
    }

    /**
     * Returns an {@code ResidentStringDescriptor} with fields containing {@code resident}'s details
     */
    public ResidentStringDescriptorBuilder(Resident resident) {
        descriptor = new ResidentStringDescriptor();
        descriptor.setName(resident.getName().toString());
        descriptor.setPhone(resident.getPhone().toString());
        descriptor.setEmail(resident.getEmail().toString());
        descriptor.setRoom(resident.getRoom().toString());
        descriptor.setGender(resident.getGender().toString());
        descriptor.setHouse(resident.getHouse().toString());
        descriptor.setMatricNumber(resident.getMatricNumber().toString());
        descriptor.setTags(convertTags(resident.getTags()));
    }

    /**
     * Sets the {@code Name} of the {@code ResidentStringDescriptor} that we are building.
     */
    public ResidentStringDescriptorBuilder withName(String name) {
        descriptor.setName(name);
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code ResidentStringDescriptor} that we are building.
     */
    public ResidentStringDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code ResidentStringDescriptor} that we are building.
     */
    public ResidentStringDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(email);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code ResidentStringDescriptor} that we are building.
     */
    public ResidentStringDescriptorBuilder withRoom(String room) {
        descriptor.setRoom(room);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code ResidentStringDescriptor} that we are building.
     */
    public ResidentStringDescriptorBuilder withGender(String gender) {
        descriptor.setGender(gender);
        return this;
    }

    /**
     * Sets the {@code House} of the {@code ResidentStringDescriptor} that we are building.
     */
    public ResidentStringDescriptorBuilder withHouse(String house) {
        descriptor.setHouse(house);
        return this;
    }


    /**
     * Sets the {@code MatricNumber} of the {@code ResidentStringDescriptor} that we are building.
     */
    public ResidentStringDescriptorBuilder withMatricNumber(String matricNumber) {
        descriptor.setMatricNumber(matricNumber);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code ResidentStringDescriptor}
     * that we are building.
     */
    public ResidentStringDescriptorBuilder withTags(String... tags) {
        Set<String> tagSet = Stream.of(tags).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Convenience method for {@code withTags} method.
     */
    public ResidentStringDescriptorBuilder withTags(Set<Tag> tags) {
        descriptor.setTags(convertTags(tags));
        return this;
    }

    /**
     * Returns a set with the tags converted to strings
     * @param tags the tags set of a resident
     * @return the set containing tag names
     */
    public Set<String> convertTags(Set<Tag> tags) {
        HashSet<String> newTags = new HashSet<>();
        for (Tag tag : tags) {
            String newString = tag.tagName;
            newTags.add(newString);
        }
        return newTags;
    }

    public ResidentStringDescriptor build() {
        return descriptor;
    }
}
