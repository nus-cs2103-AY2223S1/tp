package seedu.workbook.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import seedu.workbook.commons.core.LogsCenter;

/**
 * Controller for a tips page
 */
public class TipsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(TipsWindow.class);
    private static final String FXML = "TipsWindow.fxml";

    @FXML
    private Label tipsHeader;

    @FXML
    private GridPane gridPane;

    /**
     * Creates a new TipsWindow.
     *
     * @param root Stage to use as the root of the TipsWindow.
     */
    public TipsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new TipsWindow.
     */
    public TipsWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing tips page about the internship stage.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the tips window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the tips window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the tips window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /*
     * Set the tips window header to be the stage name.
     */
    public void setTipsHeader(String stageName) {
        tipsHeader.setText("Tips for " + stageName);
    }

    /* 
     * Populates the tips window with tips.
    */
    public void populateTips(List<String> tips) {
        for (int i = 0; i < tips.size(); i++) {

            //Indexing for the tips.
            String index = String.format("%d.", i + 1);
            Label indexLabel = new Label(index);
            indexLabel.setPadding(new Insets(0, 10, 0, 0));
            indexLabel.getStyleClass().add("indexText");

            String tip = tips.get(i);
            Label tipLabel = new Label(tip);

            //Setting up Label to be fixed width.
            tipLabel.setMinWidth(500);
            tipLabel.setMaxWidth(500);
            tipLabel.setWrapText(true);
            tipLabel.getStyleClass().add("tipText");

            HBox hbox = new HBox();
            hbox.getChildren().addAll(indexLabel, tipLabel);

            hbox.getStyleClass().add("tipContainer");

            //Ensure that rows containing hbox label can grow to accomodate.
            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraint);

            gridPane.add(hbox, 0, i, 1, 1);
        } 
    }

}
