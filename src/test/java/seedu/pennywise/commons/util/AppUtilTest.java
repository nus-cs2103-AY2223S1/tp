package seedu.pennywise.commons.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.pennywise.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppUtilTest {

    @Test
    public void getImage_exitingImage() {
        assertNotNull(AppUtil.getImage("/images/pennywise-logo/logo-black.png"));
        assertNotNull(AppUtil.getImage("/images/pennywise-logo/logo-color.png"));
        assertNotNull(AppUtil.getImage("/images/pennywise-logo/logo-no-background.png"));
        assertNotNull(AppUtil.getImage("/images/pennywise-logo/logo-white.png"));
    }

    @Test
    public void getImage_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AppUtil.getImage(null));
    }

    @Test
    public void checkArgument_true_nothingHappens() {
        AppUtil.checkArgument(true);
        AppUtil.checkArgument(true, "");
    }

    @Test
    public void checkArgument_falseWithoutErrorMessage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AppUtil.checkArgument(false));
    }

    @Test
    public void checkArgument_falseWithErrorMessage_throwsIllegalArgumentException() {
        String errorMessage = "error message";
        assertThrows(IllegalArgumentException.class, errorMessage, () -> AppUtil.checkArgument(false, errorMessage));
    }
}
