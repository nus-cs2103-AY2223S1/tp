package seedu.address.model.consultation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.commons.ModuleCode;
import seedu.address.model.commons.Venue;
import seedu.address.model.datetime.DatetimeRange;

/**
 * Represents a Consultation in ModQuik.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Consultation {
    private final ConsultationName name;
    private final ModuleCode module;
    private final Venue venue;
    private final DatetimeRange timeslot;
    private final ConsultationDescription description;

    /**
     * Every field must be present and not null.
     */
    public Consultation(ConsultationName name, ModuleCode module, Venue venue,
                        DatetimeRange timeslot, ConsultationDescription description) {
        requireAllNonNull(name, module, venue, timeslot, description);
        this.name = name;
        this.module = module;
        this.venue = venue;
        this.timeslot = timeslot;
        this.description = description;
    }

    public ConsultationName getName() {
        return name;
    }

    public ModuleCode getModule() {
        return module;
    }

    public Venue getVenue() {
        return venue;
    }

    public DatetimeRange getTimeslot() {
        return timeslot;
    }

    public ConsultationDescription getDescription() {
        return description;
    }

    /**
     * Returns true if both Consultations have the same name.
     * This defines a weaker notion of equality between two Consultations.
     */
    public boolean isSameConsultation(Consultation otherConsultation) {
        if (otherConsultation == this) {
            return true;
        }

        return otherConsultation != null
                && otherConsultation.getName().equals(getName());
    }

    /**
     * Returns true if both Consultations have the same venue and timeslot.
     */
    public boolean isClashConsultation(Consultation otherConsultation) {
        if (otherConsultation == this) {
            return true;
        }

        return otherConsultation != null
                && otherConsultation.getVenue().equals(getVenue())
                && otherConsultation.getTimeslot().equals(getTimeslot());
    }

    /**
     * Returns true if both Consultations have the same identity and data fields.
     * This defines a stronger notion of equality between two Consultations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consultation)) {
            return false;
        }

        Consultation otherConsultation = (Consultation) other;
        return otherConsultation.getName().equals(getName())
                && otherConsultation.getModule().equals(getModule())
                && otherConsultation.getVenue().equals(getVenue())
                && otherConsultation.getTimeslot().equals(getTimeslot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, module, venue, timeslot, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Module: ")
                .append(getModule())
                .append("; Venue: ")
                .append(getVenue())
                .append("; Timeslot: ")
                .append(getTimeslot())
                .append("; Description: ")
                .append(getDescription());

        return builder.toString();
    }
}
