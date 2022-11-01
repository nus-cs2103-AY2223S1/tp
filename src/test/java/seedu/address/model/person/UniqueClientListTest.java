package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.ClientNotFoundException;
import seedu.address.model.person.exceptions.DuplicateClientException;
import seedu.address.testutil.ClientBuilder;

public class UniqueClientListTest {

    private final UniqueClientList uniqueClientList = new UniqueClientList();

    @Test
    public void contains_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.contains(null));
    }

    @Test
    public void contains_clientNotInList_returnsFalse() {
        assertFalse(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_clientInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        assertTrue(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_clientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueClientList.contains(editedAlice));
    }

    @Test
    public void add_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.add(null));
    }

    @Test
    public void add_duplicateClient_throwsDuplicateClientException() {
        uniqueClientList.add(ALICE);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.add(ALICE));
    }

    @Test
    public void setClient_nullTargetClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(null, ALICE));
    }

    @Test
    public void setClient_nullEditedClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(ALICE, null));
    }

    @Test
    public void setClient_targetClientNotInList_throwsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.setClient(ALICE, ALICE));
    }

    @Test
    public void setClient_editedClientIsSameClient_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(ALICE);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasSameIdentity_success() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueClientList.setClient(ALICE, editedAlice);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(editedAlice);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasDifferentIdentity_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, BOB);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasNonUniqueIdentity_throwsDuplicateClientException() {
        uniqueClientList.add(ALICE);
        uniqueClientList.add(BOB);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.setClient(ALICE, BOB));
    }

    @Test
    public void remove_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.remove(null));
    }

    @Test
    public void remove_clientDoesNotExist_throwsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.remove(ALICE));
    }

    @Test
    public void remove_existingClient_removesClient() {
        uniqueClientList.add(ALICE);
        uniqueClientList.remove(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullUniqueClientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((UniqueClientList) null));
    }

    @Test
    public void setClients_uniqueClientList_replacesOwnListWithProvidedUniqueClientList() {
        uniqueClientList.add(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        uniqueClientList.setClients(expectedUniqueClientList);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((List<Client>) null));
    }

    @Test
    public void setClients_list_replacesOwnListWithProvidedList() {
        uniqueClientList.add(ALICE);
        List<Client> clientList = Collections.singletonList(BOB);
        uniqueClientList.setClients(clientList);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_listWithDuplicateClients_throwsDuplicateClientException() {
        List<Client> listWithDuplicateClients = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.setClients(listWithDuplicateClients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueClientList.asUnmodifiableObservableList().remove(0));
    }
}
