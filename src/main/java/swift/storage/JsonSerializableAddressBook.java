package swift.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import swift.commons.exceptions.IllegalValueException;
import swift.model.AddressBook;
import swift.model.ReadOnlyAddressBook;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";
    public static final String MESSAGE_DUPLICATE_BRIDGE = "Bridges list contains duplicate bridge(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedBridge> bridges = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons,
     * tasks and bridges.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
            @JsonProperty("bridges") List<JsonAdaptedBridge> bridges) {
        this.persons.addAll(persons);
        this.tasks.addAll(tasks);
        this.bridges.addAll(bridges);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     *               {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
        bridges.addAll(source.getBridgeList().stream().map(JsonAdaptedBridge::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (addressBook.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            addressBook.addTask(task);
        }
        for (JsonAdaptedBridge jsonAdaptedBridge : bridges) {
            PersonTaskBridge bridge = jsonAdaptedBridge.toModelType();
            if (addressBook.hasBridge(bridge)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BRIDGE);
            }
            addressBook.addBridge(bridge);
        }
        return addressBook;
    }

}
