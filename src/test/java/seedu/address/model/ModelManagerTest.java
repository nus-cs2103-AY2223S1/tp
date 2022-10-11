package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_DATE_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_MEETING1;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalMeetings.MEETING1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.MyInsuRecBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new MyInsuRec(), new MyInsuRec(modelManager.getMyInsuRec()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMyInsuRecFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setMyInsuRecFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setMyInsuRecFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setMyInsuRecFilePath(null));
    }

    @Test
    public void setMyInsuRecFilePath_validPath_setsMyInsuRecFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setMyInsuRecFilePath(path);
        assertEquals(path, modelManager.getMyInsuRecFilePath());
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInMyInsuRec_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInMyInsuRec_returnsTrue() {
        modelManager.addClient(ALICE);
        assertTrue(modelManager.hasClient(ALICE));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void getFilteredMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredMeetingList().remove(0));
    }

    @Test
    public void getDetailedMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getDetailedMeetingList().remove(0));
    }

    @Test
    public void updateFilteredMeetingList_setEmpty_success() {
        MyInsuRec myInsuRec = new MyInsuRecBuilder().withClient(ALICE).withClient(BENSON).build();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(myInsuRec, userPrefs);
        Meeting meeting1 = new MeetingBuilder(MEETING1)
                .withDescription(VALID_DESCRIPTION_MEETING1)
                .withMeetingDate(VALID_MEETING_DATE_MEETING1)
                .withMeetingTime(VALID_MEETING_TIME_MEETING1)
                .withClient(ALICE)
                .build();
        modelManager.addMeeting(meeting1);
        assertEquals(modelManager.getFilteredMeetingList().size(), 1);
        modelManager.updateFilteredMeetingList(unused -> false);
        assertEquals(modelManager.getFilteredMeetingList().size(), 0);
    }

    @Test
    public void equals() {
        MyInsuRec myInsuRec = new MyInsuRecBuilder().withClient(ALICE).withClient(BENSON).build();
        MyInsuRec differentMyInsuRec = new MyInsuRec();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(myInsuRec, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(myInsuRec, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different myInsuRec -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentMyInsuRec, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(myInsuRec, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setMyInsuRecFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(myInsuRec, differentUserPrefs)));
    }
}
