package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GameTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GameType(null));
    }


    @Test
    public void isValidGameType() {
        // null game type
        assertThrows(NullPointerException.class, () -> GameType.isValidGameType(null));

        // valid game type
        assertTrue(GameType.isValidGameType("mine plex")); // alphabets only
        assertTrue(GameType.isValidGameType("100300")); // numbers only
        assertTrue(GameType.isValidGameType("jungle 54")); // alphanumeric characters
        assertTrue(GameType.isValidGameType("MinePlex")); // with capital letters
        assertTrue(GameType.isValidGameType("Amazon Rain Forest 1988 with zombies")); // long names
    }
}

