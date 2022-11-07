package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * Represents main panel of the UI. e.g. PersonListPanel, etc.
 * It is main scene of the UI.
 */
public abstract class MainPanel extends UiPart<Region> {

    public MainPanel(String fxmlFileName) {
        super(fxmlFileName);
    }

    /**
     * Returns the panel's name of the MainPanel
     */
    public abstract MainPanelName getPanelName();
}
