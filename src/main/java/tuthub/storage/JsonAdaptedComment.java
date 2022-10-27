package tuthub.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import tuthub.commons.exceptions.IllegalValueException;
import tuthub.model.tutor.Comment;

/**
 * Jackson-friendly version of {@link Comment}.
 */
class JsonAdaptedComment {

    private final String comment;

    /**
     * Constructs a {@code JsonAdapatedComment} with the given {@code moduleName}.
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
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Comment toModelType() throws IllegalValueException {
        return new Comment(comment);
    }

}
