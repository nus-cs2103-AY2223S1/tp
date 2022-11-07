package tuthub.model.tutor;

import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentListTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommentList(null));
    }
}
