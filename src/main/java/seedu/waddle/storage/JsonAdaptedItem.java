package seedu.waddle.storage;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.text.Text;

/**
 * Jackson-friendly version of {@link Item}.
 */
public class JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    private final String description;
    private final Integer stars;
    private final String cost;
    private final String duration;
    private final String startTime;

    /**
     * Constructs a {@code JsonAdaptedItem} with the given item details.
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("description") String description,
                           @JsonProperty("priority") Integer stars,
                           @JsonProperty("cost") String cost,
                           @JsonProperty("duration") String duration,
                           @JsonProperty("startTime") String startTime) {
        this.description = description;
        this.stars = stars;
        this.cost = cost;
        this.duration = duration;
        this.startTime = startTime;
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        description = source.getDescription().description;
        stars = source.getPriority().getValue();
        cost = Text.MONEY_SAVE_FORMATTER.format(source.getCost().getValue());
        duration = source.getDuration().toString();
        if (source.getStartTime() == null) {
            startTime = null;
        } else {
            startTime = source.getStartTime().toString();
        }
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public Item toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Item.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        if (stars == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(stars)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }

        if (cost == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName()));
        }
        if (!Cost.isValidCost(cost)) {
            throw new IllegalValueException(Cost.MESSAGE_CONSTRAINTS);
        }

        if (duration == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName()));
        }
        if (!Duration.isValidDuration(duration)) {
            throw new IllegalValueException(Duration.MESSAGE_CONSTRAINTS);
        }

        final Description modelDescription = new Description(description);
        final Priority modelPriority = new Priority(stars);
        final Cost modelCost = new Cost(cost);
        final Duration modelDuration = new Duration(duration);

        Item item = new Item(modelDescription, modelPriority, modelCost, modelDuration);

        if (startTime != null) {
            final LocalTime modelStartTime = LocalTime.parse(startTime);
            item.setStartTime(modelStartTime);
        }

        return item;
    }

}
