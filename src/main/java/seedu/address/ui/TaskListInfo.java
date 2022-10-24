package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.FilterInfo;
import seedu.address.commons.SortInfo;

/**
 * Header showing sort and filter information for the task list.
 */
public class TaskListInfo extends UiPart<Region> {
    private static final String FXML = "TaskListInfo.fxml";

    @FXML
    private Label sortInfoLabel;

    @FXML
    private Label filterInfoLabel;

    /**
     * Create a TaskListInfo.
     */
    public TaskListInfo() {
        super(FXML);
        this.sortInfoLabel.setText(new SortInfo("none").toString());
        this.filterInfoLabel.setText(new FilterInfo("none").toString());
    }

    public void setSortInfo(SortInfo sortInfo) {
        this.sortInfoLabel.setText(sortInfo.toString());
    }

    public void setFilterInfo(FilterInfo filterInfo) {
        this.filterInfoLabel.setText(filterInfo.toString());
    }

}
