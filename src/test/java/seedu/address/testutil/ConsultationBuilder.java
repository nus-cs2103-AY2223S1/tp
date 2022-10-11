package seedu.address.testutil;

import seedu.address.model.consultation.*;

public class ConsultationBuilder {
    public static final String DEFAULT_NAME = "JakeKim";
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_VENUE = "COM1-0203";
    public static final String DEFAULT_TIMESLOT = "15:00-17:00";

    public static final String DEFAULT_DESCRIPTION = "Testing";

    private ConsultationName ConsultationName;
    private ConsultationModule ConsultationModule;
    private ConsultationVenue ConsultationVenue;
    private ConsultationTimeslot ConsultationTimeslot;
    private ConsultationDescription ConsultationDescription;

    /**
     * Creates a {@code ConsultationBuilder} with the default details.
     */
    public ConsultationBuilder() {
        ConsultationName = new ConsultationName(DEFAULT_NAME);
        ConsultationModule = new ConsultationModule(DEFAULT_MODULE);
        ConsultationVenue = new ConsultationVenue(DEFAULT_VENUE);
        ConsultationTimeslot = new ConsultationTimeslot(DEFAULT_TIMESLOT);
        ConsultationDescription = new ConsultationDescription(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the ConsultationBuilder with the data of {@code ConsultationToCopy}.
     */
    public ConsultationBuilder(Consultation ConsultationToCopy) {
        ConsultationName = ConsultationToCopy.getName();
        ConsultationModule = ConsultationToCopy.getModule();
        ConsultationVenue = ConsultationToCopy.getVenue();
        ConsultationTimeslot = ConsultationToCopy.getTimeslot();
        ConsultationDescription = ConsultationToCopy.getDescription();
    }

    /**
     * Sets the {@code ConsultationName} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withName(String name) {
        this.ConsultationName = new ConsultationName(name);
        return this;
    }

    /**
     * Sets the {@code ConsultationModule} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withModule(String module) {
        this.ConsultationModule = new ConsultationModule(module);
        return this;
    }

    /**
     * Sets the {@code ConsultationVenue} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withVenue(String venue) {
        this.ConsultationVenue = new ConsultationVenue(venue);
        return this;
    }

    /**
     * Sets the {@code ConsultationTimeslot} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withTimeslot(String timeslot) {
        this.ConsultationTimeslot = new ConsultationTimeslot(timeslot);
        return this;
    }

    public Consultation build() {
        return new Consultation(ConsultationName, ConsultationModule, ConsultationVenue, ConsultationTimeslot, ConsultationDescription);
    }

}
