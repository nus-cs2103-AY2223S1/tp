package tuthub.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import tuthub.model.tutor.Comment;

/**
 * Jackson-friendly version of {@link Comment}.
 */
class JsonAdaptedComment {

    private final String comment;

    /**
     * Constructs a {@code JsonAdapatedComment} with the given {@code comment}.
     */
    @JsonCreator
    public JsonAdaptedComment(String comment) {
        this.comment = comment;
    }

    /**
     * Converts a given {@code Comment} into this class for Jackson use.
     */
    public JsonAdaptedComment(Comment source) {
        comment = source.value;
    }

    @JsonValue
    public String getComment() {
        return comment;
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Comment} object.
     */
    public Comment toModelType() {
        return new Comment(comment);
    }

}
