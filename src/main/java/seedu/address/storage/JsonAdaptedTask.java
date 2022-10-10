package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.comment.Comment;
import seedu.address.model.comment.CommentDescription;
import seedu.address.model.comment.CommentTitle;

public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String title;
    private final String description;

    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.title = title;
        this.description = description;
    }

    public JsonAdaptedTask(Comment source) {
        title = source.getTitle().title;
        description = source.getDescription().description;
    }

    public Comment toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, CommentTitle.class.getSimpleName()));
        }
        if (!CommentTitle.isValidTitle(title)) {
            throw new IllegalValueException(CommentTitle.MESSAGE_CONSTRAINTS);
        }
        final CommentTitle modelTitle = new CommentTitle(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, CommentDescription.class.getSimpleName()));
        }
        if (!CommentDescription.isValidDescription(description)) {
            throw new IllegalValueException(CommentDescription.MESSAGE_CONSTRAINTS);
        }
        final CommentDescription modelDescription = new CommentDescription(description);

        return new Comment(modelTitle, modelDescription);
    }
}
