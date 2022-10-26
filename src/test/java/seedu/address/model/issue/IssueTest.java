package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Deadline;
import seedu.address.model.project.ProjectTest;

public class IssueTest {

    private static final Issue defaultIssue = new Issue(new Title("Default"),
<<<<<<< HEAD
            Deadline.EmptyDeadline.EMPTY_DEADLINE, Urgency.NONE, Status.EmptyStatus.EMPTY_STATUS,
            ProjectTest.getDefaultProject(), IssueId.EmptyIssueId.EMPTY_ISSUE_ID);

    private static final Issue otherIssue = new Issue(new Title("Other"),
            Deadline.EmptyDeadline.EMPTY_DEADLINE, Urgency.NONE, Status.EmptyStatus.EMPTY_STATUS,
=======
            Deadline.EmptyDeadline.EMPTY_DEADLINE, Priority.NONE, Status.EmptyStatus.EMPTY_STATUS,
            ProjectTest.getDefaultProject(), IssueId.EmptyIssueId.EMPTY_ISSUE_ID);

    private static final Issue otherIssue = new Issue(new Title("Other"),
            Deadline.EmptyDeadline.EMPTY_DEADLINE, Priority.NONE, Status.EmptyStatus.EMPTY_STATUS,
>>>>>>> 26a22639255513998cf6801b7fdff233448cb57b
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
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Issue(new Title(invalidTitle),
<<<<<<< HEAD
                new Deadline("2022-10-12"), Urgency.LOW, new Status(true),
=======
                new Deadline("2022-10-12"), Priority.LOW, new Status(true),
>>>>>>> 26a22639255513998cf6801b7fdff233448cb57b
                ProjectTest.getDefaultProject(), new IssueId(1)));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "12-02-2022";
        assertThrows(IllegalArgumentException.class, () -> new Issue(new Title("finish up two classes"),
<<<<<<< HEAD
                new Deadline(invalidDeadline), Urgency.LOW, new Status(true),
=======
                new Deadline(invalidDeadline), Priority.LOW, new Status(true),
>>>>>>> 26a22639255513998cf6801b7fdff233448cb57b
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
