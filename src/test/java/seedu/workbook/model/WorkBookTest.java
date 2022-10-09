package seedu.workbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.workbook.testutil.Assert.assertThrows;
import static seedu.workbook.testutil.TypicalPersons.ALICE;
import static seedu.workbook.testutil.TypicalPersons.getTypicalWorkBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.workbook.model.person.Person;
import seedu.workbook.model.person.exceptions.DuplicatePersonException;
import seedu.workbook.testutil.PersonBuilder;

public class WorkBookTest {

    private final WorkBook workBook = new WorkBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), workBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> workBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWorkBook_replacesData() {
        WorkBook newData = getTypicalWorkBook();
        workBook.resetData(newData);
        assertEquals(newData, workBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        WorkBookStub newData = new WorkBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> workBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> workBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInWorkBook_returnsFalse() {
        assertFalse(workBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInWorkBook_returnsTrue() {
        workBook.addPerson(ALICE);
        assertTrue(workBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInWorkBook_returnsTrue() {
        workBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(workBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> workBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyWorkBook whose persons list can violate interface constraints.
     */
    private static class WorkBookStub implements ReadOnlyWorkBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        WorkBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
