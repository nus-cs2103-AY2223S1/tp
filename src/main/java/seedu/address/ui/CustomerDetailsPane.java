package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.model.iteration.Iteration;

/**
 * A UI component that displays information of a {@code Customer}.
 */
public class CustomerDetailsPane extends UiPart<Region> {

    private static final String FXML = "CustomerDetailsPane.fxml";
    private static final String COMMISSION_LABEL_PLURAL = "Commissions";
    private static final String COMMISSION_LABEL_SINGULAR = "Commission";
    private static final String PIE_CHART_DATA_STYLE =
            "-fx-background-insets: 0; -fx-border-width: 0; -fx-pie-color: ";
    private static final String PIE_CHART_DATA_COMPLETED_BACKGROUND_COLOR = "#32AE46";
    private static final String PIE_CHART_DATA_IN_PROGRESS_BACKGROUND_COLOR = "#548DE1";
    private static final String PIE_CHART_DATA_NOT_STARTED_BACKGROUND_COLOR = "#9DA0A5";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Customer customer;
    private final PieChart pieChart;

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
        pieChart = instantiatePieChart();
        handleCustomerCommissionChanges(this.customer);
        customer.addListener((observable, oldValue, newValue) -> handleCustomerCommissionChanges(newValue));
    }

    private void updateUI(Customer customer) {
        if (customer == null) {
            clearAllUiFields();
        } else {
            setCustomerUiFields(customer);
            setCommissionStatUi(customer);
        }
    }

    /**
     * Creates a pie chart with the styling.
     */
    private PieChart instantiatePieChart() {
        PieChart pieChart = new PieChart();
        pieChart.setLabelsVisible(false);
        pieChart.getStyleClass().add("commissionPieChart");
        pieChart.setMinWidth(150);
        pieChart.setMaxWidth(150);
        pieChart.setMinHeight(150);
        pieChart.setMaxHeight(150);
        pieChart.setAnimated(true);
        return pieChart;
    }

    /**
     * Resets the CustomerDetailsPane UI to an 'empty state'.
     */
    private void clearAllUiFields() {
        name.setText("No customer selected");
        phone.setText("");
        address.setText("");
        email.setText("");
        totalRevenue.setText("");
        commissionLabel.setText("");
        commissionCount.setText("");
        commissionCompletedCount.setText("");
        commissionInProgressCount.setText("");
        commissionNotStartedCount.setText("");
        tags.getChildren().clear();
        pieChartPlaceholder.getChildren().clear();
    }

    /**
     * Sets the CustomerDetailsPane UI customer fields to the attributes
     * of the specified customer (name, phone, address, email, tags and total revenue).
     */
    private void setCustomerUiFields(Customer customer) {
        name.setText(customer.getName().fullName);
        phone.setText(customer.getPhone().value);
        address.setText(customer.getAddress().map(address -> address.value).orElse(""));
        email.setText(customer.getEmail().value);
        tags.getChildren().clear();
        customer.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        totalRevenue.setText(String.format("$%.2f", customer.getRevenue()));
    }

    /**
     * Updates the CustomerDetailsPane UI with commission statistics.
     */
    private void setCommissionStatUi(Customer customer) {
        long totalCommissionCount = customer.getCommissionCount();
        long completedCommissionCount = customer.getCompletedCommissionCount();
        long inProgressCommissionCount = customer.getInProgressCommissionCount();
        long notStartedCommissionCount = customer.getNotStartedCommissionCount();

        // set the field label to be grammatically correct
        commissionLabel.setText(totalCommissionCount == 1 ? COMMISSION_LABEL_SINGULAR : COMMISSION_LABEL_PLURAL);

        // sets the commission count fields
        commissionCount.setText(Long.toString(totalCommissionCount));
        commissionCompletedCount.setText(Long.toString(completedCommissionCount));
        commissionInProgressCount.setText(Long.toString(inProgressCommissionCount));
        commissionNotStartedCount.setText(Long.toString(notStartedCommissionCount));

        // generates the pie chart
        if (totalCommissionCount > 0) {
            generateCommissionPieChart(completedCommissionCount, inProgressCommissionCount, notStartedCommissionCount);
        } else {
            pieChartPlaceholder.getChildren().clear();
        }
    }

    /**
     * Handles the generation of the pie chart statistic.
     */
    private void generateCommissionPieChart(long completedCommissionCount,
                                            long inProgressCommissionCount, long notStartedCommissionCount) {
        // creates the pie chart segments and the pie chart
        PieChart.Data completed = new PieChart.Data(Commission.CompletionStatusString.COMPLETED.toString(),
                completedCommissionCount);
        PieChart.Data inProgress = new PieChart.Data(Commission.CompletionStatusString.IN_PROGRESS.toString(),
                inProgressCommissionCount);
        PieChart.Data notStarted = new PieChart.Data(Commission.CompletionStatusString.NOT_STARTED.toString(),
                notStartedCommissionCount);
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(completed, inProgress, notStarted);
        pieChart.setData(pieChartData);

        // styles each of the pie chart segment
        completed.getNode().setStyle(PIE_CHART_DATA_STYLE + PIE_CHART_DATA_COMPLETED_BACKGROUND_COLOR);
        inProgress.getNode().setStyle(PIE_CHART_DATA_STYLE + PIE_CHART_DATA_IN_PROGRESS_BACKGROUND_COLOR);
        notStarted.getNode().setStyle(PIE_CHART_DATA_STYLE + PIE_CHART_DATA_NOT_STARTED_BACKGROUND_COLOR);

        for (PieChart.Data data : pieChartData) {
            Tooltip.install(data.getNode(), new Tooltip(data.getName()));
        }

        // set the placeholder to the generated pie chart
        pieChartPlaceholder.getChildren().setAll(pieChart);
    }

    private void handleCustomerCommissionChanges(Customer newValue) {
        if (newValue != null) {
            ObservableList<Commission> observableCommissionList = newValue.getCommissionList();

            observableCommissionList.addListener((ListChangeListener<Commission>) c -> {
                updateUI(newValue);
            });

            observableCommissionList.forEach(commission -> {
                commission.getIterationList().addListener((ListChangeListener<Iteration>) c -> {
                    updateUI(newValue);
                });
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
