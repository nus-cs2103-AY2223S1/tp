package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_DINNER;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DINNER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntry.LUNCH;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;

//import java.util.Arrays;
//import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
//import java.util.List;
//import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.entry.Entry;

//import javafx.collections.ObservableList;
//import seedu.address.model.person.exceptions.DuplicatePersonException;
//import seedu.address.testutil.ExpenditureBuilder;
//import seedu.address.model.person.Person;
//import seedu.address.model.person.exceptions.DuplicatePersonException;
//import seedu.address.testutil.PersonBuilder;

public class PennyWiseTest {

    private final PennyWise pennyWise = new PennyWise();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), pennyWise.getExpenditureList());
        assertEquals(Collections.emptyList(), pennyWise.getIncomeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pennyWise.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        PennyWise newData = getTypicalPennyWise();
        pennyWise.resetData(newData);
        assertEquals(newData, pennyWise);
    }

    // @Test
    // public void resetData_withDuplicateExpenditure_throwsDuplicatePersonException() {
    //     // Two persons with the same identity fields
    //     Entry editedLunch = new ExpenditureBuilder(LUNCH)
    //             .withAmount(VALID_AMT_DINNER)
    //             .withTags(VALID_TAG_DINNER).build();
    //     List<Entry> newExpenditures = Arrays.asList(LUNCH, editedLunch);
    //     PennyWiseStub newData = new PennyWiseStub(newExpenditures, null);
    //     assertThrows(DuplicatePersonException.class, () -> pennyWise.resetData(newData));
    // }

    @Test
    public void hasExpenditure_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pennyWise.hasExpenditure(null));
    }

    @Test
    public void hasIncome_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pennyWise.hasIncome(null));
    }

    @Test
    public void hasExpenditure_expenditureNotInPennyWise_returnsFalse() {
        assertFalse(pennyWise.hasExpenditure(LUNCH));
    }

    // @Test
    // public void hasIncome_incomeNotInAddressBook_returnsFalse() {
    //     assertFalse(pennyWise.hasIncome());
    // }
    @Test
    public void hasExpenditure_expenditureInAddressBook_returnsTrue() {
        pennyWise.addExpenditure(LUNCH);
        assertTrue(pennyWise.hasExpenditure(LUNCH));
    }
    // @Test
    // public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
    //     addressBook.addPerson(ALICE);
    //     Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
    //             .build();
    //     assertTrue(addressBook.hasPerson(editedAlice));
    // }
    @Test
    public void getExpenditureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> pennyWise.getExpenditureList().remove(0));
    }

    @Test
    public void getIncomeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> pennyWise.getIncomeList().remove(0));
    }
    // /**
    //  * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
    //  */
    private static class PennyWiseStub implements ReadOnlyPennyWise {
        private final ObservableList<Entry> expenditureList = FXCollections.observableArrayList();
        private final ObservableList<Entry> incomeList = FXCollections.observableArrayList();

        PennyWiseStub(Collection<Entry> expenditures, Collection<Entry> incomes) {
            this.expenditureList.setAll(expenditures);
            this.incomeList.setAll(incomes);
        }

        /**
         * Returns an unmodifiable view of the expenditure list.
         * This list will not contain any duplicate expenditure.
         */
        @Override
        public ObservableList<Entry> getExpenditureList() {
            return expenditureList;
        }

        /**
        * Returns an unmodifiable view of the income list.
        * This list will not contain any duplicate income.
        */
        @Override
        public ObservableList<Entry> getIncomeList() {
            return incomeList;
        }
    }

}
