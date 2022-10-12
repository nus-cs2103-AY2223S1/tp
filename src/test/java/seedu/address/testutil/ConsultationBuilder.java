package seedu.address.testutil;

import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationModule;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.consultation.ConsultationTimeslot;
import seedu.address.model.consultation.ConsultationVenue;

/**
 * Class to create an instance of consultation to test.
 * @return
 */
public class ConsultationBuilder {
    public static final String DEFAULT_NAME = "JakeKim";
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_VENUE = "COM1-0203";
    public static final String DEFAULT_TIMESLOT = "15:00-17:00";

    public static final String DEFAULT_DESCRIPTION = "Testing";

    private ConsultationName consultationName;
    private ConsultationModule consultationModule;
    private ConsultationVenue consultationVenue;
    private ConsultationTimeslot consultationTimeslot;
    private ConsultationDescription consultationDescription;

    /**
     * Creates a {@code ConsultationBuilder} with the default details.
     */
    public ConsultationBuilder() {
        consultationName = new ConsultationName(DEFAULT_NAME);
        consultationModule = new ConsultationModule(DEFAULT_MODULE);
        consultationVenue = new ConsultationVenue(DEFAULT_VENUE);
        consultationTimeslot = new ConsultationTimeslot(DEFAULT_TIMESLOT);
        consultationDescription = new ConsultationDescription(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the ConsultationBuilder with the data of {@code ConsultationToCopy}.
     */
    public ConsultationBuilder(Consultation consultationToCopy) {
        consultationName = consultationToCopy.getName();
        consultationModule = consultationToCopy.getModule();
        consultationVenue = consultationToCopy.getVenue();
        consultationTimeslot = consultationToCopy.getTimeslot();
        consultationDescription = consultationToCopy.getDescription();
    }

    /**
     * Sets the {@code ConsultationName} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withName(String name) {
        this.consultationName = new ConsultationName(name);
        return this;
    }

    /**
     * Sets the {@code ConsultationModule} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withModule(String module) {
        this.consultationModule = new ConsultationModule(module);
        return this;
    }

    /**
     * Sets the {@code ConsultationVenue} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withVenue(String venue) {
        this.consultationVenue = new ConsultationVenue(venue);
        return this;
    }

    /**
     * Sets the {@code ConsultationTimeslot} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withTimeslot(String timeslot) {
        this.consultationTimeslot = new ConsultationTimeslot(timeslot);
        return this;
    }

    /**
     * Sets the {@code ConsultationDescription} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withDescription(String description) {
        this.consultationDescription = new ConsultationDescription(description);
        return this;
    }

    /**
     * Construct an instance of consultation to test.
     * @return
     */
    public Consultation build() {
        return new Consultation(consultationName, consultationModule, consultationVenue, consultationTimeslot,
                consultationDescription);
    }

}
