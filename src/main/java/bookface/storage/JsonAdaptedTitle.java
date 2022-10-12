package bookface.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import bookface.commons.exceptions.IllegalValueException;
import bookface.model.book.Title;

public class JsonAdaptedTitle {
    private final String titleName;

    /**
     * Constructs a {@code JsonAdaptedTitle} with the given {@code titleName}.
     */
    @JsonCreator
    public JsonAdaptedTitle(String titleName) {
        this.titleName = titleName;
    }

    /**
     * Converts a given {@code Title} into this class for Jackson use.
     */
    public JsonAdaptedTitle(Title source) {
        titleName = source.bookTitle;
    }

    @JsonValue
    public String getTitleName() {
        return titleName;
    }

    /**
     * Converts this Jackson-friendly adapted title object into the model's {@code Title} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted title.
     */
    public Title toModelType() throws IllegalValueException {
        if (!Title.isValidTitle(titleName)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(titleName);
    }
}
