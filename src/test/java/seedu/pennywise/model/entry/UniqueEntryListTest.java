package seedu.pennywise.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.testutil.TypicalEntry.ALLOWANCE;
import static seedu.pennywise.testutil.TypicalEntry.DINNER;
import static seedu.pennywise.testutil.TypicalEntry.INVESTMENT;
import static seedu.pennywise.testutil.TypicalEntry.LUNCH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.pennywise.model.entry.exceptions.DuplicateEntryException;
import seedu.pennywise.model.entry.exceptions.EntryNotFoundException;
import seedu.pennywise.testutil.ExpenditureBuilder;
import seedu.pennywise.testutil.IncomeBuilder;

public class UniqueEntryListTest {
    private final UniqueEntryList uniqueExpenditureList = new UniqueEntryList();
    private final UniqueEntryList uniqueIncomeList = new UniqueEntryList();

    @Test
    public void contains_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenditureList.contains(null));
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.contains(null));
    }

    @Test
    public void contains_entryNotInList_returnFalse() {
        assertFalse(uniqueExpenditureList.contains(LUNCH));
        assertFalse(uniqueIncomeList.contains(ALLOWANCE));
    }

    @Test
    public void contains_entryInList_returnTrue() {
        uniqueExpenditureList.add(LUNCH);
        uniqueIncomeList.add(ALLOWANCE);

        assertTrue(uniqueExpenditureList.contains(LUNCH));
        assertTrue(uniqueIncomeList.contains(ALLOWANCE));
    }

    @Test
    public void contains_entryWithSameFieldsInList_returnsTrue() {
        uniqueExpenditureList.add(LUNCH);
        uniqueIncomeList.add(ALLOWANCE);
        Expenditure lunchCopy = new ExpenditureBuilder(LUNCH).build();
        Income allowanceCopy = new IncomeBuilder(ALLOWANCE).build();

        assertTrue(uniqueExpenditureList.contains(lunchCopy));
        assertTrue(uniqueIncomeList.contains(allowanceCopy));
    }

    @Test
    public void add_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenditureList.add(null));
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.add(null));
    }

    @Test
    public void add_duplicateEntry_throwsDuplicateEntryException() {
        uniqueExpenditureList.add(LUNCH);
        uniqueIncomeList.add(ALLOWANCE);

        assertThrows(DuplicateEntryException.class, () -> uniqueExpenditureList.add(LUNCH));
        assertThrows(DuplicateEntryException.class, () -> uniqueIncomeList.add(ALLOWANCE));
    }

    @Test
    public void setEntry_nullTargetEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.setEntries(null, ALLOWANCE));
        assertThrows(NullPointerException.class, () -> uniqueExpenditureList.setEntries(null, LUNCH));

    }

    @Test
    public void setEntry_nullEditedEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.setEntries(ALLOWANCE, null));
        assertThrows(NullPointerException.class, () -> uniqueExpenditureList.setEntries(LUNCH, null));

    }

    @Test
    public void setEntry_targetEntryNotInList_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueIncomeList.setEntries(ALLOWANCE, ALLOWANCE));
        assertThrows(EntryNotFoundException.class, () -> uniqueExpenditureList.setEntries(LUNCH, LUNCH));

    }

    @Test
    public void setEntry_editedEntryIsSameEntry_success() {
        uniqueExpenditureList.add(LUNCH);
        uniqueExpenditureList.setEntries(LUNCH, LUNCH);
        UniqueEntryList expectedUniqueExpenditureList = new UniqueEntryList();
        expectedUniqueExpenditureList.add(LUNCH);
        assertEquals(expectedUniqueExpenditureList, uniqueExpenditureList);

        uniqueIncomeList.add(ALLOWANCE);
        uniqueIncomeList.setEntries(ALLOWANCE, ALLOWANCE);
        UniqueEntryList expectedUniqueIncomeList = new UniqueEntryList();
        expectedUniqueIncomeList.add(ALLOWANCE);
        assertEquals(expectedUniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setEntry_editedEntryHasSameIdentity_success() {
        uniqueExpenditureList.add(LUNCH);
        Expenditure editedLunch = new ExpenditureBuilder(LUNCH)
                .build();
        uniqueExpenditureList.setEntries(LUNCH, editedLunch);
        UniqueEntryList expectedUniqueExpenditureList = new UniqueEntryList();
        expectedUniqueExpenditureList.add(editedLunch);
        assertEquals(expectedUniqueExpenditureList, uniqueExpenditureList);

        uniqueIncomeList.add(ALLOWANCE);
        Income editedAllowance = new IncomeBuilder(ALLOWANCE)
                .build();
        uniqueIncomeList.setEntries(ALLOWANCE, editedAllowance);
        UniqueEntryList expectedUniqueIncomeList = new UniqueEntryList();
        expectedUniqueIncomeList.add(editedAllowance);
        assertEquals(expectedUniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setEntry_editedEntryHasDifferentIdentity_success() {
        uniqueExpenditureList.add(LUNCH);
        uniqueExpenditureList.setEntries(LUNCH, DINNER);
        UniqueEntryList expectedUniqueExpenditureList = new UniqueEntryList();
        expectedUniqueExpenditureList.add(DINNER);
        assertEquals(expectedUniqueExpenditureList, uniqueExpenditureList);

        uniqueIncomeList.add(ALLOWANCE);
        uniqueIncomeList.setEntries(ALLOWANCE, INVESTMENT);
        UniqueEntryList expectedUniqueIncomeList = new UniqueEntryList();
        expectedUniqueIncomeList.add(INVESTMENT);
        assertEquals(expectedUniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setEntry_editedEntryHasNonUniqueIdentity_throwsDuplicateEntryException() {
        uniqueExpenditureList.add(LUNCH);
        uniqueExpenditureList.add(DINNER);
        assertThrows(DuplicateEntryException.class, () -> uniqueExpenditureList.setEntries(
                LUNCH, DINNER));
        uniqueIncomeList.add(ALLOWANCE);
        uniqueIncomeList.add(INVESTMENT);
        assertThrows(DuplicateEntryException.class, () -> uniqueIncomeList.setEntries(
                ALLOWANCE, INVESTMENT));
    }

    @Test
    public void remove_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenditureList.remove(null));
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.remove(null));
    }

    @Test
    public void remove_entryDoesNotExist_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueExpenditureList.remove(LUNCH));

        assertThrows(EntryNotFoundException.class, () -> uniqueIncomeList.remove(ALLOWANCE));

    }

    @Test
    public void remove_existingEntry_removesEntry() {
        uniqueExpenditureList.add(LUNCH);
        uniqueExpenditureList.remove(LUNCH);
        UniqueEntryList expectedUniqueExpenditureList = new UniqueEntryList();
        assertEquals(expectedUniqueExpenditureList, uniqueExpenditureList);

        uniqueIncomeList.add(ALLOWANCE);
        uniqueIncomeList.remove(ALLOWANCE);
        UniqueEntryList expectedUniqueIncomeList = new UniqueEntryList();
        assertEquals(expectedUniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setEntries_nullUniqueEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenditureList
                .setEntries((UniqueEntryList) null));

        assertThrows(NullPointerException.class, () -> uniqueIncomeList
                .setEntries((UniqueEntryList) null));
    }

    @Test
    public void setEntries_uniqueEntryList_replacesOwnListWithProvidedUniqueEntryList() {
        uniqueExpenditureList.add(LUNCH);
        UniqueEntryList expectedUniqueExpenditureList = new UniqueEntryList();
        expectedUniqueExpenditureList.add(DINNER);
        uniqueExpenditureList.setEntries(expectedUniqueExpenditureList);
        assertEquals(expectedUniqueExpenditureList, uniqueExpenditureList);

        uniqueIncomeList.add(ALLOWANCE);
        UniqueEntryList expectedUniqueIncomeList = new UniqueEntryList();
        expectedUniqueIncomeList.add(INVESTMENT);
        uniqueIncomeList.setEntries(expectedUniqueIncomeList);
        assertEquals(expectedUniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setEntries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenditureList.setEntries((List<Entry>) null));
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.setEntries((List<Entry>) null));
    }

    @Test
    public void setEntries_list_replacesOwnListWithProvidedList() {
        uniqueExpenditureList.add(LUNCH);
        List<Entry> expenditureList = Collections.singletonList(DINNER);
        uniqueExpenditureList.setEntries(expenditureList);
        UniqueEntryList expectedUniqueExpenditureList = new UniqueEntryList();
        expectedUniqueExpenditureList.add(DINNER);
        assertEquals(expectedUniqueExpenditureList, uniqueExpenditureList);

        uniqueIncomeList.add(ALLOWANCE);
        List<Entry> incomeList = Collections.singletonList(INVESTMENT);
        uniqueIncomeList.setEntries(incomeList);
        UniqueEntryList expectedUniqueIncomeList = new UniqueEntryList();
        expectedUniqueIncomeList.add(INVESTMENT);
        assertEquals(expectedUniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setEntries_listWithDuplicateEntries_throwsDuplicateEntryException() {
        List<Entry> listWithDuplicateExpenditure = Arrays.asList(LUNCH, LUNCH);
        assertThrows(DuplicateEntryException.class, () -> uniqueExpenditureList
                .setEntries(listWithDuplicateExpenditure));
        List<Entry> listWithDuplicateIncome = Arrays.asList(ALLOWANCE, ALLOWANCE);
        assertThrows(DuplicateEntryException.class, () -> uniqueIncomeList
                .setEntries(listWithDuplicateIncome));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueExpenditureList.asUnmodifiableObservableList().remove(0));
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueIncomeList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(uniqueExpenditureList.equals(uniqueExpenditureList));
        assertTrue(uniqueIncomeList.equals(uniqueIncomeList));
        // null -> return false
        assertFalse(uniqueExpenditureList.equals(null));
        assertFalse(uniqueIncomeList.equals(null));
    }




}
