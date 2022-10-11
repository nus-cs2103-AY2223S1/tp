package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.ObservableObject;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.Description;
import seedu.address.model.iteration.Iteration;

/**
 * An UI component that displays information about a {@code Commission}.
 */
public class CommissionDetailsPane extends UiPart<Region> {

    private static final String FXML = "CommissionDetailsPane.fxml";

    private final Commission commission;

    @FXML
    private HBox detailsPane;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label fee;
    @FXML
    private Label deadline;
    @FXML
    private Label completionStatus;
    @FXML
    private Label customerName;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<Iteration> iterationListView;

    /**
     * Creates a {@code CommissionDetailsPane} with the given {@code Commission} and index to display.
     */
    public CommissionDetailsPane(ObservableObject<Commission> commission) {
        super(FXML);
        this.commission = commission.getValue();
        updateUI(this.commission);
        commission.addListener((observable, oldValue, newValue) -> {
            updateUI(newValue);
        });
        iterationListView.setCellFactory(listView -> new IterationListViewCell());
    }

    private void updateUI(Commission commission) {
        if (commission == null) {
            title.setText("No commission selected");
            description.setText("");
            fee.setText("");
            deadline.setText("");
            completionStatus.setText("");
            customerName.setText("");
            tags.getChildren().clear();
            iterationListView.setItems(null);
        } else {
            title.setText(commission.getTitle().title);
            description.setText(commission.getDescription().orElseGet(() -> Description.NO_DESCRIPTION).description);
            fee.setText(commission.getFee().toString());
            deadline.setText(commission.getDeadline().toString());
            completionStatus.setText(commission.getCompletionStatus().toString());
            customerName.setText(commission.getCustomer().getName().fullName);
            tags.getChildren().clear();
            commission.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
            iterationListView.setItems(commission.getIterationList());
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Iteration} using a {@code IterationListItem}.
     */
    class IterationListViewCell extends ListCell<Iteration> {
        @Override
        protected void updateItem(Iteration iteration, boolean empty) {
            super.updateItem(iteration, empty);

            if (empty || iteration == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IterationListItem(iteration, getIndex() + 1).getRoot());
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommissionDetailsPane)) {
            return false;
        }

        // state check
        CommissionDetailsPane otherPane = (CommissionDetailsPane) other;
        return title.getText().equals(otherPane.title.getText())
                && commission.equals(otherPane.commission);
    }
}
