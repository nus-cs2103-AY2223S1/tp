package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Survin;
import seedu.address.model.ReadOnlySurvin;
import seedu.address.model.person.Person;

/**
 * An Immutable Survin that is serializable to JSON format.
 */
@JsonRootName(value = "survin")
class JsonSerializableSurvin {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSurvin} with the given persons.
     */
    @JsonCreator
    public JsonSerializableSurvin(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlySurvin} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSurvin}.
     */
    public JsonSerializableSurvin(ReadOnlySurvin source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts Survin into the model's {@code Survin} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Survin toModelType() throws IllegalValueException {
        Survin survin = new Survin();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (survin.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            survin.addPerson(person);
        }
        return survin;
    }

}
