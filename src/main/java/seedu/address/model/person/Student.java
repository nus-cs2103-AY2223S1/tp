package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student which is-a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {
    private final Set<ModuleCode> moduleCodes = new HashSet<>();

    private final Year year;

    /**
     * Every field must be present and not null.
     */

    public Student(Name name, Phone phone, Email email, Gender gender, Set<Tag> tags,
                   Location location, GithubUsername username, Set<ModuleCode> moduleCodes, Year year) {
        super(name, phone, email, gender, tags, location, username);
        assert moduleCodes.size() > 0 : "At least 1 moduleCode present";
        this.moduleCodes.addAll(moduleCodes);
        this.year = year;
    }

    public Set<ModuleCode> getModuleCodes() {
        return Collections.unmodifiableSet(this.moduleCodes);
    }

    public Year getYear() {
        return this.year;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                getUsername(), moduleCodes, year);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Name: ")
                .append(getName());

        if (!this.moduleCodes.isEmpty()) {
            builder.append("; Module Code: ");
            this.moduleCodes.forEach(builder:: append);
        }

        if (!year.value.equals(Year.EMPTY_YEAR)) {
            builder.append("; Year: ")
                    .append(year.value);
        }

        builder.append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Gender: ")
                .append(getGender());

        if (!getUsername().value.equals(GithubUsername.DEFAULT_USERNAME)) {
            builder.append("; Github Username: ")
                    .append(getUsername());
        }

        builder.append("; Location: ")
                .append(getLocation());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
    public ModuleCode getHighestModuleCode() {
        assert moduleCodes.size() > 0 : "At least 1 moduleCode present";
        return moduleCodes.stream().max(ModuleCode::compareTo).get();
    }
    @Override
    public int compareModuleCode(Person person) {
        ModuleCode highest = getHighestModuleCode();
        if (person instanceof Student) {
            return highest.compareTo(((Student) person).getHighestModuleCode());
        }
        if (person instanceof Professor) {
            return highest.compareTo(((Professor) person).getModuleCode());
        }
        if (person instanceof TeachingAssistant) {
            return highest.compareTo(((TeachingAssistant) person).getModuleCode());
        }
        return this.compareModuleCode(person);
    }

    @Override

    public String getTypeString() {
        return "stu";
    }

    @Override
    public String getFullTypeString() {
        return "Student";
    }

    @Override
    public boolean doModulesMatch(Set<String> modulesSet, boolean needsAllModules) {
        Set<String> personModulesList = getModulesSetString();
        if (needsAllModules) {
            return personModulesList.equals(modulesSet) || personModulesList.containsAll(modulesSet);
        } else {
            personModulesList.retainAll(modulesSet);
            return !personModulesList.isEmpty();
        }
    }

    /**
     * Creates Set of modules in their String form. Used to check if there is a match in
     * modules provided by the user for a FindCommand.
     * @return the Set of modules in their String form
     */
    private Set<String> getModulesSetString() {
        return getModuleCodes().stream()
                .map(moduleCode -> moduleCode.value.toLowerCase()).collect(Collectors.toSet());
    }

    /**
     * Checks if the provided person is equal to this student.
     * @param other the other person
     * @return true if they are equal and false otherwise
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }
        Student otherPerson = (Student) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLocation().equals(getLocation())
                && otherPerson.getUsername().equals(getUsername())
                && otherPerson.getModuleCodes().equals(getModuleCodes())
                && otherPerson.getYear().equals(getYear());

    }

    @Override
    public boolean doesRatingMatch(List<String> ratingList) {
        return false;
    }

    @Override
    public boolean doesYearMatch(List<String> yearList) {
        return yearList.stream().anyMatch(year -> year.equals(this.year.value));
    }

    @Override
    public boolean doesSpecialisationMatch(List<String> specList) {
        return false;
    }

    @Override
    public boolean doesOfficeHourMatch(List<String> officeHoursList) {
        return false;
    }
}
