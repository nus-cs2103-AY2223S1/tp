package seedu.condonery.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.condonery.commons.exceptions.IllegalValueException;
import seedu.condonery.model.PropertyDirectory;
import seedu.condonery.model.ReadOnlyPropertyDirectory;
import seedu.condonery.model.person.Person;

/**
 * An Immutable PropertyDirectory that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializablePropertyDirectory {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePropertyDirectory} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePropertyDirectory(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyPropertyDirectory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePropertyDirectory}.
     */
    public JsonSerializablePropertyDirectory(ReadOnlyPropertyDirectory source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PropertyDirectory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PropertyDirectory toModelType() throws IllegalValueException {
        PropertyDirectory propertyDirectory = new PropertyDirectory();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (propertyDirectory.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            propertyDirectory.addPerson(person);
        }
        return propertyDirectory;
    }

}
