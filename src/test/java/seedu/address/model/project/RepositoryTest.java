package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RepositoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Repository(null));
    }

    @Test
    public void constructor_invalidRepository_throwsIllegalArgumentException() {
        String invalidRepository = "";
        assertThrows(IllegalArgumentException.class, () -> new Repository(invalidRepository));
    }

    @Test
    void isValidRepository() {
        // null repository
        assertThrows(NullPointerException.class, () -> Repository.isValidRepository(null));

        // invalid repository
        assertFalse(Repository.isValidRepository("")); // empty string
        assertFalse(Repository.isValidRepository(" ")); // spaces only
        assertFalse(Repository.isValidRepository("-")); // starting with dash
        assertFalse(Repository.isValidRepository("hello/")); // no repo name
        assertFalse(Repository.isValidRepository("/tp")); // no username
        assertFalse(Repository.isValidRepository("peter jack/tp")); // space present
        assertFalse(Repository.isValidRepository("thisIsAVeryLongStringToTestForCharacterLength/tp")); // too long

        //valid repository
        assertTrue(Repository.isValidRepository("peterjack/tp")); // alphabets only
        assertTrue(Repository.isValidRepository("12345/cs2103tp")); // alphanumeric characters
    }

    @Test
    public void getRepositoryUrl_validRepoName_stringConversionToGitHubUrlSuccess() {
        assertEquals(new Repository("peterjack/tp").getUiRepresentation(), "https://github.com/peterjack/tp");
    }
}
