package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTeachersPet;
import seedu.address.model.TeachersPet;
import seedu.address.model.person.Person;

/**
 * An Immutable TeachersPet that is serializable to JSON format.
 */
@JsonRootName(value = "teacherspet")
class JsonSerializableTeachersPet {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTeachersPet} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTeachersPet(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTeachersPet} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTeachersPet}.
     */
    public JsonSerializableTeachersPet(ReadOnlyTeachersPet source) {
        assert source != null;
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this teacher's pet into the model's {@code TeachersPet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeachersPet toModelType() throws IllegalValueException {
        TeachersPet teachersPet = new TeachersPet();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (teachersPet.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            teachersPet.addPerson(person);
        }
        return teachersPet;
    }

}
