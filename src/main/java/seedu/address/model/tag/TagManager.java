package seedu.address.model.tag;

import java.util.ArrayList;

public class TagManager {

    private final ArrayList<Tag> tagManager = new ArrayList<>();

    public void addTagToList(Tag tag) {
        tagManager.add(tag);
    }

    public void createTag(String tagName) {
        Tag tag = new Tag(tagName);
        addTagToList(tag);
    }

    public void editTag(Tag oldTag, Tag newTag ) {
        int index = tagManager.indexOf(oldTag);
        tagManager.add(index, newTag);
    }

    public boolean hasTag(Tag tag) {
        return tagManager.contains(tag);
    }

}
