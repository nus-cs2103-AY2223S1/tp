package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;

import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractSingleItem;

/**
 * A utility class to help with building Group objects.
 */
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
        super.setName(name);
        return this;
    }

    @Override
    public GroupBuilder withTags(String... tags) {
        super.setTags(tags);
        return this;
    }

    @Override
    public GroupBuilder withAttribute(Attribute<?> attribute) {
        super.addAttribute(attribute);
        return this;
    }

    @Override
    public <U> GroupBuilder withAttribute(String name, U data) {
        super.addAttribute(name, data);
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
