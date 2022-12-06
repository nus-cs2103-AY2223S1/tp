package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.BOB;
import static seedu.address.testutil.TypicalClients.CARL;
import static seedu.address.testutil.TypicalClients.getTypicalClients;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ClientInListPredicateTest {

    private final List<Client> list = getTypicalClients();

    @Test
    public void test_clientNotInList_returnsFalse() {
        ClientInListPredicate predicate = new ClientInListPredicate(list);
        assertFalse(predicate.test(AMY));
        assertFalse(predicate.test(BOB));
    }

    @Test
    public void test_clientInList_returnsTrue() {
        ClientInListPredicate predicate = new ClientInListPredicate(list.subList(0, 3));

        assertTrue(predicate.test(ALICE));
        assertTrue(predicate.test(BENSON));
        assertTrue(predicate.test(CARL));
    }

    @Test
    public void equals() {
        ClientInListPredicate firstPredicate = new ClientInListPredicate(list);
        ClientInListPredicate secondPredicate = new ClientInListPredicate(list.subList(0, 2));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same list -> returns true
        ClientInListPredicate firstPredicateCopy = new ClientInListPredicate(list);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // Different list -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
