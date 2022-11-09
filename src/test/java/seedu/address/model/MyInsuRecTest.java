package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalMyInsuRec;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;
import seedu.address.testutil.ClientBuilder;

public class MyInsuRecTest {

    private final MyInsuRec myInsuRec = new MyInsuRec();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), myInsuRec.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> myInsuRec.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMyInsuRec_replacesData() {
        MyInsuRec newData = getTypicalMyInsuRec();
        newData.resetData(newData);
        assertEquals(newData, myInsuRec);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same identity fields
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withProducts(VALID_PRODUCT_2)
                .build();
        List<Client> newClients = Arrays.asList(ALICE, editedAlice);
        // This test only tests for duplicate clients with same identity field. Meeting list can be empty.
        List<Meeting> emptyMeetings = Collections.emptyList();
        MyInsuRecStub newData = new MyInsuRecStub(newClients, emptyMeetings);

        assertThrows(DuplicateClientException.class, () -> myInsuRec.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> myInsuRec.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInMyInsuRec_returnsFalse() {
        assertFalse(myInsuRec.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInMyInsuRec_returnsTrue() {
        myInsuRec.addClient(ALICE);
        assertTrue(myInsuRec.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInMyInsuRec_returnsTrue() {
        myInsuRec.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withProducts(VALID_PRODUCT_2)
                .build();
        assertTrue(myInsuRec.hasClient(editedAlice));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myInsuRec.getClientList().remove(0));
    }

    /**
     * A stub ReadOnlyMyInsuRec whose Clients list can violate interface constraints.
     */
    private static class MyInsuRecStub implements ReadOnlyMyInsuRec {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        private final ObservableList<Product> products = FXCollections.observableArrayList();

        MyInsuRecStub(Collection<Client> clients, Collection<Meeting> meetings) {
            this.clients.setAll(clients);
            this.meetings.setAll(meetings);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }

        @Override
        public ObservableList<Product> getProductList() {
            return products;
        }

    }

}
