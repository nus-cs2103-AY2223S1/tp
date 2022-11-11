package seedu.address.storage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.tutor.Tutor;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "tutoraddressbook")
class JsonSerializableTutorAddressBook {

    public static final String MESSAGE_DUPLICATE_TUTOR = "Tutors list contains duplicate tutor(s).";

    private final List<JsonAdaptedTutor> tutors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given tutors.
     */
    @JsonCreator
    public JsonSerializableTutorAddressBook(@JsonProperty("tutors") List<JsonAdaptedTutor> tutors) {
        this.tutors.addAll(tutors);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTutorAddressBook(ReadOnlyAddressBook source) {
        tutors.addAll(source.getTutorList().sorted((first, second) -> {
            HashMap<Integer, Object> a = first.getUniqueId();
            HashMap<Integer, Object> b = second.getUniqueId();
            Instant t = (Instant) a.get(0);
            int result = t.compareTo((Instant) b.get(0));
            if (result == 0) {
                return ((int) a.get(1)) - ((int) b.get(1));
            }
            return result;
        })
                .stream()
                .map(JsonAdaptedTutor::new)
                .collect(Collectors.toList()));
    }

    /**
     * Returns the list of JsonAdaptedTutors.
     */
    public List<Tutor> getTutorsList() throws IllegalValueException {
        List<Tutor> tutorList = new ArrayList<>();
        for (JsonAdaptedTutor jsonAdaptedTutor : tutors) {
            Tutor tutor = jsonAdaptedTutor.toModelType();
            tutorList.add(tutor);
        }
        return tutorList;
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedTutor jsonAdaptedTutor : tutors) {
            Tutor tutor = jsonAdaptedTutor.toModelType();
            if (addressBook.hasPerson(tutor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUTOR);
            }
            addressBook.addPerson(tutor);
        }
        return addressBook;
    }
}
