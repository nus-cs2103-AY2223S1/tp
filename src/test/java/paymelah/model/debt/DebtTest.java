package paymelah.model.debt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.TypicalDebts.CHICKEN_RICE;
import static paymelah.testutil.TypicalDebts.MCDONALDS;

import org.junit.jupiter.api.Test;

import paymelah.testutil.DebtBuilder;

public class DebtTest {
    @Test
    public void equals() {
        // same values -> returns true
        Debt mcdonaldsCopy = new DebtBuilder(MCDONALDS).build();
        assertTrue(MCDONALDS.equals(mcdonaldsCopy));

        // same object -> returns true
        assertTrue(CHICKEN_RICE.equals(CHICKEN_RICE));

        // null -> returns false
        assertFalse(MCDONALDS.equals(null));

        // different type -> returns false
        assertFalse(MCDONALDS.equals("mcdonalds"));

        // different debt -> returns false
        assertFalse(MCDONALDS.equals(CHICKEN_RICE));

        // different description -> returns false
        Debt editedMcdonalds = new DebtBuilder(MCDONALDS).withDescription("KFC").build();
        assertFalse(MCDONALDS.equals(editedMcdonalds));

        // different money -> returns false
        editedMcdonalds = new DebtBuilder(MCDONALDS).withMoney("120.40").build();
        assertFalse(MCDONALDS.equals(editedMcdonalds));
    }

    @Test
    public void makeDebt() {
        Debt fromDebtBuilder = new DebtBuilder().withDescription("Gift cards").withMoney("50").build();
        Debt fromMakeDebt = Debt.makeDebt("Gift cards", "50");

        assertEquals(fromDebtBuilder, fromMakeDebt);
    }
}
