package seedu.pennywise.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DATE_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DATE_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DATE_TUITION;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import seedu.pennywise.testutil.ExpenditureBuilder;
import seedu.pennywise.testutil.IncomeBuilder;

public class EntryInYearMonthPredicateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EntryInYearMonthPredicate(null));
    }

    @Test
    public void test_entryInYearMonth_returnsTrue() {
        YearMonth validYearMonthInvestment = new Date(VALID_DATE_INVESTMENT).getYearMonth();
        EntryInYearMonthPredicate entryInValidYearMonthInvestment = new EntryInYearMonthPredicate(
                validYearMonthInvestment
        );

        assertTrue(entryInValidYearMonthInvestment.test(
                new ExpenditureBuilder().withDate(VALID_DATE_INVESTMENT).build())
        );
        assertTrue(entryInValidYearMonthInvestment.test(
                new IncomeBuilder().withDate(VALID_DATE_INVESTMENT).build())
        );
    }

    @Test
    public void test_entryNotInYearMonth_returnsFalse() {
        YearMonth validYearMonthInvestment = new Date(VALID_DATE_INVESTMENT).getYearMonth();
        EntryInYearMonthPredicate entryInValidYearMonthInvestment = new EntryInYearMonthPredicate(
                validYearMonthInvestment
        );

        assertFalse(entryInValidYearMonthInvestment.test(
                new ExpenditureBuilder().withDate(VALID_DATE_TUITION).build())
        );
        assertFalse(entryInValidYearMonthInvestment.test(
                new IncomeBuilder().withDate(VALID_DATE_TUITION).build())
        );
    }

    @Test
    public void equals() {
        YearMonth validYearMonthInvestment = new Date(VALID_DATE_INVESTMENT).getYearMonth();
        EntryInYearMonthPredicate entryInValidYearMonthInvestment = new EntryInYearMonthPredicate(
                validYearMonthInvestment
        );

        // same object -> returns true
        assertEquals(entryInValidYearMonthInvestment, entryInValidYearMonthInvestment);

        // same values -> returns true
        EntryInYearMonthPredicate entryInValidYearMonthInvestmentCopy = new EntryInYearMonthPredicate(
                validYearMonthInvestment
        );
        assertEquals(entryInValidYearMonthInvestment, entryInValidYearMonthInvestmentCopy);

        // different types -> returns false
        assertNotEquals(entryInValidYearMonthInvestment, validYearMonthInvestment);

        // null -> returns false
        assertNotEquals(entryInValidYearMonthInvestment, null);

        // different year month -> returns false
        assertNotEquals(
                entryInValidYearMonthInvestment,
                new EntryInYearMonthPredicate(new Date(VALID_DATE_DINNER).getYearMonth())
        );
    }
}
