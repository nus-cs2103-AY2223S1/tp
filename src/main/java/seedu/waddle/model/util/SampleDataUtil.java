package seedu.waddle.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.waddle.model.AddressBook;
import seedu.waddle.model.ReadOnlyAddressBook;
import seedu.waddle.model.itinerary.Address;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Email;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.Name;
import seedu.waddle.model.itinerary.People;
import seedu.waddle.model.itinerary.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Itinerary[] getSamplePersons() {
        return new Itinerary[] {
                new Itinerary(new Name("Graduation Trip"), new Country("Singapore"), new Date("2025-07-30"),
                        new Date("2025-08-05"), new People("5")),
                new Itinerary(new Name("Winter Trip"), new Country("Sweden"), new Date("2022-11-15"),
                        new Date("2022-11-20"), new People("3")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Itinerary sampleItinerary : getSamplePersons()) {
            sampleAb.addPerson(sampleItinerary);
        }
        return sampleAb;
    }

}