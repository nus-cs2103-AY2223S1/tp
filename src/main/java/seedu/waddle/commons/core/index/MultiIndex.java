package seedu.waddle.commons.core.index;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a series of Index objects.
 */
public class MultiIndex {
    public static final String MESSAGE_CONSTRAINTS =
            "Index should only contain positive integers separated by a decimal point";
    public static final String VALIDATION_REGEX = "\\d+\\.?\\d*";
    private List<Index> indices;

    public MultiIndex() {
        indices = new ArrayList<>();
    }

    public void appendZeroBasedIndex(int index) {
        indices.add(Index.fromZeroBased(index));
    }

    public void appendOneBasedIndex(int index) {
        indices.add(Index.fromOneBased(index));
    }

    public void addIndex(Index index) {
        indices.add(index);
    }

    /**
     * Removes indices more than or equal to specified position.
     *
     * @param pos Specified position to remove from
     */
    public void removeIndex(int pos) {
        if (!isValidPos(pos)) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = pos - 1; i < indices.size(); i++) {
            indices.remove(i);
        }
    }

    public Index getDayIndex() {
        if (this.indices.size() == 1) {
            return null;
        }
        return getIndex(1);
    }

    public Index getTaskIndex() {
        if (this.indices.size() == 1) {
            return getIndex(1);
        }
        return getIndex(2);
    }

    private Index getIndex(int pos) {
        if (!isValidPos(pos)) {
            throw new IndexOutOfBoundsException();
        }
        return indices.get(pos - 1);
    }

    /**
     * Returns true if a given string is a valid multi index.
     */
    public static boolean isValidMultiIndex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean containsMultiIndex() {
        return this.indices.size() >= 2;
    }

    private boolean isValidPos(int pos) {
        if (pos < 1 || pos > indices.size()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indices.size(); i++) {
            sb.append(indices.get(i).getOneBased());
            if (i != indices.size() - 1) {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}
