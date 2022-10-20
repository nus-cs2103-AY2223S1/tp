package seedu.foodrem.testutil;

import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.model.tag.TagName;

/**
 * A utility class to help with building Tag objects.
 */
public class TagBuilder {
    public static final String DEFAULT_NAME = "NONE";

    // Identity fields
    private TagName name;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        name = new TagName(DEFAULT_NAME);
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        name = new TagName(tagToCopy.getName());
    }

    /**
     * Sets the {@code Name} of the {@link Tag} that we are building.
     */
    public TagBuilder withTagName(String name) {
        this.name = new TagName(name);
        return this;
    }

    public Tag build() {
        return new Tag(name.toString());
    }

    public String getTagBuilderName() {
        return name.toString();
    }
}
