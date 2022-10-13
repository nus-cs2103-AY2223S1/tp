package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;

/**
 * Panel containing the list of clients.
 */
public class ClientListPanel extends UiPart<Region> {
    private static final String FXML = "ClientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClientListPanel.class);

    @FXML
    private ListView<Client> clientListView;

    @FXML
    private Label numClients;

    /**
     * Creates a {@code ClientListPanel} with the given {@code ObservableList}.
     */
    public ClientListPanel(ObservableList<Client> clientList) {
        super(FXML);
        clientListView.setItems(clientList);
        clientListView.setCellFactory(listView -> new PersonListViewCell());
        numClients.setText(numClientsString(clientList));
        clientList.addListener((ListChangeListener<? super Client>)
                c -> numClients.setText(numClientsString(clientList)));
    }

    /**
     * Returns a string denoting the number of records for {@code Client} currently shown in the {@code clientList}.
     */
    private String numClientsString(ObservableList<Client> clientList) {
        if (clientList.size() == 1) {
            return "1 record";
        } else {
            return clientList.size() + " records";
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Client} using a {@code ClientCard}.
     */
    class PersonListViewCell extends ListCell<Client> {
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
