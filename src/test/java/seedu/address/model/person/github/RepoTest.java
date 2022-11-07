package seedu.address.model.person.github;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;

import java.time.LocalDateTime;
import java.util.Optional;

import static seedu.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepoTest {

    private final String REPO_NAME = "test";
    private final String REPO_URL = "https://github.com/test";
    private final String REPO_DESC = "test";
    private final LocalDateTime REPO_LAST_UPDATE = LocalDateTime.now();
    private final Repo TEST_REPO = new Repo(REPO_NAME, REPO_URL, REPO_DESC, REPO_LAST_UPDATE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Repo(null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Repo(null, "test", "test", null));
        assertThrows(NullPointerException.class, () -> new Repo("test", null, "test", null));
        assertThrows(NullPointerException.class, () -> new Repo("test", "test", "test", null));
    }

    @Test
    public void getRepoUrl() {
        assertEquals(TEST_REPO.getRepoUrl(), REPO_URL);
    }

    @Test
    public void getDescription() {
        assertEquals(TEST_REPO.getDescription().get(), REPO_DESC);

        Repo repoWithEmptyDesc = new Repo(REPO_NAME, REPO_URL, null, REPO_LAST_UPDATE);
        assertEquals(repoWithEmptyDesc.getDescription(), Optional.empty());
    }

    @Test
    public void getLastUpdated() {
        assertEquals(TEST_REPO.getLastUpdated(), REPO_LAST_UPDATE);
    }

    @Test
    public void getRepoName() {
        assertEquals(TEST_REPO.getRepoName(), REPO_NAME);
    }

    @Test
    public void equals() {
        Repo other = new Repo("test", "https://github.com/test", "test", REPO_LAST_UPDATE);
        assertTrue(other.equals(TEST_REPO));
    }
}
