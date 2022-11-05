package soconnect.ui;

import java.util.Comparator;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Date;
import soconnect.model.todo.Description;
import soconnect.model.todo.Priority;
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
    private Label date;
    @FXML
    private Label priority;
    @FXML
    private FlowPane todoTags;

    /**
     * Creates a {@code TodoCard} with the given {@code Todo} and index to display.
     */
    public TodoCard(Todo todo, int displayedIndex) {
        super(FXML);
        this.todo = todo;
        id.setText(displayedIndex + ". ");

        Description newDescription = todo.getDescription();
        Date newDate = todo.getDate();
        Priority newPriority = todo.getPriority();
        Set<Tag> newTag = todo.getTags();

        setDescription(newDescription);
        setDate(newDate);
        setPriority(newPriority);
        setTags(newTag);
    }

    private void setDescription(Description newDescription) {
        description.setText(newDescription.value);
    }

    private void setDate(Date newDate) {
        date.setText(newDate.toString());
    }

    private void setPriority(Priority newPriority) {
        priority.setText(newPriority.priority.toUpperCase());
    }

    private void setTags(Set<Tag> newTag) {
        newTag.stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> todoTags.getChildren().add(new Label(tag.tagName)));
        todoTags.maxWidthProperty().bind(Bindings.add(-100, todoCardPane.widthProperty()));
        todoTags.getChildren().forEach(label -> label.setStyle("-fx-background-color: #9867C5;"
                + "-fx-font-size: 12;-fx-background-radius: 15;-fx-font-family: \"Karla\";"
                + "-fx-border-radius: 2;-fx-padding: 1 3 1 3; -fx-label-padding: 3 7 3 7;"
                + "-fx-text-fill: #CAEFFF; -fx-wrap-text: true;"));
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
