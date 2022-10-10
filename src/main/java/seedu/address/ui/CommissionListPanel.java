package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.commission.Commission;

/**
 * Panel containing the list of commissions.
 */
public class CommissionListPanel extends UiPart<Region> {
    private static final String FXML = "CommissionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CommissionListPanel.class);
    private final Consumer<Commission> selectCommission;

    @FXML
    private ListView<Commission> commissionListView;

    /**
     * Creates a {@code CommissionListPanel} with the given {@code ObservableList}.
     */
    public CommissionListPanel(ObservableList<Commission> commissionList,
                               Consumer<Commission> selectCommission) {
        super(FXML);
        commissionListView.setItems(commissionList);
        commissionListView.setCellFactory(listView -> new CommissionListViewCell());
        this.selectCommission = selectCommission;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Commission} using a {@code CommissionCard}.
     */
    class CommissionListViewCell extends ListCell<Commission> {
        @Override
        protected void updateItem(Commission commission, boolean empty) {
            super.updateItem(commission, empty);

            if (empty || commission == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CommissionCard(commission, getIndex() + 1).getRoot());
            }

            addEventHandler(MouseEvent.MOUSE_PRESSED, e -> selectCommission.accept(commission));
        }
    }

}
