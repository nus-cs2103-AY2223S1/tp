package seedu.intrack.testutil;

import seedu.intrack.model.InTrack;
import seedu.intrack.model.internship.Internship;

/**
 * A utility class to help with building InTrack objects.
 * Example usage: <br>
 *     {@code InTrack ab = new InTrackBuilder().withInternship("John", "Doe").build();}
 */
public class InTrackBuilder {

    private InTrack inTrack;

    public InTrackBuilder() {
        inTrack = new InTrack();
    }

    public InTrackBuilder(InTrack inTrack) {
        this.inTrack = inTrack;
    }

    /**
     * Adds a new {@code Internship} to the {@code InTrack} that we are building.
     */
    public InTrackBuilder withInternship(Internship internship) {
        inTrack.addInternship(internship);
        return this;
    }

    public InTrack build() {
        return inTrack;
    }
}
