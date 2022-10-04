package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.*;
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
        assertTrue(Repository.isValidRepository("user-x/project_y-two.alpha")); // allow dashes, underscores, periods
    }

    @Test
    public void toString_validRepoName_stringConversionToGitHubURLSuccess() {
        assertEquals(new Repository("peterjack/tp").toString(), "https://github.com/peterjack/tp");
    }
}
