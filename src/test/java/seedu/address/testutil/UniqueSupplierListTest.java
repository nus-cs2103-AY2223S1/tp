package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSuppliers.ALICE;
import static seedu.address.testutil.TypicalSuppliers.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.UniqueSupplierList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

public class UniqueSupplierListTest {
    private final UniqueSupplierList uniqueSupplierList = new UniqueSupplierList();

    @Test
    public void contains_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.contains(null));
    }

    @Test
    public void contains_supplierNotInList_returnsFalse() {
        assertFalse(uniqueSupplierList.contains(ALICE));
    }

    @Test
    public void contains_supplierInList_returnsTrue() {
        uniqueSupplierList.add(ALICE);
        assertTrue(uniqueSupplierList.contains(ALICE));
    }

    @Test
    public void contains_supplierWithSameIdentityFieldsInList_returnsTrue() {
        uniqueSupplierList.add(ALICE);
        Supplier editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildSupplier();
        assertTrue(uniqueSupplierList.contains(editedAlice));
    }

    @Test
    public void add_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.add(null));
    }

    @Test
    public void add_duplicateSupplier_throwsDuplicatePersonException() {
        uniqueSupplierList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueSupplierList.add(ALICE));
    }

    @Test
    public void setSupplier_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setPerson(null, ALICE));
    }

    @Test
    public void setSupplier_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setPerson(ALICE, null));
    }

    @Test
    public void setSupplier_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueSupplierList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setSupplier_editedPersonIsSamePerson_success() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.setPerson(ALICE, ALICE);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(ALICE);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedPersonHasSameIdentity_success() {
        uniqueSupplierList.add(ALICE);
        Supplier editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildSupplier();
        uniqueSupplierList.setPerson(ALICE, editedAlice);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(editedAlice);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedPersonHasDifferentIdentity_success() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.setPerson(ALICE, BENSON);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BENSON);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.add(BENSON);
        assertThrows(DuplicatePersonException.class, () -> uniqueSupplierList.setPerson(ALICE, BENSON));
    }

    @Test
    public void remove_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.remove(null));
    }

    @Test
    public void remove_supplierDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueSupplierList.remove(ALICE));
    }

    @Test
    public void remove_existingSupplier_removesSupplier() {
        uniqueSupplierList.add(ALICE);
        uniqueSupplierList.remove(ALICE);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_nullUniqueSupplierList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setPersons((UniqueSupplierList) null));
    }

    @Test
    public void setSupplier_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueSupplierList.add(ALICE);
        UniqueSupplierList expectedUniqueSupplierList = new UniqueSupplierList();
        expectedUniqueSupplierList.add(BENSON);
        uniqueSupplierList.setPersons(expectedUniqueSupplierList);
        assertEquals(expectedUniqueSupplierList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSupplierList.setPersons((List<Supplier>) null));
    }

    @Test
    public void setSupplier_list_replacesOwnListWithProvidedList() {
        uniqueSupplierList.add(ALICE);
        List<Supplier> supplierList = Collections.singletonList(BENSON);
        uniqueSupplierList.setPersons(supplierList);
        UniqueSupplierList expectedUniquePersonList = new UniqueSupplierList();
        expectedUniquePersonList.add(BENSON);
        assertEquals(expectedUniquePersonList, uniqueSupplierList);
    }

    @Test
    public void setSupplier_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Supplier> listWithDuplicateSuppliers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueSupplierList.setPersons(listWithDuplicateSuppliers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueSupplierList.asUnmodifiableObservableList().remove(0));
    }
}
