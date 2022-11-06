package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.tag.RiskTag.isRiskTag;

import org.junit.jupiter.api.Test;

public class RiskTagTest {

    @Test
    public void isValidRiskTagName_validInput_success() {
        assertEquals(isRiskTag("HIGH"), true);
        assertEquals(isRiskTag("MEDIUM"), true);
        assertEquals(isRiskTag("LOW"), true);
    }

    // This is to test to see if input can be case insensitive
    @Test
    public void isValidRiskTagName_validInput_ignoreCase_success() {
        assertEquals(isRiskTag("hiGH"), true);
        assertEquals(isRiskTag("mEDiuM"), true);
        assertEquals(isRiskTag("low"), true);
    }

    @Test
    public void isValidRiskTagName_invalidInput_success() {
        assertEquals(isRiskTag("EASY"), false);
        assertEquals(isRiskTag("EXTRA HIGH"), false);
    }

    @Test
    public void getRisk() {
        RiskTag highRiskTag = new RiskTag("HIGH");
        assertEquals(3, highRiskTag.getRisk());

        RiskTag mediumRiskTag = new RiskTag("MEDIUM");
        assertEquals(2, mediumRiskTag.getRisk());

        RiskTag lowRiskTag = new RiskTag("LOW");
        assertEquals(1, lowRiskTag.getRisk());
    }
}
