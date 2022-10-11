package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.getTypicalMyInsuRec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ViewMeetingCommandTest {
    private Model model;
    private Model expectedModel;

    private ViewMeetingCommand vmc = new ViewMeetingCommand(Index.fromOneBased(1));

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMyInsuRec(), new UserPrefs());
        expectedModel = new ModelManager(model.getMyInsuRec(), new UserPrefs());
    }

    @Test
    public void execute_modelIsNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> vmc.execute(null));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        // Currently, there are no filters for listMeeting.
        assertCommandSuccess(new ListMeetingCommand(), model, ListMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_indexTooBig_throwsCommandException() {
        int numberOfExistingMeetings = model.getFilteredMeetingList().size();
        // trying to access meeting index that does not exist:
        vmc = new ViewMeetingCommand(Index.fromOneBased(numberOfExistingMeetings + 1));
        assertThrows(CommandException.class, () -> vmc.execute(model));
    }

    @Test
    public void execute_allFieldsValid_returnsViewMeetingCommand() {
        assertDoesNotThrow(() -> vmc.execute(model));
    }

    @Test
    public void equals_twoEquivalentMeetings_returnsTrue() {
        // same meeting object
        assertTrue(vmc.equals(vmc));

        ViewMeetingCommand equivalentVmc = new ViewMeetingCommand(Index.fromOneBased(1));

        assertTrue(vmc.equals(equivalentVmc));
    }

    @Test
    public void equals_twoNonEquivalentMeetings_returnsFalse() {
        ViewMeetingCommand nonEquivalentMeeting = new ViewMeetingCommand(Index.fromOneBased(2));
        assertFalse(vmc.equals(nonEquivalentMeeting));
    }
}
