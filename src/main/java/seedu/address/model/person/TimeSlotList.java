package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotList {
    List<TimeSlot> timeSlots;

    public TimeSlotList() {
        this.timeSlots = new ArrayList<>();
    }

    public void add(TimeSlot timeSlot) {
        timeSlots.add(timeSlot);
        timeSlots.sort(TimeSlot::compareTo);
    }
}
