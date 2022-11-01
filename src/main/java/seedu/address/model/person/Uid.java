package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents a Person's Uid in the records system.
 * Guarantees: immutable; is valid as declared in {@link #isValidUid(String)}
 */
public class Uid implements Comparable<Uid> {
    public static final String MESSAGE_CONSTRAINTS = "Id should only contain one positive numeric character,"
            + " and it should not be blank and below 99998!";

    /**
     * The first character of the id must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[0-9]*$";
    private static final AtomicLong NEXT_UID = new AtomicLong(0);
    private static final Long UNIVERSAL_UID = 99999L;

    public final Long uid;

    /**
     * Constructor for Uid using a String as an input
     *
     * @param uid
     */
    public Uid(String uid) {
        requireNonNull(uid);
        long parsedUid = Long.parseLong(uid);
        this.uid = parsedUid;
        NEXT_UID.set(parsedUid + 1);
    }

    /**
     * Constructor for Uid using a Long as an input
     *
     * @param uid
     */
    public Uid(Long uid) {
        requireNonNull(uid);
        this.uid = uid;
        NEXT_UID.set(uid + 1);
    }

    /**
     * Constructor for Uid
     */
    public Uid() {
        this.uid = NEXT_UID.incrementAndGet();
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidUid(String test) {
        requireNonNull(test);
        try {
            Long parsedUid = Long.parseLong(test);
            return test.matches(VALIDATION_REGEX) && parsedUid < 99998L;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Uid generateUniversalUid() {
        return new Uid(UNIVERSAL_UID);
    }

    /**
     * @return the id
     */
    public Long getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return uid.toString();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Uid)) {
            return false;
        }

        Uid otherUid = (Uid) other;

        if (this.uid.equals(UNIVERSAL_UID) || otherUid.uid.equals(UNIVERSAL_UID)) {
            return true;
        }

        // state check
        return uid.equals(otherUid.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }

    @Override
    public int compareTo(Uid o) {
        return uid.compareTo(o.getUid());
    }

    public String toFormattedString() {
        return String.format("Uid: %s;", uid.toString());
    }
}
