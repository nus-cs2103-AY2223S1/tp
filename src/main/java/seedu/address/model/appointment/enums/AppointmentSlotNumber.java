package seedu.address.model.appointment.enums;

import java.time.LocalTime;

/**
 * Represents the appointment slot number
 */
public enum AppointmentSlotNumber {
    ONE(LocalTime.of(10, 0, 0)),
    TWO(LocalTime.of(12, 0, 0)),
    THREE(LocalTime.of(14, 0, 0)),
    FOUR(LocalTime.of(16, 0, 0));

    private final LocalTime time;

    AppointmentSlotNumber(LocalTime time) {
        this.time = time;
    }

    /**
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format(this.name(), time.toString());
    }
}
