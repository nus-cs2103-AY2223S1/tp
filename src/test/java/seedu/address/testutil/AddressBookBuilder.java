package seedu.address.testutil;

import seedu.address.model.TrackAScholar;
import seedu.address.model.applicant.Applicant;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code TrackAScholar ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private TrackAScholar trackAScholar;

    public AddressBookBuilder() {
        trackAScholar = new TrackAScholar();
    }

    public AddressBookBuilder(TrackAScholar trackAScholar) {
        this.trackAScholar = trackAScholar;
    }

    /**
     * Adds a new {@code Applicant} to the {@code TrackAScholar} that we are building.
     */
    public AddressBookBuilder withPerson(Applicant applicant) {
        trackAScholar.addApplicant(applicant);
        return this;
    }

    public TrackAScholar build() {
        return trackAScholar;
    }
}
