package seedu.address.ui.command.window;

import java.util.HashSet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.model.tag.Tag;

/**
 * Handles tag creation and deletion by the GUI.
 */
class TagsHandler {

    private static final String ERROR_BLANK_TAG_NAME = "Tag name should not be blank!";
    private static final String ERROR_DUPLICATED_TAG_NAME = "This tag has already been added!";

    private final HashSet<String> uniqueTags;
    private final ErrorDisplay errorDisplay;
    @FXML
    private final TextField tagField;
    @FXML
    private final FlowPane tags;

    TagsHandler(TextField tagField, FlowPane tags, ErrorDisplay errorDisplay) {
        this.tagField = tagField;
        this.tags = tags;
        this.errorDisplay = errorDisplay;
        uniqueTags = new HashSet<>();

        tagField.setOnKeyPressed(keyPressed -> {
            if (keyPressed.getCode() == KeyCode.ENTER) {
                handleAddTag();
            }
        });
    }

    void clear() {
        tagField.clear();
        tags.getChildren().clear();
        uniqueTags.clear();
    }

    HashSet<String> getTags() {
        return uniqueTags;
    }

    void handleAddTag() {
        String tagName = tagField.getText().trim();

        if (tagName.isEmpty()) {
            errorDisplay.setError(ERROR_BLANK_TAG_NAME);
            return;
        }

        if (!Tag.isValidTagName(tagName)) {
            errorDisplay.setError(Tag.MESSAGE_CONSTRAINTS);
            return;
        }

        if (uniqueTags.add(tagName)) {
            HBox newTag = constructNewTag(tagName);
            tags.getChildren().add(newTag);
            errorDisplay.clearError();
        } else {
            errorDisplay.setError(ERROR_DUPLICATED_TAG_NAME);
        }

        tagField.clear();
    }

    private HBox constructNewTag(String tagName) {
        Label newTagName = new Label(tagName);
        Label deleteTag = new Label("X");
        deleteTag.getStyleClass().add("deleteTagLabel");

        HBox newTag = new HBox(newTagName, deleteTag);
        newTag.setSpacing(4);
        newTag.setMaxWidth(100);
        deleteTag.setOnMouseClicked(e -> {
            tags.getChildren().remove(newTag);
            uniqueTags.remove(tagName);
        });

        return newTag;
    }
}
