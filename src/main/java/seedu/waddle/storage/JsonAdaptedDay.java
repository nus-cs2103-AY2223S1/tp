package seedu.waddle.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.item.Day;
import seedu.waddle.model.item.Item;

/**
 * Jackson-friendly version of {@link Day}.
 */
public class JsonAdaptedDay {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";
    public static final String MESSAGE_CLASHING_ITEMS = "Day %d contains Items with clashing timings!";

    private final Integer dayNumber;

    private final List<JsonAdaptedItem> items = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDay} with the given day details.
     */
    @JsonCreator
    public JsonAdaptedDay(@JsonProperty("dayNumber") Integer dayNumber,
                          @JsonProperty("items") List<JsonAdaptedItem> items) {
        this.dayNumber = dayNumber;
        this.items.addAll(items);
    }

    /**
     * Converts a given {@code Day} into this class for Jackson use.
     */
    public JsonAdaptedDay(Day source) {
        dayNumber = source.getDayNumber();
        for (Item item : source.getItemList()) {
            items.add(new JsonAdaptedItem(item));
        }
    }

    /**
     * Converts this Jackson-friendly adapted item object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted item.
     */
    public Day toModelType() throws IllegalValueException {

        if (dayNumber == null) {
            // TODO change to getSimpleName?
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Day Number"));
        }
        final int modelDayNumber = dayNumber;

        Day day = new Day(modelDayNumber);
        for (JsonAdaptedItem jsonAdaptedItem : items) {
            Item item = jsonAdaptedItem.toModelType();
            try {
                day.addItem(item);
            } catch (CommandException e) {
                throw new IllegalValueException(String.format(MESSAGE_CLASHING_ITEMS, modelDayNumber));
            }
        }

        return day;
    }
}
