package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalJeeqTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.testutil.ClientBuilder;

public class JeeqTrackerTest {

    private final JeeqTracker jeeqTracker = new JeeqTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), jeeqTracker.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jeeqTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyJeeqTracker_replacesData() {
        JeeqTracker newData = getTypicalJeeqTracker();
        jeeqTracker.resetData(newData);
        assertEquals(newData, jeeqTracker);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        JeeqTrackerStub newData = new JeeqTrackerStub(newClients);

        assertThrows(DuplicateClientException.class, () -> jeeqTracker.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jeeqTracker.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInJeeqTracker_returnsFalse() {
        assertFalse(jeeqTracker.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInJeeqTracker_returnsTrue() {
        jeeqTracker.addClient(ALICE);
        assertTrue(jeeqTracker.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInJeeqTracker_returnsTrue() {
        jeeqTracker.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(jeeqTracker.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> jeeqTracker.getClientList().remove(0));
    }

    /**
     * A stub ReadOnlyJeeqTracker whose clients list can violate interface constraints.
     */
    private static class JeeqTrackerStub implements ReadOnlyJeeqTracker {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();

        JeeqTrackerStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }
    }

}
