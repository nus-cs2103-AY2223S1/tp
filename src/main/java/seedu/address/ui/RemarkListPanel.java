package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.remark.Remark;

/**
 * Panel containing the list of companies.
 */
public class RemarkListPanel extends UiPart<Region> {
    private static final String FXML = "RemarkListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RemarkListPanel.class);

    @FXML
    private ListView<Remark> companyListView;

    /**
     * Creates a {@code RemarkListPanel} with an empty {@code ObservableList}.
     */
    public RemarkListPanel() {
        super(FXML);
    }

    /**
     * Sets the {@code RemarkListPanel} with the companies from {@code ObservableList}.
     */
    public void setRemarkList(ObservableList<Remark> companyList) {
        requireNonNull(companyList);

        companyListView.setItems(companyList);
        companyListView.setCellFactory(listView -> new RemarkListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Remark} using a {@code RemarkCard}.
     */
    class RemarkListViewCell extends ListCell<Remark> {
        @Override
        protected void updateItem(Remark company, boolean empty) {
            super.updateItem(company, empty);

            if (empty || company == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RemarkCard(company, getIndex() + 1).getRoot());
            }
        }
    }

}
