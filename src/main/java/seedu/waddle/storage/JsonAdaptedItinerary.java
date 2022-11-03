package seedu.waddle.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.model.item.Day;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.exceptions.DuplicateItemException;
import seedu.waddle.model.itinerary.Budget;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.People;
import seedu.waddle.model.text.Text;

/**
 * Jackson-friendly version of {@link Itinerary}.
 */
class JsonAdaptedItinerary {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Itinerary's %s field is missing!";
    public static final String MESSAGE_DUPLICATE_ITEM = "Item list contains duplicate items.";

    private final String description;
    private final String country;
    private final String startDate;
    private final String duration;
    private final String people;
    private final String budget;

    private final List<JsonAdaptedItem> items = new ArrayList<>();
    private final List<JsonAdaptedDay> days = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given itinerary details.
     */
    @JsonCreator
    public JsonAdaptedItinerary(@JsonProperty("description") String description,
                                @JsonProperty("country") String country,
                                @JsonProperty("startDate") String startDate,
                                @JsonProperty("duration") String duration,
                                @JsonProperty("people") String people,
                                @JsonProperty("budget") String budget,
                                @JsonProperty("items") List<JsonAdaptedItem> items,
                                @JsonProperty("days") List<JsonAdaptedDay> days) {
        this.description = description;
        this.country = country;
        this.startDate = startDate;
        this.duration = duration;
        this.people = people;
        this.budget = budget;
        this.items.addAll(items);
        this.days.addAll(days);
    }

    /**
     * Converts a given {@code Itinerary} into this class for Jackson use.
     */
    public JsonAdaptedItinerary(Itinerary source) {
        description = source.getDescription().description;
        country = source.getCountry().country;
        startDate = source.getStartDate().date.toString();
        duration = source.getDuration().toString();
        people = source.getPeople().numOfPeople;
        budget = Text.MONEY_SAVE_FORMATTER.format(source.getBudget().getValue());
        for (Item item : source.getItemList()) {
            items.add(new JsonAdaptedItem(item));
        }
        for (Day day : source.getDays()) {
            days.add(new JsonAdaptedDay(day));
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Itinerary toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        if (country == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Country.class.getSimpleName()));
        }
        if (!Country.isValidCountry(country)) {
            throw new IllegalValueException(Country.MESSAGE_CONSTRAINTS);
        }

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(startDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }

        if (duration == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItineraryDuration.class.getSimpleName()));
        }
        if (!ItineraryDuration.isValidDuration(duration)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }

        if (people == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    People.class.getSimpleName()));
        }
        if (!People.isValidPeople(people)) {
            throw new IllegalValueException(People.MESSAGE_CONSTRAINTS);
        }

        if (budget == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Budget.class.getSimpleName()));
        }
        if (!Budget.isValidBudget(budget)) {
            throw new IllegalValueException(Budget.MESSAGE_CONSTRAINTS);
        }
        final Description modelName = new Description(description);
        final Country modelCountry = new Country(country);
        final Date modelStartDate = new Date(startDate);
        final ItineraryDuration modelDuration = new ItineraryDuration(duration);
        final People modelPeople = new People(people);
        final Budget modelBudget = new Budget(budget);

        Itinerary itinerary = new Itinerary(modelName, modelCountry, modelStartDate, modelDuration,
                modelPeople, modelBudget);

        final List<Day> modelDays = new ArrayList<>();
        for (JsonAdaptedDay jsonAdaptedDay : days) {
            Day day = jsonAdaptedDay.toModelType();
            modelDays.add(day);
        }
        itinerary.setDays(modelDays);
        itinerary.calculateSpending();

        for (JsonAdaptedItem jsonAdaptedItem : items) {
            Item item = jsonAdaptedItem.toModelType();
            try {
                itinerary.addItem(item);
            } catch (DuplicateItemException e) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITEM);
            }
        }

        return itinerary;
    }

}
