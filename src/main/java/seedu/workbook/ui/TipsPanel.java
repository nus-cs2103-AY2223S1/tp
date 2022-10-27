package seedu.workbook.ui;

import java.util.List;
import java.util.logging.Logger;
import javafx.geometry.Pos;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import seedu.workbook.commons.core.LogsCenter;


/**
 * Controller for a tips page
 */
public class TipsPanel extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(TipsWindow.class);
    private static final String FXML = "TipsPanel.fxml";

    @FXML
    private Label tipsHeader;

    @FXML
    private GridPane gridPane;

    /**
     * Creates a new TipsWindow.
     */
    public TipsPanel() {
        super(FXML);
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

            //Indexing for the tips.
            String index = String.format("%d.", i + 1);
            Label indexLabel = new Label(index);
            indexLabel.setPadding(new Insets(0, 10, 0, 0));
            indexLabel.setMinWidth(Region.USE_PREF_SIZE);
            indexLabel.setMaxWidth(Region.USE_PREF_SIZE);
            indexLabel.getStyleClass().add("indexText");

            String tip = tips.get(i);
            Label tipLabel = new Label(tip);

            //Setting up Label to be fixed width.
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

    public void noTipsPopulate() {
        Label messageLabel = new Label("Sorry, there are no tips for this stage yet.");
        messageLabel.setMinWidth(500);
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.getStyleClass().add("tipText");

        HBox hbox = new HBox();
        hbox.getChildren().add(messageLabel);

        hbox.getStyleClass().add("tipContainer");

        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().add(rowConstraint);

        gridPane.add(hbox, 0, 0, 1, 1);
    }

}
