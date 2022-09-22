package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A window that shows the statistics of the tutor.
 */
public class StatisticsDisplay extends UiPart<Region> {

    private static final String FXML = "StatisticsDisplay.fxml";

    @FXML
    private TextArea statisticsDisplay;

    public StatisticsDisplay() {
        super(FXML);
    }

    public void showStatisticsOfUser(String statisticsOfUser) {
        requireNonNull(statisticsOfUser);
        statisticsDisplay.setText(statisticsOfUser);
    }

}
