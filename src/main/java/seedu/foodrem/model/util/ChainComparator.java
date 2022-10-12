package seedu.foodrem.model.util;

import java.util.Comparator;
import java.util.List;

/**
 * Utility class allowing to chain Comparators from a given
 * list of Comparators.
 */
public class ChainComparator<T> implements Comparator<T> {
    private final List<? extends Comparator<T>> comparatorList;

    public ChainComparator(List<? extends Comparator<T>> comparatorList) {
        this.comparatorList = comparatorList;
    }

    @Override
    public int compare(T o1, T o2) {
        int result;
        for (Comparator<T> c : comparatorList) {
            if ((result = c.compare(o1, o2)) != 0) {
                return result;
            }
        }
        return 0;
    }
}
