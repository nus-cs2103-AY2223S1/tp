package seedu.address.testutil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Student;
import seedu.address.model.person.Year;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder extends PersonBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS1101S";
    public static final String DEFAULT_YEAR = "1";

    private Set<ModuleCode> moduleCodesSet;
    private ModuleCode moduleCode;
    private Year year;
    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        super();
        this.moduleCodesSet = new HashSet<>(List.of(new ModuleCode(DEFAULT_MODULE_CODE)));
        this.year = new Year(DEFAULT_YEAR, false);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        super(personToCopy);
        this.moduleCodesSet = new HashSet<>(personToCopy.getModuleCodes());
        this.year = personToCopy.getYear();
    }

    @Override
    public StudentBuilder withName(String name) {
        super.withName(name);
        return this;
    }

    /**
     * Parses the {@code moduleCodes} into a {@code Set<moduleCode>}
     * and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withModuleCodes(String ... moduleCodes) {
        this.moduleCodesSet = SampleDataUtil.getModuleCodeSet(moduleCodes);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code Student} that we are building.
     */
    public StudentBuilder withYear(String year) {
        this.year = new Year(year);
        return this;
    }

    @Override
    public StudentBuilder withGithubUsername(String username) {
        return (StudentBuilder) super.withGithubUsername(username);
    }

    @Override
    public StudentBuilder withTags(String... tags) {
        return (StudentBuilder) super.withTags(tags);
    }

    @Override
    public Student build() {
        return new Student(getName(), getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                getGithubUsername(), getModuleCodesSet(), getYear());
    }

    public Set<ModuleCode> getModuleCodesSet() {
        return Collections.unmodifiableSet(moduleCodesSet);
    }

    public Year getYear() {
        return year;
    }
}
