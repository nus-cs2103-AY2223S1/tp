package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 * Abstract panel for the different lists.
 */
public abstract class ListPanel extends UiPart<Region> {
    protected ListPanel(String fxml) {
        super(fxml);
    }

    /**
     * Returns a string denoting the number of records for {@code T} currently shown in the {@code lList}.
     */
    public <T> String numRecordsString(ObservableList<T> list) {
        if (list.size() == 1) {
            return "1 record";
        } else {
            return list.size() + " records";
        }
    }
}
