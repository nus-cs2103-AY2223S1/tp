package soconnect.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import soconnect.model.todo.Todo;

/**
 * An UI component that displays information of a {@code Todo}.
 */
public class TodoCard extends UiPart<Region> {
    private static final String FXML = "TodoListCard.fxml";

    public final Todo todo;

    @FXML
    private HBox todoCardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label datetime;
    @FXML
    private FlowPane priority;

    /**
     * Creates a {@code TodoCard} with the given {@code Todo} and index to display.
     */
    public TodoCard(Todo todo, int displayedIndex) {
        super(FXML);
        this.todo = todo;
        id.setText(displayedIndex + ". ");
        description.setText(todo.getDescription());
        datetime.setText(todo.getDatetime());
        priority.getChildren().add(new Label(todo.getPriority()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TodoCard)) {
            return false;
        }

        // state check
        TodoCard card = (TodoCard) other;
        return id.getText().equals(card.id.getText())
                && todo.equals(card.todo);
    }
}
