package seedu.waddle.model.item;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Encapsulates a day in an itinerary.
 */
public class Day {
    private final Comparator<Item> startTimeComparator = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            return item1.getStartTime().compareTo(item2.getStartTime());
        }
    };
    private final int dayNumber;
    private final Itinerary parentItinerary;
    private UniqueItemList itemList;

    /**
     * Constructor.
     *
     * @param dayNumber       The day number.
     * @param parentItinerary The itinerary object it is stored in.
     */
    public Day(int dayNumber, Itinerary parentItinerary) {
        this.dayNumber = dayNumber;
        this.parentItinerary = parentItinerary;
    }

    public void addItem(Item item) throws CommandException {
        ArrayList<Item> conflictingItems = getConflictingItems(item);
        StringBuilder conflicts = new StringBuilder();
        if (!conflictingItems.isEmpty()) {
            for (Item cItem : conflictingItems) {
                conflicts.append(cItem.getDescription()).append(": ").append(cItem.getStartTime())
                        .append(" - ").append(cItem.getEndTime()).append("\n");
            }
            throw new CommandException(String.format(Messages.MESSAGE_CONFLICTING_ITEMS, conflicts));
        }
        this.itemList.add(item);
        this.itemList.sort(startTimeComparator);
    }

    public Item removeItem(Index index) {
        Item removedItem = this.itemList.remove(index.getZeroBased());
        removedItem.resetStartTime();
        return removedItem;
    }

    public UniqueItemList deleteDay() {
        for (Item item : this.itemList) {
            item.resetStartTime();
        }
        return this.itemList;
    }

    /**
     * For a given item, return a list of items that conflict in time.
     *
     * @param newItem The item to check for.
     * @return A list of conflicting items, possibly an empty list.
     */
    public ArrayList<Item> getConflictingItems(Item newItem) {
        ArrayList<Item> conflictingItems = new ArrayList<>();
        for (Item item : this.itemList) {
            // same start time
            boolean sameStartTime = item.getStartTime().equals(newItem.getStartTime());
            // start time of new item is within the duration of a preceding item
            boolean startTimeConflict = newItem.getStartTime().isBefore(item.getEndTime());
            // end time of new item eats into a proceeding item
            boolean endTimeConflict = newItem.getEndTime().isAfter(item.getStartTime());

            if (sameStartTime || startTimeConflict || endTimeConflict) {
                conflictingItems.add(item);
            }
        }
        return conflictingItems;
    }
}
