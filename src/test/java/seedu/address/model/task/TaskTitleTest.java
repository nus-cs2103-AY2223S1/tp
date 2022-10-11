package seedu.address.model.task;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;

public class TaskTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTitle(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskTitle(invalidTitle));
    }

}
