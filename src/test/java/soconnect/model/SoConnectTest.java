package soconnect.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static soconnect.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static soconnect.testutil.Assert.assertThrows;
import static soconnect.testutil.TypicalPersons.ALICE;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import soconnect.model.person.Person;
import soconnect.model.person.exceptions.DuplicatePersonException;
import soconnect.model.tag.Tag;
import soconnect.testutil.PersonBuilder;
import soconnect.testutil.TypicalPersons;

public class SoConnectTest {

    private final SoConnect soConnect = new SoConnect();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), soConnect.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> soConnect.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySoConnect_replacesData() {
        SoConnect newData = getTypicalSoConnect();
        soConnect.resetData(newData);
        assertEquals(newData, soConnect);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Tag> newTags = new ArrayList<>();
        SoConnectStub newData = new SoConnectStub(newPersons, newTags);

        assertThrows(DuplicatePersonException.class, () -> soConnect.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> soConnect.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInSoConnect_returnsFalse() {
        assertFalse(soConnect.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInSoConnect_returnsTrue() {
        soConnect.addPerson(ALICE);
        assertTrue(soConnect.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInSoConnect_returnsTrue() {
        soConnect.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(soConnect.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> soConnect.getPersonList().remove(0));
    }

    @Test
    void sortByName() {
        SoConnect sampleA = new SoConnect();
        SoConnect sampleB = new SoConnect();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByName(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByName(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByPhone() {
        SoConnect sampleA = new SoConnect();
        SoConnect sampleB = new SoConnect();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByPhone(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByPhone(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByEmail() {
        SoConnect sampleA = new SoConnect();
        SoConnect sampleB = new SoConnect();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByEmail(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByEmail(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByAddress() {
        SoConnect sampleA = new SoConnect();
        SoConnect sampleB = new SoConnect();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByAddress(true);
        assertEquals(sampleA, sampleB);
        sampleA.sortByAddress(false);
        assertNotEquals(sampleA, sampleB);
    }

    @Test
    void sortByTag() {
        SoConnect sampleA = new SoConnect();
        SoConnect sampleB = new SoConnect();
        sampleA.addPerson(ALICE);
        sampleA.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(TypicalPersons.BENSON);
        sampleB.addPerson(ALICE);
        sampleA.sortByTag(new Tag("owesMoney"), false);
        assertEquals(sampleA, sampleB);
        sampleA.sortByTag(new Tag("owesMoney"), true);
        assertNotEquals(sampleA, sampleB);
    }

    /**
     * A stub ReadOnlySoConnect whose persons list can violate interface constraints.
     */
    private static class SoConnectStub implements ReadOnlySoConnect {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        SoConnectStub(Collection<Person> persons, Collection<Tag> tags) {
            this.persons.setAll(persons);
            this.tags.setAll(tags);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}
