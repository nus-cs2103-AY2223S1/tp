package seedu.clinkedin.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.ReadOnlyAddressBook;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.tag.TagType;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
public class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<String> prefixMap = new ArrayList<>();

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("prefixMap") List<String> prefixMap,
                                       @JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.prefixMap.addAll(prefixMap);
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        Map<Prefix, TagType> sourcePrefixMap = source.getPrefixMap();
        List<String> prefixMapToList = new ArrayList<>();
        for (Prefix prefix: sourcePrefixMap.keySet()) {
            prefixMapToList.add(prefix.getPrefix());
            prefixMapToList.add(sourcePrefixMap.get(prefix).getTagTypeName());
        }
        prefixMap.addAll(prefixMapToList);
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this clinkedin book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        Map<Prefix, TagType> map = new HashMap<>();
        for (int i = 0; i < prefixMap.size(); i = i + 2) {
            map.put(new Prefix(prefixMap.get(i)), new TagType(prefixMap.get(i + 1), new Prefix(prefixMap.get(i))));
        }
        addressBook.setPrefixMap(map);
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
