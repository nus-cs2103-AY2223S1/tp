package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;

/**
 * Panel containing the list of commissions.
 */
public class CommissionListPanel extends UiPart<Region> {
    private static final String FXML = "CommissionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CommissionListPanel.class);
    private final Consumer<Commission> selectCommission;

    @FXML
    private ListView<Commission> commissionListView;
    @FXML
    private AnchorPane commissionPanelControlsPlaceholder;

    /**
     * Creates a {@code CommissionListPanel} with the given {@code ObservableList}.
     */
    public CommissionListPanel(ObservableObject<Pair<Customer, FilteredList<Commission>>> observableCommissionList,
                               Consumer<Commission> selectCommission,
                               ObservableObject<Commission> selectedCommission,
                               Consumer<UiPart<Stage>> addChildWindow,
                               CommandBox.CommandExecutor commandExecutor) {
        super(FXML);
        commissionPanelControlsPlaceholder.getChildren()
                .add(new CommissionListPanelControlBar(addChildWindow, commandExecutor).getRoot());

        if (observableCommissionList.getValue() != null) {
            this.updateUI(observableCommissionList.getValue().getValue());
        }
        this.selectCommission = selectCommission;

        observableCommissionList.addListener((observable, oldValue, newValue) -> this.updateUI(newValue.getValue()));
        commissionListView.getSelectionModel().select(selectedCommission.getValue());
        selectedCommission.addListener(((observable, oldValue, newValue) ->
                commissionListView.getSelectionModel().select(newValue)));
    }


    private void updateUI(ObservableList<Commission> commissionList) {
        commissionListView.setItems(commissionList);
        commissionListView.setCellFactory(listView -> new CommissionListViewCell());
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

            setOnMousePressed(e -> selectCommission.accept(commission));
        }
    }

}
