package seedu.address.commons.core.index;

import java.util.Comparator;

/**
 * Represents a comparator to compare index number to give a reverse order from high to low.
 */
public class ReverseIndexComparator implements Comparator<Index> {

    /**
     * Compare the index number.
     *
     * @param index1 the first index to be compared.
     * @param index2 the second index to be compared.
     */
    public int compare(Index index1, Index index2) {
        int index1No = index1.getZeroBased();
        int index2No = index2.getZeroBased();
        if (index1No > index2No) {
            return -1;
        } else if (index1No < index2No) {
            return 1;
        } else {
            return 0;
        }
    }

}


