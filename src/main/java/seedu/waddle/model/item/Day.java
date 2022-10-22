package seedu.waddle.model.item;

import seedu.waddle.model.itinerary.Itinerary;

/**
 * Encapsulates a day in an itinerary.
 */
public class Day {
    private int dayNumber;
    private Itinerary parentItinerary;
    private UniqueItemList itemList;

    /**
     * Constructor.
     *
     * @param dayNumber The day number.
     * @param parentItinerary The itinerary object it is stored in.
     */
    public Day(int dayNumber, Itinerary parentItinerary) {
        this.dayNumber = dayNumber;
        this.parentItinerary = parentItinerary;
    }
}
