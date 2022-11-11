package seedu.address.model.team;

import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * An abstraction of the lists to be displayed in the UI
 */
public class DisplayList<T> {
    private final SortedList<T> sorted;
    private final FilteredList<T> filtered;

    /**
     * Constructs a {@code DisplayList} that uses {@code toDisplay} as it source.
     */
    public DisplayList(ObservableList<? extends T> toDisplay) {
        sorted = new SortedList<>(toDisplay);
        filtered = new FilteredList<>(sorted);
    }

    public void setComparator(Comparator<T> comparator) {
        sorted.setComparator(comparator);
    }

    /**
     * Filters the with the given {@code Predicate}.
     *
     * @throws ClassCastException if the predicate incorrectly casts the displayed elements.
     */
    public void setPredicate(Predicate<T> predicate) throws ClassCastException {
        filtered.setPredicate(predicate);
    }

    /**
     * Returns the filtered list of displayables to be displayed.
     */
    public FilteredList<T> getFilteredDisplayList() {
        return filtered;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof DisplayList)) {
            return false;
        }

        // state check
        DisplayList other = (DisplayList) obj;
        return sorted.equals(other.sorted)
                && filtered.equals(other.filtered);
    }
}
