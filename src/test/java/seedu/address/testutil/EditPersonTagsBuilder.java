package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.TagCommand.EditPersonTags;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditPersonTags objects.
 */
public class EditPersonTagsBuilder {

    private EditPersonTags editPersonTags;

    public EditPersonTagsBuilder() {
        editPersonTags = new EditPersonTags();
    }

    public EditPersonTagsBuilder(EditPersonTags editPersonTags) {
        this.editPersonTags = new EditPersonTags(editPersonTags);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code task}'s details
     */
    public EditPersonTagsBuilder(Task task) {
        editPersonTags = new EditPersonTags();
        editPersonTags.setTags(task.getTags());
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonTagsBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        editPersonTags.setTags(tagSet);
        return this;
    }

    public EditPersonTags build() {
        return editPersonTags;
    }
}
