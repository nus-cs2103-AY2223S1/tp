package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ProfNus;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * An Immutable ProfNus that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contain duplicate module(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedStudent> persons,
                                       @JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.persons.addAll(persons);
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     * Makes use of 2 methods to either add a student or person into the storage.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().filter(x -> !(x instanceof Student))
                .map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        persons.addAll(source.getPersonList().stream().filter(x -> x instanceof Student)
                .map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ProfNus} object.
     * Polymorphism occurs.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ProfNus toModelType() throws IllegalValueException {
        ProfNus profNus = new ProfNus();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (profNus.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            profNus.addPerson(person);
        }
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (person instanceof Student) {
                Student student = (Student) person;
                if (student.isTeachingAssistant()) {
                    if (profNus.hasTutor(student)) {
                        throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
                    }
                    profNus.addTutor(student);
                }
            }
        }
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (profNus.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            profNus.addModule(module);
        }
        return profNus;
    }

}
