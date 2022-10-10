package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTrackAScholar;
import seedu.address.model.TrackAScholar;
import seedu.address.model.applicant.Applicant;

/**
 * An Immutable TrackAScholar that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate applicant(s).";

    private final List<JsonAdaptedTrackAScholar> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedTrackAScholar> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTrackAScholar} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyTrackAScholar source) {
        persons.addAll(source.getApplicantList().stream().map(JsonAdaptedTrackAScholar::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TrackAScholar} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TrackAScholar toModelType() throws IllegalValueException {
        TrackAScholar trackAScholar = new TrackAScholar();
        for (JsonAdaptedTrackAScholar jsonAdaptedTrackAScholar : persons) {
            Applicant applicant = jsonAdaptedTrackAScholar.toModelType();
            if (trackAScholar.hasApplicant(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            trackAScholar.addApplicant(applicant);
        }
        return trackAScholar;
    }

}
