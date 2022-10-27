package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;

/**
 * An UI component that displays information of a {@code Customer}.
 */
public class CustomerDetailsPane extends UiPart<Region> {

    private static final String FXML = "CustomerDetailsPane.fxml";
    private static final String COMMISSION_LABEL_PLURAL = "Commissions";
    private static final String COMMISSION_LABEL_SINGULAR = "Commission";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Customer customer;

    @FXML
    private HBox detailsPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label totalRevenue;
    @FXML
    private Label commissionCount;
    @FXML
    private Label commissionCompletedCount;
    @FXML
    private Label commissionInProgressCount;
    @FXML
    private Label commissionNotStartedCount;
    @FXML
    private Label commissionLabel;
    @FXML
    private FlowPane tags;
    @FXML
    private HBox pieChartPlaceholder;

    /**
     * Creates a {@code CustomerCode} with the given {@code Customer} and index to display.
     */
    public CustomerDetailsPane(ObservableObject<Customer> customer) {
        super(FXML);
        this.customer = customer.getValue();
        handleCustomerCommissionChanges(this.customer);
        customer.addListener((observable, oldValue, newValue) -> handleCustomerCommissionChanges(newValue));
    }

    private void updateUI(Customer customer) {
        if (customer == null) {
            name.setText("No customer selected");
            phone.setText("");
            address.setText("");
            email.setText("");
            totalRevenue.setText("");
            commissionLabel.setText(COMMISSION_LABEL_PLURAL); // 0 commissions
            commissionCount.setText("");
            commissionCompletedCount.setText("");
            commissionInProgressCount.setText("");
            commissionNotStartedCount.setText("");
            tags.getChildren().clear();
            pieChartPlaceholder.getChildren().clear();
        } else {
            name.setText(customer.getName().fullName);
            phone.setText(customer.getPhone().value);
            address.setText(customer.getAddress().map(address -> address.value).orElse(""));
            email.setText(customer.getEmail().value);
            totalRevenue.setText(String.format("$%.2f", customer.getRevenue()));
            commissionLabel.setText(customer.getCommissionCount() == 1
                    ? COMMISSION_LABEL_SINGULAR : COMMISSION_LABEL_PLURAL);
            commissionCount.setText(Long.toString(customer.getCommissionCount()));

            commissionCompletedCount.setText(Long.toString(customer.getCompletedCommissionCount()));
            commissionInProgressCount.setText(Long.toString(customer.getInProgressCommissionCount()));
            commissionNotStartedCount.setText(Long.toString(customer.getNotStartedCommissionCount()));

            if (customer.getCommissionCount() > 0) {
                PieChart.Data completed = new PieChart.Data("Completed", customer.getCompletedCommissionCount());
                PieChart.Data inProgress = new PieChart.Data("In Progress", customer.getInProgressCommissionCount());
                PieChart.Data notStarted = new PieChart.Data("Not Started", customer.getNotStartedCommissionCount());
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        completed, inProgress, notStarted);
                PieChart pieChart = new PieChart(pieChartData);
                pieChart.setLabelsVisible(false);
                pieChart.setStyle("-fx-background-insets: 0; -fx-border-width: 0;");
                pieChart.setMinWidth(150);
                pieChart.setMaxWidth(150);
                pieChart.setMinHeight(150);
                pieChart.setMaxHeight(150);
                pieChartPlaceholder.getChildren().setAll(pieChart);
                completed.getNode().setStyle("-fx-background-insets: 0; -fx-border-width: 0; -fx-pie-color: " + "#32AE46");
                inProgress.getNode().setStyle("-fx-background-insets: 0; -fx-border-width: 0; -fx-pie-color: " + "#548DE1");
                notStarted.getNode().setStyle("-fx-background-insets: 0; -fx-border-width: 0; -fx-pie-color: " + "#9DA0A5");
            } else {
                pieChartPlaceholder.getChildren().clear();
            }

            tags.getChildren().clear();
            customer.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        }
    }

    private void handleCustomerCommissionChanges(Customer newValue) {
        if (newValue != null) {
            ObservableList<Commission> observableList = newValue.getCommissionList();
            observableList.addListener((ListChangeListener<Commission>) c -> {
                updateUI(newValue);
            });
        }
        updateUI(newValue);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CustomerDetailsPane)) {
            return false;
        }

        // state check
        CustomerDetailsPane pane = (CustomerDetailsPane) other;
        return customer.isSameCustomer(pane.customer);
    }
}
