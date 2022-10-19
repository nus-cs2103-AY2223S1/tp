package seedu.address.model.module;

import java.time.LocalTime;

public abstract class Lesson {
    public LocalTime startTime;
    public LocalTime endTime;
    public String day;

    public Lesson(String day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
