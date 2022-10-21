package seedu.waddle.model.item;

import java.util.Comparator;

public class ItemContainer {

    private UniqueItemList unscheduledItemList;
    private UniqueItemList scheduledItemList;

    private Comparator<Item> PriorityComparator = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            return item1.getPriority().compareTo(item2.getPriority());
        }
    };

    private Comparator<Item> TimeComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return 0;
        }
//        public int compare(Item item1, Item item2) {
//            return item1.getTime().compareTo(item2.getTime());
//        }
    };

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
        this.unscheduledItemList.sort(PriorityComparator);
    }

    private void sortScheduledItemList() {
        this.unscheduledItemList.sort(TimeComparator);
    }

    public void scheduleItem(int index) {
        Item item = this.unscheduledItemList.remove(index);
        sortUnscheduledItemList();
        this.scheduledItemList.add(item);
        sortScheduledItemList();
    }

    public void unscheduleItem(int index) {
        Item item = this.scheduledItemList.remove(index);
        sortScheduledItemList();
        this.unscheduledItemList.add(item);
        sortUnscheduledItemList();
    }

}
