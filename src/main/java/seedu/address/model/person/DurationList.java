package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

public class DurationList {
    public final List<Duration> durationList;

    public DurationList() {
        durationList = new ArrayList<>();
    }

    public DurationList(List<Duration> durationList) {
        this.durationList = durationList;
    }

    public void addDuration(Duration duration) {
        durationList.add(duration);
    }

    public String shortDescription() {
        if (durationList.isEmpty()) {
            return toString();
        }
        durationList.sort(new DurationCompare());
        String shortDesc = "Next Up: "
                + durationList.get(0).toString() + ".";
        return shortDesc;
    }

    public void clearList() {
        durationList.clear();
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Session Timings: \n");
        if (durationList.isEmpty()) {
            description.append("No session timings found!\n");
        }
        for (int i = 0; i < durationList.size(); i++) {
            description.append(i + 1).append(this.durationList.get(i)).append("\n");
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
