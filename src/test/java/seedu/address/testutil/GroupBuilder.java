package seedu.address.testutil;

import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.util.SampleDataUtil;

import java.util.ArrayList;
import java.util.HashSet;

import static java.util.Objects.requireNonNull;

public class GroupBuilder extends AbstractSingleItemBuilder {

    public static final String DEFAULT_NAME = "Team_Alpha";

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        super(new Name(DEFAULT_NAME), new ArrayList<>(), new HashSet<>());
    }

    /**
     * Initializes the {@code GroupBuilder} with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        super(groupToCopy.getName(), new ArrayList<>(groupToCopy.getAttributes()),
                new HashSet<>(groupToCopy.getTags()));
    }

    /**
     * Sets the parent of the {@code GroupBuilder} that is being built.
     */
    public GroupBuilder withParent(AbstractSingleItem item) {
        requireNonNull(item);
        this.parent = item;
        return this;
    }

    @Override
    public GroupBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    @Override
    public GroupBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    @Override
    public Group build() {
        Group group = new Group(name.fullName);

        if (parent != null) {
            group.setParent(parent);
        }
        group.setTags(tags);

        for (Attribute<?> attr : attributes) {
            group.addAttribute(attr);
        }
        return group;
    }
}
