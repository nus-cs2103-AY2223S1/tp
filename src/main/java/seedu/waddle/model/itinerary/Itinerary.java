package seedu.waddle.model.itinerary;

import static seedu.waddle.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.item.Day;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.UniqueItemList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Itinerary {

    // Details field
    private final Name name;
    private final Country country;
    private final Date startDate;
    private final ItineraryDuration duration;
    private final People people;
    private final Budget budget;
    private final UniqueItemList unscheduledItemList;
    private final List<Day> days;
    private final Comparator<Item> priorityComparator = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            return item2.getPriority().compareTo(item1.getPriority());
        }
    };

    /**
     * Every field must be present and not null.
     */
    public Itinerary(Name name, Country country, Date startDate, ItineraryDuration duration,
                     People people, Budget budget) {
        requireAllNonNull(name, startDate, duration);
        this.name = name;
        this.country = country;
        this.startDate = startDate;
        this.duration = duration;
        this.people = people;
        this.budget = budget;
        this.unscheduledItemList = new UniqueItemList();
        this.days = new ArrayList<>();
        for (int i = 0; i < duration.getValue(); i++) {
            // TODO day number should start with 1 instead of 0?
            this.days.add(new Day(i));
        }
    }

    public Name getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ItineraryDuration getDuration() {
        return this.duration;
    }

    public String getTimeString() {
        if (this.startDate != null) {
            if (this.duration != null) {
                return this.startDate + " - " + this.startDate.getValue().plusDays(this.duration.getValue());
            } else {
                return this.startDate.toString();
            }
        }
        return "(Not planned)";
    }

    public People getPeople() {
        return people;
    }

    public Budget getBudget() {
        return this.budget;
    }

    public UniqueItemList getItemList() {
        return unscheduledItemList;
    }

    public List<Day> getDays() {
        return this.days;
    }

    public void setDays(List<Day> dayList) {
        for (int i = 0; i < dayList.size(); i++) {
            if (i < getDuration().getValue()) {
                this.days.set(i, dayList.get(i));
            } else {
                for (Item item : dayList.get(i).deleteDay()) {
                    addItem(item);
                }
            }
        }
    }

    /**
     * Returns true if both itineraries have the same name.
     * This defines a weaker notion of equality between two itineraries.
     */
    public boolean isSameItinerary(Itinerary otherItinerary) {
        if (otherItinerary == this) {
            return true;
        }

        return otherItinerary != null
                && otherItinerary.getName().equals(getName());
    }

    public boolean hasItem(Item item) {
        return this.unscheduledItemList.contains(item);
    }

    /**
     * Add item into itinerary.
     *
     * @param item Item to be added.
     */
    public void addItem(Item item) {
        this.unscheduledItemList.add(item);
        sortUnscheduledItemList();
    }

    /**
     * Remove item from itinerary.
     *
     * @param index A MultiIndex specifying position of task.
     * @return The item to be removed.
     */
    public Item removeItem(MultiIndex index) {
        if (index.getDayIndex() == null) {
            return this.unscheduledItemList.remove(index.getTaskIndex().getZeroBased());
        } else {
            Day day = this.days.get(index.getDayIndex().getZeroBased());
            return day.removeItem(index.getTaskIndex());
        }
    }

    public void setItem(Item target, Item editedItem, MultiIndex index) throws CommandException {
        if (index.getDayIndex() == null) {
            this.unscheduledItemList.setItem(target, editedItem);
            sortUnscheduledItemList();
        } else {
            Day day = this.days.get(index.getDayIndex().getZeroBased());
            day.removeItem(index.getTaskIndex());
            try {
                day.addItem(editedItem);
            } catch (CommandException e) {
                day.addItem(target);
                throw e;
            }
        }
    }

    public int getItemSize() {
        return this.unscheduledItemList.getSize();
    }

    public UniqueItemList getUnscheduledItemList() {
        return this.unscheduledItemList;
    }

    private void sortUnscheduledItemList() {
        this.unscheduledItemList.sort(priorityComparator);
    }

    /**
     * Unplan an item.
     *
     * @param index A multiIndex to locate the day and index of task within the day
     */
    public Item unplanItem(MultiIndex index) {
        Day day = this.days.get(index.getDayIndex().getZeroBased());
        Item unplannedItem = day.removeItem(index.getTaskIndex());
        unplannedItem.resetStartTime();
        addItem(unplannedItem);
        sortUnscheduledItemList();
        this.budget.updateSpending(-unplannedItem.getCost().getValue());
        return unplannedItem;
    }

    /**
     * Plan an item.
     *
     * @param itemIndex Index of item in unscheduled list.
     * @param dayNumber Day to include this item.
     * @param startTime startTime of the item.
     * @return The planned item.
     * @throws CommandException When adding item to specific day leads to conflict in time.
     */
    public Item planItem(Index itemIndex, DayNumber dayNumber, LocalTime startTime) throws CommandException {
        Item item = this.unscheduledItemList.get(itemIndex.getZeroBased());
        item.setStartTime(startTime);
        Day day = this.days.get(dayNumber.dayNumber.getZeroBased());
        day.addItem(item);
        this.unscheduledItemList.remove(itemIndex.getZeroBased());
        this.budget.updateSpending(item.getCost().getValue());
        return item;
    }

    public Item getItem(MultiIndex index) {
        if (index.getDayIndex() == null) {
            return this.unscheduledItemList.get(index.getTaskIndex().getZeroBased());
        } else {
            Day day = this.days.get(index.getDayIndex().getZeroBased());
            return day.getItem(index.getTaskIndex());
        }
    }

    public ObservableList<ObservableList<Item>> getUnmodifiableItemGroups() {
        ObservableList<ObservableList<Item>> itemGroups = FXCollections.observableArrayList();
        itemGroups.add(this.unscheduledItemList.asUnmodifiableObservableList());
        for (Day day : this.days) {
            ObservableList<Item> itemList = day.getItemList().asUnmodifiableObservableList();
            itemGroups.add(itemList);
        }
        return FXCollections.unmodifiableObservableList(itemGroups);
    }

    public String getVacantSlots() {
        StringBuilder vacantSlots = new StringBuilder();
        for (Day day : this.days) {
            vacantSlots.append(day.getVacantSlots()).append(System.getProperty("line.separator"));
        }
        return vacantSlots.toString();
    }

    /**
     * Returns true if both itineraries have the same identity and data fields.
     * This defines a stronger notion of equality between two itineraries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Itinerary)) {
            return false;
        }

        Itinerary otherItinerary = (Itinerary) other;
        return otherItinerary.getName().equals(getName())
                && otherItinerary.getCountry().equals(getCountry())
                && otherItinerary.getStartDate().equals(getStartDate())
                && otherItinerary.getDuration().equals(getDuration())
                && otherItinerary.getPeople().equals(getPeople())
                && otherItinerary.getBudget().equals(getBudget());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, country, startDate, duration, people, budget);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Country: ")
                .append(getCountry())
                .append("; Start Date: ")
                .append(getStartDate())
                .append("; Duration: ")
                .append(getDuration())
                .append("; Number of people: ")
                .append(getPeople())
                .append("; Budget: ")
                .append(getBudget())
                .append("; Leftover Budget: ")
                .append(getBudget().calculateLeftOverBudget());

        return builder.toString();
    }

    public void setSpending(Budget budget) {
        this.budget.setSpending(budget.getSpending());
    }

}
