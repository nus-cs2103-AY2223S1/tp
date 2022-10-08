package seedu.address.model.issue;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IssueTest {

    @Test
    public void constructor_nullParam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Issue(null, new Deadline("2019-12-02"),
                null, new Status(false)));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Issue(new Description(invalidDescription),
                new Deadline("2022-10-12"), Priority.LOW, new Status(true)));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "12-02-2022";
        assertThrows(IllegalArgumentException.class, () -> new Issue(new Description("finish up two classes"),
                new Deadline(invalidDeadline), Priority.LOW, new Status(true)));
    }
}
