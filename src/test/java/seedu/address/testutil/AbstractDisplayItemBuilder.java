package seedu.address.testutil;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.Node;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.item.AbstractDisplayItem;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

abstract class AbstractDisplayItemBuilder {

    protected Name name;
    protected List<Attribute<?>> attributes = new ArrayList<>();
    protected Set<Tag> tags = new HashSet<>();

    protected AbstractDisplayItemBuilder(Name name, List<Attribute<?>> attributes,
                                         Set<Tag> tags) {
        requireAllNonNull(name, attributes, tags);
        this.name = name;
        this.attributes.addAll(attributes);
        this.tags.addAll(tags);
    }

    /**
     * Sets the {@code Name} of the {@code AbstractDisplayItem} that is being built.
     */
    public abstract AbstractDisplayItemBuilder withName(String name);

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code AbstractDisplayItem} that we are
     * building.
     */
    public abstract AbstractDisplayItemBuilder withTags(String... tags);

    /**
     * Returns a {@code AbstractDisplayItem} with all specified attributes in builder.
     */
    public abstract AbstractDisplayItem build();

    /**
     * Adds a custom attribute to the {@code AbstractDisplayItemBuilder}.
     */
    public abstract AbstractDisplayItemBuilder withAttribute(Attribute<?> attribute);

    /**
     * Adds a custom attribute. Refer to {@link #withAttribute(Attribute)} for more information.
     */
    public abstract <U> AbstractDisplayItemBuilder withAttribute(String name, U data);

    /**
     * Sets the name for this {@code AbstractDisplayItem}.
     */
    protected void setName(String name) {
        this.name = new Name(name);
    }

    /**
     * Sets the tags for this {@code AbstractDisplayItem}.
     */
    protected void setTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
    }

    /**
     * Adds a custom attribute. Refer to {@link #withAttribute(Attribute)} for more information.
     */
    protected void addAttribute(Attribute<?> attribute) {
        this.attributes.add(attribute);
    }

    /**
     * Adds a custom attribute. Refer to {@link #withAttribute(Attribute)} for more information.
     */
    protected <U> void addAttribute(String name, U data) {
        this.attributes.add(new Attribute<U>() {
            @Override
            public String getAttributeType() {
                return name;
            }

            @Override
            public U getAttributeContent() {
                return data;
            }

            @Override
            public boolean isVisibleInMenu() {
                return true;
            }

            @Override
            public boolean isDisplayable() {
                return true;
            }

            @Override
            public boolean isAllFlagMatch(int flag) {
                return false;
            }

            @Override
            public boolean isAnyFlagMatch(int flag) {
                return false;
            }

            @Override
            public boolean isAnyStyleMatch(int flag) {
                return false;
            }

            @Override
            public boolean isAllStyleMatch(int flag) {
                return false;
            }

            @Override
            public Node getJavaFxRepresentation() {
                return null;
            }

            @Override
            public <T> boolean isSameType(Attribute<T> o) {
                return false;
            }

            @Override
            public Map<String, Object> toSaveableData() {
                return null;
            }

            @Override
            public boolean isNameMatch(String name) {
                return false;
            }
        });
    }
}
