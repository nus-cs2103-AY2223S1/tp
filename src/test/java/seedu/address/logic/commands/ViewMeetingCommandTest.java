package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalMyInsuRec;

import org.junit.jupiter.api.Assertions;
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

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMyInsuRec(), new UserPrefs());
        expectedModel = new ModelManager(model.getMyInsuRec(), new UserPrefs());
    }

    @Test
    public void execute_modelIsNull_throwsNullPointerException() {
        ViewMeetingCommand vmc = new ViewMeetingCommand(Index.fromOneBased(1));
        Assertions.assertThrows(NullPointerException.class, () -> vmc.execute(null));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        // Currently, there are no filters for listMeeting.
        assertCommandSuccess(new ListMeetingCommand(), model, ListMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_indexTooBig_throwsCommandException() {
        ViewMeetingCommand vmc = new ViewMeetingCommand(Index.fromOneBased(1));
        Assertions.assertThrows(CommandException.class, () -> vmc.execute(model));
    }
}
