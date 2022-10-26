package seedu.waddle.model.item;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.item.exceptions.Period;

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
    private UniqueItemList itemList;

    /**
     * Constructor.
     *
     * @param dayNumber The day number.
     */
    public Day(int dayNumber) {
        this.dayNumber = dayNumber;
        this.itemList = new UniqueItemList();
    }

    /**
     * Adds an item to this day if there are no time conflicts.
     *
     * @param item The item to be added.
     * @throws CommandException Conflicting items message thrown if there are time conflicts.
     */
    public void addItem(Item item) throws CommandException {
        ArrayList<Item> conflictingItems = getConflictingItems(item);
        StringBuilder conflicts = new StringBuilder();
        if (!conflictingItems.isEmpty()) {
            for (Item cItem : conflictingItems) {
                conflicts.append("    ").append(cItem.getDescription()).append(": ").append(cItem.getStartTime())
                        .append(" - ").append(cItem.getEndTime()).append("\n");
            }
            throw new CommandException(String.format(Messages.MESSAGE_CONFLICTING_ITEMS, conflicts));
        }
        this.itemList.add(item);
        this.itemList.sort(startTimeComparator);
    }

    /**
     * Removes an item from this day. Resets the item's startTime field.
     *
     * @param index The index of the item to be removed.
     * @return The removed item.
     */
    public Item removeItem(Index index) {
        Item removedItem = this.itemList.remove(index.getZeroBased());
        //removedItem.resetStartTime();
        return removedItem;
    }

    public Item getItem(Index index) {
        return this.itemList.get(index.getZeroBased());
    }

    /**
     * Deletes the day. Resets the startTime field of all items in this day.
     *
     * @return The list of items stored in this day.
     */
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
    private ArrayList<Item> getConflictingItems(Item newItem) {
        ArrayList<Item> conflictingItems = new ArrayList<>();
        for (Item item : this.itemList) {
            // same start time
            boolean sameStartTime = item.getStartTime().equals(newItem.getStartTime());
            // start time of new item is within the duration of a preceding item
            boolean startTimeConflict = newItem.getStartTime().isAfter(item.getStartTime())
                && newItem.getStartTime().isBefore(item.getEndTime());
            // end time of new item eats into a proceeding item
            boolean endTimeConflict = newItem.getEndTime().isAfter(item.getStartTime())
                    && newItem.getEndTime().isBefore(item.getEndTime());

            if (sameStartTime || startTimeConflict || endTimeConflict) {
                conflictingItems.add(item);
            }
        }
        return conflictingItems;
    }

    public int getItemSize() {
        return itemList.getSize();
    }

    public boolean hasItem(Item item) {
        return this.itemList.contains(item);
    }

    public UniqueItemList getItemList() {
        return this.itemList;
    }

    public int getDayNumber() {
        return this.dayNumber;
    }

    /**
     * Compiles the vacant time slots in this day and formats it as a string.
     *
     * @return The vacant slots as a string.
     */
    public String getVacantSlots() {
        if (this.itemList.getSize() == 0) {
            return "Day " + (this.dayNumber + 1) + ":\n    Free!\n";
        }
        StringBuilder vacantSlots = new StringBuilder("Day ");
        vacantSlots.append((this.dayNumber + 1)).append(":").append(System.getProperty("line.separator"));

        ArrayList<Period> vacantPeriods = new ArrayList<>();
        Period toBeSplit = new Period(LocalTime.MIN, LocalTime.parse("23:59"));
        for (Item item : this.itemList) {
            vacantPeriods.addAll(splitTimeSlot(toBeSplit, new Period(item.getStartTime(), item.getEndTime())));
            if (vacantPeriods.size() > 0) {
                // remove the last period to continue splitting
                toBeSplit = vacantPeriods.remove(vacantPeriods.size() - 1);
            } else {
                toBeSplit = null;
                break;
            }
        }
        // add the last period back if there is any
        if (toBeSplit != null) {
            vacantPeriods.add(toBeSplit);
        }
        for (Period period : vacantPeriods) {
            vacantSlots.append("    ").append(period.getStart()).append(" - ")
                    .append(period.getEnd()).append(System.getProperty("line.separator"));
        }

        return vacantSlots.toString();
    }

    private ArrayList<Period> splitTimeSlot(Period big, Period small) {
        ArrayList<Period> splitPeriods = new ArrayList<>();
        if (big.getStart().equals(small.getStart()) && big.getEnd().equals(small.getEnd())) {
            return splitPeriods;
        } else if (big.getStart().equals(small.getStart())) {
            splitPeriods.add(new Period(small.getEnd(), big.getEnd()));
        } else if (big.getEnd().equals(small.getEnd())) {
            splitPeriods.add(new Period(big.getStart(), small.getStart()));
        } else if (small.getStart().isAfter(big.getStart()) && big.getEnd().isAfter(small.getEnd())) {
            splitPeriods.add(new Period(big.getStart(), small.getStart()));
            splitPeriods.add(new Period(small.getEnd(), big.getEnd()));
        }
        return splitPeriods;
    }
}
