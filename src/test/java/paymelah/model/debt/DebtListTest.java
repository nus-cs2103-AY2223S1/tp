package paymelah.model.debt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.Assert.assertThrows;
import static paymelah.testutil.TypicalDebts.MCDONALDS;

import org.junit.jupiter.api.Test;

import paymelah.testutil.DebtBuilder;
import paymelah.testutil.DebtListBuilder;

public class DebtListTest {

    @Test
    public void asList_modifyList_throwsUnsupportedOperationException() {
        DebtList debts = new DebtListBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> debts.asList().remove(0));
    }

    @Test
    public void addDebt_debtAdded() {
        DebtList debts = new DebtList();
        DebtList newDebts = new DebtListBuilder(debts).withDebt(MCDONALDS).build();
        assertEquals(newDebts, debts.addDebt(MCDONALDS));
    }

    @Test
    public void removeDebt_debtRemoved() {
        Debt newDebt = new DebtBuilder().build();
        DebtList debts = new DebtListBuilder().withDebt(MCDONALDS).withDebt(newDebt).build();
        DebtList newDebts = new DebtListBuilder().withDebt(newDebt).build();
        assertEquals(newDebts, debts.removeDebt(MCDONALDS));
    }

    @Test
    public void markDebt_debtMarked() {
        DebtList debts = new DebtListBuilder().withDebt(MCDONALDS).build();
        DebtList newDebts = new DebtListBuilder().withDebt(MCDONALDS.setPaid(true)).build();
        assertEquals(newDebts, debts.markDebt(MCDONALDS));
    }

    @Test
    public void unmarkDebt_debtUnmarked() {
        DebtList debts = new DebtListBuilder().withDebt(MCDONALDS.setPaid(true)).build();
        DebtList newDebts = new DebtListBuilder().withDebt(MCDONALDS).build();
        assertEquals(newDebts, debts.unmarkDebt(MCDONALDS));
    }

    @Test
    public void equals() {
        DebtList debts = new DebtListBuilder().withDebt(MCDONALDS).withDebt(new DebtBuilder().build()).build();
        DebtList debtsCopy = new DebtListBuilder(debts).build();

        assertTrue(debts.equals(debtsCopy));
    }

}
