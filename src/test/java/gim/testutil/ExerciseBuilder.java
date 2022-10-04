package gim.testutil;

import java.util.HashSet;
import java.util.Set;

import gim.model.exercise.Email;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Phone;
import gim.model.exercise.Rep;
import gim.model.tag.Tag;
import gim.model.util.SampleDataUtil;

/**
 * A utility class to help with building Exercise objects.
 */
public class ExerciseBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_REP = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Rep rep;
    private Set<Tag> tags;

    /**
     * Creates a {@code ExerciseBuilder} with the default details.
     */
    public ExerciseBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        rep = new Rep(DEFAULT_REP);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ExerciseBuilder with the data of {@code exerciseToCopy}.
     */
    public ExerciseBuilder(Exercise exerciseToCopy) {
        name = exerciseToCopy.getName();
        phone = exerciseToCopy.getPhone();
        email = exerciseToCopy.getEmail();
        rep = exerciseToCopy.getRep();
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
     * Sets the {@code Rep} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withRep(String rep) {
        this.rep = new Rep(rep);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Exercise build() {
        return new Exercise(name, phone, email, rep, tags);
    }

}
