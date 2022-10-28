package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }

    @Test
    public void equals() {
        String c1 = "Always on time!";
        String c2 = "Always late";
        Comment comment1 = new Comment(c1);
        Comment comment2 = new Comment(c2);

        assertTrue(comment1.equals(new Comment(c1)));
        assertTrue(c1.equals(comment1.toString()));
        assertTrue(c2.equals(comment2.toString()));
        assertFalse(comment1.equals(1));

        assertFalse(comment1.equals(null));

        assertFalse(comment1.equals(comment2));
    }

}
