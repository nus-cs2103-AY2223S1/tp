package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label mobile;
    @FXML
    private Label email;
    @FXML
    private FlowPane projects;

    /**
     * Creates a {@code ClientCard} with the given {@code Client} and index to display.
     */
    public ClientCard(Client client) {
        super(FXML);
        this.client = client;
        String clientIdString = client.getClientId().uiRepresentation();
        name.setText(client.getClientName().uiRepresentation(client.isPinned(), clientIdString));
        mobile.setText(client.getClientMobile().uiRepresentation());
        email.setText(client.getClientEmail().uiRepresentation());
        client.getProjects().stream()
                .sorted(Comparator.comparing(project -> project.getProjectName().toString()))
                .forEach(project -> projects.getChildren().add(new Label(project.getProjectName().toString())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}
