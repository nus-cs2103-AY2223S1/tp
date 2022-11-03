package seedu.address.testutil;

import seedu.address.model.tutorial.Content;
import seedu.address.model.tutorial.Group;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;

/**
 * A utility class to help with building Tutorial objects
 */
public class TutorialBuilder {

    public static final String DEFAULT_CONTENT = "UML Diagrams";
    public static final String DEFAULT_GROUP = "T08";
    public static final String DEFAULT_TIME = "2022-10-01 0800";
    public static final boolean DEFAULT_STATUS = false;

    private Content content;
    private Group group;
    private Time time;
    private boolean status;

    /**
     * Creates a {@code TutorialBuilder} with the default details
     */
    public TutorialBuilder() {
        content = new Content(DEFAULT_CONTENT);
        group = new Group(DEFAULT_GROUP);
        time = new Time(DEFAULT_TIME);
        status = DEFAULT_STATUS;
    }

    /**
     * Initializes the TutorialBuilder with the data of {@code tutorialToCopy}
     */
    public TutorialBuilder(Tutorial tutorialToCopy) {
        content = tutorialToCopy.getContent();
        group = tutorialToCopy.getGroup();
        time = tutorialToCopy.getTime();
        status = tutorialToCopy.getStatus();
    }

    /**
     * Sets the {@code Content} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withContent(String content) {
        this.content = new Content(content);
        return this;
    }

    /**
     * Sets the {@code Group} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withGroup(String group) {
        this.group = new Group(group);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withStatus(boolean status) {
        this.status = status;
        return this;
    }

    public Tutorial build() {
        return new Tutorial(group, content, time, status);
    }

}
