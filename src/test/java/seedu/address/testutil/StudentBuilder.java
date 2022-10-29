package seedu.address.testutil;

import java.util.Collections;
import java.util.HashSet;
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

    private Set<ModuleCode> moduleCodes;
    private Year year;
    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        super();
        this.moduleCodes = new HashSet<>();
        this.moduleCodes.add(new ModuleCode(DEFAULT_MODULE_CODE));
        this.year = new Year(DEFAULT_YEAR, false);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        super(personToCopy);
        this.moduleCodes = new HashSet<>(personToCopy.getModuleCodes());
        this.year = personToCopy.getYear();
    }

    /**
     * Parses the {@code moduleCodes} into a {@code Set<moduleCode>}
     * and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withModuleCodes(String ... moduleCodes) {
        this.moduleCodes = SampleDataUtil.getModuleCodeSet(moduleCodes);
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
    public Student build() {
        return new Student(getName(), getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                getGithubUsername(), getModuleCodes(), getYear());
    }

    public Set<ModuleCode> getModuleCodes() {
        return Collections.unmodifiableSet(moduleCodes);
    }

    public Year getYear() {
        return year;
    }
}
