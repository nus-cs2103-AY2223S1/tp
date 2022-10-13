package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Deadline;
import seedu.address.model.project.ProjectTest;

public class IssueTest {

    private static final Issue defaultIssue = new Issue(new Description("Default"),
            Deadline.EmptyDeadline.EMPTY_DEADLINE, Priority.NONE, Status.EmptyStatus.EMPTY_STATUS,
            ProjectTest.getDefaultProject(), IssueId.EmptyIssueId.EMPTY_ISSUE_ID);

    private static final Issue otherIssue = new Issue(new Description("Other"),
            Deadline.EmptyDeadline.EMPTY_DEADLINE, Priority.NONE, Status.EmptyStatus.EMPTY_STATUS,
            ProjectTest.getDefaultProject(), IssueId.EmptyIssueId.EMPTY_ISSUE_ID);

    public static Issue getDefaultIssue() {
        return defaultIssue;
    }

    public static Issue getOtherIssue() {
        return otherIssue;
    }

    @Test
    public void constructor_nullParam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Issue(null, new Deadline("2019-12-02"),
                null, new Status(false), ProjectTest.getDefaultProject(), null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Issue(new Description(invalidDescription),
                new Deadline("2022-10-12"), Priority.LOW, new Status(true),
                ProjectTest.getDefaultProject(), new IssueId(1)));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "12-02-2022";
        assertThrows(IllegalArgumentException.class, () -> new Issue(new Description("finish up two classes"),
                new Deadline(invalidDeadline), Priority.LOW, new Status(true),
                ProjectTest.getDefaultProject(), new IssueId(1)));
    }

    @Test
    public void equals() {
        Issue defaultIssue = getDefaultIssue();
        Issue otherIssue = getOtherIssue();

        // same values -> returns true
        assertEquals(defaultIssue, defaultIssue);

        // same object -> returns true
        assertEquals(defaultIssue, defaultIssue);

        // different object -> returns false
        assertNotEquals(defaultIssue, otherIssue);

        // null -> returns false
        assertNotEquals(null, defaultIssue);
    }

}
