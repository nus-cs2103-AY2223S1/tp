package seedu.address.testutil;

import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Rating;
import seedu.address.model.person.TeachingAssistant;

/**
 * A utility class to help with building Person objects.
 */
public class TeachingAssistantBuilder extends PersonBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS1231S";

    public static final String DEFAULT_RATING = "5";

    private ModuleCode moduleCode;
    private Rating rating;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public TeachingAssistantBuilder() {
        super();
        this.moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        this.rating = new Rating(DEFAULT_RATING, false);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public TeachingAssistantBuilder(TeachingAssistant personToCopy) {
        super(personToCopy);
        moduleCode = personToCopy.getModuleCode();
    }

    /**
     * Builds a TeachingAssistant
     */
    public TeachingAssistant build() {
        return new TeachingAssistant(getName(), moduleCode, getPhone(), getEmail(), getGender(), getTags(),
            getLocation(), getGithubUsername(), rating);
    }

    /**
     * Sets the {@code Rating} of the {@code TeachingAssistant} that we are building.
     */
    public TeachingAssistantBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code TeachingAssistant} that we are building.
     */
    public TeachingAssistantBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

}
