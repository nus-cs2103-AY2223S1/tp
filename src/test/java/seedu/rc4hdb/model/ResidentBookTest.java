package seedu.rc4hdb.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.exceptions.DuplicateResidentException;
import seedu.rc4hdb.testutil.ResidentBuilder;

public class ResidentBookTest {

    private final ResidentBook residentBook = new ResidentBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), residentBook.getResidentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> residentBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyResidentBook_replacesData() {
        ResidentBook newData = getTypicalResidentBook();
        residentBook.resetData(newData);
        assertEquals(newData, residentBook);
    }

    @Test
    public void resetData_withDuplicateResidents_throwsDuplicateResidentException() {
        // Two residents with the same identity fields
        Resident editedAlice = new ResidentBuilder(ALICE).withRoom(VALID_ROOM_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Resident> newResidents = Arrays.asList(ALICE, editedAlice);
        ResidentBookStub newData = new ResidentBookStub(newResidents);

        assertThrows(DuplicateResidentException.class, () -> residentBook.resetData(newData));
    }

    @Test
    public void hasResident_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> residentBook.hasResident(null));
    }

    @Test
    public void hasResident_residentNotInResidentBook_returnsFalse() {
        assertFalse(residentBook.hasResident(ALICE));
    }

    @Test
    public void hasResident_residentInResidentBook_returnsTrue() {
        residentBook.addResident(ALICE);
        assertTrue(residentBook.hasResident(ALICE));
    }

    @Test
    public void hasResident_residentWithSameIdentityFieldsInResidentBook_returnsTrue() {
        residentBook.addResident(ALICE);
        Resident editedAlice = new ResidentBuilder(ALICE).withRoom(VALID_ROOM_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(residentBook.hasResident(editedAlice));
    }

    @Test
    public void getResidentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> residentBook.getResidentList().remove(0));
    }

    /**
     * A stub ReadOnlyResidentBook whose residents list can violate interface constraints.
     */
    private static class ResidentBookStub implements ReadOnlyResidentBook {
        private final ObservableList<Resident> residents = FXCollections.observableArrayList();

        ResidentBookStub(Collection<Resident> residents) {
            this.residents.setAll(residents);
        }

        @Override
        public ObservableList<Resident> getResidentList() {
            return residents;
        }
    }
}
