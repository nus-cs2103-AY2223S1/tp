package soconnect.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import soconnect.commons.exceptions.IllegalValueException;
import soconnect.model.SoConnect;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;

/**
 * An Immutable SoConnect that is serializable to JSON format.
 */
@JsonRootName(value = "soconnect")
class JsonSerializableSoConnect {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TAG = "Tags list contains duplicate tag(s).";

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
     * @param source future changes to this will not affect the created {@code JsonSerializableSoConnect}.
     */
    public JsonSerializableSoConnect(ReadOnlySoConnect source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tags.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this SoConnect into the model's {@code SoConnect} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SoConnect toModelType() throws IllegalValueException {
        SoConnect soConnect = new SoConnect();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (soConnect.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            soConnect.addPerson(person);
        }
        for (JsonAdaptedTag jsonAdaptedTag : tags) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (soConnect.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAG);
            }
            soConnect.addTag(tag);
        }
        return soConnect;
    }

}
