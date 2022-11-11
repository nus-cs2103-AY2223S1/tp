package longtimenosee.model.policy;

import static longtimenosee.testutil.Assert.assertThrows;
import static longtimenosee.testutil.TypicalPolicies.MANUEXTRA;
import static longtimenosee.testutil.TypicalPolicies.PRULIFE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.model.policy.exceptions.DuplicatePolicyException;
import longtimenosee.model.policy.exceptions.PolicyNotFoundException;

public class UniquePolicyListTest {

    private final UniquePolicyList uniquePolicyList = new UniquePolicyList();

    @Test
    public void contains_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.contains(null));
    }

    @Test
    public void contains_policyNotInList_returnsFalse() {
        assertFalse(uniquePolicyList.contains(PRULIFE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePolicyList.add(PRULIFE);
        assertTrue(uniquePolicyList.contains(PRULIFE));
    }


    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePolicyList.add(PRULIFE);
        assertThrows(DuplicatePolicyException.class, () -> uniquePolicyList.add(PRULIFE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicy(null, PRULIFE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicy(PRULIFE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PolicyNotFoundException.class, () -> uniquePolicyList.setPolicy(PRULIFE, PRULIFE));
    }


    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PolicyNotFoundException.class, () -> uniquePolicyList.remove(PRULIFE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePolicyList.add(PRULIFE);
        uniquePolicyList.remove(PRULIFE);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePolicyList.add(PRULIFE);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(MANUEXTRA);
        uniquePolicyList.setPolicies(expectedUniquePolicyList);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicies((List<Policy>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePolicyList.add(PRULIFE);
        List<Policy> policyList = Collections.singletonList(MANUEXTRA);
        uniquePolicyList.setPolicies(policyList);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(MANUEXTRA);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Policy> listWithDuplicatePolicies = Arrays.asList(PRULIFE, PRULIFE);
        assertThrows(DuplicatePolicyException.class, () -> uniquePolicyList.setPolicies(listWithDuplicatePolicies));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniquePolicyList.asUnmodifiableObservableList().remove(0));
    }
}
