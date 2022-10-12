package gim.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import gim.commons.exceptions.IllegalValueException;
import gim.model.ExerciseTracker;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.exercise.Exercise;

/**
 * An Immutable ExerciseTracker that is serializable to JSON format.
 */
@JsonRootName(value = "exercisetracker")
class JsonSerializableExerciseTracker {

    public static final String MESSAGE_DUPLICATE_EXERCISE = "Exercises list contains duplicate exercise(s).";

    private final List<JsonAdaptedExercise> exercises = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableExerciseTracker} with the given exercises.
     */
    @JsonCreator
    public JsonSerializableExerciseTracker(@JsonProperty("exercises") List<JsonAdaptedExercise> exercises) {
        this.exercises.addAll(exercises);
    }

    /**
     * Converts a given {@code ReadOnlyExerciseTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableExerciseTracker}.
     */
    public JsonSerializableExerciseTracker(ReadOnlyExerciseTracker source) {
        exercises.addAll(source.getExerciseList().stream().map(JsonAdaptedExercise::new).collect(Collectors.toList()));
    }

    /**
     * Converts this exercise tracker into the model's {@code ExerciseTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ExerciseTracker toModelType() throws IllegalValueException {
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        for (JsonAdaptedExercise jsonAdaptedExercise : exercises) {
            Exercise exercise = jsonAdaptedExercise.toModelType();
            //            if (exerciseTracker.hasExercise(exercise)) {
            //                throw new IllegalValueException(MESSAGE_DUPLICATE_EXERCISE);
            //            }
            exerciseTracker.addExercise(exercise);
        }
        return exerciseTracker;
    }

}
