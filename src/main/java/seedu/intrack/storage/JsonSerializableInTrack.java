package seedu.intrack.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.internship.Internship;

/**
 * An Immutable InTrack that is serializable to JSON format.
 */
@JsonRootName(value = "intrack")
class JsonSerializableInTrack {

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "Internships list contains duplicate internship(s).";

    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInTrack} with the given internships.
     */
    @JsonCreator
    public JsonSerializableInTrack(@JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code ReadOnlyInTrack} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInTrack}.
     */
    public JsonSerializableInTrack(ReadOnlyInTrack source) {
        internships.addAll(source.getInternshipList().stream().map(JsonAdaptedInternship::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this internship tracker into the model's {@code InTrack} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InTrack toModelType() throws IllegalValueException {
        InTrack inTrack = new InTrack();
        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (inTrack.hasInternship(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERNSHIP);
            }
            inTrack.addInternship(internship);
        }
        return inTrack;
    }

}
