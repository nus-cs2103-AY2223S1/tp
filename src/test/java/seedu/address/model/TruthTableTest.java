package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.team.Team;
import seedu.address.testutil.PersonBuilder;

public class TruthTableTest {

    private final TruthTable truthTable = TruthTable.createNewTruthTable();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), truthTable.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> truthTable.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTruthTable_replacesData() {
        TruthTable newData = getTypicalTruthTable();
        truthTable.resetData(newData);
        assertEquals(newData, truthTable);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        TruthTableStub newData = new TruthTableStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> truthTable.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> truthTable.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTruthTable_returnsFalse() {
        assertFalse(truthTable.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInTruthTable_returnsTrue() {
        truthTable.addPerson(ALICE);
        assertTrue(truthTable.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTruthTable_returnsTrue() {
        truthTable.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(truthTable.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> truthTable.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyTruthTable whose persons list can violate interface constraints.
     */
    private static class TruthTableStub implements ReadOnlyTruthTable {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Team> teams = FXCollections.observableArrayList();
        private final Team team = Team.createDefaultTeam();

        TruthTableStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }


        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        public Team getTeam() {
            return team;
        }

        @Override
        public ObservableList<Team> getTeamList() {
            return teams;
        }
    }
}
