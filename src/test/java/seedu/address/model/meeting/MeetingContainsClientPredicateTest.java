package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.Client;
import seedu.address.testutil.MeetingBuilder;

public class MeetingContainsClientPredicateTest {

    @Test
    public void equals() {
        List<Client> firstPredicateClientList = Collections.singletonList(ALICE);
        List<Client> secondPredicateClientList = Arrays.asList(ALICE, BENSON);

        MeetingContainsClientPredicate firstPredicate = new MeetingContainsClientPredicate(firstPredicateClientList);
        MeetingContainsClientPredicate secondPredicate = new MeetingContainsClientPredicate(secondPredicateClientList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingContainsClientPredicate firstPredicateCopy =
                new MeetingContainsClientPredicate(firstPredicateClientList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        //different types -> returns false
        assertFalse(firstPredicate.equals(1));

        //null -> return false
        assertFalse(firstPredicate.equals(null));

        // different meeting -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_meetingContainsClient_returnTrue() {
        //One client to one client
        MeetingContainsClientPredicate predicate =
                new MeetingContainsClientPredicate(Collections.singletonList(ALICE));
        assertTrue(predicate.test(new MeetingBuilder().withClient(ALICE).build()));

        //Multiple client to multiple client
        predicate = new MeetingContainsClientPredicate(Arrays.asList(ALICE, BENSON));
        assertTrue(predicate.test(new MeetingBuilder().withClient(ALICE).withClient(BENSON).build()));

        //Multiple client to one client
        predicate = new MeetingContainsClientPredicate(Arrays.asList(ALICE, BENSON));
        assertTrue(predicate.test(new MeetingBuilder().withClient(ALICE).build()));
    }

    @Test
    public void test_meetingDoesNotContainClient_returnFalse() {
        //No client to one client
        MeetingContainsClientPredicate predicate =
                new MeetingContainsClientPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withClient(ALICE).build()));

        //Non-matching client
        predicate = new MeetingContainsClientPredicate(Collections.singletonList(ALICE));
        assertFalse(predicate.test(new MeetingBuilder().withClient(BENSON).build()));

        //One client to multiple client
        predicate = new MeetingContainsClientPredicate(Arrays.asList(ALICE));
        assertFalse(predicate.test(new MeetingBuilder().withClient(ALICE).withClient(BENSON).build()));
    }

}
