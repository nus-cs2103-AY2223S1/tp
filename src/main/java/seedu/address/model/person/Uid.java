package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's Uid in the records system.
 * Guarantees: immutable; is valid as declared in {@link #isValidUid(String)}
 */
public class Uid implements Comparable<Uid> {
    public static final String MESSAGE_CONSTRAINTS = "Ids should only contain numeric characters,"
            + " and it should not be blank";

    /**
     * The first character of the id must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[0-9]*$";

    public final Long uid;

    /**
     * Constructs a {@code Uid}.
     *
     * @param inputId A valid id.
     */
    public Uid(Long inputId) {
        requireNonNull(inputId);
        uid = inputId;
    }

    /**
     * Constructs a {@code Uid}.
     *
     * @param stringId A valid id.
     */
    public Uid(String stringId) {
        requireNonNull(stringId);
        uid = Long.parseLong(stringId);
    }

    /**
     * @return the id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidUid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return uid.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Uid // instanceof handles nulls
                        && uid.equals(((Uid) other).uid)); // state check
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }

    @Override
    public int compareTo(Uid o) {
        return uid.compareTo(o.getUid());
    }
}
