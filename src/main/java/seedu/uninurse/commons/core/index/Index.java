package seedu.uninurse.commons.core.index;

/**
 * Represents a zero-based or one-based index.
 *
 * Index should be used right from the start (when parsing in a new user input), so that if the current
 * component wants to communicate with another component, it can send an Index to avoid having to know what
 * base the other component is using for its index. However, after receiving the Index, that component can
 * convert it back to an int if the index will not be passed to a different component again.
 */
public class Index {
    private final int zeroBasedIndex;

    /**
     * Index can only be created by calling factory methods fromZeroBased() or fromOneBased().
     */
    private Index(int zeroBasedIndex) {
        if (zeroBasedIndex < 0) {
            throw new IndexOutOfBoundsException();
        }

        this.zeroBasedIndex = zeroBasedIndex;
    }

    public int getZeroBased() {
        return zeroBasedIndex;
    }

    public int getOneBased() {
        return zeroBasedIndex + 1;
    }

    /**
     * Creates a new Index using a zero-based index.
     *
     * @param zeroBasedIndex the integer that is zero-based
     */
    public static Index fromZeroBased(int zeroBasedIndex) {
        return new Index(zeroBasedIndex);
    }

    /**
     * Creates a new Index using a one-based index.
     *
     * @param oneBasedIndex the integer that is one-based
     */
    public static Index fromOneBased(int oneBasedIndex) {
        return new Index(oneBasedIndex - 1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Index // instanceof handles nulls
                && zeroBasedIndex == ((Index) other).zeroBasedIndex); // state check
    }
}
