package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.RemarkName;
import seedu.address.model.remark.Text;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Remark}.
 */
class JsonAdaptedRemark {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Remark's %s field is missing!";

    private final String text;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRemark} with the given remark details.
     */
    @JsonCreator
    public JsonAdaptedRemark(@JsonProperty("text") String text,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.text = text;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Remark} into this class for Jackson use.
     */
    public JsonAdaptedRemark(Remark source) {
        text = source.getText().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted remark object into the model's {@code Remark} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted remark.
     */
    public Remark toModelType() throws IllegalValueException {
        final List<Tag> clientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            clientTags.add(tag.toModelType());
        }

        if (text == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Text.class.getSimpleName()));
        }
        if (!Text.isValidText(text)) {
            throw new IllegalValueException(Text.MESSAGE_CONSTRAINTS);
        }
        final Text modelText = new Text(text);

        final Set<Tag> modelTags = new HashSet<>(clientTags);
        return new Remark(modelText, modelTags);
    }

}
