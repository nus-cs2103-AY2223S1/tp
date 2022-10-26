package seedu.condonery.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.model.client.Client;

/**
 * Panel containing the list of properties.
 */
public class ClientListPanel extends UiPart<Region> {
    private static final String FXML = "ClientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClientListPanel.class);

    @FXML
    private ListView<Client> clientListView;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public ClientListPanel(ObservableList<Client> clientList) {
        super(FXML);
        clientListView.setItems(clientList);
        clientListView.setCellFactory(listView -> new ClientListViewCell());
    }

    /**
     * Forces re-render of the PropertyListPanel.
     */
    public void refresh() {
        clientListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
     */
    class ClientListViewCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClientCard(client, getIndex() + 1).getRoot());
            }
        }
    }

}
