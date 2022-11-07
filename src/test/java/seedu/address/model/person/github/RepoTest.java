package seedu.address.model.person.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class RepoTest {

    private static final String NAME = "test";
    private static final String URL = "https://github.com/test";
    private static final String DESC = "test";
    private static final LocalDateTime LASTUPDATE = LocalDateTime.now();
    private static final Repo REPO = new Repo(NAME, URL, DESC, LASTUPDATE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Repo(null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Repo(null, "test", "test", null));
        assertThrows(NullPointerException.class, () -> new Repo("test", null, "test", null));
        assertThrows(NullPointerException.class, () -> new Repo("test", "test", "test", null));
    }

    @Test
    public void getRepoUrl() {
        assertEquals(REPO.getRepoUrl(), URL);
    }

    @Test
    public void getDescription() {
        assertEquals(REPO.getDescription().get(), DESC);

        Repo repoWithEmptyDesc = new Repo(NAME, URL, null, LASTUPDATE);
        assertEquals(repoWithEmptyDesc.getDescription(), Optional.empty());
    }

    @Test
    public void getLastUpdated() {
        assertEquals(REPO.getLastUpdated(), LASTUPDATE);
    }

    @Test
    public void getRepoName() {
        assertEquals(REPO.getRepoName(), NAME);
    }

    @Test
    public void equals() {
        Repo other = new Repo("test", "https://github.com/test", "test", LASTUPDATE);
        assertTrue(other.equals(REPO));
    }
}
