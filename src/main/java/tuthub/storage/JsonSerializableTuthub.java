package tuthub.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tuthub.commons.exceptions.IllegalValueException;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.Tuthub;
import tuthub.model.tutor.Tutor;

/**
 * An Immutable Tuthub that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTuthub {

    public static final String MESSAGE_DUPLICATE_TUTOR = "Tutors list contains duplicate tutor(s).";

    private final List<JsonAdaptedTutor> tutors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTuthub} with the given tutors.
     */
    @JsonCreator
    public JsonSerializableTuthub(@JsonProperty("tutors") List<JsonAdaptedTutor> tutors) {
        this.tutors.addAll(tutors);
    }

    /**
     * Converts a given {@code ReadOnlyTuthub} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTuthub}.
     */
    public JsonSerializableTuthub(ReadOnlyTuthub source) {
        tutors.addAll(source.getTutorList().stream().map(JsonAdaptedTutor::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Tuthub} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Tuthub toModelType() throws IllegalValueException {
        Tuthub addressBook = new Tuthub();
        for (JsonAdaptedTutor jsonAdaptedTutor : tutors) {
            Tutor tutor = jsonAdaptedTutor.toModelType();
            if (addressBook.hasTutor(tutor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTOR);
            }
            addressBook.addTutor(tutor);
        }
        return addressBook;
    }

}
