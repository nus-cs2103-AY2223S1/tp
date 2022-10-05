package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DetailPanel extends MainPanel {

    private static final String FXML = "DetailPanel.fxml";

    public DetailPanel(Person person) {
        super(FXML);
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.Detail;
    }
}
