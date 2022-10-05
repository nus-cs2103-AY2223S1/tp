package foodwhere.model.detail;

import static foodwhere.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DetailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Detail(null));
    }

    @Test
    public void constructor_invalidDetailName_throwsIllegalArgumentException() {
        String invalidDetailName = "";
        assertThrows(IllegalArgumentException.class, () -> new Detail(invalidDetailName));
    }

    @Test
    public void isValidDetailName() {
        // null detail
        assertThrows(NullPointerException.class, () -> Detail.isValidDetail(null));
    }

}
