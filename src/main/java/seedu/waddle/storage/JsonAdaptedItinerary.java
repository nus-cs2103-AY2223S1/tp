package seedu.waddle.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.Name;
import seedu.waddle.model.itinerary.People;

/**
 * Jackson-friendly version of {@link Itinerary}.
 */
class JsonAdaptedItinerary {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Itinerary's %s field is missing!";
    public static final String MESSAGE_DUPLICATE_ITEM = "Item list contains duplicate items.";

    private final String name;
    private final String country;
    private final String startDate;
    private final String endDate;
    private final String people;

    private final List<JsonAdaptedItem> items = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given itinerary details.
     */
    @JsonCreator
    public JsonAdaptedItinerary(@JsonProperty("name") String name, @JsonProperty("country") String country,
                                @JsonProperty("startDate") String startDate, @JsonProperty("endDate") String endDate,
                                @JsonProperty("people") String people,
                                @JsonProperty("items") List<JsonAdaptedItem> items) {
        this.name = name;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.people = people;
        this.items.addAll(items);
    }

    /**
     * Converts a given {@code Itinerary} into this class for Jackson use.
     */
    public JsonAdaptedItinerary(Itinerary source) {
        name = source.getName().fullName;
        country = source.getCountry().country;
        startDate = source.getStartDate().date;
        endDate = source.getEndDate().date;
        people = source.getPeople().numOfPeople;
        for (Item item : source.getItemList()) {
            items.add(new JsonAdaptedItem(item));
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Itinerary toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (country == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Country.class.getSimpleName()));
        }
        if (!Country.isValidCountry(country)) {
            throw new IllegalValueException(Country.MESSAGE_CONSTRAINTS);
        }
        final Country modelCountry = new Country(country);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(startDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelStartDate = new Date(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(endDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelEndDate = new Date(endDate);

        if (people == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, People.class.getSimpleName()));
        }
        if (!People.isValidPeople(people)) {
            throw new IllegalValueException(People.MESSAGE_CONSTRAINTS);
        }
        final People modelPeople = new People(people);

        Itinerary itinerary = new Itinerary(modelName, modelCountry, modelStartDate, modelEndDate, modelPeople);
        for (JsonAdaptedItem jsonAdaptedItem : items) {
            Item item = jsonAdaptedItem.toModelType();
            if (itinerary.hasItem(item)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
            itinerary.addItem(item);
        }
        return itinerary;
    }

}
