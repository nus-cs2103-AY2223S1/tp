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
import seedu.address.model.group.Group;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Group}.
 */
class JsonAdaptedGroup extends JsonAdaptedAbstractDisplayItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("name") String name, @JsonProperty("uid") String uid,
                            @JsonProperty("tags") List<JsonAdaptedTag> tags,
                            @JsonProperty("attributes") List<JsonAdaptedAbstractAttribute> attributes) {
        super(name, uid, attributes, tags);
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        super(source.getName().fullName, source.getUuid().toString(),
                source.getAttributes().stream()
                        .map(JsonAdaptedAbstractAttribute::new)
                        .collect(Collectors.toList()),
                source.getTags().stream()
                        .map(JsonAdaptedTag::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's
     * {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Group toModelType() throws IllegalValueException {
        final List<Tag> groupTags = new ArrayList<>();
        final List<Attribute> modelAttributes = new ArrayList<>();

        // Exception handling is not supported in Java streams.
        for (JsonAdaptedTag tag : getTags()) {
            groupTags.add(tag.toModelType());
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

        final Name modelName = new Name(name);
        final Set<Tag> modelTags = new HashSet<>(groupTags);

        Name groupName = new Name(modelName.fullName);
        Group group = new Group(groupName);
        group.setTags(modelTags);
        modelAttributes.forEach(attribute -> group.addAttribute(attribute));
        return group;
    }
}
