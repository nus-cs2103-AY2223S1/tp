package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106_SWAP_TASKS_ONE_AND_TWO;
import static seedu.address.logic.commands.SwapTaskNumbersCommand.SwapTaskNumbersDescriptor;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS2106;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.testutil.SwapTaskNumbersDescriptorBuilder;

public class SwapTaskNumbersDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        SwapTaskNumbersDescriptor descriptorWithSameValues =
                new SwapTaskNumbersDescriptor(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO);
        assertTrue(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO.equals(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO));

        // same numbers in different order -> returns true
        SwapTaskNumbersDescriptor descriptorWithDifferentTaskNumberOrder =
                new SwapTaskNumbersDescriptor(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO);
        descriptorWithDifferentTaskNumberOrder.setIndexesOfTaskToSwap(
                    Arrays.asList(Index.fromOneBased(2), Index.fromOneBased(1)));
        assertTrue(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO.equals(descriptorWithDifferentTaskNumberOrder));

        // null -> returns false
        assertFalse(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO.equals(5));

        // both task numbers different -> returns false
        SwapTaskNumbersDescriptor descriptorWithDifferentTaskNumbers =
                new SwapTaskNumbersDescriptorBuilder(CS2106,
                        Index.fromOneBased(5),
                        Index.fromOneBased(6)).build();
        assertFalse(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO.equals(descriptorWithDifferentTaskNumbers));

        // one task number different -> returns false
        SwapTaskNumbersDescriptor descriptorWithOneDifferentTaskNumber =
                new SwapTaskNumbersDescriptorBuilder(CS2106,
                        Index.fromOneBased(1),
                        Index.fromOneBased(3)).build();
        assertFalse(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO.equals(descriptorWithOneDifferentTaskNumber));

        // different module code -> returns false
        SwapTaskNumbersDescriptor descriptorWithDifferentModuleCode =
                new SwapTaskNumbersDescriptorBuilder(CS2103T).build();
        assertFalse(DESC_CS2106_SWAP_TASKS_ONE_AND_TWO.equals(descriptorWithDifferentModuleCode));
    }
}
