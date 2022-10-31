package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the list of command names.
 */
public class CommandNameListPanel extends UiPart<Region> {
    private static final String FXML = "CommandNameListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CommandNameListPanel.class);

    @FXML
    private ListView<String> commandNameListView;

    /**
     * Creates a {@code CommandNameListPanel} with the given {@code ObservableList}.
     */
    public CommandNameListPanel(ObservableList<String> commandNameList) {
        super(FXML);
        commandNameListView.setItems(commandNameList);
    }

    /**
     * Gets ListView
     *
     * @return ListView
     */
    public ListView<String> getCommandNameListView() {
        return commandNameListView;
    }
}
