package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Person's duration list in the address book.
 */
public class DurationList {
    public final List<Duration> durationList;

    /**
     * Constructs a {@code DurationList}.
     */
    public DurationList() {
        durationList = new ArrayList<>();
    }

    public DurationList(List<Duration> durationList) {
        this.durationList = durationList;
    }

    /**
     * Adds a duration to the attendance list.
     *
     * @param duration The duration object to be added.
     */
    public void addDuration(Duration duration) {
        durationList.add(duration);
    }

    /**
     * Returns a description of the next duration in the duration list.
     */
    public String shortDescription() {
        if (durationList.isEmpty()) {
            return toString();
        }
        durationList.sort(Duration::compareTo);
        String shortDesc = "NEXT UP: \n"
                + durationList.get(0).toString() + ".";
        return shortDesc;
    }

    /**
     * clears the duration list.
     */
    public void clearList() {
        durationList.clear();
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Durations: \n");
        if (durationList.isEmpty()) {
            description.append("No durations found!\n");
        }
        for (int i = 0; i < durationList.size(); i++) {
            description.append(i + 1).append(". ").append(this.durationList.get(i)).append("\n");
        }
        return description.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DurationList
                && this.durationList.equals(((DurationList) other).durationList));
    }

    @Override
    public int hashCode() {
        return this.durationList.hashCode();
    }
}
