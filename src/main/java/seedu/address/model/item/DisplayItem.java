package seedu.address.model.item;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents a unique item in the addressbook, it can be either a accessable
 * team or an entry
 */
public interface DisplayItem {
    /**
     * Returns the entry type of the displayable item to determine what type of item
     * this is.
     */
    int getTypeFlag();

    /**
     * Defines a stronger notions of equality between display items.
     */
    boolean stronglyEqual(DisplayItem o);

    /**
     * Defines a weaker notion of equality between display items.
     */
    boolean weaklyEqual(DisplayItem o);

    /**
     * Makes the current item to belong under {@code DisplayItem o}
     */
    void setParent(DisplayItem o);

    /**
     * Renames the current item with the name
     */
    void rename(String name);

    /**
     * Removes o as the parent of the current item {@code DisplayItem o}
     */
    void removeParent(DisplayItem o);

    /**
     * Returns the full path of the object.
     */
    String getFullPath();

    /**
     * Returns relative path of the current object
     */
    String getRelativePath(DisplayItem parent);

    /**
     * Gets a list of attributes applied to DisplayItem
     */
    List<Attribute<?>> getAttributes();

    /**
     * Gets attributes that requires saving
     */
    List<Attribute<?>> getSavedAttributes();

    Optional<Attribute<?>> getAttribute(String type);

    /**
     * Gets the name of the display item.
     */
    Name getName();

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    Set<Tag> getTags();

    /**
     * Adds a tag or multiple tags to this item
     */
    void addTags(String... description);

    /**
     * Deletes a tag from this item
     */
    void deleteTag(String description);

    /**
     * Deletes a tag from this item
     */
    void setTags(Set<Tag> tags);

    /**
     * Gets the list of parents of this displayItem.
     */
    Set<? extends DisplayItem> getParents();

    /**
     * Add a attribute to the current object.
     */
    void addAttribute(Attribute<?> attribute);

    /**
     * Delete an attribute to the current object.
     */
    void deleteAttribute(String type);

    /**
     * Returns true if {@code DisplayItem o} is a parent of this item
     */
    boolean isPartOfContext(DisplayItem o);

    /**
     * Returns unique uid for this displayItem.
     */
    UUID getUid();
}
