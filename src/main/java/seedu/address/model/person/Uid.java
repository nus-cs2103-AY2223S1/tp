package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's Uid in the records system.
 * Guarantees: immutable; is valid as declared in {@link #isValidUid(String)}
 */
public class Uid implements Comparable<Uid> {
    public static final String MESSAGE_CONSTRAINTS = "Ids should only contain numeric characters,"
            + " and it should not be blank";

    /*
     * The first character of the id must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[0-9]*$";

    public final Long id;

    /**
     * Constructs a {@code Uid}.
     *
     * @param inputId A valid id.
     */
    public Uid(Long inputId) {
        requireNonNull(inputId);
        id = inputId;
    }

    /**
     * Constructs a {@code Uid}.
     *
     * @param stringId A valid id.
     */
    public Uid(String stringId) {
        requireNonNull(stringId);
        id = Long.parseLong(stringId);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidUid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Uid // instanceof handles nulls
                        && id.equals(((Uid) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Uid o) {
        return id.compareTo(o.getId());
    }
}
