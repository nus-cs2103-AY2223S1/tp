package seedu.address.ui;

import seedu.address.model.person.Person;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class HelpPanel extends MainPanel {

    private static final String FXML = "HelpPanel.fxml";

    public HelpPanel() {
        super(FXML);
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.Help;
    }
}
