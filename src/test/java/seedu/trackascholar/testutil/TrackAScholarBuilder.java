package seedu.trackascholar.testutil;

import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * A utility class to help with building TrackAScholar objects.
 * Example usage: <br>
 *     {@code TrackAScholar ab = new TrackAScholarBuilder().withApplicant("John", "Doe").build();}
 */
public class TrackAScholarBuilder {

    private TrackAScholar trackAScholar;

    public TrackAScholarBuilder() {
        trackAScholar = new TrackAScholar();
    }

    public TrackAScholarBuilder(TrackAScholar trackAScholar) {
        this.trackAScholar = trackAScholar;
    }

    /**
     * Adds a new {@code Applicant} to the {@code TrackAScholar} that we are building.
     */
    public TrackAScholarBuilder withApplicant(Applicant applicant) {
        trackAScholar.addApplicant(applicant);
        return this;
    }

    public TrackAScholar build() {
        return trackAScholar;
    }
}
