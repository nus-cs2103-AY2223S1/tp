package gim.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.tag.Date;
import gim.model.util.SampleDataUtil;


/**
 * A utility class to help with building Exercise objects.
 */
public class ExerciseBuilder {

    public static final String DEFAULT_NAME = "Arm Curls";
    public static final String DEFAULT_WEIGHT = "50";
    public static final String DEFAULT_SETS = "5";
    public static final String DEFAULT_REPS = "5";
    public static final String DEFAULT_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    private Name name;
    private Weight weight;
    private Sets sets;
    private Reps reps;
    private Date date;

    /**
     * Creates a {@code ExerciseBuilder} with the default details.
     */
    public ExerciseBuilder() {
        name = new Name(DEFAULT_NAME);
        weight = new Weight(DEFAULT_WEIGHT);
        sets = new Sets(DEFAULT_SETS);
        reps = new Reps(DEFAULT_REPS);
        date = new Date(DEFAULT_DATE);
    }

    /**
     * Initializes the ExerciseBuilder with the data of {@code exerciseToCopy}.
     */
    public ExerciseBuilder(Exercise exerciseToCopy) {
        name = exerciseToCopy.getName();
        weight = exerciseToCopy.getWeight();
        sets = exerciseToCopy.getSets();
        reps = exerciseToCopy.getReps();
        date = exerciseToCopy.getDate();
    }

    /**
     * Sets the {@code Name} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code dates} and set it to the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withDates(String date) {
        this.date = SampleDataUtil.getDate(date);
        return this;
    }

    /**
     * Sets the {@code Reps} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withReps(String reps) {
        this.reps = new Reps(reps);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Sets the {@code Sets} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withSets(String sets) {
        this.sets = new Sets(sets);
        return this;
    }

    public Exercise build() {
        return new Exercise(name, weight, sets, reps, date);
    }

}
