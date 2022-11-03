package seedu.phu.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.phu.testutil.TypicalInternships.AMAZON;
import static seedu.phu.testutil.TypicalInternships.BYTEDANCE;
import static seedu.phu.testutil.TypicalInternships.CITADEL;

import org.junit.jupiter.api.Test;

import javafx.scene.control.Label;
import seedu.phu.model.InternshipBook;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.ApplicationProcess;

public class ReminderBarFooterTest extends GuiUnitTest {
    private static final String CHILD_ID = "#reminderStatus"; // id to handle fxml query
    private ReminderBarFooter reminderBar;
    private Label reminderStatus;

    @Test
    public void constructor() {
        ReadOnlyInternshipBook book = new InternshipBook();
        reminderBar = new ReminderBarFooter(book);
        reminderStatus = getChildNode(reminderBar.getRoot(), CHILD_ID);
        assert reminderStatus != null; // make sure that reminderStatus is not null
        uiPartExtension.setUiPart(reminderBar);
    }

    @Test
    public void getStatusCount_emptyBook() {
        ReadOnlyInternshipBook book = new InternshipBook();
        ReadOnlyInternshipBook emptyBook = new InternshipBook();
        reminderBar = new ReminderBarFooter(book);
        assertEquals(0, reminderBar.getStatusCount(ApplicationProcess.ApplicationProcessState.ASSESSMENT, emptyBook));
        assertEquals(0, reminderBar.getStatusCount(ApplicationProcess.ApplicationProcessState.INTERVIEW, emptyBook));
        assertEquals(0, reminderBar.getStatusCount(ApplicationProcess.ApplicationProcessState.OFFER, emptyBook));
    }

    @Test
    public void getStatusCount_filledBook_criteriaNotMet() {
        ReadOnlyInternshipBook book = new InternshipBook();
        InternshipBook filledBook = new InternshipBook();
        filledBook.addInternship(AMAZON); //ApplicationProcess: APPLIED
        filledBook.addInternship(BYTEDANCE); //Date:24-09-2022. Assessment in the past
        filledBook.addInternship(CITADEL); //ApplicationProcess: APPLIED
        reminderBar = new ReminderBarFooter(book);
        assertEquals(0, reminderBar.getStatusCount(ApplicationProcess.ApplicationProcessState.ASSESSMENT, filledBook));
        assertEquals(0, reminderBar.getStatusCount(ApplicationProcess.ApplicationProcessState.INTERVIEW, filledBook));
        assertEquals(0, reminderBar.getStatusCount(ApplicationProcess.ApplicationProcessState.OFFER, filledBook));
    }
}
