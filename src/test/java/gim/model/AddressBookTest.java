package gim.model;

import static gim.logic.commands.CommandTestUtil.VALID_REP_BOB;
import static gim.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
import static gim.testutil.TypicalExercises.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import gim.model.exercise.Exercise;
import gim.model.exercise.exceptions.DuplicateExerciseException;
import gim.testutil.ExerciseBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getExerciseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateExercises_throwsDuplicateExerciseException() {
        // Two exercises with the same identity fields
        Exercise editedAlice = new ExerciseBuilder(ALICE).withRep(VALID_REP_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Exercise> newExercises = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newExercises);

        assertThrows(DuplicateExerciseException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasExercise_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasExercise(null));
    }

    @Test
    public void hasExercise_exerciseNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasExercise(ALICE));
    }

    @Test
    public void hasExercise_exerciseInAddressBook_returnsTrue() {
        addressBook.addExercise(ALICE);
        assertTrue(addressBook.hasExercise(ALICE));
    }

    @Test
    public void hasExercise_exerciseWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addExercise(ALICE);
        Exercise editedAlice = new ExerciseBuilder(ALICE).withRep(VALID_REP_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasExercise(editedAlice));
    }

    @Test
    public void getExerciseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getExerciseList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose exercises list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();

        AddressBookStub(Collection<Exercise> exercises) {
            this.exercises.setAll(exercises);
        }

        @Override
        public ObservableList<Exercise> getExerciseList() {
            return exercises;
        }
    }

}
