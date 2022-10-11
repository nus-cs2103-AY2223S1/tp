package seedu.foodrem.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.UniqueItemList;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
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

    public FoodRem() {
    }

    /**
     * Creates an AddressBook using the Items in the {@code toBeCopied}
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
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFoodRem newData) {
        requireNonNull(newData);

        setItems(newData.getItemList());
        setTags(newData.getTagList());
    }

    //// item-level operations

    /**
     * Returns true if an item with the same identity as {@code item} exists in FoodRem.
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
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in FoodRem.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    //// tag-level methods
    /**
     * Returns true if a tag with the same name as {@code tag} exists in the address book.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds a tag to the address book.
     * The tag must not already exist in the address book.
     */
    public void addTag(Tag t) {
        tags.add(t);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the address book.
     * The tag in {@code editedTag} must not be the same as another existing tag in the address book.
     */
    public void setTag(Tag target, Tag editedTag) {
        requireNonNull(editedTag);

        tags.setTag(target, editedTag);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTag(Tag key) {
        tags.remove(key);
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
        return other == this // short circuit if same object
                || (other instanceof FoodRem // instanceof handles nulls
                && items.equals(((FoodRem) other).items))
                && tags.equals(((FoodRem) other).tags);
    }

    @Override
    public int hashCode() {
        return items.hashCode() ^ tags.hashCode();
    }
}
