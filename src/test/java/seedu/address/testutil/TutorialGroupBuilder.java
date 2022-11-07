package seedu.address.testutil;

import seedu.address.model.student.TutorialGroup;


/**
 * A utility class to help with building Student objects.
 */
public class TutorialGroupBuilder {

    public static final String DEFAULT_TUTORIAL_GROUP = "T01";

    private TutorialGroup tutorialGroup;


    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public TutorialGroupBuilder() {
        tutorialGroup = new TutorialGroup(DEFAULT_TUTORIAL_GROUP);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code StudentToCopy}.
     */
    public TutorialGroupBuilder(TutorialGroup tutorialGroupToCopy) {
        tutorialGroup = tutorialGroupToCopy;
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public TutorialGroupBuilder withName(String name) {
        tutorialGroup = new TutorialGroup(name);
        return this;
    }

    public TutorialGroup build() {
        return this.tutorialGroup;
    }

}
