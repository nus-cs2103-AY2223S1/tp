package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalClientBook;

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

public class ClientBookTest {

    private final ClientBook clientBook = new ClientBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), clientBook.getClientsList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clientBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClientBook_replacesData() {
        ClientBook newData = getTypicalClientBook();
        ClientBook.resetData(newData);
        assertEquals(newData, clientBook);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        clientBookStub newData = new clientBookStub(newClients);

        assertThrows(DuplicateClientException.class, () -> clientBook.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clientBook.hasClient(null));
    }

    @Test
    public void hasClient_ClientNotInClientBook_returnsFalse() {
        assertFalse(clientBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_ClientInClientBook_returnsTrue() {
        clientBook.addClient(ALICE);
        assertTrue(clientBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_ClientWithSameIdentityFieldsInClientBook_returnsTrue() {
        clientBook.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(clientBook.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> clientBook.getClientList().remove(0));
    }

    /**
     * A stub ReadOnlyClientBook whose Clients list can violate interface constraints.
     */
    private static class clientBookStub implements ReadOnlyClientBook {
        private final ObservableList<Client> Clients = FXCollections.observableArrayList();

        clientBookStub(Collection<Client> Clients) {
            this.Clients.setAll(Clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return Clients;
        }
    }

}
