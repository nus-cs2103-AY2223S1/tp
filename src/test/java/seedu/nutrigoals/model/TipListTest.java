package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TipListTest {
    @Test
    public void generateTipTest() {
        assertTrue(TipList.getListOfTips().contains(TipList.generateTip()));
    }
}
