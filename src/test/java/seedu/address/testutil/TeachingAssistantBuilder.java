package seedu.address.testutil;

import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.TeachingAssistant;

/**
 * A utility class to help with building Person objects.
 */
public class TeachingAssistantBuilder extends PersonBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS1231S";

    private final ModuleCode moduleCode;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public TeachingAssistantBuilder() {
        super();
        this.moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
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
            getLocation(), getGithubUsername());
    }

}
