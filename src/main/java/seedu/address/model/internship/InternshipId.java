package seedu.address.model.internship;

import seedu.address.model.person.PersonId;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class InternshipId {
    public static final String MESSAGE_CONSTRAINTS =
            "Internship Id must be a non-negative number, and it should not be blank";

    public final Integer id;

    public InternshipId(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = Integer.parseInt(id);
    }

    public InternshipId(Integer id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    public static boolean isValidId(String test) {
        try {
            return isValidId(Integer.parseInt(test));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidId(Integer test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternshipId // instanceof handles nulls
                && id.equals(((InternshipId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
