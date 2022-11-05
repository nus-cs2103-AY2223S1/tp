package seedu.address.model.appointment;


import static java.util.Objects.requireNonNull;

import java.time.LocalTime;

/**
 * Represents a time with a formatted String value
 * Guarantees: immutable;
 */
public class Time implements Comparable<Time> {
    private final LocalTime localTime;

    /**
     * Constructs a {@code Time}.
     *
     * @param localTime A valid LocalTime.
     */
    public Time(LocalTime localTime) {
        requireNonNull(localTime);
        this.localTime = localTime;
    }

    @Override
    public String toString() {
        return localTime.format(java.time.format
                .DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public int compareTo(Time other) {
        return this.localTime.compareTo(other.localTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time// instanceof handles nulls
                && localTime.equals(((Time) other).localTime)); // state check
    }

    @Override
    public int hashCode() {
        return localTime.hashCode();
    }
}

