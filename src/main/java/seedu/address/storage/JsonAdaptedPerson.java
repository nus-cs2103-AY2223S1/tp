package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniqueTagTypeMap;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagType;
import seedu.address.model.tag.UniqueTagList;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
//    private final Map<JsonAdaptedTagType, JsonAdaptedTagList> tagged = new HashMap<>();
    private final List<List<JsonAdaptedTag>> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<List<JsonAdaptedTag>> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
//        if (tagged != null) {
//            this.tagged.putAll(tagged);
//        }
        if (tagged != null) {
            this.tags.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        Map<TagType, UniqueTagList> map = source.getTags();
        for (TagType t: map.keySet()) {
            Tag tagtype = new Tag(t.toString());
            JsonAdaptedTag jTagType = new JsonAdaptedTag(tagtype);
            List<JsonAdaptedTag> list = new ArrayList<>();
            list.add(jTagType);
            list.addAll(map.get(t).toStream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
            tags.add(list);
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final Map<TagType, UniqueTagList> personTags = new HashMap<>();

        for (List<JsonAdaptedTag> tags : tags) {
            String tagType = tags.get(0).toModelType().toString();
            TagType t = new TagType(tagType, UniqueTagTypeMap.getPrefixFromTagType(tagType));
            List<Tag> tagList = new ArrayList<>();
            for (JsonAdaptedTag jsonAdaptedTag : tags.subList(1, tags.size())) {
                Tag toModelType = jsonAdaptedTag.toModelType();
                tagList.add(toModelType);
            }
            UniqueTagList uniqueTagList = new UniqueTagList();
            uniqueTagList.setTags(tagList);
            personTags.put(t,uniqueTagList);
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final UniqueTagTypeMap modelTags = new UniqueTagTypeMap();
        modelTags.setTagTypeMap(personTags);
        
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags);
    }

}


