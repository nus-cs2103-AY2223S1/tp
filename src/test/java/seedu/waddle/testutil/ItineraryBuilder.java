package seedu.waddle.testutil;

import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.Name;
import seedu.waddle.model.itinerary.People;

/**
 * A utility class to help with building Itinerary objects.
 */
public class ItineraryBuilder {
    public static final String DEFAULT_NAME = "Summer";
    public static final String DEFAULT_COUNTRY = "FRANCE";
    public static final String DEFAULT_START_DATE = "2022-10-14";
    public static final String DEFAULT_END_DATE = "2022-10-15";
    public static final String DEFAULT_PEOPLE = "1";

    private Name name;
    private Country country;
    private Date startDate;
    private Date endDate;
    private People people;

    /**
     * Creates a {@code ItineraryBuilder} with the default details.
     */
    public ItineraryBuilder() {
        name = new Name(DEFAULT_NAME);
        country = new Country(DEFAULT_COUNTRY);
        startDate = new Date(DEFAULT_START_DATE);
        endDate = new Date(DEFAULT_END_DATE);
        people = new People(DEFAULT_PEOPLE);
    }

    /**
     * Initializes the ItineraryBuilder with the data of {@code itineraryToCopy}.
     */
    public ItineraryBuilder(Itinerary itineraryToCopy) {
        name = itineraryToCopy.getName();
        country = itineraryToCopy.getCountry();
        startDate = itineraryToCopy.getStartDate();
        endDate = itineraryToCopy.getEndDate();
        people = itineraryToCopy.getPeople();
    }

    /**
     * Sets the {@code Name} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Country} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withCountry(String country) {
        this.country = new Country(country);
        return this;
    }

    /**
     * Sets the {@code Start Date} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withStartDate(String startDate) {
        this.startDate = new Date(startDate);
        return this;
    }

    /**
     * Sets the {@code End Date} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withEndDate(String endDate) {
        this.endDate = new Date(endDate);
        return this;
    }

    /**
     * Sets the {@code People} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withPeople(String people) {
        this.people = new People(people);
        return this;
    }

    public Itinerary build() {
        return new Itinerary(name, country, startDate, endDate, people);
    }

}
