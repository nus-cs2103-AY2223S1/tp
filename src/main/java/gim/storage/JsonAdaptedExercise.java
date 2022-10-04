package gim.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import gim.commons.exceptions.IllegalValueException;
import gim.model.exercise.Address;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Exercise}.
 */
class JsonAdaptedExercise {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Exercise's %s field is missing!";

    private final String name;
    private final String weight;
    private final String sets;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedExercise} with the given exercise details.
     */
    @JsonCreator
    public JsonAdaptedExercise(@JsonProperty("name") String name, @JsonProperty("weight") String weight,
            @JsonProperty("sets") String sets, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Exercise} into this class for Jackson use.
     */
    public JsonAdaptedExercise(Exercise source) {
        name = source.getName().fullName;
        weight = source.getWeight().value;
        sets = source.getSets().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted exercise object into the model's {@code Exercise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exercise.
     */
    public Exercise toModelType() throws IllegalValueException {
        final List<Tag> exerciseTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            exerciseTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(weight);

        if (sets == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Sets.class.getSimpleName()));
        }
        if (!Sets.isValidSets(sets)) {
            throw new IllegalValueException(Sets.MESSAGE_CONSTRAINTS);
        }
        final Sets modelSets = new Sets(sets);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(exerciseTags);
        return new Exercise(modelName, modelWeight, modelSets, modelAddress, modelTags);
    }

}
