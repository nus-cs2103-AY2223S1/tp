package seedu.uninurse.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.person.Patient;

/**
 * An Immutable UninurseBook that is serializable to JSON format.
 */
@JsonRootName(value = "uninursebook")
class JsonSerializableUninurseBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUninurseBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableUninurseBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyUninurseBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUninurseBook}.
     */
    public JsonSerializableUninurseBook(ReadOnlyUninurseBook source) {
        persons.addAll(source.getPatientList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this uninurse book into the model's {@code UninurseBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UninurseBook toModelType() throws IllegalValueException {
        UninurseBook uninurseBook = new UninurseBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Patient person = jsonAdaptedPerson.toModelType();
            if (uninurseBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            uninurseBook.addPerson(person);
        }
        return uninurseBook;
    }

}
