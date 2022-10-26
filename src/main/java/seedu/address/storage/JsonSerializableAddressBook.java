package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MassLinkers;
import seedu.address.model.ReadOnlyMassLinkers;
import seedu.address.model.person.Person;

/**
 * An Immutable MassLinkers that is serializable to JSON format.
 */
@JsonRootName(value = "massLinkers")
class JsonSerializableMassLinkers {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMassLinkers} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMassLinkers(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyMassLinkers} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMassLinkers}.
     */
    public JsonSerializableMassLinkers(ReadOnlyMassLinkers source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this mass linkers into the model's {@code MassLinkers} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MassLinkers toModelType() throws IllegalValueException {
        MassLinkers massLinkers = new MassLinkers();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (massLinkers.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            massLinkers.addPerson(person);
        }
        return massLinkers;
    }

}
