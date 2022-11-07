package soconnect.model;

import javafx.collections.ObservableList;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;

/**
 * Unmodifiable view of an SoConnect.
 */
public interface ReadOnlySoConnect {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

    /**
     * Adds the tag to the tagList.
     *
     * @param tag The tag to be added.
     */
    void addTag(Tag tag);

    /**
     * Returns true if tag already does exists.
     *
     * @param tag The tag added.
     * @return True if tag exists. False if otherwise.
     */
    boolean hasTag(Tag tag);

    /**
     * Gets the tag from the tagList specified by the index.
     *
     * @param index The reference number.
     * @return The tag based on the index.
     */
    Tag getTagFromList(int index);

    /**
     * Gets the number of where the tag is in the tagList.
     *
     * @param tag The tag to be referenced.
     * @return The integer of where the tag is in the tagList.
     */
    int getTagReference(Tag tag);
}
