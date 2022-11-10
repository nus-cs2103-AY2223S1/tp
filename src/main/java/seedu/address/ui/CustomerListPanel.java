package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.customer.Customer;

/**
 * Panel containing the list of customers.
 */
public class CustomerListPanel extends UiPart<Region> {
    private static final String FXML = "CustomerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CustomerListPanel.class);
    private final Consumer<Customer> selectCustomer;

    @FXML
    private ListView<Customer> customerListView;
    @FXML
    private AnchorPane customerPanelControlsPlaceholder;

    /**
     * Creates a {@code CustomerListPanel} with the given {@code ObservableList}.
     */
    public CustomerListPanel(ObservableList<Customer> customerList, Consumer<Customer> selectCustomer,
                             ObservableObject<Customer> selectedCustomer, Consumer<UiPart<Stage>> addChildWindow,
                             CommandBox.CommandExecutor commandExecutor) {
        super(FXML);
        customerPanelControlsPlaceholder.getChildren()
                        .add(new CustomerListPanelControlBar(addChildWindow, commandExecutor).getRoot());
        customerListView.setItems(customerList);
        customerListView.setCellFactory(listView -> new CustomerListViewCell());
        this.selectCustomer = selectCustomer;
        customerListView.getSelectionModel().select(selectedCustomer.getValue());
        selectedCustomer.addListener((observable, oldValue, newValue) ->
                customerListView.getSelectionModel().select(newValue));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Customer} using a {@code CustomerCard}.
     */
    class CustomerListViewCell extends ListCell<Customer> {

        @Override
        protected void updateItem(Customer customer, boolean empty) {
            super.updateItem(customer, empty);

            if (empty || customer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CustomerCard(customer, getIndex() + 1).getRoot());
            }
            setOnMousePressed(e -> selectCustomer.accept(customer));
        }
    }

}
