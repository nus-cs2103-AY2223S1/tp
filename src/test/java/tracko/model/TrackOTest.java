package tracko.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static tracko.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static tracko.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tracko.testutil.Assert.assertThrows;
import static tracko.testutil.TypicalOrders.ORDER_1;
import static tracko.testutil.TypicalOrders.getTrackOWithTypicalOrders;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tracko.model.person.Person;
import tracko.model.person.exceptions.DuplicatePersonException;
import tracko.testutil.OrderBuilder;

public class TrackOTest {

    private final TrackO addressBook = new TrackO();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getOrderList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        TrackO newData = getTrackOWithTypicalOrders();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

//    @Test
//    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
//        // Two persons with the same identity fields
//        Person editedAlice = new OrderBuilder(ORDER_1).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
//                .build();
//        List<Person> newPersons = Arrays.asList(ORDER_1, editedAlice);
//        AddressBookStub newData = new AddressBookStub(newPersons);
//
//        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
//    }
//
//    @Test
//    public void hasPerson_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
//    }
//
//    @Test
//    public void hasPerson_personNotInAddressBook_returnsFalse() {
//        assertFalse(addressBook.hasPerson(ORDER_1));
//    }
//
//    @Test
//    public void hasPerson_personInAddressBook_returnsTrue() {
//        addressBook.addPerson(ORDER_1);
//        assertTrue(addressBook.hasPerson(ORDER_1));
//    }
//
//    @Test
//    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
//        addressBook.addPerson(ORDER_1);
//        Person editedAlice = new OrderBuilder(ORDER_1).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
//                .build();
//        assertTrue(addressBook.hasPerson(editedAlice));
//    }
//
//    @Test
//    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
//        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
//    }
//
//    /**
//     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
//     */
//    private static class AddressBookStub implements ReadOnlyTrackO {
//        private final ObservableList<Person> persons = FXCollections.observableArrayList();
//
//        AddressBookStub(Collection<Person> persons) {
//            this.persons.setAll(persons);
//        }
//
//        @Override
//        public ObservableList<Person> getPersonList() {
//            return persons;
//        }
//    }

}
