package coydir.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import coydir.commons.exceptions.IllegalValueException;
import coydir.model.Database;
import coydir.model.ReadOnlyDatabase;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;

/**
 * An Immutable database that is serializable to JSON format.
 */
@JsonRootName(value = "database")
class JsonSerializableDatabase {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final int maxID;
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDatabase} with the given persons.
     */
    @JsonCreator
    public JsonSerializableDatabase(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                    @JsonProperty("maxID") int maxID) {
        this.persons.addAll(persons);
        this.maxID = maxID;
    }

    /**
     * Converts a given {@code ReadOnlyDatabase} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDatabase}.
     */
    public JsonSerializableDatabase(ReadOnlyDatabase source, int maxID) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        this.maxID = maxID;
    }

    /**
     * Converts this database into the model's {@code database} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Database toModelType() throws IllegalValueException {
        EmployeeId.setCount(maxID);
        Database database = new Database();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (database.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            database.addPerson(person);
        }
        return database;
    }

}
