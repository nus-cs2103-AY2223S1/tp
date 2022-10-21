package seedu.address.model.person;

import java.util.List;
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

    private final Specialisation field;

    private final OfficeHour officeHour;
    /**
     * Every field must be present and not null.
     */
    public Professor(Name name, ModuleCode moduleCode, Phone phone, Email email, Gender gender, Set<Tag> tags,
                     Location location, GithubUsername username,
                     Rating rating, Specialisation field, OfficeHour officeHour) {
        super(name, phone, email, gender, tags, location, username);
        this.moduleCode = moduleCode;
        this.rating = rating;
        this.field = field;
        this.officeHour = officeHour;
    }

    public ModuleCode getModuleCode() {
        return this.moduleCode;
    }

    public Rating getRating() {
        return this.rating;
    }

    public Specialisation getSpecialisation() {
        return this.field;
    }

    public OfficeHour getOfficeHour() {
        return this.officeHour;
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
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLocation().equals(getLocation())
                && otherPerson.getUsername().equals(getUsername())
                && otherPerson.getModuleCode().equals(getModuleCode())
                && otherPerson.getSpecialisation().equals(getSpecialisation())
                && otherPerson.getOfficeHour().equals(getOfficeHour());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                getUsername(), moduleCode, rating, field, officeHour);
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Name: ")
                .append(getName());

        if (!getSpecialisation().value.equals(Specialisation.EMPTY_SPECIALISATION)) {
            builder.append("; Specialisation: ")
                    .append(getSpecialisation());
        }

        builder.append("; Module Code: ")
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

        if (!getOfficeHour().value.equals(OfficeHour.EMPTY_OFFICE_HOUR)) {
            builder.append("; Office hour: ")
                    .append(getOfficeHour());
        }

        builder.append("; Location: ")
                .append(getLocation());

        if (!getRating().value.equals(Rating.EMPTY_RATING)) {
            builder.append("; Rating: ")
                    .append(getRating());
        }

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
        return specList.stream().anyMatch(specialisation
                -> specialisation.equals(this.getSpecialisation().value.toLowerCase()));
    }
}
