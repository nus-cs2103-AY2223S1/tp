package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson extends JsonAdaptedAbstractDisplayItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
        @JsonProperty("uid") String uid, @JsonProperty("tags") List<JsonAdaptedTag> tags,
        @JsonProperty("attributes") List<JsonAdaptedAbstractAttribute> attributes) {
        super(name, uid, attributes, tags);
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        super(source.getName().fullName, source.getUid().toString(),
            source.getSavedAttributes().stream()
                .map(JsonAdaptedAbstractAttribute::new)
                .collect(Collectors.toList()),
            source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        final List<Attribute> modelAttributes = new ArrayList<>();

        // Exception handling is not supported in Java streams.
        for (JsonAdaptedTag tag : getTags()) {
            personTags.add(tag.toModelType());
        }

        for (JsonAdaptedAbstractAttribute attribute : getAttributes()) {
            modelAttributes.add(attribute.toModelType());
        }

        String name = getName();
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        // dummy fields
        final Set<Tag> modelTags = new HashSet<>(personTags);

        Person p = new Person(name);
        p.setTags(modelTags);
        modelAttributes.stream().forEach(attribute -> p.addAttribute(attribute));
        return p;
    }

}
