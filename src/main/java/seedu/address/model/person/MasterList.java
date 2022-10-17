package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Stores all unique/distinct Buyers, Deliverers, and Suppliers.
 */
public class MasterList {
    private final ObservableList<Object> internalList = FXCollections.observableArrayList();

    public ObservableList<Object> getMasterList() {
        return internalList;
    }

    public void addAll(FilteredList<? extends Object> c) {
        internalList.addAll(c);
    }

    public void clear() {
        internalList.clear();
    }
}
