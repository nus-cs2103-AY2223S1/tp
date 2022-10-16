package soconnect.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import soconnect.commons.exceptions.IllegalValueException;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.SoConnect;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;

/**
 * An Immutable SoConnect that is serializable to JSON format.
 */
@JsonRootName(value = "soconnect")
class JsonSerializableSoConnect {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TAG = "Tags list contains duplicate tag(s).";
    public static final String MESSAGE_TAG_NOT_FOUND = "Person contains tags not found in the Tags list.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSoConnect} with the given persons.
     */
    @JsonCreator
    public JsonSerializableSoConnect(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.persons.addAll(persons);
        this.tags.addAll(tags);
    }

    /**
     * Converts a given {@code ReadOnlySoConnect} into this class for Jackson use.
     *
     * @param source Future changes to this will not affect the created {@code JsonSerializableSoConnect}.
     */
    public JsonSerializableSoConnect(ReadOnlySoConnect source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tags.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this SoConnect into the model's {@code SoConnect} object.
     *
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public SoConnect toModelType() throws IllegalValueException {
        SoConnect soConnect = new SoConnect();
        List<Tag> tempTagList = new ArrayList<>();

        for (JsonAdaptedTag jsonAdaptedTag : tags) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (soConnect.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAG);
            }
            soConnect.addTag(tag);
            tempTagList.add(tag);
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (soConnect.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }

            List<Tag> personTags = new ArrayList<>(person.getTags());
            for (int i = 0; i < personTags.size(); i++) {
                if (!soConnect.hasTag(personTags.get(i))) {
                    throw new IllegalValueException(MESSAGE_TAG_NOT_FOUND);
                } else {
                    int index = tempTagList.indexOf(personTags.get(i));
                    personTags.set(i, tempTagList.get(index));
                }
            }
            Set<Tag> updatedTags = new HashSet<>(personTags);
            Person newPerson = new Person(person.getName(),
                    person.getPhone(),
                    person.getEmail(),
                    person.getAddress(),
                    updatedTags);
            soConnect.addPerson(newPerson);
        }

        return soConnect;
    }

}
