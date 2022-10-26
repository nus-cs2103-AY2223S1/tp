package seedu.foodrem.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.UniqueItemList;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.model.tag.UniqueTagList;

/**
 * Wraps all data in FoodRem
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class FoodRem implements ReadOnlyFoodRem {
    private final UniqueItemList items;
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        items = new UniqueItemList();
        tags = new UniqueTagList();
    }

    public FoodRem() {}

    /**
     * Creates a FoodRem using the Items in the {@code toBeCopied}
     */
    public FoodRem(ReadOnlyFoodRem toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<Item> items) {
        this.items.setItems(items);
    }

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code FoodRem} with {@code newData}.
     */
    public void resetData(ReadOnlyFoodRem newData) {
        requireNonNull(newData);

        setItems(newData.getItemList());
        setTags(newData.getTagList());
    }

    //// item-level operations

    /**
     * Returns {@code true} if an item with the same identity as {@code item} exists in FoodRem.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Adds an item to FoodRem.
     * The item must not already exist in FoodRem.
     */
    public void addItem(Item p) {
        items.add(p);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in FoodRem.
     * The item identity of {@code editedItem} must not be the same as another existing item in FoodRem.
     */
    public void setItem(Item target, Item editedItem) {
        requireNonNull(editedItem);
        items.setItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code FoodRem}.
     * {@code key} must exist in FoodRem.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    //// tag-level methods

    /**
     * Returns {@code true} if a tag with the same name as {@code tag} exists in foodRem.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds a tag to the foodRem.
     * The tag must not already exist in the foodRem.
     */
    public void addTag(Tag t) {
        tags.add(t);
    }

    /**
     * Replaces the {@code originalTag} with the {@code renamedTag} in the item.
     * The {@code originalTag} must exist in foodRem
     * The tag in {@code renamedTag} must not be the same as another existing tag in the foodRem.
     */
    public void renameTag(Tag originalTag, Tag renamedTag) {
        requireNonNull(originalTag);
        requireNonNull(renamedTag);

        for (Item item : items) {
            Set<Tag> tags = item.getTagSet();
            if (tags.contains(originalTag)) {
                tags.remove(originalTag);
                tags.add(renamedTag);
                setItem(item, Item.createItemWithTags(item, tags));
            }
        }
        tags.setTag(originalTag, renamedTag);
    }

    /**
     * Removes {@code key} from this {@code FoodRem}.
     * {@code key} must exist in the foodRem.
     */
    public void removeTag(Tag tag) {
        requireNonNull(tag);

        for (Item item : items) {
            Set<Tag> tags = item.getTagSet();
            if (tags.contains(tag)) {
                tags.remove(tag);
                setItem(item, Item.createItemWithTags(item, tags));
            }
        }
        tags.remove(tag);
    }

    //// util methods

    @Override
    public String toString() {
        return items.asUnmodifiableObservableList().size() + " items"
                + tags.asUnmodifiableObservableList().size() + " tags";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getItemList() {
        return items.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FoodRem
                && items.equals(((FoodRem) other).items))
                && tags.equals(((FoodRem) other).tags);
    }

    @Override
    public int hashCode() {
        return items.hashCode() ^ tags.hashCode();
    }
}
