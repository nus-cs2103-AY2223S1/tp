package taskbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import taskbook.commons.exceptions.IllegalValueException;
import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.TaskBook;
import taskbook.model.person.Person;

/**
 * An Immutable TaskBook that is serializable to JSON format.
 */
// TODO: Verify JsonRootName
@JsonRootName(value = "taskbook")
class JsonSerializableTaskBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTaskBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTaskBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskBook}.
     */
    public JsonSerializableTaskBook(ReadOnlyTaskBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task book into the model's {@code TaskBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskBook toModelType() throws IllegalValueException {
        TaskBook taskBook = new TaskBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (taskBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            taskBook.addPerson(person);
        }
        return taskBook;
    }

}
