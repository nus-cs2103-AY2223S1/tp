package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DOG;
import static seedu.address.testutil.TypicalCommissions.ALICE_CAT;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BOB;
import static seedu.address.testutil.TypicalIterations.ADD_COLOR;
import static seedu.address.testutil.TypicalIterations.FINALISED;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.iteration.exceptions.DuplicateIterationException;
import seedu.address.model.iteration.exceptions.IterationNotFoundException;
import seedu.address.testutil.CommissionBuilder;

class CommissionTest {

    private Commission testDefaultCommission = new CommissionBuilder().build(ALICE);

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> testDefaultCommission.getTags().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> testDefaultCommission.getIterationList().remove(0));
    }

    @Test
    public void isSameCommission() {
        // same object -> returns True
        assertTrue(ALICE_CAT.isSameCommission(ALICE_CAT));

        // null -> returns false
        assertFalse(ALICE_CAT.isSameCommission(null));

        // same title and customer, all other attributes different -> returns true
        Commission editedCommission = new CommissionBuilder(ALICE_CAT).withDescription(VALID_DESCRIPTION_DOG)
                .withCompletionStatus(true).withFee(3.0).withTags(VALID_TAG_FRIEND).build(ALICE);
        assertTrue(ALICE_CAT.isSameCommission(editedCommission));

        // different title and all other attributes same -> returns false
        editedCommission = new CommissionBuilder(ALICE_CAT).withTitle("Different Cat").build(ALICE);
        assertFalse(ALICE_CAT.isSameCommission(editedCommission));

        // title differs in case, all other attributes same -> returns false
        editedCommission = new CommissionBuilder(ALICE_CAT).withTitle("cAT").build(ALICE);
        assertFalse(ALICE_CAT.isSameCommission(editedCommission));

        // title has trailing spaces, all other attributes same, return false;
        String titleWithTrailingSpaces = VALID_TITLE_CAT + " ";
        editedCommission = new CommissionBuilder(ALICE_CAT).withTitle(titleWithTrailingSpaces).build(ALICE);
        assertFalse(ALICE_CAT.isSameCommission(editedCommission));

        // different customer, all other attributes same, return false;
        editedCommission = new CommissionBuilder(ALICE_CAT).build(BOB);
        assertFalse(ALICE_CAT.isSameCommission(editedCommission));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Commission aliceCatCopy = new CommissionBuilder(ALICE_CAT).build(ALICE);
        assertTrue(ALICE_CAT.isSameCommission(aliceCatCopy));

        // same object -> returns true
        assertTrue(ALICE_CAT.equals(ALICE_CAT));

        // null -> returns false
        assertFalse(ALICE_CAT.equals(null));

        // different type -> returns false
        assertFalse(ALICE_CAT.equals(5));

        // different title -> returns false
        Commission editedAliceCat = new CommissionBuilder(ALICE_CAT).withTitle(VALID_TITLE_DOG).build(ALICE);
        assertFalse(ALICE_CAT.equals(editedAliceCat));

        // different fee -> returns false;
        editedAliceCat = new CommissionBuilder(ALICE_CAT).withFee(ALICE_CAT.getFee().fee + 1).build(ALICE);
        assertFalse(ALICE_CAT.equals(editedAliceCat));

        // different deadline -> returns false
        editedAliceCat = new CommissionBuilder(ALICE_CAT).withDeadline(LocalDate.parse("2022-05-05")).build(ALICE);
        assertFalse(ALICE_CAT.equals(editedAliceCat));

        // different tags -> returns false
        editedAliceCat = new CommissionBuilder(ALICE_CAT).withTags(VALID_TAG_FRIEND).build(ALICE);
        assertFalse(ALICE_CAT.equals(editedAliceCat));

        // different description -> returns false
        editedAliceCat = new CommissionBuilder(ALICE_CAT).withDescription(VALID_DESCRIPTION_DOG).build(ALICE);
        assertFalse(ALICE_CAT.equals(editedAliceCat));

        // different completion status -> returns false
        editedAliceCat = new CommissionBuilder(ALICE_CAT).withCompletionStatus(true).build(ALICE);
        assertFalse(ALICE_CAT.equals(editedAliceCat));

        // different customer -> returns false;
        editedAliceCat = new CommissionBuilder(ALICE_CAT).build(BOB);
        assertFalse(ALICE_CAT.equals(editedAliceCat));

    }

    @Test
    public void addIteration_nullIteration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testDefaultCommission.addIteration(null));
    }

    @Test
    public void addIteration_validIteration_success() {
        assertDoesNotThrow(() -> testDefaultCommission.addIteration(FINALISED));
        assertTrue(testDefaultCommission.hasIteration(FINALISED));
    }

    @Test
    public void addIteration_duplicateIteration_throwsDuplicateIterationException() {
        assertDoesNotThrow(() -> testDefaultCommission.addIteration(FINALISED));
        assertThrows(DuplicateIterationException.class, () -> testDefaultCommission.addIteration(FINALISED));
    }

    @Test
    public void setIteration_targetNotInList_throwsIterationNotFoundException() {
        assertThrows(IterationNotFoundException.class, () -> testDefaultCommission.setIteration(FINALISED, FINALISED));
    }

    @Test
    public void setIteration_sameIteration_success() {
        assertDoesNotThrow(() -> testDefaultCommission.addIteration(FINALISED));
        assertDoesNotThrow(() -> testDefaultCommission.setIteration(FINALISED, FINALISED));
    }

    @Test
    public void setIteration_differentIteration_success() {
        assertDoesNotThrow(() -> testDefaultCommission.addIteration(FINALISED));
        assertDoesNotThrow(() -> testDefaultCommission.setIteration(FINALISED, ADD_COLOR));
        assertFalse(testDefaultCommission.hasIteration(FINALISED));
        assertTrue(testDefaultCommission.hasIteration(ADD_COLOR));
    }

    @Test
    public void getCompletionStatusString() {
        Commission completedCommission = new CommissionBuilder().withCompletionStatus(true).build(ALICE);
        assertEquals(Commission.CompletionStatusString.COMPLETED, completedCommission.getCompletionStatusString());

        Commission notStartedCommission = new CommissionBuilder().withCompletionStatus(false).build(ALICE);
        assertEquals(Commission.CompletionStatusString.NOT_STARTED, notStartedCommission.getCompletionStatusString());

        Commission inProgressCommission = new CommissionBuilder().withCompletionStatus(false).toCommissionBuilder()
                .addIteration(ADD_COLOR).build(ALICE);
        assertEquals(Commission.CompletionStatusString.IN_PROGRESS, inProgressCommission.getCompletionStatusString());
    }

}

