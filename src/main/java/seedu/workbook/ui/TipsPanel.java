package seedu.workbook.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * Controller for a tips panel
 */
public class TipsPanel extends UiPart<Region> {

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
        //Clear no tips label
        gridPane.getChildren().clear();

        for (int i = 0; i < tips.size(); i++) {
            int index = i + 1;
            String tip = tips.get(i);
            TipCard tipCard = new TipCard(index, tip);
            gridPane.add(tipCard.getRoot(), 0, i, 1, 1);
        }
    }

}
