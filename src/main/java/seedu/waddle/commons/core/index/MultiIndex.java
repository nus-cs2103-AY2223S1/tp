package seedu.waddle.commons.core.index;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a series of Index objects.
 */
public class MultiIndex {
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

    public Index getIndex(int pos) {
        if (!isValidPos(pos)) {
            throw new IndexOutOfBoundsException();
        }
        return indices.get(pos - 1);
    }



    private boolean isValidPos(int pos) {
        if (pos < 1 || pos >= indices.size()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indices.size(); i++) {
            sb.append(indices.get(i));
            if (i != indices.size() - 1) {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}
