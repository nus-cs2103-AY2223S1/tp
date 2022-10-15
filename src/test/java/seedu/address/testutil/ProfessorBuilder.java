package seedu.address.testutil;

import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Rating;

/**
 * A utility class to help with building Person objects.
 */
public class ProfessorBuilder extends PersonBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS1231S";
    public static final String DEFAULT_RATING = "5";

    private ModuleCode moduleCode;
    private Rating rating;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ProfessorBuilder() {
        super();
        this.moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        this.rating = new Rating(DEFAULT_RATING);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ProfessorBuilder(Professor personToCopy) {
        super(personToCopy);
        moduleCode = personToCopy.getModuleCode();
    }

    /**
     * Builds a Professor.
     *
     * @return the Professor that has been built.
     */
    public Professor build() {
        return new Professor(getName(), moduleCode, getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                rating);
    }
    /**
     * Sets the {@code ModuleCode} of the {@code Professor} that we are building.
     */
    public PersonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

}
