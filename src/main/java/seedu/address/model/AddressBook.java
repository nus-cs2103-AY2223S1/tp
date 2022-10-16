package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagSet;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    private final TagSet tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tags = new TagSet();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
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

    public void setTags(Set<Tag> tags) {
        for (Tag tag: tags) {
            this.tags.add(tag);
        }
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTags(newData.getTags());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// tag operations

    /**
     * Removes the given tags from the person
     * {@code tagsToRemove} must already be tagged to the person.
     * Returns the untagged {@code Person}.
     */
    public Person removeTags(Person target, Collection<Tag> tagsToRemove) {
        Set<Tag> newTags = new HashSet<>(target.getTags());
        newTags.removeAll(tagsToRemove);

        Person untaggedPerson = new Person(
                target.getName(), target.getPhone(), target.getEmail(),
                target.getAddress(), target.getRemark(), newTags);
        persons.setPerson(target, untaggedPerson);
        return untaggedPerson;
    }

    /**
     * Returns true if the address book contains the given tag.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds the tag into the address book.
     */
    public void createTag(Tag tag) {
        requireNonNull(tag);
        tags.add(tag);
    }

    /**
     * Delete tags from address book.
     */
    public Set<Tag> deleteTags(Set<Tag> tagsToDelete) {
        Set<Tag> deletedTags = tags.remove(tagsToDelete);
        persons.asUnmodifiableObservableList()
                .forEach(person -> removeTags(person, deletedTags));
        return deletedTags;
    }

    //// util methods

    @Override
    public String toString() {
        String personsCount = persons.asUnmodifiableObservableList().size() + " persons";
        String tagsCount = tags.asUnmodifiableSet().size() + " tags";
        return personsCount + ", " + tagsCount;

        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    /**
     * Returns the set of tags as an unmodifiable {@code UnmodifiableSet}.
     */
    @Override
    public Set<Tag> getTags() {
        return tags.asUnmodifiableSet();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                        && persons.equals(((AddressBook) other).persons))
                        && tags.equals(((AddressBook) other).tags);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
