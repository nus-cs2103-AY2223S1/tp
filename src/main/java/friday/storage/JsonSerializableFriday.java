package friday.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import friday.commons.exceptions.IllegalValueException;
import friday.model.Friday;
import friday.model.ReadOnlyFriday;
import friday.model.student.Student;

/**
 * An Immutable Friday that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableFriday {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFriday} with the given persons.
     */
    @JsonCreator
    public JsonSerializableFriday(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyFriday} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFriday}.
     */
    public JsonSerializableFriday(ReadOnlyFriday source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Friday} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Friday toModelType() throws IllegalValueException {
        Friday addressBook = new Friday();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Student student = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(student);
        }
        return addressBook;
    }

}
