package seedu.address.model.module;

import java.time.LocalTime;

public class Lecture extends Lesson {
    public Lecture(String day, LocalTime startTime, LocalTime endTime) {
        super(day, startTime, endTime);
    }
}
