package seedu.waddle.model.itinerary;

import static seedu.waddle.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.item.Day;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.UniqueItemList;
import seedu.waddle.model.text.Text;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Itinerary {

    // Details field
    private final Description description;
    private final Country country;
    private final Date startDate;
    private final ItineraryDuration duration;
    private final People people;
    private final Budget budget;
    private final StringProperty observableBudgetString;
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
    public Itinerary(Description description, Country country, Date startDate, ItineraryDuration duration,
                     People people, Budget budget) {
        requireAllNonNull(description, startDate, duration);
        this.description = description;
        this.country = country;
        this.startDate = startDate;
        this.duration = duration;
        this.people = people;
        this.budget = budget;
        this.observableBudgetString = new SimpleStringProperty();
        this.unscheduledItemList = new UniqueItemList();
        this.days = new ArrayList<>();
        for (int i = 0; i < duration.getValue(); i++) {
            // TODO day number should start with 1 instead of 0?
            this.days.add(new Day(i));
        }
    }

    public Description getDescription() {
        return description;
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
                // transfer all items from extra days to unscheduled item list
                for (Item item : dayList.get(i).deleteDay()) {
                    addItem(item);
                    this.budget.updateSpending(-item.getCost().getValue());
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
                && otherItinerary.getDescription().equals(getDescription());
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
            this.budget.updateSpending(-target.getCost().getValue());
            this.budget.updateSpending(editedItem.getCost().getValue());
            this.observableBudgetString.set(getBudgetString(Text.INDENT_NONE));
        }
    }

    public int getUnscheduledSize() {
        return this.unscheduledItemList.getSize();
    }

    public UniqueItemList getUnscheduledItemList() {
        return this.unscheduledItemList;
    }

    private void sortUnscheduledItemList() {
        this.unscheduledItemList.sort(priorityComparator);
    }

    public Item getItem(MultiIndex index) {
        if (index.getDayIndex() == null) {
            return this.unscheduledItemList.get(index.getTaskIndex().getZeroBased());
        } else {
            Day day = this.days.get(index.getDayIndex().getZeroBased());
            return day.getItem(index.getTaskIndex());
        }
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
        try {
            day.addItem(item);
        } catch (CommandException e) {
            // if time conflict detected, reset the time of the item
            item.resetStartTime();
            throw e;
        }

        this.unscheduledItemList.remove(itemIndex.getZeroBased());
        this.budget.updateSpending(item.getCost().getValue());
        this.observableBudgetString.set(getBudgetString(Text.INDENT_NONE));
        return item;
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
        this.observableBudgetString.set(getBudgetString(Text.INDENT_NONE));
        return unplannedItem;
    }

    /**
     * Calculates the total spending and updates the budget
     */
    public float calculateSpending() {
        float totalSpending = 0;
        for (Day day : this.days) {
            for (Item item : day.getItemList()) {
                totalSpending += item.getCost().getValue();
            }
        }
        this.budget.setSpending(totalSpending);
        return totalSpending;
    }

    public String getVacantSlots() {
        StringBuilder vacantSlots = new StringBuilder();
        for (Day day : this.days) {
            vacantSlots.append(day.getVacantSlots()).append(System.getProperty("line.separator"));
        }
        return vacantSlots.toString();
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

    public String getDescriptionString(int indents) {
        return Text.indent(this.description.toString(), indents);
    }

    public String getCountryString(int indents) {
        return Text.indent("Country: " + this.country, indents);
    }

    public String getDurationString(int indents) {
        return Text.indent("Duration: " + this.duration.getValue() + " Days", indents);
    }

    public String getTimeString(int indents) {
        if (this.startDate != null) {
            if (this.duration != null) {
                return Text.indent("Dates: " + this.startDate + " - "
                        + this.startDate.getValue().plusDays(this.duration.getValue()), indents);
            } else {
                return Text.indent("Dates: " + this.startDate, indents);
            }
        }
        return Text.indent("Dates: (Not planned)", indents);
    }

    public String getPeopleString(int indents) {
        return Text.indent("Waddlers: " + this.people, indents);
    }

    public String getBudgetString(int indents) {
        String budgetString = Text.MONEY_PRINT_FORMATTER.format(this.budget.getValue());
        String leftOverString = Text.MONEY_PRINT_FORMATTER.format(this.budget.calculateLeftOverBudget());
        if (this.budget.getSpending() == 0) {
            return Text.indent("Budget: $" + budgetString, indents);
        } else {
            return Text.indent("Budget: $" + budgetString + ", $"
                    + leftOverString + " remaining", indents);
        }
    }

    public StringProperty getObservableBudgetString(int indents) {
        this.observableBudgetString.set(getBudgetString(indents));
        return this.observableBudgetString;
    }

    /**
     * Generates a text representation of the day.
     *
     * @return The text representation.
     */
    public String getTextRepresentation() {
        StringBuilder itineraryText = new StringBuilder();
        itineraryText.append(this)
                .append(System.lineSeparator()).append(System.lineSeparator());
        StringBuilder daysText = new StringBuilder();
        for (Day day : this.days) {
            daysText.append(day.getTextRepresentation());
        }
        itineraryText.append(daysText);

        return itineraryText.toString();
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
        return otherItinerary.getDescription().equals(getDescription())
                && otherItinerary.getCountry().equals(getCountry())
                && otherItinerary.getStartDate().equals(getStartDate())
                && otherItinerary.getDuration().equals(getDuration())
                && otherItinerary.getPeople().equals(getPeople())
                && otherItinerary.getBudget().equals(getBudget());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, country, startDate, duration, people, budget);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescriptionString(Text.INDENT_NONE))
                .append(System.getProperty("line.separator"))
                .append(getCountryString(Text.INDENT_FOUR))
                .append(System.getProperty("line.separator"))
                .append(getDurationString(Text.INDENT_FOUR))
                .append(System.getProperty("line.separator"))
                .append(getTimeString(Text.INDENT_FOUR))
                .append(System.getProperty("line.separator"))
                .append(getPeopleString(Text.INDENT_FOUR))
                .append(System.getProperty("line.separator"))
                .append(getBudgetString(Text.INDENT_FOUR));

        return builder.toString();
    }
}
