package gim.testutil;

import static gim.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_REP_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_REP_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_AMY;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
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
            .withRep("123, Jurong West Ave 6, #08-111").withSets("alice@example.com")
            .withWeight("94351253")
            .withTags("friends").build();
    public static final Exercise BENSON = new ExerciseBuilder().withName("Benson Meier")
            .withRep("311, Clementi Ave 2, #02-25")
            .withSets("johnd@example.com").withWeight("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Exercise CARL = new ExerciseBuilder().withName("Carl Kurz").withWeight("95352563")
            .withSets("heinz@example.com").withRep("wall street").build();
    public static final Exercise DANIEL = new ExerciseBuilder().withName("Daniel Meier").withWeight("87652533")
            .withSets("cornelia@example.com").withRep("10th street").withTags("friends").build();
    public static final Exercise ELLE = new ExerciseBuilder().withName("Elle Meyer").withWeight("9482224")
            .withSets("werner@example.com").withRep("michegan ave").build();
    public static final Exercise FIONA = new ExerciseBuilder().withName("Fiona Kunz").withWeight("9482427")
            .withSets("lydia@example.com").withRep("little tokyo").build();
    public static final Exercise GEORGE = new ExerciseBuilder().withName("George Best").withWeight("9482442")
            .withSets("anna@example.com").withRep("4th street").build();

    // Manually added
    public static final Exercise HOON = new ExerciseBuilder().withName("Hoon Meier").withWeight("8482424")
            .withSets("stefan@example.com").withRep("little india").build();
    public static final Exercise IDA = new ExerciseBuilder().withName("Ida Mueller").withWeight("8482131")
            .withSets("hans@example.com").withRep("chicago ave").build();

    // Manually added - Exercise's details found in {@code CommandTestUtil}
    public static final Exercise AMY = new ExerciseBuilder().withName(VALID_NAME_AMY).withWeight(VALID_WEIGHT_AMY)
            .withSets(VALID_SETS_AMY).withRep(VALID_REP_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Exercise BOB = new ExerciseBuilder().withName(VALID_NAME_BOB).withWeight(VALID_WEIGHT_BOB)
            .withSets(VALID_SETS_BOB).withRep(VALID_REP_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
