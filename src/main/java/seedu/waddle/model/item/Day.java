package seedu.waddle.model.item;

import static seedu.waddle.commons.core.Messages.MESSAGE_CONFLICTING_ITEMS;
import static seedu.waddle.commons.core.Messages.MESSAGE_ITEM_PAST_MIDNIGHT;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.PdfFieldInfo;
import seedu.waddle.logic.PdfFiller;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.item.exceptions.Period;
import seedu.waddle.model.text.Text;

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
    private final UniqueItemList itemList;

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
        Optional<ArrayList<Item>> conflictingItems = getConflictingItems(item);
        if (conflictingItems.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ITEM_PAST_MIDNIGHT, item.getDescription()));
        }
        StringBuilder conflicts = new StringBuilder();
        if (!conflictingItems.get().isEmpty()) {
            for (Item cItem : conflictingItems.get()) {
                conflicts.append("    ").append(cItem.getDescription()).append(": ").append(cItem.getStartTime())
                        .append(" - ").append(cItem.getEndTime()).append("\n");
            }
            throw new CommandException(String.format(MESSAGE_CONFLICTING_ITEMS, conflicts));
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
     * For a given item, return an Optional list of items that conflict in time.
     * An Optional with an empty list is returned if there are no conflicts.
     * If the item goes past midnight (not allowed), an empty Optional is returned.
     * If there are conflicting items, an Optional with the list of conflicting items are returned.
     *
     * @param newItem The item to check for.
     * @return A list of conflicting items, possibly an empty list.
     */
    private Optional<ArrayList<Item>> getConflictingItems(Item newItem) {
        ArrayList<Item> conflictingItems = new ArrayList<>();
        // item goes past midnight and overflows
        if (newItem.getEndTime().isBefore(newItem.getStartTime())
                || newItem.getEndTime().equals(newItem.getStartTime())) {
            return Optional.empty();
        }
        // check for conflicting items
        for (Item item : this.itemList) {
            // same start time
            boolean sameStartTime = item.getStartTime().equals(newItem.getStartTime());
            // if new start time is before item start time
            // conflict if new end time is after item start time
            boolean startTimeConflict = newItem.getStartTime().isBefore(item.getStartTime())
                    && newItem.getEndTime().isAfter(item.getStartTime());
            // if new start time is after item start time
            // conflict if new start time is before item end time
            boolean endTimeConflict = newItem.getStartTime().isAfter(item.getStartTime())
                    && newItem.getStartTime().isBefore(item.getEndTime());

            if (sameStartTime || startTimeConflict || endTimeConflict) {
                conflictingItems.add(item);
            }
        }
        return Optional.of(conflictingItems);
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
        Period toBeSplit = new Period(LocalTime.MIN, LocalTime.MAX);
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
            vacantSlots.append("    ").append(period.getStartString()).append(" - ")
                    .append(period.getEndString()).append(System.getProperty("line.separator"));
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

    /**
     * Generates a text representation of the day.
     *
     * @return The text representation.
     */
    public String getTextRepresentation() {
        StringBuilder dayText = new StringBuilder();
        dayText.append("Day ").append((this.dayNumber + 1)).append(System.lineSeparator());
        StringBuilder itemsText = new StringBuilder();
        int itemCount = 1;
        for (Item item : this.itemList) {
            itemsText.append(itemCount).append(". ").append(item.toString())
                    .append(System.lineSeparator());
            if (itemCount < this.itemList.getSize()) {
                itemsText.append(System.lineSeparator());
            }
            itemCount++;
        }
        dayText.append(Text.indent(itemsText.toString(), Text.INDENT_FOUR))
                .append(System.lineSeparator());

        return dayText.toString();
    }

    public List<PdfFieldInfo> getPdfFieldInfoList() {
        List<PdfFieldInfo> fieldList = new ArrayList<>();
        for (int i = 0; i < this.itemList.getSize(); i++) {
            Item item = this.itemList.get(i);
            PdfFieldInfo time = new PdfFieldInfo("time" + i, item.getTimeString(Text.INDENT_NONE));
            PdfFieldInfo activity = new PdfFieldInfo("item" + i, item.getDescription().toString());
            fieldList.add(time);
            fieldList.add(activity);
        }
        int remainder = (fieldList.size() / 2) % PdfFiller.MAX_DISPLAY;
        if (remainder != 0) {
            for (int i = 0; i < PdfFiller.MAX_DISPLAY - remainder; i++) {
                int nextPos = remainder + i;
                PdfFieldInfo time = new PdfFieldInfo("time" + nextPos, "");
                PdfFieldInfo activity = new PdfFieldInfo("item" + nextPos, "");
                fieldList.add(time);
                fieldList.add(activity);
            }
        }
        if (fieldList.size() == 0) {
            for (int i = 0; i < PdfFiller.MAX_DISPLAY; i++) {
                PdfFieldInfo time = new PdfFieldInfo("time" + i, "");
                PdfFieldInfo activity = new PdfFieldInfo("item" + i, "");
                fieldList.add(time);
                fieldList.add(activity);
            }
        }
        return fieldList;
    }
}
