package bookface.model;

import static bookface.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static bookface.testutil.Assert.assertThrows;
import static bookface.testutil.TypicalPersons.ALICE;
import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import bookface.model.book.Book;
import bookface.model.person.Person;
import bookface.model.person.exceptions.DuplicatePersonException;
import bookface.testutil.PersonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookFaceTest {

    private final BookFace bookFace = new BookFace();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bookFace.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookFace.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBookFace_replacesData() {
        BookFace newData = getTypicalBookFaceData();
        bookFace.resetData(newData);
        assertEquals(newData, bookFace);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        BookFaceStub newData = new BookFaceStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> bookFace.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookFace.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInBookFace_returnsFalse() {
        assertFalse(bookFace.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInBookFace_returnsTrue() {
        bookFace.addPerson(ALICE);
        assertTrue(bookFace.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInBookFace_returnsTrue() {
        bookFace.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(bookFace.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bookFace.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyBookFace whose persons list can violate interface constraints.
     */
    private static class BookFaceStub implements ReadOnlyBookFace {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Book> books = FXCollections.observableArrayList();

        BookFaceStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Book> getBookList() {
            return books;
        }
    }

}
