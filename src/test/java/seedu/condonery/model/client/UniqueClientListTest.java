package seedu.condonery.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.TypicalClients.AMY_CLIENT;
import static seedu.condonery.testutil.TypicalClients.BOB_CLIENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.condonery.model.client.exceptions.DuplicateClientException;
import seedu.condonery.model.client.exceptions.UniqueClientNotFoundException;

public class UniqueClientListTest {
    @Test
    public void duplicateClientInList() {
        UniqueClientList clients = new UniqueClientList();
        clients.add(AMY_CLIENT);
        assertEquals(clients.getUniqueClientByName(AMY_CLIENT.getName().toString()),AMY_CLIENT);
    }

    @Test
    public void duplicateClientInList_throwsDuplicateClientException() {
        UniqueClientList clients = new UniqueClientList();
        clients.add(AMY_CLIENT);
        assertThrows(DuplicateClientException.class, () -> clients.add(AMY_CLIENT));
    }

    @Test
    public void setClientsWithDuplicateClients_throwsDuplicateClientException() {
        List<Client> clientsList = new ArrayList<>();
        UniqueClientList clients = new UniqueClientList();
        clientsList.add(AMY_CLIENT);
        clientsList.add(AMY_CLIENT);
        assertThrows(DuplicateClientException.class, () -> clients.setClients(clientsList));
    }

    @Test
    public void setClientsWithoutDuplicate_success() {
        List<Client> clientsList = new ArrayList<>();
        UniqueClientList clients = new UniqueClientList();
        clientsList.add(AMY_CLIENT);
        clientsList.add(BOB_CLIENT);
        clients.setClients(clientsList);
        assertEquals(clients.getUniqueClientByName(AMY_CLIENT.getName().toString()).getName().toString(),
                AMY_CLIENT.getName().toString());
    }

    @Test
    public void hasUniqueClientName() {
        List<Client> clientsList = new ArrayList<>();
        UniqueClientList clients = new UniqueClientList();
        clientsList.add(AMY_CLIENT);
        clients.setClients(clientsList);
        assertTrue(clients.hasUniqueClientName(AMY_CLIENT.getName().toString()));
    }

    @Test
    public void getUniqueClientByName_failure() {
        List<Client> clientsList = new ArrayList<>();
        UniqueClientList clients = new UniqueClientList();
        clientsList.add(AMY_CLIENT);
        clients.setClients(clientsList);
        assertThrows(UniqueClientNotFoundException.class,
                () -> clients.getUniqueClientByName(BOB_CLIENT.getName().toString()));
    }

    @Test
    public void hasClientName_success() {
        List<Client> clientsList = new ArrayList<>();
        UniqueClientList clients = new UniqueClientList();
        clientsList.add(AMY_CLIENT);
        clients.setClients(clientsList);
        assertTrue(clients.hasClientName(AMY_CLIENT.getName().toString()));
    }

    @Test
    public void hasClientName_failure() {
        List<Client> clientsList = new ArrayList<>();
        UniqueClientList clients = new UniqueClientList();
        clientsList.add(AMY_CLIENT);
        clients.setClients(clientsList);
        assertFalse(clients.hasClientName(BOB_CLIENT.getName().toString()));
    }

    @Test
    public void setClients() {
        UniqueClientList clients = new UniqueClientList();
        UniqueClientList client2 = clients;
        clients.setClients(client2);
        assertEquals(clients, client2);
    }

    @Test
    public void equals() {
        UniqueClientList clients = new UniqueClientList();
        clients.add(AMY_CLIENT);

        //same values -> returns true
        assertEquals(clients, clients);
    }


}
