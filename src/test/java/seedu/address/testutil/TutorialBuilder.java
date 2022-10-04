package seedu.address.testutil;

import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

/**
 * A utility class to help with building Tutorial objects.
 */
public class TutorialBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private TutorialName tutorialName;

    /**
     * Creates a {@code TutorialBuilder} with the default details.
     */
    public TutorialBuilder() {
        tutorialName = new TutorialName(DEFAULT_NAME);
    }

    /**
     * Initializes the TutorialBuilder with the data of {@code tutorialToCopy}.
     */
    public TutorialBuilder(Tutorial tutorialToCopy) {
        tutorialName = tutorialToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withName(String name) {
        this.tutorialName = new TutorialName(name);
        return this;
    }

    public Tutorial build() {
        return new Tutorial(tutorialName);
    }

}
