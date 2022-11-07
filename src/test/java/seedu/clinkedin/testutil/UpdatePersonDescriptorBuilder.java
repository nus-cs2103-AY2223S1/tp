package seedu.clinkedin.testutil;

import java.util.List;

import seedu.clinkedin.logic.commands.AddToCommand;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;

/**
 * A utility class to help with building UpdatePersonDescriptor objects.
 */
public class UpdatePersonDescriptorBuilder {
    private AddToCommand.UpdatePersonDescriptor descriptor;

    public UpdatePersonDescriptorBuilder() {
        descriptor = new AddToCommand.UpdatePersonDescriptor();
    }

    public UpdatePersonDescriptorBuilder(AddToCommand.UpdatePersonDescriptor descriptor) {
        this.descriptor = new AddToCommand.UpdatePersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdatePersonDescriptor} with fields containing {@code person}'s details
     */
    public UpdatePersonDescriptorBuilder(Person person) {
        descriptor = new AddToCommand.UpdatePersonDescriptor();
        UniqueTagTypeMap tagTypeMap = new UniqueTagTypeMap();
        tagTypeMap.setTagTypeMap(person.getTags());
        descriptor.setTagTypeMap(tagTypeMap);
        descriptor.setNote(person.getNote());
        descriptor.setRating(person.getRating());
        descriptor.setLinks(person.getLinks());
    }

    /**
     * Parses the {@code tags} into a {@code UniqueTagTypeMap} and set it to the {@code UpdatePersonDescriptor}
     * that we are building.
     */
    public UpdatePersonDescriptorBuilder withTags(List<List<String>> tags) {
        UniqueTagTypeMap newMap = new UniqueTagTypeMap();
        for (List<String> tag: tags) {
            TagType tagType = new TagType(tag.get(0), new Prefix(tag.get(1)));
            for (String t: tag.subList(2, tag.size())) {
                newMap.mergeTag(tagType, new Tag(t));
            }
        }
        descriptor.setTagTypeMap(newMap);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code UpdatePersonDescriptor} that we are
     * building.
     */
    public UpdatePersonDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code UpdatePersonDescriptor} that we are
     * building.
     * @param rating
     * @return
     */
    public UpdatePersonDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    public AddToCommand.UpdatePersonDescriptor build() {
        return descriptor;
    }
}
