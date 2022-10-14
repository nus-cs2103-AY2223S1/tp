package seedu.address.model.person;

import java.util.Comparator;

public class DurationCompare implements Comparator<Duration> {
    public int compare(Duration duration1, Duration duration2) {
        return duration1.time.compareTo(duration2.time);
    }
}
