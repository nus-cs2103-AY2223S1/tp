package seedu.address.model.commission;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public class Deadline {

    public final LocalDate deadline;

    public Deadline(LocalDate deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return deadline.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Deadline)
                && deadline.equals(((Deadline) other).deadline);
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }

}
