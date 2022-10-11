package nus.climods.storage.module.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nus.climods.commons.exceptions.IllegalValueException;
import nus.climods.model.AddressBook;
import nus.climods.model.ReadOnlyAddressBook;
import nus.climods.model.person.Person;

/**
 * An Immutable userModuleList that is serializable to JSON format.
 */
@JsonRootName(value = "usermodulelist")
class JsonSerializableUserModuleList {

    // TODO: Change AddressBook model to UserModuleList after model has been created.
    public static final String MESSAGE_DUPLICATE_MODULE = "Modules list contains duplicate person(s).";

    private final List<JsonAdaptedUserModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableUserModuleList} with the given modules.
     */
    @JsonCreator
    public JsonSerializableUserModuleList(@JsonProperty("persons") List<JsonAdaptedUserModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUserModuleList}.
     */
    public JsonSerializableUserModuleList(ReadOnlyAddressBook source) {
        modules.addAll(source.getPersonList().stream().map(JsonAdaptedUserModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedUserModule jsonAdaptedModule : modules) {
            Person person = jsonAdaptedModule.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
