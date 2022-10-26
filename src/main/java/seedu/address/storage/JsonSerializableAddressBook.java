package seedu.address.storage;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    // Contains the parent child relationship for persons, groups and tasks by their Uuids.
    private final Map<String, List<String>> itemRelationship = new HashMap<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, groups and tasks.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("groups") List<JsonAdaptedGroup> groups,
                                       @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                       @JsonProperty("hierarchy") Map<String, List<String>> itemRelationship) {
        this.persons.addAll(persons);
        this.groups.addAll(groups);
        this.tasks.addAll(tasks);
        this.itemRelationship.putAll(itemRelationship);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        ObservableList<Person> personList = source.getPersonList();
        ObservableList<Group> groupList = source.getTeamsList();
        ObservableList<Task> taskList = source.getTasksList();

        persons.addAll(personList.stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        groups.addAll(groupList.stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
        tasks.addAll(taskList.stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));

        personList.stream().forEach(person -> itemRelationship.put(
                UUID.fromString(person.getName().fullName).toString(),
                person.getParents().stream().map(Object::toString).collect(Collectors.toList())));

        groupList.stream().forEach(group -> itemRelationship.put(
                UUID.fromString(group.getName().fullName).toString(),
                group.getParents().stream().map(Object::toString).collect(Collectors.toList())));

        taskList.stream().forEach(task -> itemRelationship.put(
                UUID.fromString(task.getName().fullName).toString(),
                task.getParents().stream().map(Object::toString).collect(Collectors.toList())));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = json
        }
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

    private Group groupBuilder(Group )
}
