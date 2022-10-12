package gim.logic.commands;

import static gim.logic.commands.CommandTestUtil.DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_DATE_2;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_REPS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_SETS_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.testutil.EditExerciseDescriptorBuilder;


public class EditExerciseDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditExerciseDescriptor descriptorWithSameValues = new EditExerciseDescriptor(DESC_ARM_CURLS);
        assertTrue(DESC_ARM_CURLS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ARM_CURLS.equals(DESC_ARM_CURLS));

        // null -> returns false
        assertFalse(DESC_ARM_CURLS.equals(null));

        // different types -> returns false
        assertFalse(DESC_ARM_CURLS.equals(5));

        // different values -> returns false
        assertFalse(DESC_ARM_CURLS.equals(DESC_BENCH_PRESS));

        // different name -> returns false
        EditExerciseDescriptor editedArmCurls = new EditExerciseDescriptorBuilder(DESC_ARM_CURLS)
                .withName(VALID_NAME_BENCH_PRESS).build();
        assertFalse(DESC_ARM_CURLS.equals(editedArmCurls));

        // different weight -> returns false
        editedArmCurls = new EditExerciseDescriptorBuilder(DESC_ARM_CURLS).withWeight(VALID_WEIGHT_BENCH_PRESS).build();
        assertFalse(DESC_ARM_CURLS.equals(editedArmCurls));

        // different sets -> returns false
        editedArmCurls = new EditExerciseDescriptorBuilder(DESC_ARM_CURLS).withSets(VALID_SETS_BENCH_PRESS).build();
        assertFalse(DESC_ARM_CURLS.equals(editedArmCurls));

        // different address -> returns false
        editedArmCurls = new EditExerciseDescriptorBuilder(DESC_ARM_CURLS).withRep(VALID_REPS_BENCH_PRESS).build();
        assertFalse(DESC_ARM_CURLS.equals(editedArmCurls));

        // different tags -> returns false
        editedArmCurls = new EditExerciseDescriptorBuilder(DESC_ARM_CURLS).withDates(VALID_DATE_2).build();
        assertFalse(DESC_ARM_CURLS.equals(editedArmCurls));
    }
}
