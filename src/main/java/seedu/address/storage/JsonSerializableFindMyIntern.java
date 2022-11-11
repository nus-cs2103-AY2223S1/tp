package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FindMyIntern;
import seedu.address.model.ReadOnlyFindMyIntern;
import seedu.address.model.internship.Internship;

/**
 * An Immutable FindMyIntern that is serializable to JSON format.
 */
@JsonRootName(value = "FindMyIntern")
class JsonSerializableFindMyIntern {

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "Internships list contains duplicate internship(s).";

    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFindMyIntern} with the given internships.
     */
    @JsonCreator
    public JsonSerializableFindMyIntern(@JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code ReadOnlyFindMyIntern} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFindMyIntern}.
     */
    public JsonSerializableFindMyIntern(ReadOnlyFindMyIntern source) {
        internships.addAll(source.getInternshipList().stream().map(JsonAdaptedInternship::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this findMyIntern into the model's {@code FindMyIntern} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FindMyIntern toModelType() throws IllegalValueException {
        FindMyIntern findMyIntern = new FindMyIntern();
        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (findMyIntern.hasInternship(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERNSHIP);
            }
            findMyIntern.addInternship(internship);
        }
        return findMyIntern;
    }

}
