package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_ADDRESS_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_ADDRESS_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_REMARK_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_STATUS_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalInternships.ALICE;
import static seedu.intrack.testutil.TypicalInternships.MSFT;

import org.junit.jupiter.api.Test;

import seedu.intrack.model.tag.Tag;
import seedu.intrack.testutil.InternshipBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getTags().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(ALICE.isSameInternship(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameInternship(null));

        // same name, all other attributes different -> returns true
        Internship editedAlice = new InternshipBuilder(ALICE).withPhone(VALID_PHONE_MSFT).withEmail(VALID_EMAIL_MSFT)
                .withStatus(VALID_STATUS_MSFT).withAddress(VALID_ADDRESS_MSFT).withTags(VALID_TAG_URGENT).build();
        assertTrue(ALICE.isSameInternship(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE).withName(VALID_NAME_MSFT).build();
        assertFalse(ALICE.isSameInternship(editedAlice));

        // different position, all other attributes same -> returns false
        editedAlice = new InternshipBuilder(ALICE).withPosition(VALID_POSITION_MSFT).build();
        assertFalse(ALICE.isSameInternship(editedAlice));

        // same name, same position, all other attributes different -> returns true
        editedAlice = new InternshipBuilder(ALICE).withPhone(VALID_PHONE_MSFT).withEmail(VALID_EMAIL_MSFT)
                .withStatus(VALID_STATUS_MSFT).withAddress(VALID_ADDRESS_MSFT).withTags(VALID_TAG_URGENT).build();
        assertTrue(ALICE.isSameInternship(editedAlice));

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
        Internship aliceCopy = new InternshipBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different internship -> returns false
        assertFalse(ALICE.equals(MSFT));

        // different name -> returns false
        Internship editedAlice = new InternshipBuilder(ALICE).withName(VALID_NAME_MSFT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different position -> returns false
        editedAlice = new InternshipBuilder(ALICE).withPosition(VALID_POSITION_MSFT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new InternshipBuilder(ALICE).withPhone(VALID_PHONE_MSFT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new InternshipBuilder(ALICE).withEmail(VALID_EMAIL_MSFT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new InternshipBuilder(ALICE).withAddress(VALID_ADDRESS_MSFT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new InternshipBuilder(ALICE).withTags(VALID_TAG_URGENT).build();
        assertFalse(ALICE.equals(editedAlice));
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
                new Status(VALID_STATUS_AAPL), new Phone(VALID_PHONE_AAPL), new Email(VALID_EMAIL_AAPL),
                new Address(VALID_ADDRESS_AAPL), taskList,
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
                new Status(VALID_STATUS_AAPL), new Phone(VALID_PHONE_AAPL), new Email(VALID_EMAIL_AAPL),
                new Address(VALID_ADDRESS_AAPL), taskList,
                tagList, new Remark(VALID_REMARK_AAPL));
        LocalDateTime nearestDate = internship.getNearestTaskDate();
        LocalDateTime expectedNearestDate = second.getTaskTime();
        assertTrue(nearestDate.equals(expectedNearestDate));
    }
}
