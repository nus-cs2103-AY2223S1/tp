package seedu.address.testutil;

import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialDay;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialTimeslot;
import seedu.address.model.tutorial.TutorialVenue;

/**
 * A utility class to help with building Tutorial objects.
 */
public class TutorialBuilder {

    public static final String DEFAULT_NAME = "W01";
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_VENUE = "COM1-0203";
    public static final String DEFAULT_TIMESLOT = "15:00-17:00";
    public static final String DEFAULT_DAY = "3";

    private TutorialName tutorialName;
    private TutorialModule tutorialModule;
    private TutorialVenue tutorialVenue;
    private TutorialTimeslot tutorialTimeslot;
    private TutorialDay tutorialDay;

    /**
     * Creates a {@code TutorialBuilder} with the default details.
     */
    public TutorialBuilder() {
        tutorialName = new TutorialName(DEFAULT_NAME);
        tutorialModule = new TutorialModule(DEFAULT_MODULE);
        tutorialVenue = new TutorialVenue(DEFAULT_VENUE);
        tutorialTimeslot = new TutorialTimeslot(DEFAULT_TIMESLOT);
        tutorialDay = new TutorialDay(DEFAULT_DAY);
    }

    /**
     * Initializes the TutorialBuilder with the data of {@code tutorialToCopy}.
     */
    public TutorialBuilder(Tutorial tutorialToCopy) {
        tutorialName = tutorialToCopy.getName();
        tutorialModule = tutorialToCopy.getModule();
        tutorialVenue = tutorialToCopy.getVenue();
        tutorialTimeslot = tutorialToCopy.getTimeslot();
        tutorialDay = tutorialToCopy.getDay();
    }

    /**
     * Sets the {@code TutorialName} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withName(String name) {
        this.tutorialName = new TutorialName(name);
        return this;
    }

    /**
     * Sets the {@code TutorialModule} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withModule(String module) {
        this.tutorialModule = new TutorialModule(module);
        return this;
    }

    /**
     * Sets the {@code TutorialVenue} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withVenue(String venue) {
        this.tutorialVenue = new TutorialVenue(venue);
        return this;
    }

    /**
     * Sets the {@code TutorialTimeslot} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withTimeslot(String timeslot) {
        this.tutorialTimeslot = new TutorialTimeslot(timeslot);
        return this;
    }

    /**
     * Sets the {@code TutorialDay} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withDay(String day) {
        this.tutorialDay = new TutorialDay(day);
        return this;
    }

    public Tutorial build() {
        return new Tutorial(tutorialName, tutorialModule, tutorialVenue, tutorialTimeslot, tutorialDay);
    }

}
