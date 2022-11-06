package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ArchivedTaskList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "archivedTaskList")
public class JsonSerializableArchivedTaskList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate task(s).";

    private final List<JsonAdaptedPerson> archivedTasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableArchivedTaskList(@JsonProperty("archivedTasks") List<JsonAdaptedPerson> persons) {
        this.archivedTasks.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableArchivedTaskList(ReadOnlyAddressBook source) {
        archivedTasks.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ArchivedTaskList toModelType() throws IllegalValueException {
        ArchivedTaskList archivedTaskList = new ArchivedTaskList();
        for (JsonAdaptedPerson jsonAdaptedPerson : archivedTasks) {
            Task task = jsonAdaptedPerson.toModelType();
            if (archivedTaskList.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            archivedTaskList.addTask(task);
        }
        return archivedTaskList;
    }
}
