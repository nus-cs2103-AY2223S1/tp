package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Represents a Professor which is-a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Professor extends Person {

    private final ModuleCode moduleCode;

    private final Rating rating;

    /**
     * Every field must be present and not null.
     */
    public Professor(Name name, ModuleCode moduleCode, Phone phone, Email email, Gender gender, Set<Tag> tags,
                     Location location, GithubUsername username, Rating rating) {
        super(name, phone, email, gender, tags, location, username);
        this.moduleCode = moduleCode;
        this.rating = rating;
    }

    public ModuleCode getModuleCode() {
        return this.moduleCode;
    }

    public Rating getRating() {
        return this.rating;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Professor)) {
            return false;
        }

        Professor otherPerson = (Professor) other;
        return super.equals(other) && otherPerson.getModuleCode().equals(getModuleCode());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, super.hashCode());
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
            return this.moduleCode.toString()
                    .compareTo(((Professor) person).moduleCode.toString());
        }
        if (person instanceof TeachingAssistant) {
            return this.moduleCode.toString()
                    .compareTo(((TeachingAssistant) person).getModuleCode().toString());
        }
        return 1;
    }

    @Override
    public String getTypeString() {
        return "prof";
    }

    @Override
    public boolean doModulesMatch(Set<String> modulesSet, boolean needsAllModules) {
        if (modulesSet.size() > 1 && needsAllModules) {
            return false;
        }

        return modulesSet.stream()
                .anyMatch(module -> StringUtil.containsWordIgnoreCase(this.moduleCode.value, module));
    }
}
