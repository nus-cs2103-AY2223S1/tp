package seedu.taassist.commons.core.index;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to contain utility methods for retrieving elements from a list using Index objects.
 */
public class IndexUtil {

    /**
     * Retrieves the element at the relative zero-based {@code index} from the {@code list}.
     */
    public static <T> T getAtIndex(List<T> list, Index index) throws IndexOutOfBoundsException {
        return list.get(index.getZeroBased());
    }

    /**
     * Retrieves the elements at the relative zero-based {@code indices} from the {@code list}.
     */
    public static <T> List<T> getAtIndices(List<T> list, List<Index> indices) throws IndexOutOfBoundsException {
        List<T> returnList = new ArrayList<>();
        for (Index index : indices) {
            int zeroBasedIndex = index.getZeroBased();
            returnList.add(list.get(zeroBasedIndex));
        }
        return returnList;
    }

}
