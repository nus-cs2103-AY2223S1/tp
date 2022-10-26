package coydir.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a Leave in the database.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLeave}
 */
public class Leave {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public final LocalDate startDate;
    public final LocalDate endDate;

    private final SimpleStringProperty col1;
    private final SimpleStringProperty col2;
    private final SimpleStringProperty col3;

    /**
     * Constructs a {@code Leave}.
     *
     * @param startDate A valid start date.
     * @param endDate A valid start date.
     */
    public Leave(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate, FORMAT);
        this.endDate = LocalDate.parse(endDate, FORMAT);
        this.col1 = new SimpleStringProperty(this.startDate.toString());
        this.col2 = new SimpleStringProperty((this.endDate.toString()));
        this.col3 = new SimpleStringProperty(String.valueOf(getTotalDays()));
    }

    public String getCol1() {
        return this.col1.get();
    }

    public String getCol2() {
        return this.col2.get();
    }

    public String getCol3() {
        return this.col3.get();
    }

    /**
     * Returns true if a endDate and StartDate makes sense.
     */
    public static boolean isValidLeave(String startDate, String endDate) {
        final int b = LocalDate.parse(endDate, FORMAT).compareTo(LocalDate.parse(startDate, FORMAT));
        return b >= 0;
    }

    /**
     * Returns number of days
     */
    public int getTotalDays() {
        return this.endDate.compareTo(this.startDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Leave)) {
            return false;
        }

        Leave otherLeave = (Leave) other;
        return this.startDate.equals(otherLeave.startDate)
                && this.endDate.equals(otherLeave.endDate);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", startDate, endDate);
    }
}
