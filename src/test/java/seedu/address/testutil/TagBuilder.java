package seedu.address.testutil;

import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Person objects.
 */
public class TagBuilder {

    public static final String DEFAULT_TAG_NAME = "CS2103T";
    public static final int DEFAULT_TAG_COUNT = 1;

    private String tagName;
    private int tagCount;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        tagName = DEFAULT_TAG_NAME;
        tagCount = DEFAULT_TAG_COUNT;
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        tagName = tagToCopy.getName();
        tagCount = tagToCopy.getCount();
    }

    /**
     * Sets the {@code Name} of the {@code Tag} that we are building.
     */
    public TagBuilder withName(String name) {
        this.tagName = name;
        return this;
    }

    /**
     * Sets the {@code tagCount} of the {@code Tag} that we are building.
     */
    public TagBuilder withCount(int count) {
        this.tagCount = count;
        return this;
    }

    public Tag build() {
        return new Tag(tagName, tagCount);
    }

}
