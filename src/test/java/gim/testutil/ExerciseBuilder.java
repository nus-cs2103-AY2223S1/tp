package gim.testutil;

import java.util.HashSet;
import java.util.Set;

import gim.model.exercise.Address;
import gim.model.exercise.Sets;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Weight;
import gim.model.tag.Tag;
import gim.model.util.SampleDataUtil;


/**
 * A utility class to help with building Exercise objects.
 */
public class ExerciseBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_WEIGHT = "85355255";
    public static final String DEFAULT_SETS = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Weight weight;
    private Sets sets;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code ExerciseBuilder} with the default details.
     */
    public ExerciseBuilder() {
        name = new Name(DEFAULT_NAME);
        weight = new Weight(DEFAULT_WEIGHT);
        sets = new Sets(DEFAULT_SETS);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ExerciseBuilder with the data of {@code exerciseToCopy}.
     */
    public ExerciseBuilder(Exercise exerciseToCopy) {
        name = exerciseToCopy.getName();
        weight = exerciseToCopy.getWeight();
        sets = exerciseToCopy.getSets();
        address = exerciseToCopy.getAddress();
        tags = new HashSet<>(exerciseToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withAddress(String address) {
        this.address = new Address(address);
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
        return new Exercise(name, weight, sets, address, tags);
    }

}
