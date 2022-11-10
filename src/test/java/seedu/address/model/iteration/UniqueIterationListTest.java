package seedu.address.model.iteration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_DESCRIPTION_FINALISE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIterations.ADD_COLOR;
import static seedu.address.testutil.TypicalIterations.FINALISED;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.iteration.exceptions.DuplicateIterationException;
import seedu.address.model.iteration.exceptions.IterationNotFoundException;
import seedu.address.testutil.IterationBuilder;

public class UniqueIterationListTest {

    private final UniqueIterationList uniqueIterationList = new UniqueIterationList();

    @Test
    public void contains_nullIteration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIterationList.add(null));
    }

    @Test
    public void contains_iterationNotInList_returnsFalse() {
        assertFalse(uniqueIterationList.contains(FINALISED));
    }

    @Test
    public void contains_iterationWithSameDescription_returnsTrue() {
        uniqueIterationList.add(FINALISED);
        Iteration iteration = new IterationBuilder(FINALISED).build();
        assertTrue(uniqueIterationList.contains(iteration));
    }

    @Test
    public void contains_iterationInList_returnsTrue() {
        uniqueIterationList.add(FINALISED);
        assertTrue(uniqueIterationList.contains(FINALISED));
    }

    @Test
    public void add_nullIteration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIterationList.add(null));
    }

    @Test
    public void add_duplicateIteration_throwsDuplicateIterationException() {
        uniqueIterationList.add(FINALISED);
        assertThrows(DuplicateIterationException.class, () -> uniqueIterationList.add(FINALISED));
    }

    @Test
    public void setIteration_nullTargetIteration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIterationList.setIteration(null, FINALISED));
    }

    @Test
    public void setIteration_nullEditedIteration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIterationList.setIteration(FINALISED, null));
    }

    @Test
    public void setIteration_targetIterationNotInList_throwsIterationNotFoundException() {
        assertThrows(IterationNotFoundException.class, () -> uniqueIterationList.setIteration(FINALISED, FINALISED));
    }

    @Test
    public void setIteration_editedIterationIsSameIteration_success() {
        uniqueIterationList.add(FINALISED);
        uniqueIterationList.setIteration(FINALISED, FINALISED);
        UniqueIterationList expectedUniqueIterationList = new UniqueIterationList();
        expectedUniqueIterationList.add(FINALISED);
        assertEquals(expectedUniqueIterationList, uniqueIterationList);
    }

    @Test
    public void setIteration_editedIterationHasSameIdentity_success() {
        uniqueIterationList.add(FINALISED);
        Iteration editedFinalised =
                new IterationBuilder().withDescription(VALID_ITERATION_DESCRIPTION_FINALISE).build();
        uniqueIterationList.setIteration(FINALISED, editedFinalised);

        UniqueIterationList expectedUniqueIterationList = new UniqueIterationList();
        expectedUniqueIterationList.add(editedFinalised);
        assertEquals(expectedUniqueIterationList, uniqueIterationList);
    }

    @Test
    public void setIteration_editedIterationHasDifferentIdentity_success() {
        uniqueIterationList.add(FINALISED);
        uniqueIterationList.setIteration(FINALISED, ADD_COLOR);
        UniqueIterationList expectedUniqueIterationList = new UniqueIterationList();
        expectedUniqueIterationList.add(ADD_COLOR);
        assertEquals(expectedUniqueIterationList, uniqueIterationList);
    }

    @Test
    public void setIteration_editedIterationHasNonUniqueIdentity_throwsDuplicateIterationException() {
        uniqueIterationList.add(FINALISED);
        uniqueIterationList.add(ADD_COLOR);
        assertThrows(DuplicateIterationException.class, () -> uniqueIterationList.setIteration(FINALISED, ADD_COLOR));
    }

    @Test
    public void remove_nullIteration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIterationList.remove(null));
    }

    @Test
    public void remove_iterationDoesNotExist_throwsIterationNotFoundException() {
        assertThrows(IterationNotFoundException.class, () -> uniqueIterationList.remove(FINALISED));
    }

    @Test
    public void remove_existingIteration_removesIteration() {
        uniqueIterationList.add(FINALISED);
        uniqueIterationList.remove(FINALISED);
        UniqueIterationList expectedUniqueIterationList = new UniqueIterationList();
        assertEquals(expectedUniqueIterationList, uniqueIterationList);
    }

    @Test
    public void setIterations_nullUniqueIterationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIterationList.setIterations((UniqueIterationList) null));
    }

    @Test
    public void setIterations_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIterationList.setIterations((List<Iteration>) null));
    }

    @Test
    public void setIterations_uniqueIterationList_replacesOwnListWithProvidedUniqueIterationList() {
        uniqueIterationList.add(FINALISED);
        UniqueIterationList expectedUniqueIterationList = new UniqueIterationList();
        expectedUniqueIterationList.add(ADD_COLOR);
        uniqueIterationList.setIterations(expectedUniqueIterationList);
        assertEquals(expectedUniqueIterationList, uniqueIterationList);
    }

    @Test
    public void setIterations_list_replacesOwnListWithProvidedList() {
        uniqueIterationList.add(FINALISED);
        List<Iteration> iterationList = Collections.singletonList(ADD_COLOR);
        uniqueIterationList.setIterations(iterationList);
        UniqueIterationList expectedUniqueIterationList = new UniqueIterationList();
        expectedUniqueIterationList.add(ADD_COLOR);
        assertEquals(expectedUniqueIterationList, uniqueIterationList);
    }

    @Test
    public void setIteration_listWithDuplicateIterations_throwsDuplicateIterationException() {
        List<Iteration> listWithDuplicateIterations = Arrays.asList(FINALISED, FINALISED);
        assertThrows(DuplicateIterationException.class, () -> uniqueIterationList.setIterations(
                listWithDuplicateIterations));
    }

    @Test
    public void size_emptyUniqueIterationList_sizeZeroSuccess() {
        assertEquals(0, uniqueIterationList.size());
    }
}
