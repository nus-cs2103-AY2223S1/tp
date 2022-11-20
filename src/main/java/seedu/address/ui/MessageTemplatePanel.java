package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.message.Message;

/**
 * Panel which displays the list of available message templates.
 */
public class MessageTemplatePanel extends UiPart<Region> {
    private static final String FXML = "MessageTemplatePanel.fxml";

    @FXML
    private ListView<Message> messageTemplateView;

    /**
     * Creates a {@code MessageTemplatePanel} with the given {@code ObservableList} of messages.
     * @param messageList
     */
    public MessageTemplatePanel(ObservableList<Message> messageList) {
        super(FXML);
        messageTemplateView.setItems(messageList);
        messageTemplateView.setCellFactory(listView -> new MessageTemplateListViewCell());
    }
}

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Message} using a {@code MessageCard}.
 */
class MessageTemplateListViewCell extends ListCell<Message> {
    private static final String PLACEHOLDER_TEXT = "PLACEHOLDER MESSAGE TEMPLATE";
    private final StackPane pane; // needed to enforce textwrapping

    public MessageTemplateListViewCell() {
        pane = new StackPane();
        pane.setMinWidth(0);
        pane.setPrefWidth(1);
        pane.setMinHeight(0);
        pane.getChildren().add(new MessageTemplateCard(-1, new Message(PLACEHOLDER_TEXT)).getRoot());
    }

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty || message == null) {
            setGraphic(null);
            setText(null);
        } else {
            pane.getChildren().clear();
            pane.getChildren().add(new MessageTemplateCard(getIndex() + 1, message).getRoot());
            setGraphic(pane);
        }
    }
}
