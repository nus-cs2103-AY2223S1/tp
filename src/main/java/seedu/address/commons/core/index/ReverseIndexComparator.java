package seedu.address.commons.core.index;

import java.util.Comparator;

/**
 * Represents a comparator to compare index number to give a reverse order from
 * high to low.
 */
public class ReverseIndexComparator implements Comparator<Index> {

    /**
     * Compare the index number.
     *
     * @param firstIndex  the first index to be compared.
     * @param secondIndex the second index to be compared.
     */
    public int compare(Index firstIndex, Index secondIndex) {
        return -1 * Integer.compare(firstIndex.getZeroBased(), secondIndex.getZeroBased());
    }

}
