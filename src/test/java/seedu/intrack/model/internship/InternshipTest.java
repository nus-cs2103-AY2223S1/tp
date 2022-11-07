package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_REMARK_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_SALARY_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_SALARY_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_MSFT;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalInternships.GOOG;
import static seedu.intrack.testutil.TypicalInternships.MSFT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.intrack.model.tag.Tag;
import seedu.intrack.testutil.InternshipBuilder;

public class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getTags().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(GOOG.isSameInternship(GOOG));

        // null -> returns false
        assertFalse(GOOG.isSameInternship(null));

        // same name, all other attributes different -> returns true
        Internship editedGoogle = new InternshipBuilder(GOOG).withSalary(VALID_SALARY_MSFT).withEmail(VALID_EMAIL_MSFT)
                .withStatus(VALID_STATUS_MSFT).withWebsite(VALID_WEBSITE_MSFT).withTags(VALID_TAG_REMOTE).build();
        assertTrue(GOOG.isSameInternship(editedGoogle));

        // different name, all other attributes same -> returns false
        editedGoogle = new InternshipBuilder(GOOG).withName(VALID_NAME_MSFT).build();
        assertFalse(GOOG.isSameInternship(editedGoogle));

        // different position, all other attributes same -> returns false
        editedGoogle = new InternshipBuilder(GOOG).withPosition(VALID_POSITION_MSFT).build();
        assertFalse(GOOG.isSameInternship(editedGoogle));

        // same name, same position, all other attributes different -> returns true
        editedGoogle = new InternshipBuilder(GOOG).withSalary(VALID_SALARY_MSFT).withEmail(VALID_EMAIL_MSFT)
                .withStatus(VALID_STATUS_MSFT).withWebsite(VALID_WEBSITE_MSFT).withTags(VALID_TAG_REMOTE).build();
        assertTrue(GOOG.isSameInternship(editedGoogle));

        // name differs in case, all other attributes same -> returns true
        Internship editedMsft = new InternshipBuilder(MSFT).withName(VALID_NAME_MSFT.toLowerCase()).build();
        assertTrue(MSFT.isSameInternship(editedMsft));

        // position differs in case, all other attributes same -> returns true
        editedMsft = new InternshipBuilder(MSFT).withPosition(VALID_POSITION_MSFT.toLowerCase()).build();
        assertTrue(MSFT.isSameInternship(editedMsft));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_MSFT + " ";
        editedMsft = new InternshipBuilder(MSFT).withName(nameWithTrailingSpaces).build();
        assertFalse(MSFT.isSameInternship(editedMsft));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship googleCopy = new InternshipBuilder(GOOG).build();
        assertTrue(GOOG.equals(googleCopy));

        // same object -> returns true
        assertTrue(GOOG.equals(GOOG));

        // null -> returns false
        assertFalse(GOOG.equals(null));

        // different type -> returns false
        assertFalse(GOOG.equals(5));

        // different internship -> returns false
        assertFalse(GOOG.equals(MSFT));

        // different name -> returns false
        Internship editedGoogle = new InternshipBuilder(GOOG).withName(VALID_NAME_MSFT).build();
        assertFalse(GOOG.equals(editedGoogle));

        // different position -> returns false
        editedGoogle = new InternshipBuilder(GOOG).withPosition(VALID_POSITION_MSFT).build();
        assertFalse(GOOG.equals(editedGoogle));

        // different salary -> returns false
        editedGoogle = new InternshipBuilder(GOOG).withSalary(VALID_SALARY_MSFT).build();
        assertFalse(GOOG.equals(editedGoogle));

        // different email -> returns false
        editedGoogle = new InternshipBuilder(GOOG).withEmail(VALID_EMAIL_MSFT).build();
        assertFalse(GOOG.equals(editedGoogle));

        // different website -> returns false
        editedGoogle = new InternshipBuilder(GOOG).withWebsite(VALID_WEBSITE_MSFT).build();
        assertFalse(GOOG.equals(editedGoogle));

        // different tags -> returns false
        editedGoogle = new InternshipBuilder(GOOG).withTags(VALID_TAG_REMOTE).build();
        assertFalse(GOOG.equals(editedGoogle));
    }

    @Test
    public void getFurthestTaskDate() {
        List<Task> taskList = new ArrayList<>();
        Set<Tag> tagList = new HashSet<>();
        tagList.add(new Tag("Urgent"));
        Task first = new Task("HRA", "10-09-2023 11:00");
        Task second = new Task("HRB", "10-06-2023 11:00");
        Task third = new Task("HRC", "10-09-2023 12:00");
        taskList.add(first);
        taskList.add(second);
        taskList.add(third);
        Internship internship = new Internship(new Name(VALID_NAME_AAPL), new Position(VALID_POSITION_AAPL),
                new Status(VALID_STATUS_AAPL), new Email(VALID_EMAIL_AAPL),
                new Website(VALID_WEBSITE_AAPL), taskList, new Salary(VALID_SALARY_AAPL),
                tagList, new Remark(VALID_REMARK_AAPL));
        LocalDateTime furthestDate = internship.getFurthestTaskDate();
        LocalDateTime expectedFurthestDate = third.getTaskTime();
        assertTrue(furthestDate.equals(expectedFurthestDate));

    }

    @Test
    public void getNearestTaskDate() {
        List<Task> taskList = new ArrayList<>();
        Set<Tag> tagList = new HashSet<>();
        tagList.add(new Tag("Urgent"));
        Task first = new Task("HRA", "10-09-2023 11:00");
        Task second = new Task("HRB", "10-06-2023 11:00");
        Task third = new Task("HRC", "10-09-2023 12:00");
        taskList.add(first);
        taskList.add(second);
        taskList.add(third);
        Internship internship = new Internship(new Name(VALID_NAME_AAPL), new Position(VALID_POSITION_AAPL),
                new Status(VALID_STATUS_AAPL), new Email(VALID_EMAIL_AAPL),
                new Website(VALID_WEBSITE_AAPL), taskList, new Salary(VALID_SALARY_AAPL),
                tagList, new Remark(VALID_REMARK_AAPL));
        LocalDateTime nearestDate = internship.getNearestTaskDate();
        LocalDateTime expectedNearestDate = second.getTaskTime();
        assertTrue(nearestDate.equals(expectedNearestDate));
    }

    @Test
    public void isTaskListEmpty() {
        List<Task> taskList = new ArrayList<>();
        Set<Tag> tagList = new HashSet<>();
        Internship internship = new Internship(new Name(VALID_NAME_AAPL), new Position(VALID_POSITION_AAPL),
                new Status(VALID_STATUS_AAPL), new Email(VALID_EMAIL_AAPL),
                new Website(VALID_WEBSITE_AAPL), taskList, new Salary(VALID_SALARY_AAPL),
                tagList, new Remark(VALID_REMARK_AAPL));
        assertTrue(internship.isTaskListEmpty());
    }

    @Test
    public void clearTag() {
        List<Task> taskList = new ArrayList<>();
        Set<Tag> tagList = new HashSet<>();
        tagList.add(new Tag("Urgent"));
        Internship internship = new Internship(new Name(VALID_NAME_AAPL), new Position(VALID_POSITION_AAPL),
                new Status(VALID_STATUS_AAPL), new Email(VALID_EMAIL_AAPL),
                new Website(VALID_WEBSITE_AAPL), taskList, new Salary(VALID_SALARY_AAPL),
                tagList, new Remark(VALID_REMARK_AAPL));
        internship.clearTag();
        assertTrue(internship.getTags().equals(new HashSet<>()));
    }

    @Test
    public void isHasUpcomingTasks_empty() {
        List<Task> taskList = new ArrayList<>();
        Set<Tag> tagList = new HashSet<>();
        Internship internship = new Internship(new Name(VALID_NAME_AAPL), new Position(VALID_POSITION_AAPL),
                new Status(VALID_STATUS_AAPL), new Email(VALID_EMAIL_AAPL),
                new Website(VALID_WEBSITE_AAPL), taskList, new Salary(VALID_SALARY_AAPL),
                tagList, new Remark(VALID_REMARK_AAPL));
        assertFalse(internship.isHasUpcomingTasks());
    }

    @Test
    public void isHasUpcomingTasks_notEmpty() {
        LocalDateTime taskTime = LocalDateTime.now().plusDays(7);
        String newTaskTime = taskTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("Technical Interview", newTaskTime));
        Set<Tag> tagList = new HashSet<>();
        Internship internship = new Internship(new Name(VALID_NAME_AAPL), new Position(VALID_POSITION_AAPL),
                new Status(VALID_STATUS_AAPL), new Email(VALID_EMAIL_AAPL),
                new Website(VALID_WEBSITE_AAPL), taskList, new Salary(VALID_SALARY_AAPL),
                tagList, new Remark(VALID_REMARK_AAPL));
        assertTrue(internship.isHasUpcomingTasks());
    }
}
