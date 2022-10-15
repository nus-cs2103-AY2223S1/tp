package seedu.address.testutil;

import seedu.address.model.datetime.WeeklyTimeslot;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialVenue;

/**
 * A utility class to help with building Tutorial objects.
 */
public class TutorialBuilder {

    public static final String DEFAULT_NAME = "W01";
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_VENUE = "COM1-0203";
    public static final String DEFAULT_TIMESLOT_START = "15:00";
    public static final String DEFAULT_TIMESLOT_END = "17:00";
    public static final String DEFAULT_DAY = "3";

    private TutorialName tutorialName;
    private TutorialModule tutorialModule;
    private TutorialVenue tutorialVenue;
    private String tutorialTimeStart;
    private String tutorialTimeEnd;
    private String tutorialDay;

    /**
     * Creates a {@code TutorialBuilder} with the default details.
     */
    public TutorialBuilder() {
        tutorialName = new TutorialName(DEFAULT_NAME);
        tutorialModule = new TutorialModule(DEFAULT_MODULE);
        tutorialVenue = new TutorialVenue(DEFAULT_VENUE);
        tutorialDay = "1";
        tutorialTimeStart = "08:00";
        tutorialTimeEnd = "09:00";
    }

    /**
     * Initializes the TutorialBuilder with the data of {@code tutorialToCopy}.
     */
    public TutorialBuilder(Tutorial tutorialToCopy) {
        tutorialName = tutorialToCopy.getName();
        tutorialModule = tutorialToCopy.getModule();
        tutorialVenue = tutorialToCopy.getVenue();
        //        tutorialTimeslot = tutorialToCopy.getTimeslot();
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
    public TutorialBuilder withTimeslot(String timeStart, String timeEnd) {
        tutorialTimeStart = timeStart;
        tutorialTimeEnd = timeEnd;
        return this;
    }

    /**
     * Sets the {@code TutorialDay} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withDay(String day) {
        this.tutorialDay = day;
        return this;
    }

    /**
     * Builds a Tutorial.
     *
     * @return A Tutorial
     */
    public Tutorial build() {
        WeeklyTimeslot timeslot = new WeeklyTimeslot(tutorialDay, tutorialTimeStart, tutorialTimeEnd);
        return new Tutorial(tutorialName, tutorialModule, tutorialVenue, timeslot);
    }

}
