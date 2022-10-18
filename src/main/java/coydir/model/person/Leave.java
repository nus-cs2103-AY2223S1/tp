package coydir.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Leave {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Leave(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate, FORMAT);
        this.endDate = LocalDate.parse(endDate, FORMAT);
    }

    /**
     * Returns true if a endDate and StartDate makes sense.
     */
    public static boolean isValidLeave(String startDate, String endDate) {
        final int b = LocalDate.parse(endDate, FORMAT).compareTo(LocalDate.parse(startDate, FORMAT));
        return b > 0;
    }

    /**
     * Returns number of days
     */
    public int getTotalDays() {
        return this.endDate.compareTo(this.startDate);
    }
}
