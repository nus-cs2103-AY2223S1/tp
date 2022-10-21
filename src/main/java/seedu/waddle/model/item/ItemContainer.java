package seedu.waddle.model.item;

import java.util.Comparator;

/**
 * Represents an ItemContainer in an Itinerary.
 */
public class ItemContainer {

    private UniqueItemList unscheduledItemList;
    private UniqueItemList scheduledItemList;

    private Comparator<Item> priorityComparator = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            return item1.getPriority().compareTo(item2.getPriority());
        }
    };

    private Comparator<Item> timeComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return 0;
        }
        /*public int compare(Item item1, Item item2) {
            return item1.getDay().compareTo(item2.getDay());
        }*/
    };

    /**
     * Constructor  for an ItemContainer.
     */
    public ItemContainer() {
        this.unscheduledItemList = new UniqueItemList();
        this.scheduledItemList = new UniqueItemList();
    }

    public UniqueItemList getUnscheduledItemList() {
        return this.unscheduledItemList;
    }

    public UniqueItemList getScheduledItemList() {
        return this.scheduledItemList;
    }
    private void sortUnscheduledItemList() {
        this.unscheduledItemList.sort(priorityComparator);
    }

    private void sortScheduledItemList() {
        this.unscheduledItemList.sort(timeComparator);
    }

    /**
     * Schedule an Item.
     * @param index Position of item.
     */
    public void scheduleItem(int index) {
        Item item = this.unscheduledItemList.remove(index);
        sortUnscheduledItemList();
        this.scheduledItemList.add(item);
        sortScheduledItemList();
    }

    /**
     * Unschedule an Item.
     * @param index Position of item.
     */
    public void unscheduleItem(int index) {
        Item item = this.scheduledItemList.remove(index);
        sortScheduledItemList();
        this.unscheduledItemList.add(item);
        sortUnscheduledItemList();
    }

}
