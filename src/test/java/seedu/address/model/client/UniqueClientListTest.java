package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.ALICE;
import static seedu.address.testutil.TypicalCompanies.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.testutil.CompanyBuilder;

public class UniqueClientListTest {

    private final UniqueClientList uniqueClientList = new UniqueClientList();

    @Test
    public void contains_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.contains(null));
    }

    @Test
    public void contains_companyNotInList_returnsFalse() {
        assertFalse(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_companyInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        assertTrue(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_companyWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new CompanyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueClientList.contains(editedAlice));
    }

    @Test
    public void add_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.add(null));
    }

    @Test
    public void add_duplicateCompany_throwsDuplicateCompanyException() {
        uniqueClientList.add(ALICE);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.add(ALICE));
    }

    @Test
    public void setCompany_nullTargetCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(null, ALICE));
    }

    @Test
    public void setCompany_nullEditedCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(ALICE, null));
    }

    @Test
    public void setCompany_targetCompanyNotInList_throwsCompanyNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.setClient(ALICE, ALICE));
    }

    @Test
    public void setCompany_editedCompanyIsSameCompany_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(ALICE);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setCompany_editedCompanyHasSameIdentity_success() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new CompanyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueClientList.setClient(ALICE, editedAlice);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(editedAlice);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setCompany_editedCompanyHasDifferentIdentity_success() {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, BOB);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setCompany_editedCompanyHasNonUniqueIdentity_throwsDuplicateCompanyException() {
        uniqueClientList.add(ALICE);
        uniqueClientList.add(BOB);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.setClient(ALICE, BOB));
    }

    @Test
    public void remove_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.remove(null));
    }

    @Test
    public void remove_companyDoesNotExist_throwsCompanyNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.remove(ALICE));
    }

    @Test
    public void remove_existingCompany_removesCompany() {
        uniqueClientList.add(ALICE);
        uniqueClientList.remove(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setCompanies_nullUniqueCompanyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((UniqueClientList) null));
    }

    @Test
    public void setCompanies_uniqueCompanyList_replacesOwnListWithProvidedUniqueCompanyList() {
        uniqueClientList.add(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        uniqueClientList.setClients(expectedUniqueClientList);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setCompanies_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((List<Client>) null));
    }

    @Test
    public void setCompanies_list_replacesOwnListWithProvidedList() {
        uniqueClientList.add(ALICE);
        List<Client> clientList = Collections.singletonList(BOB);
        uniqueClientList.setClients(clientList);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setCompanies_listWithDuplicateCompanies_throwsDuplicateCompanyException() {
        List<Client> listWithDuplicateCompanies = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.setClients(listWithDuplicateCompanies));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueClientList.asUnmodifiableObservableList().remove(0));
    }
}
