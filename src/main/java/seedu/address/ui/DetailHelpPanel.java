package seedu.address.ui;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DetailHelpPanel extends MainPanel {

    private static final String FXML = "DetailHelpPanel.fxml";

    public DetailHelpPanel() {
        super(FXML);
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.DetailHelp;
    }
}
