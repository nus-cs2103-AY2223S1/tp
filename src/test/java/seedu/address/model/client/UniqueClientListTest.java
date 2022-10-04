package seedu.address.model.client;

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

import seedu.address.model.client.exceptions.DuplicatePersonException;
import seedu.address.model.client.exceptions.PersonNotFoundException;
import seedu.address.testutil.ClientBuilder;

public class UniqueClientListTest {

    private final UniqueClientList uniqueClientList = new UniqueClientList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        assertTrue(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueClientList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueClientList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueClientList.add(ALICE));
    }

    @Test
    public void setClient_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(null, ALICE));
    }

    @Test
    public void setClient_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(ALICE, null));
    }

    @Test
    public void setClient_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueClientList.setClient(ALICE, ALICE));
    }

    @Test
    public void setClient_editedPersonIsSamePerson_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, ALICE);
        UniqueClientList expecteduniqueClientList = new UniqueClientList();
        expecteduniqueClientList.add(ALICE);
        assertEquals(expecteduniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedPersonHasSameIdentity_success() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueClientList.setClient(ALICE, editedAlice);
        UniqueClientList expecteduniqueClientList = new UniqueClientList();
        expecteduniqueClientList.add(editedAlice);
        assertEquals(expecteduniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedPersonHasDifferentIdentity_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, BOB);
        UniqueClientList expecteduniqueClientList = new UniqueClientList();
        expecteduniqueClientList.add(BOB);
        assertEquals(expecteduniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueClientList.add(ALICE);
        uniqueClientList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueClientList.setClient(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueClientList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueClientList.add(ALICE);
        uniqueClientList.remove(ALICE);
        UniqueClientList expecteduniqueClientList = new UniqueClientList();
        assertEquals(expecteduniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nulluniqueClientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((List<Client>) null));
    }

    @Test
    public void setClients_uniqueClientList_replacesOwnListWithProvideduniqueClientList() {
        uniqueClientList.add(ALICE);
        UniqueClientList expecteduniqueClientList = new UniqueClientList();
        expecteduniqueClientList.add(BOB);
        uniqueClientList.setClient(expecteduniqueClientList);
        assertEquals(expecteduniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((List<Client>) null));
    }

    @Test
    public void setClients_list_replacesOwnListWithProvidedList() {
        uniqueClientList.add(ALICE);
        List<Client> personList = Collections.singletonList(BOB);
        uniqueClientList.setClients(personList);
        UniqueClientList expecteduniqueClientList = new UniqueClientList();
        expecteduniqueClientList.add(BOB);
        assertEquals(expecteduniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Client> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueClientList.setClients(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueClientList.asUnmodifiableObservableList().remove(0));
    }
}
