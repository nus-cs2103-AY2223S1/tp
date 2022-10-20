package seedu.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.TypicalApplications.FACEBOOK;
import static seedu.application.testutil.TypicalApplications.GOOGLE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.ApplicationBookBuilder;

public class VersionedApplicationBookTest {

    private final ReadOnlyApplicationBook facebookApplicationBook = new ApplicationBookBuilder()
            .withApplication(FACEBOOK).build();
    private final ReadOnlyApplicationBook googleApplicationBook = new ApplicationBookBuilder()
            .withApplication(GOOGLE).build();

    @Test
    public void constructor() {
        VersionedApplicationBook versionedApplicationBook = createVersionedApplicationBook(facebookApplicationBook);
        assertEquals(List.of(new ApplicationBook(), facebookApplicationBook), versionedApplicationBook.getHistory());
        assertEquals(facebookApplicationBook, versionedApplicationBook.getCurrentState());
    }

    @Test
    public void commit_noPreviouslyUndoneStates_currentStateSavedNoStatesRemoved() {
        VersionedApplicationBook versionedApplicationBook = createVersionedApplicationBook(facebookApplicationBook);

        versionedApplicationBook.commit();
        assertEquals(List.of(new ApplicationBook(), facebookApplicationBook, facebookApplicationBook),
                versionedApplicationBook.getHistory());
        assertEquals(facebookApplicationBook, versionedApplicationBook.getCurrentState());
    }

    @Test
    public void commit_hasPreviouslyUndoneStates_currentStateSavedPreviouslyUndoneStatesRemoved() {
        VersionedApplicationBook versionedApplicationBook = createVersionedApplicationBook(
                facebookApplicationBook, googleApplicationBook);

        versionedApplicationBook.undo();
        versionedApplicationBook.commit();
        assertEquals(List.of(new ApplicationBook(), facebookApplicationBook, facebookApplicationBook),
                versionedApplicationBook.getHistory());
        assertEquals(facebookApplicationBook, versionedApplicationBook.getCurrentState());
    }

    @Test
    public void undo_hasPreviousStates_success() {
        VersionedApplicationBook versionedApplicationBook = createVersionedApplicationBook(
                facebookApplicationBook, googleApplicationBook);

        versionedApplicationBook.undo();
        assertEquals(List.of(new ApplicationBook(), facebookApplicationBook, googleApplicationBook),
                versionedApplicationBook.getHistory());
        assertEquals(facebookApplicationBook, versionedApplicationBook.getCurrentState());
    }

    @Test
    public void undo_noPreviousState_failure() {
        VersionedApplicationBook versionedApplicationBook = createVersionedApplicationBook();
        assertThrows(AssertionError.class, () -> versionedApplicationBook.undo());
    }

    @Test
    public void redo_hasPreviouslyUndoneState_success() {
        VersionedApplicationBook versionedApplicationBook = createVersionedApplicationBook(
                facebookApplicationBook, googleApplicationBook);
        versionedApplicationBook.undo();
        versionedApplicationBook.redo();
        assertEquals(List.of(new ApplicationBook(), facebookApplicationBook, googleApplicationBook),
                versionedApplicationBook.getHistory());
        assertEquals(googleApplicationBook, versionedApplicationBook.getCurrentState());
    }

    @Test
    public void redo_noPreviouslyUndoneState_failure() {
        VersionedApplicationBook versionedApplicationBook = createVersionedApplicationBook();
        assertThrows(AssertionError.class, () -> versionedApplicationBook.redo());
    }

    @Test
    public void equals() {
        VersionedApplicationBook versionedApplicationBook = createVersionedApplicationBook(
                facebookApplicationBook, googleApplicationBook);

        // same values -> returns true
        VersionedApplicationBook versionedApplicationBookCopy = createVersionedApplicationBook(
                facebookApplicationBook, googleApplicationBook);
        assertTrue(versionedApplicationBook.equals(versionedApplicationBookCopy));

        // same object -> returns true
        assertTrue(versionedApplicationBook.equals(versionedApplicationBook));

        // null -> returns false
        assertFalse(versionedApplicationBook.equals(null));

        // different types -> returns false
        assertFalse(versionedApplicationBook.equals(1));

        // different state list -> returns false
        VersionedApplicationBook differentApplicationBookList = createVersionedApplicationBook(facebookApplicationBook);
        assertFalse(versionedApplicationBook.equals(differentApplicationBookList));

        // different pointer -> returns false
        versionedApplicationBookCopy.undo();
        assertFalse(versionedApplicationBook.equals(versionedApplicationBookCopy));
    }

    private VersionedApplicationBook createVersionedApplicationBook(ReadOnlyApplicationBook... applicationBooks) {
        VersionedApplicationBook versionedApplicationBook = new VersionedApplicationBook(new ApplicationBook());
        for (ReadOnlyApplicationBook applicationBook : applicationBooks) {
            versionedApplicationBook.resetData(applicationBook);
            versionedApplicationBook.commit();
        }
        return versionedApplicationBook;
    }
}
