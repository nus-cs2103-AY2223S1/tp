package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.ShallowCopyable;
import seedu.address.model.person.Person;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 * Tags are uniquely identified by their {@code tagName}. Two different tags may not share
 * the same name.
 */
public class Tag implements ShallowCopyable {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    private final List<Person> personList = new ArrayList<>();

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Adds person to tag's person list.
     * @param person A person
     */
    public void addPerson(Person person) {
        if (!personList.contains(person)) {
            this.personList.add(person);
        }
    }

    /**
     * Removes person from tag's person list.
     * @param person A person
     */
    public void removePerson(Person person) {
        this.personList.remove(person);
    }

    /**
     * Returns true if this tag's person list is empty.
     */
    public boolean isPersonListEmpty() {
        return personList.isEmpty();
    }

    /**
     * Checks if a person exists under the tag group
     * @param person the person to check for existence
     * @return true if the person exists in this tag group
     */
    public boolean doesPersonExist(Person person) {
        return personList.contains(person);
    }

    /**
     * Creates a deep copy of the person's list of this tag object for read-only access
     * Modifications to persons retrieved from this copied list will not affect the original
     * person that it was copied from.
     * Guarantees complete protection against mutations of persons through tag access
     * @return a deep copy of the person's list
     */
    public List<Person> getDeepCopiedPersonList() {
        return personList.stream().map(Person::deepCopy).collect(Collectors.toList());
    }

    /**
     * Returns true only if the names of two tags are the same.
     * Two tags are defined to be equal only if their names are equal, it
     * is not necessary for their persons contained within to be the same.
     * @param other the other tag to check against
     * @return true if two tags have the same tag name
     */
    @Override
    public boolean equals(Object other) {
        requireNonNull(other);
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return otherTag.tagName.equals(tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + tagName + ']';
    }

    @Override
    public Tag shallowCopy() {
        Tag clone = new Tag(tagName);
        personList.forEach(clone::addPerson);

        return clone;
    }

}
