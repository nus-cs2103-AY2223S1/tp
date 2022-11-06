package seedu.workbook.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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

    /**
     * Populates the tips window with tips.
     * @param tips List of tips to populate the tip window.
     */
    public void populateTips(List<String> tips) {
        for (int i = 0; i < tips.size(); i++) {

            int index = i + 1;
            String tip = tips.get(i);
            TipCard tipCard = new TipCard(index, tip);
            gridPane.add(tipCard.getRoot(), 0, i, 1, 1);
        }
    }

}
