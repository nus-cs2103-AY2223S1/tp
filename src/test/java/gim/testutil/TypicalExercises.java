package gim.testutil;

import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_REP_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_REP_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gim.model.AddressBook;
import gim.model.exercise.Exercise;


/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercises {

    public static final Exercise ALICE = new ExerciseBuilder().withName("Alice Pauline")
            .withRep("1").withSets("1")
            .withWeight("94351253")
            .withDates("friends").build();
    public static final Exercise BENSON = new ExerciseBuilder().withName("Benson Meier")
            .withRep("1")
            .withSets("1").withWeight("98765432")
            .withDates("owesMoney", "friends").build();
    public static final Exercise CARL = new ExerciseBuilder().withName("Carl Kurz").withWeight("95352563")
            .withSets("1").withRep("1").build();
    public static final Exercise DANIEL = new ExerciseBuilder().withName("Daniel Meier").withWeight("87652533")
            .withSets("1").withRep("1").withDates("friends").build();
    public static final Exercise ELLE = new ExerciseBuilder().withName("Elle Meyer").withWeight("9482224")
            .withSets("1").withRep("1").build();
    public static final Exercise FIONA = new ExerciseBuilder().withName("Fiona Kunz").withWeight("9482427")
            .withSets("1").withRep("1").build();
    public static final Exercise GEORGE = new ExerciseBuilder().withName("George Best").withWeight("9482442")
            .withSets("1").withRep("1").build();

    // Manually added
    public static final Exercise HOON = new ExerciseBuilder().withName("Hoon Meier").withWeight("8482424")
            .withSets("1").withRep("1").build();
    public static final Exercise IDA = new ExerciseBuilder().withName("Ida Mueller").withWeight("8482131")
            .withSets("1").withRep("1").build();

    // Manually added - Exercise's details found in {@code CommandTestUtil}
    public static final Exercise AMY = new ExerciseBuilder().withName(VALID_NAME_AMY).withWeight(VALID_WEIGHT_AMY)
            .withSets(VALID_SETS_AMY).withRep(VALID_REP_AMY).withDates(VALID_DATE).build();
    public static final Exercise BOB = new ExerciseBuilder().withName(VALID_NAME_BOB).withWeight(VALID_WEIGHT_BOB)
            .withSets(VALID_SETS_BOB).withRep(VALID_REP_BOB).withDates(VALID_DATE, VALID_DATE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical exercises.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Exercise exercise : getTypicalExercises()) {
            ab.addExercise(exercise);
        }
        return ab;
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
