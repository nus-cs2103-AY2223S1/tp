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
import seedu.address.model.remark.RemarkAddress;
import seedu.address.model.remark.RemarkName;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Remark}.
 */
class JsonAdaptedRemark {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Remark's %s field is missing!";

    private final String name;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRemark} with the given company details.
     */
    @JsonCreator
    public JsonAdaptedRemark(@JsonProperty("name") String name, @JsonProperty("address") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Remark} into this class for Jackson use.
     */
    public JsonAdaptedRemark(Remark source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted company object into the model's {@code Remark} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted company.
     */
    public Remark toModelType() throws IllegalValueException {
        final List<Tag> clientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            clientTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RemarkName.class.getSimpleName()));
        }
        if (!RemarkName.isValidName(name)) {
            throw new IllegalValueException(RemarkName.MESSAGE_CONSTRAINTS);
        }
        final RemarkName modelName = new RemarkName(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RemarkAddress.class.getSimpleName()));
        }
        if (!RemarkAddress.isValidRemarkAddress(address)) {
            throw new IllegalValueException(RemarkAddress.MESSAGE_CONSTRAINTS);
        }
        final RemarkAddress modelAddress = new RemarkAddress(address);

        final Set<Tag> modelTags = new HashSet<>(clientTags);
        return new Remark(modelName, modelAddress, modelTags);
    }

}
