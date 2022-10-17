package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.clinkedin.commons.core.GuiSettings;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ReadOnlyAddressBook;
import seedu.clinkedin.model.ReadOnlyUserPrefs;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.TagType;

public class DeleteTagCommandTest {

    @Test
    public void constructor_nullIndexNullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null, new UniqueTagTypeMap()));
    }

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromZeroBased(0);
        Index secondIndex = Index.fromZeroBased(1);
        UniqueTagTypeMap firstTag = new UniqueTagTypeMap();
        UniqueTagTypeMap secondTag = new UniqueTagTypeMap();

        DeleteTagCommand deleteFirstTagCommand = new DeleteTagCommand(firstIndex, firstTag);
        DeleteTagCommand deleteSecondTagCommand = new DeleteTagCommand(secondIndex, secondTag);

        // same object -> returns true
        assertTrue(deleteFirstTagCommand.equals(deleteFirstTagCommand));

        // same values -> returns true
        DeleteTagCommand deleteFirstTagCommandCopy = new DeleteTagCommand(firstIndex, firstTag);
        assertTrue(deleteFirstTagCommand.equals(deleteFirstTagCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTagCommand.equals(null));

        // different add tag -> returns false
        assertFalse(deleteFirstTagCommand.equals(deleteSecondTagCommand));
    }

}
