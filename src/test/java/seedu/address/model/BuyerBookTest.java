package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class BuyerBookTest {

    private final PersonBook personBook = new PersonBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), personBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        PersonBook newData = getTypicalPersonsBook();
        personBook.resetData(newData);
        assertEquals(newData, personBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two buyers with the same identity fields
        Buyer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withPriority(VALID_PRIORITY_HIGH)
                .build();
        List<Buyer> newBuyers = Arrays.asList(ALICE, editedAlice);
        PersonBookStub newData = new PersonBookStub(newBuyers);

        assertThrows(DuplicatePersonException.class, () -> personBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(personBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        personBook.addPerson(ALICE);
        assertTrue(personBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        personBook.addPerson(ALICE);
        Buyer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withPriority(VALID_PRIORITY_HIGH)
                .build();
        assertTrue(personBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> personBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyPersonBook whose buyers list can violate interface constraints.
     */
    private static class PersonBookStub implements ReadOnlyPersonBook {
        private final ObservableList<Buyer> buyers = FXCollections.observableArrayList();

        PersonBookStub(Collection<Buyer> buyers) {
            this.buyers.setAll(buyers);
        }

        @Override
        public ObservableList<Buyer> getPersonList() {
            return buyers;
        }
    }
}
