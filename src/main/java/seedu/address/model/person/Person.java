package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Gender gender;

    // Data fields
    private final Location location;
    private final GithubUsername username;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Gender gender, Set<Tag> tags,
                  Location location, GithubUsername username) {
        requireAllNonNull(name, phone, email, gender, tags, location);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.tags.addAll(tags);
        this.location = location;
        this.username = username;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public Location getLocation() {
        return location;
    }

    public GithubUsername getUsername() {
        return username;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getTypeString().equals(getTypeString());
    }

    /**
     * Return 1 if this Person's name has higher precedence
     * lexicographically over the target's name.
     * 0 if their precedence is the same.
     * -1 if this Person's name has lower precedence
     * over the target's name.
     * @param person target Person of comparison.
     * @return int.
     */
    public int compareName(Person person) {
        return this.name.toString().toUpperCase().compareTo(person.name.toString().toUpperCase());
    }

    /**
     * Return 1 if this Person's moduleCode has higher precedence
     * lexicographically over the target's moduleCode.
     * 0 if their precedence is the same.
     * -1 if this Person's moduleCode has lower precedence
     * over the target's moduleCode. For Person objects without
     * moduleCode field. They will be compared based on their name.
     * @param person target Person of comparison.
     * @return int.
     */
    public int compareModuleCode(Person person) {
        if (person instanceof Student || person instanceof Professor || person instanceof TeachingAssistant) {
            return -1;
        }
        return 0;
    }

    /**
     * Returns the string short form of the Person to be used for FindCommand by type.
     * @return the string short form.
     */
    public abstract String getTypeString();

    /**
     * Returns the full string form of the Person's type.
     * @return the full string form.
     */
    public abstract String getFullTypeString();

    /**
     * Returns true if some or all the Person's module(s) match the provided set of modules.
     * @param modulesSet the Set of modules to be matched against
     * @param needsAllModules determines if all or some of the modules need to be matched.
     * @return true if there is match in modules.
     */
    public abstract boolean doModulesMatch(Set<String> modulesSet, boolean needsAllModules);

    public abstract boolean doesRatingMatch(List<String> ratingList);

    public abstract boolean doesYearMatch(List<String> yearList);

    public abstract boolean doesSpecialisationMatch(List<String> specList);

    public abstract boolean doesOfficeHourMatch(List<String> officeHoursList);
}
