package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.ui.SpecialTagLabel.HIGH_KEYWORD;
import static seedu.address.ui.SpecialTagLabel.LOW_KEYWORD;
import static seedu.address.ui.SpecialTagLabel.MEDIUM_KEYWORD;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
public class SpecialTagLabelTest {
    private static final String LOW_STYLE = "-fx-text-fill: white;-fx-background-color: "
            + "green;-fx-padding: 1 3 1 3;-fx-border-radius: 2;-fx-background-radius: 2;-fx-font-size: 11;";
    private static final String MEDIUM_STYLE = "-fx-text-fill: white;-fx-background-color: "
            + "orange;-fx-padding: 1 3 1 3;-fx-border-radius: 2;-fx-background-radius: 2;-fx-font-size: 11;";
    private static final String HIGH_STYLE = "-fx-text-fill: white;-fx-background-color: "
            + "red;-fx-padding: 1 3 1 3;-fx-border-radius: 2;-fx-background-radius: 2;-fx-font-size: 11;";
    private static final String DEFAULT_STYLE = "-fx-text-fill: white;-fx-background-color: "
            + "#3e7b91;-fx-padding: 1 3 1 3;-fx-border-radius: 2;-fx-background-radius: 2;-fx-font-size: 11;";
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SpecialTagLabel(null));
    }

    @Test
    public void constructor_lowRisk_correctStyle() {
        SpecialTagLabel specialTagLabel = new SpecialTagLabel(LOW_KEYWORD);
        assertEquals(specialTagLabel.getStyle(), LOW_STYLE);
    }

    @Test
    public void constructor_mediumRisk_correctStyle() {
        SpecialTagLabel specialTagLabel = new SpecialTagLabel(MEDIUM_KEYWORD);
        assertEquals(specialTagLabel.getStyle(), MEDIUM_STYLE);
    }

    @Test
    public void constructor_highRisk_correctStyle() {
        SpecialTagLabel specialTagLabel = new SpecialTagLabel(HIGH_KEYWORD);
        assertEquals(specialTagLabel.getStyle(), HIGH_STYLE);
    }

    @Test
    public void constructor_nonRisk_correctStyle() {
        //empty string
        SpecialTagLabel firstSpecialTagLabel = new SpecialTagLabel("");
        assertEquals(firstSpecialTagLabel.getStyle(), DEFAULT_STYLE);

        SpecialTagLabel secondSpecialTagLabel = new SpecialTagLabel("MODERATE");
        assertEquals(firstSpecialTagLabel.getStyle(), DEFAULT_STYLE);
    }
}
