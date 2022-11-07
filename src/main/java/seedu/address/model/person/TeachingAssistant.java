package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Represents a Teaching Assistant which is-a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TeachingAssistant extends Person {

    private final ModuleCode moduleCode;

    private final Rating rating;

    /**
     * Every field must be present and not null.
     */
    public TeachingAssistant(Name name, ModuleCode moduleCode, Phone phone, Email email, Gender gender, Set<Tag> tags,
                             Location location, GithubUsername username, Rating rating) {
        super(name, phone, email, gender, tags, location, username);
        this.moduleCode = moduleCode;
        this.rating = rating;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public Rating getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Name: ")
            .append(getName())
            .append("; Module Code: ")
            .append(getModuleCode())
            .append("; Phone: ")
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
                .append(getLocation())
                .append("; Rating: ")
                .append(getRating());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
    @Override
    public int compareModuleCode(Person person) {
        if (person instanceof Professor) {
            return this.moduleCode.toString().toUpperCase()
                    .compareTo(((Professor) person).getModuleCode().toString().toUpperCase());
        }
        if (person instanceof TeachingAssistant) {
            return this.moduleCode.toString().toUpperCase()
                    .compareTo(((TeachingAssistant) person).getModuleCode().toString().toUpperCase());
        }
        if (person instanceof Student) {
            return this.moduleCode
                    .compareTo(((Student) person).getHighestModuleCode());
        }
        return this.compareModuleCode(person);
    }

    @Override

    public String getTypeString() {
        return "ta";
    }

    @Override
    public String getFullTypeString() {
        return "Teaching Assistant";
    }

    @Override
    public boolean doModulesMatch(Set<String> modulesSet, boolean needsAllModules) {
        if (modulesSet.size() > 1 && needsAllModules) {
            return false;
        }
        return modulesSet.stream()
                .anyMatch(module -> StringUtil.containsWordIgnoreCase(this.moduleCode.value, module));
    }

    /**
     * Computes a unique hashcode based on this TeachingAssistant's fields.
     * @return the computed hashcode.
     */
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                getUsername(), moduleCode, rating);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TeachingAssistant)) {
            return false;
        }

        TeachingAssistant otherPerson = (TeachingAssistant) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLocation().equals(getLocation())
                && otherPerson.getUsername().equals(getUsername())
                && otherPerson.getModuleCode().equals(getModuleCode())
                && otherPerson.getRating().equals(getRating());
    }

    @Override
    public boolean doesRatingMatch(List<String> ratingList) {
        return ratingList.stream().anyMatch(rating -> rating.equals(this.rating.value));
    }

    @Override
    public boolean doesYearMatch(List<String> yearList) {
        return false;
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
