package gim.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import gim.commons.exceptions.IllegalValueException;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.date.Date;


/**
 * Jackson-friendly version of {@link Exercise}.
 */
class JsonAdaptedExercise {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Exercise's %s field is missing!";

    private final String name;
    private final String weight;
    private final String sets;
    private final String reps;
    private final JsonAdaptedTag date;

    /**
     * Constructs a {@code JsonAdaptedExercise} with the given exercise details.
     */
    @JsonCreator
    public JsonAdaptedExercise(@JsonProperty("name") String name, @JsonProperty("weight") String weight,
            @JsonProperty("sets") String sets, @JsonProperty("reps") String reps,
            @JsonProperty("tagged") JsonAdaptedTag date) {
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
        this.date = date;
    }

    /**
     * Converts a given {@code Exercise} into this class for Jackson use.
     */
    public JsonAdaptedExercise(Exercise source) {
        name = source.getName().fullName;
        weight = source.getWeight().value;
        sets = source.getSets().value;
        reps = source.getReps().value;
        date = new JsonAdaptedTag(source.getDate());
    }

    /**
     * Converts this Jackson-friendly adapted exercise object into the model's {@code Exercise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted exercise.
     */
    public Exercise toModelType() throws IllegalValueException {
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

        if (reps == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Reps.class.getSimpleName()));
        }
        if (!Reps.isValidReps(reps)) {
            throw new IllegalValueException(Reps.MESSAGE_CONSTRAINTS);
        }
        final Reps modelReps = new Reps(reps);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date.toModelType().toString())) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDates = date.toModelType();
        return new Exercise(modelName, modelWeight, modelSets, modelReps, modelDates);
    }

}
