package soconnect.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javafx.collections.ObservableList;
import soconnect.model.person.Person;
import soconnect.model.person.UniquePersonList;
import soconnect.model.tag.Tag;
import soconnect.model.tag.UniqueTagList;
import soconnect.model.todo.Todo;

/**
 * Wraps all data at the SoConnect level.
 * Duplicate person is not allowed (by .isSamePerson comparison).
 */
public class SoConnect implements ReadOnlySoConnect {

    private final UniquePersonList persons;
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tags = new UniqueTagList();
    }

    public SoConnect() {}

    /**
     * Creates an SoConnect using the Persons in the {@code toBeCopied}.
     */
    public SoConnect(ReadOnlySoConnect toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the tag list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }
    /**
     * Resets the existing data of this {@code SoConnect} with {@code newData}.
     */
    public void resetData(ReadOnlySoConnect newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTags(newData.getTagList());
    }

    /// sorting operations

    /**
     * Sorts the SoConnect by {@code Name} in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    public void sortByName(Boolean isReverse) {
        persons.sortByName(isReverse);
    }

    /**
     * Sorts the SoConnect by {@code Phone} in increasing order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    public void sortByPhone(Boolean isReverse) {
        persons.sortByPhone(isReverse);
    }

    /**
     * Sorts the SoConnect by {@code Email} in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    public void sortByEmail(Boolean isReverse) {
        persons.sortByEmail(isReverse);
    }

    /**
     * Sorts the SoConnect by {@code Address} in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    public void sortByAddress(Boolean isReverse) {
        persons.sortByAddress(isReverse);
    }

    /**
     * Sorts the SoConnect by a {@code Tag}.
     * Contacts with the {@code Tag} appear before those without the {@code Tag}.
     *
     * @param tag       The {@code Tag} to sort with.
     * @param isReverse Whether the sorting should be in reverse order.
     */
    public void sortByTag(Tag tag, Boolean isReverse) {
        persons.sortByTag(tag, isReverse);
    }

    public TreeSet<String> getUniqueNames() {
        return persons.getUniqueNames();
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the SoConnect.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if all the tags in the contact exist in the tagList.
     *
     * @param person The person to be added.
     * @return True if all the tags exists in the tagList. False if otherwise.
     */
    public boolean areTagsAvailable(Person person) {
        List<Tag> personTags = new ArrayList<>(person.getTags());
        return tags.containsAll(personTags);
    }

    /**
     * Returns true if all the {@code tag}s in the {@code todo} exist in the {@code tagList}.
     *
     * @param todo The {@code Todo} to be added.
     * @return True if all the {@code tag}s exists in the {@code tagList}. False if otherwise.
     */
    public boolean areTagsAvailable(Todo todo) {
        List<Tag> todoTags = new ArrayList<>(todo.getTags());
        return tags.containsAll(todoTags);
    }

    /**
     * Adds a person to the SoConnect.
     * The person must not already exist in the SoConnect.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the SoConnect.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the SoConnect.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code SoConnect}.
     * {@code key} must exist in the SoConnect.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// tag-level operations

    /**
     * Returns true if tag already does exists.
     *
     * @param tag The tag added.
     * @return True if tag exists. False if otherwise.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.hasTag(tag);
    }

    /**
     * Adds the tag to the tagList.
     *
     * @param tag The tag to be added.
     */
    public void addTag(Tag tag) {
        tags.addTagToList(tag);
    }

    /**
     * Changes the original tag to the new tag.
     *
     * @param oldTag The original tag.
     * @param newTag The new tag to replace the original tag.
     */
    public void editTag(Tag oldTag, Tag newTag) {
        tags.editTag(oldTag, newTag);
        persons.changeRelevantPersonTag(oldTag, newTag);
    }

    /**
     * Deletes the tag from the tagList.
     *
     * @param tag The tag to be deleted.
     */
    public void deleteTag(Tag tag) {
        tags.deleteTag(tag);
        persons.removeRelevantPersonTag(tag);
    }

    /**
     * Gets the tag from the tagList.
     *
     * @param tag The reference tag.
     * @return The tag that has the same name as the reference tag.
     */
    public Tag getTagFromList(Tag tag) {
        return tags.getTag(tag);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SoConnect // instanceof handles nulls
                && persons.equals(((SoConnect) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
