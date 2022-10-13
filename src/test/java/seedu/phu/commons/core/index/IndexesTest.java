package seedu.phu.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.phu.testutil.Assert.assertThrows;
import static seedu.phu.testutil.TypicalInternships.BENSON;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternships;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.phu.commons.exceptions.IllegalIndexException;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.UniqueInternshipList;

public class IndexesTest {
    @Test
    public void createIndexesByParsing_validInput() {
        try {
            String[] unParsedIndexes = {"1", "2"};
            Indexes createdIndexes = new Indexes(unParsedIndexes);
            Indexes expectedIndexes = new Indexes(Set.of(Index.fromOneBased(1), Index.fromOneBased(2)));
            assertTrue(expectedIndexes.equals(createdIndexes));
        } catch (ParseException error) {
            fail("Should not have thrown exception!");
        }
    }

    @Test
    public void createIndexesByParsing_invalidInput_throwsParseException() {
        String[] invalidUnParsedIndexes = {"0"};
        assertThrows(ParseException.class, () -> new Indexes(invalidUnParsedIndexes));
    }

    @Test
    public void getInternshipsFromIndexes_validIndexes() {
        try {
            List<Internship> internshipList = getTypicalInternships();
            Indexes validIndexes = new Indexes(Set.of(Index.fromOneBased(2)));
            assert(internshipList.size() >= 2); // confirm that validIndexes is indeed valid

            UniqueInternshipList returnedInternshipList = validIndexes.getAllInternshipsFromIndexes(internshipList);
            UniqueInternshipList expectedInternshipList = new UniqueInternshipList();
            expectedInternshipList.add(BENSON);

            assertTrue(returnedInternshipList.equals(expectedInternshipList));
        } catch (IllegalIndexException error) {
            fail("Should not have thrown exception!");
        }
    }

    @Test
    public void getInternshipsFromIndexes_invalidIndexes_throwsIllegalIndexException() {
        List<Internship> internshipList = getTypicalInternships();
        Index outOfBoundIndex = Index.fromOneBased(internshipList.size() + 1);
        Indexes invalidIndexes = new Indexes(Set.of(Index.fromOneBased(1), outOfBoundIndex));
        assertThrows(IllegalIndexException.class, () -> invalidIndexes.getAllInternshipsFromIndexes(internshipList));
    }

    @Test
    public void equals() {
        Indexes firstAndSecondInternshipIndexes = new Indexes(Set.of(Index.fromOneBased(1), Index.fromOneBased(2)));
        Indexes secondAndFirstInternshipIndexes = new Indexes(Set.of(Index.fromOneBased(2), Index.fromOneBased(1)));
        Indexes secondInternshipIndexes = new Indexes(Set.of(Index.fromOneBased(2)));

        // same set of index -> returns true
        assertTrue(firstAndSecondInternshipIndexes.equals(new Indexes(Set.of(
                Index.fromOneBased(1), Index.fromOneBased(2)))));
        assertTrue(firstAndSecondInternshipIndexes.equals(secondAndFirstInternshipIndexes));

        // same object -> returns true
        assertTrue(firstAndSecondInternshipIndexes.equals(firstAndSecondInternshipIndexes));

        // null -> returns false
        assertFalse(firstAndSecondInternshipIndexes.equals(null));

        // different types -> returns false
        assertFalse(firstAndSecondInternshipIndexes.equals(5.0f));

        // different set of index -> returns false
        assertFalse(firstAndSecondInternshipIndexes.equals(secondInternshipIndexes));
    }
}
