package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_GROUP = "Groups list contains duplicate group(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";
    public static final String MESSAGE_INVALID_GROUP_PARENT_COUNT = "Wrong number of group parent.";
    public static final String MESSAGE_NONEXISTING_UID_PARENT_PAIR =
            "Uid (%s) does not have a corresponding parent associated.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    // Contains the parent child relationship for persons, groups and tasks by their Uuids.
    private final Map<String, List<String>> itemRelationship = new HashMap<>();

    // Contains information from the singleton AddressBookParser.
    private final JsonAdaptedAddressBookParser jsonAdaptedAddressBookParser;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, groups and tasks.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("groups") List<JsonAdaptedGroup> groups,
            @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
            @JsonProperty("itemRelationship") Map<String, List<String>> itemRelationship,
            @JsonProperty("addressBookParser") JsonAdaptedAddressBookParser jsonAdaptedAddressBookParser) {
        this.persons.addAll(persons);
        this.groups.addAll(groups);
        this.tasks.addAll(tasks);
        this.itemRelationship.putAll(itemRelationship);
        this.jsonAdaptedAddressBookParser = jsonAdaptedAddressBookParser;
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

        personList.forEach(person -> itemRelationship.put(person.getUid().toString(),
                person.getParents().stream().map(parent -> parent.getUid().toString()).collect(Collectors.toList())));

        groupList.forEach(group -> itemRelationship.put(
                group.getUid().toString(),
                group.getParents().stream().map(parent -> parent.getUid().toString()).collect(Collectors.toList())));

        taskList.forEach(task -> itemRelationship.put(
                task.getUid().toString(),
                task.getParents().stream().map(parent -> parent.getUid().toString()).collect(Collectors.toList())));

        jsonAdaptedAddressBookParser = new JsonAdaptedAddressBookParser(AddressBookParser.get());
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        Map<String, Group> builtGroup = new HashMap<>();
        Map<String, Person> builtPerson = new HashMap<>();
        Map<String, Task> builtTask = new HashMap<>();

        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            builtGroup.put(jsonAdaptedGroup.getUid(), jsonAdaptedGroup.toModelType());
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            builtPerson.put(jsonAdaptedPerson.getUid(), jsonAdaptedPerson.toModelType());
        }

        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            builtTask.put(jsonAdaptedTask.getUid(), jsonAdaptedTask.toModelType());
        }

        // Build groups
        for (Map.Entry<String, Group> pair : builtGroup.entrySet()) {
            Group group = pair.getValue();
            List<String> parentUid = itemRelationship.get(pair.getKey());
            if (parentUid.size() > 1) {
                throw new IllegalValueException(MESSAGE_INVALID_GROUP_PARENT_COUNT);
            }

            if (parentUid.size() == 1) {
                group.setParent(builtGroup.get(parentUid.get(0)));
            }

            if (addressBook.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }

            addressBook.addTeam(group);
        }

        Map<String, AbstractSingleItem> builtSingleItem = new HashMap<>(builtTask);
        builtSingleItem.putAll(builtGroup);

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            List<String> parentUidList = itemRelationship.get(jsonAdaptedPerson.getUid());
            for (String parentUid : parentUidList) {
                if (!builtSingleItem.containsKey(parentUid)) {
                    throw new IllegalValueException(MESSAGE_NONEXISTING_UID_PARENT_PAIR);
                }

                person.setParent(builtSingleItem.get(parentUid));
            }

            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }

            addressBook.addPerson(person);
        }

        Map<String, DisplayItem> builtDisplayItem = new HashMap<>(builtGroup);
        builtDisplayItem.putAll(builtTask);
        builtDisplayItem.putAll(builtPerson);

        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            List<String> parentUidList = itemRelationship.get(jsonAdaptedTask.getUid());
            for (String parentUid : parentUidList) {
                if (!builtDisplayItem.containsKey(parentUid)) {
                    throw new IllegalValueException(MESSAGE_NONEXISTING_UID_PARENT_PAIR);
                }

                task.setParent(builtDisplayItem.get(parentUid));
            }

            if (addressBook.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            addressBook.addTask(task);
        }

        if (jsonAdaptedAddressBookParser != null) {
            jsonAdaptedAddressBookParser.toModelType();
        }

        return addressBook;
    }
}
