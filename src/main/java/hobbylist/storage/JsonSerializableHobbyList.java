package hobbylist.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import hobbylist.commons.exceptions.IllegalValueException;
import hobbylist.model.HobbyList;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.activity.Activity;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableHobbyList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedActivity> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHobbyList(@JsonProperty("persons") List<JsonAdaptedActivity> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableHobbyList(ReadOnlyHobbyList source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedActivity::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HobbyList toModelType() throws IllegalValueException {
        HobbyList hobbyList = new HobbyList();
        for (JsonAdaptedActivity jsonAdaptedActivity : persons) {
            Activity activity = jsonAdaptedActivity.toModelType();
            if (hobbyList.hasPerson(activity)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            hobbyList.addPerson(activity);
        }
        return hobbyList;
    }

}
