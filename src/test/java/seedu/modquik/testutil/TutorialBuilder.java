package seedu.modquik.testutil;

import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.commons.Venue;
import seedu.modquik.model.datetime.WeeklyTimeslot;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.model.tutorial.TutorialName;

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
    private ModuleCode moduleCode;
    private Venue venue;
    private String tutorialTimeStart;
    private String tutorialTimeEnd;
    private String tutorialDay;

    /**
     * Creates a {@code TutorialBuilder} with the default details.
     */
    public TutorialBuilder() {
        tutorialName = new TutorialName(DEFAULT_NAME);
        moduleCode = new ModuleCode(DEFAULT_MODULE);
        venue = new Venue(DEFAULT_VENUE);
        tutorialDay = "1";
        tutorialTimeStart = "08:00";
        tutorialTimeEnd = "09:00";
    }

    /**
     * Initializes the TutorialBuilder with the data of {@code tutorialToCopy}.
     */
    public TutorialBuilder(Tutorial tutorialToCopy) {
        tutorialName = tutorialToCopy.getName();
        moduleCode = tutorialToCopy.getModule();
        venue = tutorialToCopy.getVenue();
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
     * Sets the {@code ModuleCode} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withModule(String module) {
        this.moduleCode = new ModuleCode(module);
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code Tutorial} that we are building.
     */
    public TutorialBuilder withVenue(String venue) {
        this.venue = new Venue(venue);
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
        WeeklyTimeslot timeslot = WeeklyTimeslot.fromFormattedString(tutorialDay, tutorialTimeStart, tutorialTimeEnd);
        return new Tutorial(tutorialName, moduleCode, venue, timeslot);
    }

}
