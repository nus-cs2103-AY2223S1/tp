package seedu.nutrigoals.ui;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;

/**
 * Controller for the daily calorie intake progress bar.
 */
public class ProgressBarController extends UiPart<Region> {

    private static final String FXML = "ProgressBar.fxml";

    @FXML
    private Label progressBarLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressPercentage;

    /**
     * Creates a {@code ProgressBarController} with the given {@code DoubleProperty}.
     */
    public ProgressBarController(DoubleProperty progress) {
        super(FXML);
        progressBarLabel.setText("Daily Calorie Intake Progress");
        progressBar.progressProperty().bind(progress);
        progressPercentage.textProperty().bind(progress.multiply(100.0).asString("%.0f%%"));
    }
}
