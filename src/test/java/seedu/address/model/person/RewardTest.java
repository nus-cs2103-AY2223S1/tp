package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RewardTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Reward(null));
    }

    @Test
    public void constructor_invalidReward_throwsIllegalArgumentException() {
        String invalidReward = "";
        assertThrows(IllegalArgumentException.class, () -> new Reward(invalidReward));
    }

    @Test
    public void isValidReward() {
        // null reward
        assertThrows(NullPointerException.class, () ->Reward.isValidReward(null));

        // invalid rewards
        assertFalse(Reward.isValidReward("")); // empty string
        assertFalse(Reward.isValidReward(" ")); // spaces only

        // valid rewards
        assertTrue(Reward.isValidReward("4000"));
        assertTrue(Reward.isValidReward("0")); // one character
        assertTrue(Reward.isValidReward("000069")); // trailing zeroes
        assertTrue(Reward.isValidReward("99999999999999999999")); // long reward
    }
}
